package org.sbelei.rally.provider;

import com.rallydev.rest.request.*;
import com.rallydev.rest.util.*;

import java.util.logging.*;

import org.sbelei.rally.helpers.QueryRequestDecorator;

import static org.sbelei.rally.helpers.FilterHelper.*;

/**
 * Created by sbelei on 7/17/13.
 */
public class BasicRequestBuilder {

    Logger log = Logger.getLogger(BasicRequestBuilder.class.getCanonicalName());

    Level STACKTRACE = Level.INFO;


    String workspaceId;
    String projectId;


    public QueryRequest newRequest(String type, QueryFilter filter){
        QueryRequestDecorator request = new QueryRequestDecorator(type);
        
        request.setWorkspace(workspaceId);

        request.addFilter(byProjectId(projectId));
        request.addFilter(filter);

        return request.getRequest();
    }         

}
