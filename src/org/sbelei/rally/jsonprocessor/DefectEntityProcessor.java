package org.sbelei.rally.jsonprocessor;

import org.sbelei.rally.JsonElementWrapper;
import org.sbelei.rally.domain.Defect;

import com.rallydev.rest.RallyRestApi;

public class DefectEntityProcessor extends EntityProcessor<Defect>{

	public DefectEntityProcessor(RallyRestApi restApi) {
		super(restApi);
	}

	@Override
	public void fillAdditionalInfo(JsonElementWrapper json, Defect entity) {
		entity.formattedId = json.string("FormattedID");
		entity.severity = json.string("Severity");
		entity.priority = json.string("Priority");
		entity.state = json.string("State");
		entity.taskStatus = json.string("TaskStatus");
	}

	@Override
	public Defect newEntity() {		
		return new Defect();
	}

}
