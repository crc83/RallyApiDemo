package org.sbelei.rally.provider;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sbelei.rally.domain.BasicEntity;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;

import static org.mockito.Mockito.*;
import static org.sbelei.rally.TestHelper.*;

public class IterationProviderTest {

    private static final String STUB_PROJECT_ID = "projectId";
	private static final String STUB_WORKSPACE_ID = "workspaceId";
	
	IterationProvider iterationProvider;
    
    @Before
    public void setUp() throws IOException {
    	RallyRestApi restApi = getRestApiWithResponce("/iteration-responce.json");
        iterationProvider = new IterationProvider(restApi, STUB_WORKSPACE_ID, STUB_PROJECT_ID);        
	}
    
    @Test
    @Ignore
    /**
     * Something wrong with stubbed responces
     * @throws Exception
     */
	public void testGetIterations() throws Exception {
		List<BasicEntity> iterations = iterationProvider.getIterations();
		assertEquals(3, iterations.size());
		assertEquals("Iteration A", iterations.get(0).name);
	}

	private static RallyRestApi getRestApiWithResponce(String responcePath)
			throws IOException {
		RallyRestApi restApi = mock(RallyRestApi.class);
		String responceMessage = getResourseAsString(responcePath);
		QueryResponse stubResponce = new QueryResponse(responceMessage);
		stub(restApi.query(any(QueryRequest.class))).toReturn(stubResponce);
		return restApi;
	}
    
    
}
