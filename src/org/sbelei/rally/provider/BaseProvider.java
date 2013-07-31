package org.sbelei.rally.provider;

import static org.sbelei.rally.helpers.FilterHelper.byProjectId;

import java.util.List;
import java.util.logging.Logger;

import org.sbelei.rally.Credentials;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.helpers.FilterHelper;
import org.sbelei.rally.helpers.QueryRequestDecorator;
import org.sbelei.rally.jsonprocessor.BasicEntityProcessor;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.util.QueryFilter;

public abstract class BaseProvider {
	
    Logger log = Logger.getLogger(IterationProvider.class.getCanonicalName());
       
    String workspaceId;
    String projectId;
    
    BasicEntityProcessor processor;
    
    BaseProvider(RallyRestApi restApi, String workspaceId, String projectId){
        this.processor = new BasicEntityProcessor(restApi);
        this.workspaceId = workspaceId;
        this.projectId = projectId;
    }
    
    abstract String getType();


    QueryRequest newRequest(String type, QueryFilter filter){
        QueryRequestDecorator request = new QueryRequestDecorator(type);
        
        request.setWorkspace(workspaceId);

        request.addFilter(byProjectId(projectId));
        request.addFilter(filter);

        return request.getRequest();
    }         

	List<BasicEntity> fetch(QueryFilter additionalFilter) {
        QueryRequest request = newRequest(getType(), additionalFilter);
        return processor.getEntitiesByRequest(request);
	}


    public List<BasicEntity> getMine() {
        QueryFilter filter = FilterHelper.includeByOwner(Credentials.USER);
        return fetch(filter);
    }

}
