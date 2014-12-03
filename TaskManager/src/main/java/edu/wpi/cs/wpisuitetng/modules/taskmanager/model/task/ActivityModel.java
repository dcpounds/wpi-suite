package edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * Model to contain a single activity for the activity list
 * 
 * Code was base on postboard message code
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
        return this.date;
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
        return this.comment;
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
        
        if (this.date == null) {
            if (other.date != null)
                return false;
        } else if (!this.date.equals(other.date))
            return false;
        if (this.user == null) {
            if (other.user != null)
                return false;
        } else if (!this.user.equals(other.user))
            return false;
        if (this.comment == null) {
            if (other.comment != null)
                return false;
        } else if (!this.comment.equals(other.comment))
            return false;
        
        return true;
    }

}
