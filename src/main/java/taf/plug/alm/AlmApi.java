package taf.plug.alm;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

public class AlmApi 
{
	private RestConnector con;


	public AlmApi() 
	{
		con = RestConnector.getInstance().init(
				new HashMap<String, String>(),
				"http://" + Constants.HOST + ":" +
						Constants.PORT + "/qcbin",
						Constants.DOMAIN,
						Constants.PROJECT);
	}
	
	public boolean startSession()
	{

		String sessionStarter = con.buildUrl("rest/site-session");

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Accept", "application/xml");

		Response serverResponse;
		try 
		{
			serverResponse = con.httpPost(sessionStarter,
					null, requestHeaders);

			return con.getCookieString().contains("QCSession");

		}catch (Exception e) 
		{
			return false;
		}
	}
	
	public String getTestDefinition(String testId) throws Exception
	{
		 Map<String, String> requestHeaders = new HashMap<String, String>();
	        requestHeaders.put("Accept", "application/xml");

		String testURL = con.buildEntityCollectionUrl("test");
        testURL+="/" + testId;
        
        Response serverResponse = con.httpGet(testURL,
                null, requestHeaders);
        
        return serverResponse.toString();
	}
	
	public String getTestSteps(String testId) throws Exception
	{
		String query = "design-steps?query={parent-id["+testId+"]}";
		Map<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put("Accept", "application/xml");
        String stepsURL = con.buildQueryURL(query);
        Response serverResponse = con.httpGet(stepsURL, null, requestHeaders);
        
        return serverResponse.toString();
	}
	/**
	 * @param loginUrl
	 *            to authenticate at
	 * @param username
	 * @param password
	 * @return true on operation success, false otherwise
	 * @throws Exception
	 *
	 * Logging in to our system is standard http login (basic authentication),
	 * where one must store the returned cookies for further use.
	 */
	public boolean login()
			throws Exception {

		String loginUrl = isAuthenticated();
		if (loginUrl != null) 
		{
			//create a string that looks like:
			// "Basic ((username:password)<as bytes>)<64encoded>"
			byte[] credBytes = (Constants.USERNAME + ":" + Constants.PASSWORD).getBytes();
			String credEncodedString = "Basic " +  Base64Encoder.encode(credBytes);

			Map<String, String> map = new HashMap<String, String>();
			map.put("Authorization", credEncodedString);

			Response response = con.httpGet(loginUrl, null, map);

			boolean ret = response.getStatusCode() == HttpURLConnection.HTTP_OK;

			return ret;
		}
		return true;
	}

	/**
	 * @return true if logout successful
	 * @throws Exception
	 *             close session on server and clean session cookies on client
	 */
	public boolean logout() throws Exception {

		//note the get operation logs us out by setting authentication cookies to:
		// LWSSO_COOKIE_KEY="" via server response header Set-Cookie
		Response response =
				con.httpGet(con.buildUrl("authentication-point/logout"),
						null, null);

		return (response.getStatusCode() == HttpURLConnection.HTTP_OK);

	}

	/**
	 * @return null if authenticated.<br>
	 *         a url to authenticate against if not authenticated.
	 * @throws Exception
	 */
	public String isAuthenticated() throws Exception {

		String isAuthenticateUrl = con.buildUrl("rest/is-authenticated");
		String ret;

		Response response = con.httpGet(isAuthenticateUrl, null, null);
		int responseCode = response.getStatusCode();

		//if already authenticated
		if (responseCode == HttpURLConnection.HTTP_OK) {

			ret = null;
		}

		//if not authenticated - get the address where to authenticate
		// via WWW-Authenticate
		else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {

			Iterable<String> authenticationHeader =
					response.getResponseHeaders().get("WWW-Authenticate");

			String newUrl =
					authenticationHeader.iterator().next().split("=")[1];
			newUrl = newUrl.replace("\"", "");
			newUrl += "/authenticate";
			ret = newUrl;
		}

		//Not ok, not unauthorized. An error, such as 404, or 500
		else {

			throw response.getFailure();
		}

		return ret;
	}

}
