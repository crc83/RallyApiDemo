package org.sbelei.rally.provider;

import java.util.List;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.*;
import com.rallydev.rest.util.*;

import org.sbelei.rally.Credentials;
import org.sbelei.rally.domain.*;
import org.sbelei.rally.helpers.FilterHelper;
import org.sbelei.rally.jsonprocessor.BasicEntityProcessor;


public class StoryProvider extends EntityProvider{

    String iterationId;


    public StoryProvider(RallyRestApi restApi, String workspaceId, String projectId, String iterationId){
    	super(workspaceId, projectId);
    	this.processor = new BasicEntityProcessor(restApi);
        this.iterationId = iterationId;
    }

	@Override
	String getType() {
		return Type.STORY;
	}
}
