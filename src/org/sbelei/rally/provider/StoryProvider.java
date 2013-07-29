package org.sbelei.rally.provider;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.*;
import com.rallydev.rest.util.*;

import org.sbelei.rally.Credentials;
import org.sbelei.rally.domain.*;
import org.sbelei.rally.jsonprocessor.*;

import java.util.*;
import java.util.logging.*;

public class StoryProvider {

    Logger log = Logger.getLogger(IterationProvider.class.getCanonicalName());
    BasicEntityProcessor processor;
    BasicRequestBuilder requestBuilder;

    String workspaceId = "41593629";
    String projectId = "9216950819";
    String iterationId = "11597902889";


    public StoryProvider(RallyRestApi restApi){ //, String workspaceId, String projectId, String iterationId){
        processor = new BasicEntityProcessor(restApi);
        requestBuilder = new BasicRequestBuilder();
//        requestBuilder.workspaceId = workspaceId;
//        requestBuilder.projectId = projectId;
    }

    private List<BasicEntity> fetch(QueryFilter additionalFilter) {
        //create request for workspace and project
        QueryRequest request = requestBuilder.newRequest("hierarchicalrequirement", additionalFilter);
        //process responce and return resul
        return processor.getEntitiesByRequest(request);
    }

    public List<BasicEntity> getMyStoriesForCurrentIteration() {
        QueryFilter filter = ownerIsMe();
        return fetch(filter);
    }

	private QueryFilter ownerIsMe() {
		return new QueryFilter("Owner","=",Credentials.USER);
	}
}
