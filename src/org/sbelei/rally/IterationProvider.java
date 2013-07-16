package org.sbelei.rally;

import java.util.Date;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.QueryFilter;

import static org.sbelei.rally.QueryHelper.*;

public class IterationProvider {
	
	String workspaceId = "41593629";
	RallyRestApi resApi;
	
	public void fetchData(){
		QueryRequest request = new QueryRequest("iteration");
		request.setWorkspace(workspaceId);
		QueryFilter projectFilter = byProjectId("9216950819");
		QueryFilter dateFilter = isDateInIteration(new Date());
		request.setQueryFilter(projectFilter.and(dateFilter));

//			.and(new QueryFilter("State", "<", "Fixed"))		
//			.and(new QueryFilter("Iteration.ObjectID", "=", "11597902889")
//			
//			))
		
		QueryResponse queryResponse = resApi.query(request);
//		EntityProcessor.saveResponceToFile("${home}/iteration-responce.json",queryResponse.getResults().toString());
		List<Project> projects= EntityProcessor.fetchProjects(queryResponse.getResults());
	}

	private QueryFilter isDateInIteration(Date date) {
		QueryFilter startDateFilter = new QueryFilter("StartDate","<=",queryDate(date));
		QueryFilter endDateFilter = new QueryFilter("EndDate",">",queryDate(date));
		return  startDateFilter.and(endDateFilter);
	}
	
	public List<BasicEntity> getIterations(){
		
	}
	
	public BasicEntity getCurrentIteration(){
		
	}

}
