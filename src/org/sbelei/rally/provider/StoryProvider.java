package org.sbelei.rally.provider;


import com.rallydev.rest.RallyRestApi;

import org.sbelei.rally.domain.*;


public class StoryProvider extends EntityProvider<BasicEntity>{

    String iterationId;


    public StoryProvider(RallyRestApi restApi, String workspaceId, String projectId, String iterationId){
    	super(restApi, workspaceId, projectId);
        this.iterationId = iterationId;
    }

	@Override
	String getType() {
		return Type.STORY;
	}

	@Override
	public BasicEntity newEntity() {
		return new BasicEntity();
	}
}
