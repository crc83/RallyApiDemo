package org.sbelei.rally

import com.rallydev.rest.RallyRestApi
import com.rallydev.rest.request.QueryRequest
import com.rallydev.rest.response.QueryResponse
import com.rallydev.rest.util.Fetch
import com.rallydev.rest.util.QueryFilter

import static org.sbelei.rally.QueryHelper.*;
import org.sbelei.rally.domain.State;
import org.sbelei.rally.domain.Type;

class RestCalls {
	
	public static void main(String[] args) {
		println("Hello")
		RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);
			
		QueryRequest request = new QueryRequest("workspace");
		
		QueryResponse queryResponse = restApi.query(request);
		
		println(queryResponse.getResults());
	}

	/*
	 * QueryResponse queryResponse = sampleGetDefects(restApi);
	 */
	private static QueryResponse sampleGetDefects(RallyRestApi restApi) {
		QueryRequest request = new QueryRequest(Type.DEFECT);

		request.setFetch(new Fetch("FormattedID", "Name", "State", "Priority"));
		request.setQueryFilter(includeByStates(State.SUBMITTED, State.FIXED));
		request.setQueryFilter(includeByOwner(Credentials.USER));
		request.setOrder("Priority ASC,FormattedID ASC");

		request.setPageSize(25);
		request.setLimit(100);

		QueryResponse queryResponse = restApi.query(request)
		return queryResponse
	}

}
