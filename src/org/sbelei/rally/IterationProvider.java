package org.sbelei.rally;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.jsonprocessor.BasicEntityProcessor;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.QueryFilter;

import static org.sbelei.rally.QueryHelper.*;

public class IterationProvider {
	
	String workspaceId = "41593629";
	String projectId = "9216950819";
	RallyRestApi resApi;
	
	private QueryFilter isDateInIteration(Date date) {
		QueryFilter startDateFilter = new QueryFilter("StartDate","<=",queryDate(date));
		QueryFilter endDateFilter = new QueryFilter("EndDate",">",queryDate(date));
		return  startDateFilter.and(endDateFilter);
	}
	
	public List<BasicEntity> getIterations(){
		return fetch(null);
	}

	private List<BasicEntity> fetch(QueryFilter additionalFilter) {
		QueryRequest request = new QueryRequest("iteration");
		request.setWorkspace(workspaceId);
		QueryFilter projectFilter = byProjectId(projectId);

		if (additionalFilter == null){
			request.setQueryFilter(projectFilter);
		} else {
			request.setQueryFilter(projectFilter.and(additionalFilter));
		}
		
		QueryResponse responce;
		List<BasicEntity> result;
		try {
			responce = resApi.query(request);
			result = new BasicEntityProcessor().fetchBasicEntities(responce.getResults());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new ArrayList<>();
		}
		return result;
	}
	
	public BasicEntity getCurrentIteration(){
		//TODO SB : Fix it
		return fetch(isDateInIteration(new Date())).get(0);
	}

}
