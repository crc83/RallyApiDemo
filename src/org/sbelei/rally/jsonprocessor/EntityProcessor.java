package org.sbelei.rally.jsonprocessor;

import static org.sbelei.rally.JsonElementWrapper.wrap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import com.rallydev.rest.*;
import com.rallydev.rest.request.*;
import com.rallydev.rest.response.*;
import org.sbelei.rally.JsonElementWrapper;
import org.sbelei.rally.domain.BasicEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public abstract class EntityProcessor<T extends BasicEntity> {

    Level STACKTRACE = Level.INFO;
    Logger log = Logger.getLogger(EntityProcessor.class.getCanonicalName());
    RallyRestApi restApi;

    public EntityProcessor(RallyRestApi restApi) {
        this.restApi = restApi;
    }


    private static void fillBasicInfo(JsonElementWrapper json, BasicEntity entity) {
		entity.name = json.string("_refObjectName");
		entity.ref = json.string("_ref");
		entity.id = json.string("ObjectID");
	}

	public List<T> fetchBasicEntities(JsonArray responce) {
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

	public abstract T newEntity();


    public void fillAdditionalInfo(JsonElementWrapper json, T entity){

    }

    public List<T> getEntitiesByRequest(QueryRequest request) {
        QueryResponse responce;
        List<T> result = null;
        try {
            responce = restApi.query(request);
            result = fetchBasicEntities(responce.getResults());
        } catch (IOException | NullPointerException e) {
            if ("HTTP/1.1 401 Unauthorized".equalsIgnoreCase(e.getMessage())) {
            	log.info("Authorization failed");
            } else {
            	log.severe("Can't fetch iteration.");
            	log.log(STACKTRACE,"Caused by:",e);
            }
        }
        return result;
    }

}
