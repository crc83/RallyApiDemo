package org.sbelei.rally;

import java.util.ArrayList;
import java.util.List;

import org.sbelei.rally.domain.Project;
import org.sbelei.rally.domain.Workspace;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import static org.sbelei.rally.JsonElementWrapper.wrap;

public class ResponceHelper {
	
	public static List<Workspace> fetchWorkspaces(JsonArray responce) {
		List<Workspace> workspaces = new ArrayList<Workspace>();
		for (JsonElement workspaceJson : responce){
			Workspace ws = new Workspace();
			//populate workspace fields
			JsonElementWrapper json = wrap(workspaceJson);
		    ws.ref = json.string("_ref");
		    ws.name = json.string("Name");
		    ws.id = json.string("ObjectID");
		    ws.description = json.string("Description"); 
		    ws.notes = json.string("Notes");
		    if (json.hasNode("Projects")) {
			    for (JsonElement projectJson : json.array("Projects") ) {
			        JsonElementWrapper jsonP = wrap(projectJson);
			        Project project = new Project();
			        project.name = jsonP.string("_refObjectName");
			        project.ref = jsonP.string("_ref");
			        ws.projects.add(project);
			    }
		    }

		    workspaces.add(ws);
		}
		return workspaces;		
	}

}
