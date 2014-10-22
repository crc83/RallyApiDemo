package org.sbelei.rally.provider;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import org.sbelei.rally.domain.Iteration;

import static org.mockito.Mockito.*;
import static org.sbelei.rally.TestHelper.*;

public class IterationProviderTest {

    private static final String STUB_PROJECT_ID = "projectId";
	private static final String STUB_WORKSPACE_ID = "workspaceId";
	
	IterationProvider iterationProvider;
    
    @Before
    public void setUp() throws IOException {
    	RallyRestApi restApi = getRestApiWithResponse("/iteration-responce.json");
        iterationProvider = new IterationProvider(restApi, STUB_WORKSPACE_ID, STUB_PROJECT_ID);        
	}
    
    @Test
    @Ignore
    /**
     * Something wrong with stubbed responses
     * java.lang.ClassCastException: com.google.gson.JsonArray cannot be cast to com.google.gson.JsonObject
     * @throws Exception
     */
	public void testGetIterations() throws Exception {
		List<Iteration> iterations = iterationProvider.getIterations();
		assertEquals(3, iterations.size());
		assertEquals("Iteration A", iterations.get(0).name);
	}

	private static RallyRestApi getRestApiWithResponse(String responsePath)
			throws IOException {
		RallyRestApi restApi = mock(RallyRestApi.class);
		String responseMessage = getResourseAsString(responsePath);
		QueryResponse stubResponse = new QueryResponse(responseMessage);
		stub(restApi.query(any(QueryRequest.class))).toReturn(stubResponse);
		return restApi;
	}
    
    
}
