package taf.core.reporting;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;


public class TestOverview 
{
	private long duration;
	private Date startedAt;
	private Date finishedAt;
	private int skipped;
	private int passed;
	private int failed;
	private int total;
	private String testname;
	
	@XmlAttribute (name = "duration-ms")
	public long getDuration() {
		return duration;
	}
	public void setDuration(long l) {
		this.duration = l;
	}
	@XmlAttribute (name = "started-at")
	public Date getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}
	@XmlAttribute (name = "finished-at")
	public Date getFinishedAt() {
		return finishedAt;
	}
	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}
	@XmlAttribute
	public int getSkipped() {
		return skipped;
	}
	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}
	@XmlAttribute
	public int getPassed() {
		return passed;
	}
	public void setPassed(int passed) {
		this.passed = passed;
	}
	@XmlAttribute
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
	}
	@XmlAttribute
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	@XmlAttribute
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}

}
