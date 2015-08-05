package taf.core.reporting;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestOutput 
{
	private TestOverview overview;
	
	 // XmLElementWrapper generates a wrapper element around XML representation
	@XmlElementWrapper(name = "testCases")
	  // XmlElement sets the name of the entities
	  
	@XmlElement(name = "testcase")
	private ArrayList<TestCaseOutput> testcases = new ArrayList<TestCaseOutput>();

	@XmlElement (name = "overview")
	public TestOverview getOverview() {
		return overview;
	}

	public void setOverview(TestOverview overview) {
		this.overview = overview;
	}
	
	public void addTestCaseOutput(TestCaseOutput testcase)
	{
		testcases.add(testcase);
	}
	

}
