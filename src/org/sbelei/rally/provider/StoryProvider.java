package org.sbelei.rally.provider;


import com.rallydev.rest.RallyRestApi;

import org.sbelei.rally.JsonElementWrapper;
import org.sbelei.rally.domain.*;
import org.sbelei.rally.domain.constants.Type;
import static org.sbelei.rally.helpers.FilterHelper.*;

public class StoryProvider extends EntityProvider<Story>{

    String iterationId;


    public StoryProvider(RallyRestApi restApi, String workspaceId, String projectId, String iterationId){
    	super(restApi, workspaceId, projectId);
        this.iterationId = iterationId;
        filters.add(byIterationId(iterationId));
    }

	@Override
	String getType() {
		return Type.STORY;
	}

	@Override
	public Story newEntity() {
		return new Story();
	}
	
	@Override
	public void fillAdditionalInfo(JsonElementWrapper json, Story entity) {
		entity.formattedId = json.string("FormattedID");
		entity.scheduleState = json.string("ScheduleState");
	}
}
