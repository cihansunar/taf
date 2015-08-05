package taf.plug.testng;

import java.io.File;

import org.apache.commons.*;
import org.apache.commons.io.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.testng.ITestResult;

import taf.core.reporting.TestCaseOutput;
import taf.core.reporting.TestLog;
import taf.core.reporting.TestOutput;
import taf.core.reporting.TestOverview;

public class TestManager 
{
	private  String CurrentContextDir = "";
	private static TestManager instance;
	private static int runningTestCount = 0;
	private TestOutput  testOutput;
	private ArrayList<TestLog> logStack;
	//private static 
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
	private TestManager ()
	{
		testOutput = new TestOutput();
		logStack= new ArrayList<TestLog>();
	}
	public static TestManager getInstance() 
	{
		if(instance == null) {
			instance = new TestManager();
		}
		return instance;
	}

	public void HandleTestRunningNotification()
	{
		synchronized (instance)
		{
			runningTestCount++;
			System.out.println(runningTestCount);
		}	
	}

	public void HandleTestFinishedNotification(ITestResult tr)
	{
		synchronized (instance)
		{
			runningTestCount--;
			System.out.println(runningTestCount);
		}	
	}

	public void SetupTestContext(String contextName)
	{
		System.out.println("SetupTestContext");
		Date date = new Date();
		String contextDir = contextName + dateFormat.format(date);
		//CurrentContextDir = contextDir;
		File theDir = new File(contextDir);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + contextDir);
			boolean result = false;

			try{
				theDir.mkdir();
				CurrentContextDir = theDir.getPath();
				System.out.println(CurrentContextDir);
				result = true;
			} catch(SecurityException ex){
				//handle it
			}        
			if(result) {    
				System.out.println("DIR created");  
			}
		}

	}

	public String getCurrentContextDir(){

		return CurrentContextDir;
	}

	public void dumpLogFile() throws JAXBException, IOException
	{

		JAXBContext contextObj = JAXBContext.newInstance(TestOutput.class);  

		Marshaller marshallerObj = contextObj.createMarshaller();  
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 

		marshallerObj.marshal(testOutput, new FileOutputStream(getCurrentContextDir() + "\\CustomLog.xml")); 
		File source = new File(getCurrentContextDir());
		File destination  = new File("C:\\D\\DEV\\TestAutomationReporting\\TestNGenReporter\\TestNGenReporterDemo\\TestNGenReporterDemo\\reports\\test\\");
		FileUtils.copyDirectory(source, destination);
		//Path sourcePath = FileSystems.getDefault().getPath(getCurrentContextDir()+"\\");
		//Path targetPath = FileSystems.getDefault().getPath( "C:\\D\\DEV\\TestAutomationReporting\\TestNGenReporter\\TestNGenReporterDemo\\TestNGenReporterDemo\\reports\\test\\");
		
		//Files.copy(sourcePath,targetPath, REPLACE_EXISTING );

	}

	public void addTestCaseLog(TestCaseOutput testCaseOutput)
	{
		synchronized (logStack)
		{
			for (TestLog log : logStack)
				testCaseOutput.addTestLog(log);
			this.testOutput.addTestCaseOutput(testCaseOutput);
			logStack.clear();
		}
	}
	
	public void addLog(TestLog log)
	{
		synchronized (logStack)
		{
			logStack.add(log);
		}
	}
	public void setTestOverview(TestOverview testOverview) 
	{
		testOutput.setOverview(testOverview);
		// TODO Auto-generated method stub
		
	}




}
