/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * Model to contain a single activity for the activity list
 * Code was based on postboard message code
 */
public class ActivityModel extends AbstractModel {
    
    /** The message */
    private final String comment;
    
    /** The date-time stamp */
    private Date date;
    
    private String user;
    
    /**
     * Constructs an activity for the given string comment
     * 
     * @param comment
     */
    public ActivityModel(String comment) {
        this.comment = comment;
        date = new Date();
		user = ConfigManager.getConfig().getUserName();
    }
    
    /**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
     * Returns a JSON-encoded string representation of this message object
     */
    @Override
    public String toJson() {
        return new Gson().toJson(this, ActivityModel.class);
    }
    
    /**
     * Returns an instance of Activity constructed using the given
     * Activity encoded as a JSON string.
     * 
     * @param json the json-encoded Activity to deserialize
     * @return the Activity contained in the given JSON
     */
    public static ActivityModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, ActivityModel.class);
    }
    
    /**
     * Returns an array of Activity parsed from the given JSON-encoded
     * string.
     * 
     * @param json a string containing a JSON-encoded array of Activity
     * @return an array of Activity deserialzied from the given json
     *         string
     */
    public static ActivityModel[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, ActivityModel[].class);
    }
    
    //Getters and Setters
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return comment;
    }
    
    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // Format the date-time stamp
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm a");
        return dateFormat.format(date) + ", " + user + ": " + comment;
    }
    
    /**
     * @return the stamp for the activity
     */
    public String getStamp(){
        // Format the date-time stamp
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm a");
        return dateFormat.format(date) + ", " + user + ": ";
    }
    
    /*
     * The methods below are required by the model interface, however they
     * do not need to be implemented for a basic model like Activity.
     */
    
    @Override
    public void save() {
    }
    
    @Override
    public void delete() {
    }
    
    @Override
    public Boolean identify(Object o) {
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        ActivityModel other = (ActivityModel) obj;
        
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        
        return true;
    }

}
