package org.sbelei.rally;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sbelei.rally.domain.Project;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class EntityProcessorProjectsTest {
	
	List<Project> projects;

	@Before
	public void setUp() {
		JsonParser parser =new JsonParser();
		JsonArray array = (JsonArray) parser.parse(TestHelper.getResourceAsReader("/project-responce.json"));
		projects = EntityProcessor.fetchProjects(array);
	}
	
	
	@Test
	public void testFetchProjectsSize(){
		assertEquals(2,projects.size());	
	}

	@Test
	public void testFetchProjectsFields() {
		Project prj = projects.get(0);
		assertEquals("AB", prj.name);
		assertEquals("9216950819", prj.id);
		assertEquals("https://rally1.rallydev.com/slm/webservice/1.42/project/9216950819.js",
				prj.ref);
	}

}
