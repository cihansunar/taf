package taf.plug.testng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import taf.core.reporting.TestCaseOutput;
import taf.core.reporting.TestException;
import taf.core.reporting.TestReporter;
import taf.core.reporting.TestResult;

public class TestListener  extends TestListenerAdapter 
{
	private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) 
    {
    	
    	putTestcaseOutput(tr, TestResult.FAIL);
        log(tr.getName()+ "--Test method failed\n");
        TestManager.getInstance().HandleTestFinishedNotification(tr);
        TestReporter.logScreenshot("failed");
    }
	 
    @Override
    public void onTestSkipped(ITestResult tr) 
    {
    	putTestcaseOutput(tr, TestResult.SKIP);
        log(tr.getName()+ "--Test method skipped\n");
        TestManager.getInstance().HandleTestFinishedNotification(tr);
    }
	 
    @Override
    public void onTestSuccess(ITestResult tr) 
    {
    	putTestcaseOutput(tr, TestResult.PASS);
        log(tr.getName()+ "--Test method success\n");
        TestManager.getInstance().HandleTestFinishedNotification(tr);
    }
	 
    @Override
    public void onTestStart(ITestResult tr) 
    {
        log(tr.getName()+ "--Test method started\n");
        TestManager.getInstance().HandleTestRunningNotification();
    }
    @Override
    public void onStart(ITestContext testContext) 
    {
        log(testContext.getName()+ "--Test method started\n");
        //TestManager.getInstance().SetupTestContext(testContext.getName());
        
    }
    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
	    System.out.println("");
        }
    }
    
    private void putTestcaseOutput(ITestResult tr, TestResult result)
    {	
    	TestCaseOutput testcaseOutput = new TestCaseOutput();
    	if(result == TestResult.FAIL)
    	{
    		TestException te = new TestException();
    		te.setMessage(tr.getThrowable().getMessage());
    		te.setExceptionClass(tr.getThrowable().getClass().toString());
    		testcaseOutput.setException(te);
    	}
    	testcaseOutput.setDuration(tr.getEndMillis()-tr.getStartMillis());
    	testcaseOutput.setId(tr.getName());
    	testcaseOutput.setModuleName(tr.getMethod().getDescription());
    	testcaseOutput.setResult(result);
    	TestManager.getInstance().addTestCaseLog(testcaseOutput);
    }


}
