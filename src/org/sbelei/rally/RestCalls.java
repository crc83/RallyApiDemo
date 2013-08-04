package org.sbelei.rally;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.rallydev.rest.RallyRestApi;

import org.sbelei.rally.domain.*;
import org.sbelei.rally.domain.constants.StoryState;
import org.sbelei.rally.provider.*;

class RestCalls {
	
	public static String home = "C:/Documents and Settings/user/workspace/RallySandbox/test-resources";
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		System.out.println("*** start*** ");
		RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);

		WorkspaceProvider wprovider = new WorkspaceProvider(restApi);
		List<Workspace> workspaces = wprovider.fetch();
		System.out.println("* Workspaces *");
		System.out.println(workspaces);
		String workspaceId = workspaces.get(0).id;
		System.out.println("* Projects *");
		ProjectProvider pprovider = new ProjectProvider(restApi, workspaceId);
		List<Project> projects = pprovider.fetch();
		System.out.println(projects);
		String projectId = "9216950819";//projects.get(0).id;
		System.out.println("* Iterations *");
		IterationProvider iprovider = new IterationProvider(restApi,workspaceId,projectId);
		List<BasicEntity> iterations = iprovider.fetch();
		BasicEntity currentIteration = iprovider.fetchCurrentIteration();
		System.out.println(iterations);
		System.out.println("Current : "+ currentIteration);
		String iterationId = currentIteration.id;
		System.out.println("* Defects and stories *");
		DefectsProvider dprovider = new DefectsProvider(restApi, workspaceId, projectId, iterationId);
		List<Defect> defects = dprovider.onlyMine().fetch();
		StoryProvider sprovider = new StoryProvider(restApi, workspaceId, projectId, iterationId);
		List<Story> stories = sprovider.onlyMine().fetch();
		List<BasicEntity> tasks = new ArrayList<BasicEntity>();
		tasks.addAll(defects);
		tasks.addAll(stories);
		System.out.println(tasks);
		System.out.println("*** finish ***");
	}

}
