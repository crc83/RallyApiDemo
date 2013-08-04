rally-api-helper
================
[![Build Status](https://buildhive.cloudbees.com/job/crc83/job/rally-api-helper/badge/icon)](https://buildhive.cloudbees.com/job/crc83/job/rally-api-helper/)

This project contains api that will help work easier with RallyApi.

I.e.
```java
  	RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"),
				Credentials.USER,
				Credentials.PASSWORD);
			
		WorkspaceProvider provider = new WorkspaceProvider(restApi);
		List<Workspace> entities= provider.fetch();

```
