package org.sbelei.rally.provider;

import com.rallydev.rest.request.*;
import com.rallydev.rest.util.*;

import java.util.logging.*;

import static org.sbelei.rally.QueryHelper.*;

/**
 * Created by sbelei on 7/17/13.
 */
public class BasicRequestBuilder {

    Logger log = Logger.getLogger(BasicRequestBuilder.class.getCanonicalName());

    Level STACKTRACE = Level.INFO;


    String workspaceId = "41593629";
    String projectId = "9216950819";


    public QueryRequest newRequest(String type, QueryFilter filter){
        QueryRequest request = new QueryRequest(type);
        if (workspaceId != null) {
            request.setWorkspace(workspaceId);
        }
        QueryFilter projectFilter = byProjectId(projectId);

        //apply additional filter if aviable
        if (filter == null){
            request.setQueryFilter(projectFilter);
        } else {
            request.setQueryFilter(projectFilter.and(filter));
        }
        return request;
    }

}
