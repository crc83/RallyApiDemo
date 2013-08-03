package org.sbelei.rally.domain;

public class Story extends BasicEntity{
	
	public String formattedId;
	public String scheduleState;
	
	@Override
	public String toString() {
		return "\nStory\n\tformattedId=" + formattedId + "\nscheduleState="
				+ scheduleState + "\nname=" + name + "\nref=" + ref;
	}

}
