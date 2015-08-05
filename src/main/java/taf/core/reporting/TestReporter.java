package taf.core.reporting;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.xml.XmlSuite;

import taf.plug.testng.TestManager;

public class TestReporter implements IReporter
{
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmssSSS");
	static DateFormat dateFormatForLogging = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
	public static void log(String s)
	{
		customLog(s, LogType.INFO);
	}

	
	public static void log(LogType logtype, String message)
	{
		customLog(message, logtype);
	}
	public static void logScreenshot(WebDriver driver, String message)
	{
		logScreenshot(message);
		/*try 
		{
			
			
			Date date = new Date();
			//System.out.println();

			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			System.out.println("Context:" + TestManager.getInstance().getCurrentContextDir());
			String imagePath = dateFormat.format(date) + ".png";
			FileUtils.copyFile(scrFile, new File(TestManager.getInstance().getCurrentContextDir() +"\\"+  imagePath));
			
			customLog(imagePath, LogType.SCREENSHOT);
			
			
			//log(HtmlScreenshotMessageFormatter(message, TestManager.getInstance().getCurrentContextDir(), imagePath));
		} catch (IOException e) {
			System.out.println("ERROR");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public static void logScreenshot(String message)
	{
		try 
		{
			System.out.println("logScreenshot");
			Date date = new Date();
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = new Robot().createScreenCapture(screenRect);

			String imagePath =   dateFormat.format(date) + ".png";
			ImageIO.write(capture, "png", new File(TestManager.getInstance().getCurrentContextDir() +"\\"+imagePath));
			customLog(imagePath, LogType.SCREENSHOT);
			//log(HtmlScreenshotMessageFormatter(message, TestManager.getInstance().getCurrentContextDir(), imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("error: " + e.getMessage());	
		}
	}

	public void generateReport(List<XmlSuite> xmlsuites, List<ISuite> suites,
			String arg2) {
		// TODO Auto-generated method stub
		for (ISuite suite : suites) {
			//Following code gets the suite name
			String suiteName = suite.getName();
			//Getting the results for the said suite
			Map<String,ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult sr : suiteResults.values()) {
				ITestContext tc = sr.getTestContext();
				//tc.getPassedTests().getAllMethods().iterator().next().
				System.out.println("Passed tests for suite '" + suiteName +
						"' is:" + tc.getPassedTests().getAllResults().size());
				System.out.println("Failed tests for suite '" + suiteName +
						"' is:" + 
						tc.getFailedTests().getAllResults().size());
				System.out.println("Skipped tests for suite '" + suiteName +
						"' is:" + 
						tc.getSkippedTests().getAllResults().size());
			}

		}
	}

	private static void customLog (String message, LogType logtype){
		
		TestLog log = new TestLog();
		log.setSingleLog(message);
		log.setTimeStamp(new Date());
		log.setLogType(logtype);
		TestManager.getInstance().addLog(log);
		
	}
}
