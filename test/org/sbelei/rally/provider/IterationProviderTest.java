package org.sbelei.rally.provider;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sbelei.rally.domain.BasicEntity;

import com.rallydev.rest.RallyRestApi;
import org.sbelei.rally.provider.*;


public class IterationProviderTest {

    IterationProvider iterationProvider;
    
    @Before
    public void setUp() {
        // TODO Auto-generated method stub
        RallyRestApi restApi = mock(RallyRestApi.class);
        iterationProvider = new IterationProvider(restApi, "workspaceId", "projectId");
    }
    
    @Test
    public void testGetCurrentIterationForEmptyResponce() throws Exception {
        BasicEntity oneIteration = iterationProvider.getCurrentIteration();
        assertNull(oneIteration);
    }
    
    @Test
    public void testGetIteration() throws Exception {
        List<BasicEntity> iterations = iterationProvider.getIterations();
        assertNull(iterations);
    }
}
