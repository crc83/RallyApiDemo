package org.sbelei.rally;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sbelei.rally.domain.Project;
import org.sbelei.rally.domain.Workspace;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class ResponceHelperTestWorkspace {
	
	List<Workspace> workspaces;

	@Before
	public void setUp() {
		JsonParser parser =new JsonParser();
		JsonArray array = (JsonArray) parser.parse(TestHelper.getResourceAsReader("/workspace-responce.txt"));
		workspaces = ResponceHelper.fetchWorkspaces(array);
	}
	
	
	@Test
	public void testFetchWorkspacesSize(){
		assertEquals(1,workspaces.size());	
	}

	@Test
	public void testFetchWorkspaceFields() {
		Workspace ws = workspaces.get(0);
		assertEquals("Meaningfull name of workspace", ws.name);
		assertEquals("41593629", ws.id);
		assertEquals("https://rally1.rallydev.com/slm/webservice/1.42/workspace/41593629.js", ws.ref);
	}
	
	@Test
	public void testFetchProjectsOfWorkspace() {
	    List<Project> projects = workspaces.get(0).projects;
	    assertEquals(3, projects.size());
	}
	
	@Test
	public void testFetchProjectFields() {
	    Project project = workspaces.get(0).projects.get(0);
        assertEquals("https://rally1.rallydev.com/slm/webservice/1.42/project/8035573910.js",project.ref);
        assertEquals("The project name one",project.name);
	}


}
