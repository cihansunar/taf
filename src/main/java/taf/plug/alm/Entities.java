package taf.plug.alm;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import taf.core.reporting.TestLog;

@XmlRootElement(name = "Entities")
public class Entities 
{
		// XmLElementWrapper generates a wrapper element around XML representation
		//@XmlElementWrapper(name = "testLogs")
		// XmlElement sets the name of the entities
		
		private ArrayList<Entity> entities = new ArrayList<Entity>();

		@XmlElement(name ="Entity")
		public ArrayList<Entity> getEntities() {
			return entities;
		}

		public void setEntities(ArrayList<Entity> entities) {
			this.entities = entities;
		}

		

}
