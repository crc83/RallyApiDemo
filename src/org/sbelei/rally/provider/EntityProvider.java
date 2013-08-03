package org.sbelei.rally.provider;

import static org.sbelei.rally.JsonElementWrapper.wrap;
import static org.sbelei.rally.helpers.FilterHelper.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sbelei.rally.Credentials;
import org.sbelei.rally.JsonElementWrapper;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.helpers.QueryFilterBuilder;
import org.sbelei.rally.helpers.QueryRequestDecorator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.QueryFilter;

public abstract class EntityProvider <T extends BasicEntity>{
	
    /** responses will be saved in this file if value of variable is not null or "" **/
    public String dumpFileName;

	Level STACKTRACE = Level.INFO;
    Logger log = Logger.getLogger(EntityProvider.class.getCanonicalName());
       
    String workspaceId;
    String projectId;
    RallyRestApi restApi;
    QueryFilterBuilder filters;

    
	EntityProvider(RallyRestApi restApi, String workspaceId, String projectId){		
		this.restApi = restApi;
        this.workspaceId = workspaceId;
        this.projectId = projectId;
        filters = new QueryFilterBuilder();
        filters.add(byProjectId(projectId));
    }
    
    abstract String getType();


    QueryRequest newRequest(String type, QueryFilter filter){
        QueryRequestDecorator request = new QueryRequestDecorator(type);
        
        request.setWorkspace(workspaceId);

        request.andFilter(filters.buildQuery());
        request.andFilter(filter);

        return request.getRequest();
    }         

	List<T> fetch(QueryFilter additionalFilter) {
        QueryRequest request = newRequest(getType(), additionalFilter);

        return getEntitiesByRequest(request);
	}
	
	public List<T> fetch() {
		return fetch(null);
	}

    public EntityProvider<T> onlyMine() {
    	filters.add(includeByOwner(Credentials.USER));
        return this;
    }
    
    /**
     * 
     */    

    private static void fillBasicInfo(JsonElementWrapper json, BasicEntity entity) {
		entity.name = json.string("_refObjectName");
		entity.ref = json.string("_ref");
		entity.id = json.string("ObjectID");
	}

	public List<T> fetchEntities(JsonArray responce) {
		List<T> entities = new ArrayList<T>();
		for (JsonElement rawJson : responce){
			T entity = newEntity();
			JsonElementWrapper json = wrap(rawJson);
			fillBasicInfo(json,entity);
            fillAdditionalInfo(json,entity);
			entities.add(entity);
		}
		return entities;		
	}

	/**
	 * Implement this method to create bean of specific type.
	 * I.e.<br/> <code>return new Defect();</code>
	 * @return new empty bean;
	 */
	public abstract T newEntity();


	/**
	 * Override this method if you want to provide additional mappings
	 * between json fields and fields of your bean. I.e.<br/>
	 * <code>entity.myFieldd = json.string("fieldA");</code> 
	 * @param json
	 * @param entity
	 */
    public void fillAdditionalInfo(JsonElementWrapper json, T entity){

    }

    /**
     * Retrieves list of entities by request.
     * Provider class prepares request and adds necessary filters.
     * This class only invokes request through RestApi, 
     * processes response and handles connection issues.
     * @param request
     * @return
     */
    public List<T> getEntitiesByRequest(QueryRequest request) {
        QueryResponse responce;
        List<T> result = null;
        try {
            responce = restApi.query(request);
            saveResponceToFile(responce.getResults().toString());
            result = fetchEntities(responce.getResults());
        } catch (IOException | NullPointerException e) {
            if ("HTTP/1.1 401 Unauthorized".equalsIgnoreCase(e.getMessage())) {
            	log.info("Authorization failed");
            } else {
            	log.severe("Can't fetch entities.");
            	log.log(STACKTRACE,"Caused by:",e);
            }
        }
        return result;
    }
    
    /**
     * Saves raw response to <code>dumpFileName</code> if it's defined.
     * @param responce
     */
	private void saveResponceToFile(String responce){
        if (dumpFileName == null || "".equals(dumpFileName)) {
        	return;
        }
		try {
			PrintWriter pw = new PrintWriter(dumpFileName);
			pw.write(responce);
			pw.close();
			log.log(Level.INFO,"Dump saved to file "+dumpFileName);
		} catch (FileNotFoundException e) {
			log.log(Level.WARNING,"File not found",e);
		}
		
	}

}
