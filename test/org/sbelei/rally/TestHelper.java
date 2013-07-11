package org.sbelei.rally;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TestHelper {
	
	public static InputStream getResource(String name) {
		TestHelper helper = new TestHelper();
		return helper.getClass().getResourceAsStream(name);
	}
	
	public static Reader getResourceAsReader(String name) {
		InputStream is = getResource(name);
		if (is==null){
			throw new IllegalArgumentException();
		}
		return new InputStreamReader(is);
	}

}
