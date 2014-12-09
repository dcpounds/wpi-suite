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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;
/*
 * This formats the date to be set up in the correct format, and then it sets the calendar to the 
 * correct view. It also formats the date here, so that it can be used later.
 */
public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "MM-dd-yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException{
    	if(!"-".equals(text.substring(2, 3))){
    		text = text.substring(0, 2) + "-" + text.substring(3, 5) + "-" + text.substring(6);
    	}
		return dateFormatter.parseObject(text);
      
    }

    @Override
    public String valueToString(Object value) throws ParseException{
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
