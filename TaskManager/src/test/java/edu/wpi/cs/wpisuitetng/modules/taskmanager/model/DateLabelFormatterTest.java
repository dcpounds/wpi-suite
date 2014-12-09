/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
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
