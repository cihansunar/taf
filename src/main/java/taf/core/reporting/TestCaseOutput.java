package taf.core.reporting;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class TestCaseOutput 
{
	private long duration;
	private TestResult result;
	private String id;
	private String moduleName;
	private TestException exception;
	private String testText;
	//private 

	@XmlAttribute (name = "duration-ms")
	public long getDuration() {
		return duration;
	}

	public void setDuration(long testDuration) {
		this.duration = testDuration;
	}

	@XmlAttribute
	public TestResult getResult() {
		return result;
	}

	public void setResult(TestResult result) {
		this.result = result;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@XmlElement
	public TestException getException() {
		return exception;
	}

	public void setException(TestException exception) {
		this.exception = exception;
	}

	@XmlElement
	public String getTestText() {
		return testText;
	}

	public void setTestText(String testText) {
		this.testText = testText;
	}

	// XmLElementWrapper generates a wrapper element around XML representation
	@XmlElementWrapper(name = "testLogs")
	// XmlElement sets the name of the entities
	@XmlElement(name = "log")
	private ArrayList<TestLog> logs = new ArrayList<TestLog>();

	public void addTestLog(TestLog log)
	{
		logs.add(log);
	}


}
