package org.sbelei.rally;

import com.rallydev.rest.util.QueryFilter;

public class QueryHelper {
	
	/** states **/
	public static String OPEN = "Open";
	public static String IN_PROGRESS = "InProgress";
	public static String COMPLEATED = "Compleated";
	public static String SUBMITTED = "Submitted";
	public static String CLOSED = "Closed";
	public static String FIXED = "Fixed";
	
	public static QueryFilter filterByOwner(String owner){
		return new QueryFilter("Owner.Name", "=", owner);
	}
	
	public static QueryFilter excludeByStates(String state, String... states){
		QueryFilter filter = new QueryFilter("State", "!=", state);
		for(String theState : states) {
			filter = filter.or(new QueryFilter("State", "!=", theState));
		}
		return filter;
	}

	
	public static QueryFilter filterByStates(String state, String... states){
		QueryFilter filter = new QueryFilter("State", "=", state);
		for(String theState : states) {
			filter = filter.and(new QueryFilter("State", "=", theState));
		}
		return filter;
	}
 
}
