package org.sbelei.rally;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.rallydev.rest.RallyRestApi;

import org.sbelei.rally.domain.*;
import org.sbelei.rally.provider.*;

class RestCalls {
	
	public static String home = "C:/Documents and Settings/user/workspace/RallySandbox/test-resources";
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		System.out.println("*** start*** ");
		RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);

		ProviderFasade provider = new ProviderFasade(restApi);
		List<Workspace> workspaces = provider.fetchWorkspaces();
		System.out.println("* Workspaces *");
		System.out.println(workspaces);
		provider.setWorkspaceId( workspaces.get(0).id );
		System.out.println("* Projects *");
		List<Project> projects = provider.fetchProjects();
		System.out.println(projects);
		provider.setProjectId("9216950819");
		String projectId = "9216950819";//projects.get(0).id;
		System.out.println("* Iterations *");
		System.out.println(provider.fetchIterations());
		System.out.println("Current : "+ provider.fetchCurrentIteration());
		provider.setUserLogin(Credentials.USER);
		provider.setUseCurrentIteration(true);
		provider.setOnlyMine(true);
		provider.showAll(false);
		
		System.out.println("* Defects and stories *");

		System.out.println(provider.fetchStoriesAndDefects());
		System.out.println("*** finish ***");
	}

}
