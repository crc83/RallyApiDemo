package org.sbelei.rally.provider;

import java.util.Date;
import java.util.List;

import org.sbelei.rally.domain.*;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.util.QueryFilter;

import static org.sbelei.rally.helpers.FilterHelper.*;

public class IterationProvider extends BaseProvider{


	IterationProvider(RallyRestApi restApi, String workspaceId, String projectId) {
		super(restApi, workspaceId, projectId);
	}

	private QueryFilter isDateInIteration(Date date) {
		QueryFilter startDateFilter = new QueryFilter("StartDate","<=",queryDate(date));
		QueryFilter endDateFilter = new QueryFilter("EndDate",">",queryDate(date));
		return  startDateFilter.and(endDateFilter);
	}
	
	public List<BasicEntity> getIterations(){
		return fetch(null);
	}

    public BasicEntity getCurrentIteration(){
	    List<BasicEntity> result = fetch(isDateInIteration(new Date()));
	    if ((result == null) || (result.size()<1)){
	        return null;
	    } else {
	        return result.get(0);
	    }
	}

	@Override
	String getType() {
		return Type.ITERATION;
	}

}
