package org.sbelei.rally.provider;

import static org.sbelei.rally.helpers.FilterHelper.byProjectId;

import java.util.List;
import java.util.logging.Logger;

import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.domain.Type;
import org.sbelei.rally.helpers.QueryRequestDecorator;
import org.sbelei.rally.jsonprocessor.BasicEntityProcessor;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.util.QueryFilter;

public class BaseProvider {
	
    Logger log = Logger.getLogger(IterationProvider.class.getCanonicalName());
       
    String workspaceId;
    String projectId;
    
    BasicEntityProcessor processor;
    
    BaseProvider(RallyRestApi restApi, String workspaceId, String projectId){
        this.processor = new BasicEntityProcessor(restApi);
        this.workspaceId = workspaceId;
        this.projectId = projectId;
    }


    QueryRequest newRequest(String type, QueryFilter filter){
        QueryRequestDecorator request = new QueryRequestDecorator(type);
        
        request.setWorkspace(workspaceId);

        request.addFilter(byProjectId(projectId));
        request.addFilter(filter);

        return request.getRequest();
    }         

	List<BasicEntity> fetch(String type, QueryFilter additionalFilter) {
        QueryRequest request = newRequest(Type.ITERATION, additionalFilter);
        return processor.getEntitiesByRequest(request);
	}


}
