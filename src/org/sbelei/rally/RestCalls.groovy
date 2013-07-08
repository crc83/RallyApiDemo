package org.sbelei.rally

import com.rallydev.rest.RallyRestApi
import com.rallydev.rest.request.QueryRequest
import com.rallydev.rest.response.QueryResponse
import com.rallydev.rest.util.Fetch
import com.rallydev.rest.util.QueryFilter

import static org.sbelei.rally.QueryHelper.*;

class RestCalls {
	
	public static void main(String[] args) {
		println("Hello")
		RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);
			
		QueryRequest defectRequest = new QueryRequest("defect");
	
		defectRequest.setFetch(new Fetch("FormattedID", "Name", "State", "Priority"));
		defectRequest.setFetch(new Fetch("State"));
		defectRequest.setQueryFilter(excludeByStates(SUBMITTED,CLOSED,FIXED));
//		defectRequest.setQueryFilter(filterByOwner(Credentials.USER));
		defectRequest.setOrder("Priority ASC,FormattedID ASC");
	
		defectRequest.setPageSize(25);
		defectRequest.setLimit(100);
	
		QueryResponse queryResponse = restApi.query(defectRequest);

		println(queryResponse.getResults());
	}

}
