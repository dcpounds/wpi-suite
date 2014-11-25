package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

/**
 * @author Nathaniel Jefferson
 *
 */
public class DateLabelFormatterTest {

	/**
	 * @throws ParseException
	 */
	@Test
	public void test() throws ParseException {
		DateLabelFormatter date = new DateLabelFormatter();
		
		assertTrue(date.stringToValue("01-15-2014").equals(date.stringToValue("01-15-2014")));
		assertTrue(date.valueToString(null).equals(""));
	}

}
