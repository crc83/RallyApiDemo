package org.sbelei.rally;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rallydev.rest.util.QueryFilter;

public class QueryHelper {
	
	private static SimpleDateFormat FORMAT = new SimpleDateFormat("YYYY-MM-dd'T'00:00:00.000'Z'");
	
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
	
	public static QueryFilter byProjectId(String projectId){
		return new QueryFilter("Project.ObjectID", "=", projectId);
	}
	
	public static String queryDate(Date date){		
		return FORMAT.format(date);		
	}

}
