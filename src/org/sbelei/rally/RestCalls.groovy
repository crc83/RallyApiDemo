package org.sbelei.rally

import com.rallydev.rest.RallyRestApi
import com.rallydev.rest.request.QueryRequest
import com.rallydev.rest.response.QueryResponse
import com.rallydev.rest.util.Fetch
import com.rallydev.rest.util.QueryFilter

import static org.sbelei.rally.QueryHelper.*;
import org.sbelei.rally.domain.Project
import org.sbelei.rally.domain.State;
import org.sbelei.rally.domain.Type;

class RestCalls {
	
	public static void main(String[] args) {
		println("*** start*** ")
		RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);
			
		QueryRequest request = getAllProjectsIHaveAccessTo();
		request.setWorkspace("41593629");
		
		QueryResponse queryResponse = restApi.query(request);
		EntityProcessor.saveResponceToFile("C:/Documents and Settings/user/workspace/RallySandbox/test-resources/project-responce.json",queryResponse.getResults().toString());
		List<Project> projects= EntityProcessor.fetchProjects(queryResponse.getResults());
		println(projects);
		println("*** finish ***")
	}

	private static QueryRequest getAllWorkspacesIHaveAccessTo() {
		QueryRequest request = new QueryRequest("workspace")
		return request
	}

	private static QueryRequest getAllProjectsIHaveAccessTo() {
		QueryRequest request = new QueryRequest("project")
		return request
	}

	/*
	 * QueryRequest request = = sampleGetDefects(restApi);
	 */
	private static QueryRequest getDefects(RallyRestApi restApi) {
		QueryRequest request = new QueryRequest(Type.DEFECT);

		request.setFetch(new Fetch("FormattedID", "Name", "State", "Priority"));
		request.setQueryFilter(includeByStates(State.SUBMITTED, State.FIXED));
		request.setQueryFilter(includeByOwner(Credentials.USER));
		request.setOrder("Priority ASC,FormattedID ASC");

		request.setPageSize(25);
		request.setLimit(100);

		return request
	}

}
