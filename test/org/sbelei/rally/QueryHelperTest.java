package org.sbelei.rally;

import static org.junit.Assert.*;
import static org.sbelei.rally.TestHelper.date;
import org.junit.Test;


public class QueryHelperTest {

	@Test
	public void testQueryDate() throws Exception {
		assertEquals("2013-07-14T00:00:00.000Z", QueryHelper.queryDate(date("14/07/2013")));		
	}
}
