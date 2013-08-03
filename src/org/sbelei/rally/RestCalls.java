package org.sbelei.rally;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.rallydev.rest.RallyRestApi;

import org.sbelei.rally.domain.*;
import org.sbelei.rally.domain.constants.StoryState;
import org.sbelei.rally.provider.*;

class RestCalls {
	
	public static String home = "C:/Documents and Settings/user/workspace/RallySandbox/test-resources";
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		System.out.println("*** start*** ");
		RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);
			
		StoryProvider provider = new StoryProvider(restApi, "41593629", "9216950819", "11597902889");
		provider.dumpFileName = "E:/PROJECTS/RallyApiDemo/test-resources/story-responce.json";
		List<Story> entities= provider.onlyMine().fetch();
		System.out.println(entities);
		System.out.println("*** finish ***");
	}

}
