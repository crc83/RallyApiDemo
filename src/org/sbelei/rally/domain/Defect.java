package org.sbelei.rally.domain;

import java.util.LinkedHashMap;

public class Defect extends BasicEntity{
	
	public String formattedId;
	public String severity;
	public String priority;
	public String state;
	public String taskStatus;
	
	@Override
	public String toString() {
		return "\nDefect [" + formattedId + "] \nseverity="
				+ severity + " [" + priorities.get(priority) + "] " + state
				+ "\ntaskStatus=" + taskStatus + "\nname=" + name + "\nid="
				+ id + "\nref=" + ref;
	}
	
	private static LinkedHashMap<String, String> priorities = new LinkedHashMap<String, String>() {
		{
			put("Resolve Immediately", "P1");
			put("High Attention", "P2");
			put("Normal", "P3");
			put("Low", "P4");
		}
	};

}
