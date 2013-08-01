package org.sbelei.rally.provider;

import java.util.List;

import org.sbelei.rally.Credentials;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.domain.Defect;
import org.sbelei.rally.domain.Type;
import org.sbelei.rally.helpers.FilterHelper;
import org.sbelei.rally.jsonprocessor.DefectEntityProcessor;

import com.rallydev.rest.RallyRestApi;

public class DefectsProvider extends EntityProvider<Defect>{
	
	String iterationId;

	public DefectsProvider(RallyRestApi restApi, String workspaceId, String projectId, String iterationId) {
		super(workspaceId, projectId);
		this.processor = new DefectEntityProcessor(restApi);
		this.iterationId = iterationId;
	}

	@Override
	String getType() {
		return Type.DEFECT;
	}

	public List<Defect> getNotClosed() {
		return fetch(FilterHelper
				.includeByOwner(Credentials.USER)
				.and(FilterHelper
                        //TODO SB : Make enum of it
				.includeByStates("Submitted", "Open", "In progress", "Closed", "Accepted")));
	}
	
}
