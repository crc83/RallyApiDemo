package org.sbelei.rally;

import java.util.ArrayList;
import java.util.List;

import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.domain.Project;
import org.sbelei.rally.domain.Workspace;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import static org.sbelei.rally.JsonElementWrapper.wrap;

public class DeprecatedEntityProcessor {
	
	public static List<Workspace> fetchWorkspaces(JsonArray responce) {
		List<Workspace> workspaces = new ArrayList<Workspace>();
		for (JsonElement workspaceJson : responce){
			Workspace ws = new Workspace();
			//populate workspace fields
			JsonElementWrapper json = wrap(workspaceJson);
			fillBasicInfo(json,ws);
			ws.id = json.string("ObjectID");
		    ws.description = json.string("Description"); 
		    ws.notes = json.string("Notes");

		    workspaces.add(ws);
		}
		return workspaces;		
	}

	private static void fillBasicInfo(JsonElementWrapper json, BasicEntity entity) {
		entity.name = json.string("_refObjectName");
		entity.ref = json.string("_ref");
	}

	public static List<Project> fetchProjects(JsonArray responce) {
		List<Project> projects = new ArrayList<Project>();
		for (JsonElement workspaceJson : responce){
			Project prj = new Project();
			//populate workspace fields
			JsonElementWrapper json = wrap(workspaceJson);
			fillBasicInfo(json,prj);
			prj.id = json.string("ObjectID");
//		    prj.description = json.string("Description"); 
//		    prj.notes = json.string("Notes");

		    projects.add(prj);
		}
		return projects;		
	}
	
	public static List<BasicEntity> fetchBasicEntities(JsonArray responce) {
		List<BasicEntity> entities = new ArrayList<BasicEntity>();
		for (JsonElement rawJson : responce){
			BasicEntity entity = new BasicEntity();
			JsonElementWrapper json = wrap(rawJson);
			fillBasicInfo(json,entity);
            entity.id = json.string("ObjectID");
            entities.add(entity);
		}
		return entities;
	}

}
