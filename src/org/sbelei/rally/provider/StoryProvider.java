package org.sbelei.rally.provider;

import java.util.List;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.*;
import com.rallydev.rest.util.*;

import org.sbelei.rally.Credentials;
import org.sbelei.rally.domain.*;
import org.sbelei.rally.helpers.FilterHelper;


public class StoryProvider extends BaseProvider{

    String iterationId;


    public StoryProvider(RallyRestApi restApi, String workspaceId, String projectId, String iterationId){
    	super(restApi, workspaceId, projectId);
        this.iterationId = iterationId;
    }

	@Override
	String getType() {
		return Type.STORY;
	}
}
