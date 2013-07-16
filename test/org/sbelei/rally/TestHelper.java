package org.sbelei.rally;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestHelper {

	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static InputStream getResource(String name) {
		TestHelper helper = new TestHelper();
		return helper.getClass().getResourceAsStream(name);
	}

	public static Reader getResourceAsReader(String name) {
		InputStream is = getResource(name);
		if (is == null) {
			throw new IllegalArgumentException();
		}
		return new InputStreamReader(is);
	}

	/**
	 * convert String to date
	 * 
	 * @param date
	 *            "dd/mm/yyyy"
	 * @return Date object
	 */
	public static Date date(String date) {
		Date today = null;
		try {
			today = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return today;
	}
}
