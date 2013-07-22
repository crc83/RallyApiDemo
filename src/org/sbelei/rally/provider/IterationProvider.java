package org.sbelei.rally.provider;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.sbelei.rally.domain.*;
import org.sbelei.rally.jsonprocessor.*;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.util.QueryFilter;

import static org.sbelei.rally.QueryHelper.*;

public class IterationProvider {

    Logger log = Logger.getLogger(IterationProvider.class.getCanonicalName());
    BasicEntityProcessor processor;
    BasicRequestBuilder requestBuilder;

    public IterationProvider(RallyRestApi restApi, String workspaceId, String projectId){
        processor = new BasicEntityProcessor(restApi);
        requestBuilder = new BasicRequestBuilder();
        requestBuilder.workspaceId = workspaceId;
        requestBuilder.projectId = projectId;
    }
	
	private QueryFilter isDateInIteration(Date date) {
		QueryFilter startDateFilter = new QueryFilter("StartDate","<=",queryDate(date));
		QueryFilter endDateFilter = new QueryFilter("EndDate",">",queryDate(date));
		return  startDateFilter.and(endDateFilter);
	}
	
	public List<BasicEntity> getIterations(){
		return fetch(null);
	}

	private List<BasicEntity> fetch(QueryFilter additionalFilter) {
	    //create request for workspace and project
        QueryRequest request = requestBuilder.newRequest(Type.ITERATION, additionalFilter);
		//process response and return result
        return processor.getEntitiesByRequest(request);
	}

    public BasicEntity getCurrentIteration(){
	    List<BasicEntity> result = fetch(isDateInIteration(new Date()));
	    if ((result == null) || (result.size()<1)){
	        return null;
	    } else {
	        return result.get(0);
	    }
	}

}
