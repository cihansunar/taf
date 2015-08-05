package taf.core.reporting;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;;


public class TestLog 
{
	
	private Date timeStamp;
	private String singleLog;
	private LogType logType;
	
	@XmlAttribute (name = "timestamp")
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@XmlAttribute (name = "type")
	public LogType getLogType() {
		return logType;
	}
	public void setLogType(LogType logType) {
		this.logType = logType;
	}
	
	@XmlValue
	public String getSingleLog() {
		return singleLog;
	}
	public void setSingleLog(String singleLog) {
		this.singleLog = singleLog;
	}
	
}
