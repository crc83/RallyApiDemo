package org.sbelei.rally;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;

import static org.sbelei.rally.helpers.FilterHelper.*;

import org.sbelei.rally.domain.*;
import org.sbelei.rally.domain.defect.State;
import org.sbelei.rally.provider.*;

class RestCalls {
	
	public static String home = "C:/Documents and Settings/user/workspace/RallySandbox/test-resources";
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		System.out.println("*** start*** ");
		RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);
			
//		EntityProcessor.saveResponceToFile("${home}/iteration-responce.json",queryResponse.getResults().toString());
		DefectsProvider provider = new DefectsProvider(restApi, "41593629", "9216950819", "11597902889");
//		provider.setDumpFileName("E:/PROJECTS/RallyApiDemo/test-resources/defect-responce.json");
		List<Defect> entities= provider.getNotClosed();
		System.out.println(entities);
		System.out.println("*** finish ***");
	}

}
