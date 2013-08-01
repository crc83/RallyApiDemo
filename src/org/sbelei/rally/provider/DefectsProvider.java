package org.sbelei.rally.provider;

import java.util.List;

import org.sbelei.rally.Credentials;
import org.sbelei.rally.JsonElementWrapper;
import org.sbelei.rally.domain.Defect;
import org.sbelei.rally.domain.Type;
import org.sbelei.rally.helpers.FilterHelper;

import com.rallydev.rest.RallyRestApi;

public class DefectsProvider extends EntityProvider<Defect>{
	
	String iterationId;

	public DefectsProvider(RallyRestApi restApi, String workspaceId, String projectId, String iterationId) {
		super(restApi, workspaceId, projectId);
		this.iterationId = iterationId;
	}

	@Override
	String getType() {
		return Type.DEFECT;
	}

	public List<Defect> getNotClosed() {
		return fetch(FilterHelper
				.includeByOwner(Credentials.USER)
				.and(FilterHelper
                        //TODO SB : Make enum of it
				.includeByStates("Submitted", "Open", "In progress", "Closed", "Accepted")));
	}

	@Override
	public Defect newEntity() {
		return new Defect();
	}
	
	@Override
	public void fillAdditionalInfo(JsonElementWrapper json, Defect entity) {
		entity.formattedId = json.string("FormattedID");
		entity.severity = json.string("Severity");
		entity.priority = json.string("Priority");
		entity.state = json.string("State");
		entity.taskStatus = json.string("TaskStatus");
	}
}
