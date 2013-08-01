package org.sbelei.rally.domain;

public class Defect extends BasicEntity{
	
	public String formattedId;
	public String severity;
	public String priority;
	public String state;
	public String taskStatus;
	
	@Override
	public String toString() {
		return "\nDefect\n\tformattedId=" + formattedId + "\nseverity="
				+ severity + "\npriority=" + priority + "\nstate=" + state
				+ "\ntaskStatus=" + taskStatus + "\nname=" + name + "\nid="
				+ id + "\nref=" + ref;
	}

}
