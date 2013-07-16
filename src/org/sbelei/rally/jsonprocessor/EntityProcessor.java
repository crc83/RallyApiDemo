package org.sbelei.rally.jsonprocessor;

import static org.sbelei.rally.JsonElementWrapper.wrap;

import java.util.ArrayList;
import java.util.List;

import org.sbelei.rally.JsonElementWrapper;
import org.sbelei.rally.domain.BasicEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public abstract class EntityProcessor<T extends BasicEntity> {
	
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
			entities.add(entity);
		}
		return entities;		
	}

	public abstract T newEntity();

}
