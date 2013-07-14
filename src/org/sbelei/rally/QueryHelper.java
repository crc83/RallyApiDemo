package org.sbelei.rally;

import com.rallydev.rest.util.QueryFilter;

public class QueryHelper {
	
	public static QueryFilter includeByOwner(String owner){
		return new QueryFilter("Owner.Name", "=", owner);
	}
	
	public static QueryFilter includeByStates(String state, String... states){
		QueryFilter filter = new QueryFilter("State", "=", state);
		for(String theState : states) {
			filter = filter.or(new QueryFilter("State", "=", theState));
		}
		return filter;
	}

}
