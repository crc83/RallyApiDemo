package org.sbelei.rally.provider;

import java.util.List;

import org.sbelei.rally.Credentials;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.domain.Type;
import org.sbelei.rally.helpers.FilterHelper;

import com.rallydev.rest.RallyRestApi;

public class DefectsProvider extends BaseProvider{
	
	String iterationId;

	public DefectsProvider(RallyRestApi restApi, String workspaceId, String projectId, String iterationId) {
		super(restApi, workspaceId, projectId);
		this.iterationId = iterationId;
	}

	@Override
	String getType() {
		return Type.DEFECT;
	}

	public List<BasicEntity> getNotClosed() {
		return fetch(FilterHelper
				.includeByOwner(Credentials.USER)
				.and(FilterHelper
				.includeByStates("Submitted", "Open", "In progress")));
	}
	
}
