package org.sbelei.rally;

import java.util.Date;

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
	
	public static String date(Date date){
	    //new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	    return "";
	}

}
