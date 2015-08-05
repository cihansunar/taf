package taf.plug.testng;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

import taf.core.reporting.TestOverview;

public class SuiteListener implements ISuiteListener {

	public void onFinish(ISuite suite) 
	{
		Map<String,ISuiteResult> suiteResults = suite.getResults();
		String suiteName = suite.getName();
		TestOverview testOverview = new TestOverview();
		int passed = 0;
		int failed = 0;
		int skipped = 0;
		long duration =0;
		boolean firstVisited = false;
		for (ISuiteResult sr : suiteResults.values()) 
		{

			ITestContext tc = sr.getTestContext();
			if(!firstVisited){
				testOverview.setStartedAt(tc.getStartDate());
				firstVisited= true;
			}
			testOverview.setFinishedAt(tc.getEndDate());

			duration += tc.getEndDate().getTime()- tc.getStartDate().getTime();
			passed += tc.getPassedTests().getAllResults().size();
			failed += tc.getFailedTests().getAllResults().size();
			skipped += tc.getSkippedTests().getAllResults().size();
			System.out.println(tc.getName());
		}
		testOverview.setDuration( duration);
		testOverview.setFailed(failed);
		testOverview.setPassed(passed);
		testOverview.setSkipped(skipped);


		testOverview.setTotal(failed+skipped+passed);
		testOverview.setTestname(suiteName);

		TestManager.getInstance().setTestOverview(testOverview);
		try {
			TestManager.getInstance().dumpLogFile();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onStart(ISuite suite) {
		TestManager.getInstance().SetupTestContext(suite.getName());
		// TODO Auto-generated method stub

	}

}
