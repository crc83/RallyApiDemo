rally-api-helper
================

*PROJECT IS OUTDATED. NOT SURE IF IT WORKS WITH LATEST API*

This project contains api that will help work easier with RallyApi.

I.e.
```java
  	RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);
			
		WorkspaceProvider provider = new WorkspaceProvider(restApi);
		List<Workspace> entities= provider.fetch();

```
