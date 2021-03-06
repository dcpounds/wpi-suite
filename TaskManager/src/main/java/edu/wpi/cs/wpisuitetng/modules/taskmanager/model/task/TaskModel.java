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
import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.DateLabelFormatter;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.IDisplayModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.reports.TaskSnapshot;

/** Model to represent a task **/
public class TaskModel extends AbstractModel implements IDisplayModel {
	private final long DAY_IN_MILLIS = 24 * 60 * 60 * 1000;  // Static for converting milliseconds into days
	private int estimatedEffort, actualEffort;
	private int id;
	private String title, description;
	private String creatorName;
	private ArrayList<String> usersAssignedTo;
	private String associatedRequirement;
	private Date creationDate;
	private Date creationDateBuffer;
	private String dueDate;
	private int stageID;
	private int catID;
	private int timeThreshold;
	private boolean isExpanded;
	private boolean isArchived;
	private Color color;
	private Color CatColor;
	private ActivityListModel activities;
	private Color[] urgencyArray = {new Color(51,199,72),new Color(51,199,72),new Color(51,199,72)};
	
	/** The default constructor for a Task **/
	@SuppressWarnings("deprecation")
	public TaskModel(){
		this.id = this.hashCode();
		this.estimatedEffort = 0;
		this.actualEffort = 0;
		this.title = description = "";
		this.creatorName = "";
		this.creationDate = new Date();
		this.usersAssignedTo = new ArrayList<String>();
		this.dueDate = "";
		this.stageID = 0;
		this.catID = 0;
		this.timeThreshold = 1;
		this.isExpanded = false;
		this.isArchived = false;
		this.activities = new ActivityListModel();
		this.associatedRequirement = "";
	}
	
	/** Copies the contents of updatedStage into this one
	 * @param updatedTask the stage to copy from
	 */
	public void copyFrom(TaskModel updatedTask) {
		this.id = updatedTask.id;
		this.estimatedEffort = updatedTask.getEstimatedEffort();
		this.actualEffort = updatedTask.getActualEffort();
		this.title = updatedTask.title;
		this.creatorName = updatedTask.getCreatorName();
		this.description = updatedTask.getDescription();
		creationDate = updatedTask.getCreationDate();
		this.usersAssignedTo = updatedTask.getUsersAssignedTo();
		dueDate = updatedTask.getDueDate();	
		this.isExpanded = updatedTask.getIsExpanded();
		this.stageID = updatedTask.getStageID();
		this.catID = updatedTask.getCatID();
		this.isArchived = updatedTask.getIsArchived();
		activities = updatedTask.getActivities();
		this.color = updatedTask.getCatColor();
		this.associatedRequirement = updatedTask.getAssociatedRequirement();
	}
	

	/**
	 * @return the ID of this task
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Sets the id of the task
	 * @param id
	 */
	public void setID(int id){
		this.id = id;
	}
	
	/**
	 * @return the stageIndex that this task is in
	 */
	public int getStageID(){
		return stageID;
	}
	
	/**
	 * @param stageIndex - the stageIndex to set the task to
	 */
	public void setStageID(int stageIndex){
		this.stageID = stageIndex;
	}
	
	/**
	 * @return the estimated effort needed to complete the task
	 */
	public int getEstimatedEffort() {
		return estimatedEffort;
	}
	
	/**
	 * @param estimatedEffort - sets the estimated effort needed to complete the task
	 */
	public void setEstimatedEffort(int estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}
	
	/**
	 * @return the actual effort needed to complete the task
	 */
	public int getActualEffort() {
		return actualEffort;
	}
	

	/**
	 * @param actualEffort - sets the actual effort needed to complete the task
	 */
	public void setActualEffort(int actualEffort) {
		this.actualEffort = actualEffort;
	}
	
	/**
	 * @return the title of the task
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title - set the title of the task
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the description of the task
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description - set the description of this task
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the core user that created this task
	 */
	public String getCreatorName() {
		return creatorName;
	}
	
	/**
	 * @param creator - set the user that created this task
	 */
	public void setCreator(String creatorName) {
		this.creatorName = creatorName;
	}
	
	
	/**
	 * @return the user that created the task
	 */
	public ArrayList<String> getUsersAssignedTo(){
		if(usersAssignedTo == null){
			System.out.println("WARNING: NO USERS ASSIGNED, COUNT " + usersAssignedTo.size() );
			return new ArrayList<String>();
		}
		return usersAssignedTo;
	}
	
	/**
	 * @param arrayList - set the list of users that this task is assigned to
	 */
	public void setUsersAssignedTo(ArrayList<String> usernameList) {
		this.usersAssignedTo.clear();
		this.usersAssignedTo.addAll(usernameList);
	}
	
	/**
	 * @return the date that the task was created
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * @param creationDate - set the date that this task was created
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	/**
	 * @return the date that this task is due
	 */
	public String getDueDate() {
		return dueDate;		
	}
	
	/**
	 * @return number of days until the task is due
	 * @throws ParseException
	 */
	public int daysUntilDue(){
		int days = 0;
		try{
			DateLabelFormatter format = new DateLabelFormatter();
			Calendar currentTime = Calendar.getInstance();
			Date due = (Date) format.stringToValue(getDueDate());
			Calendar dueTime = Calendar.getInstance();
			dueTime.setTime(due);
			days = (int)(((dueTime.getTimeInMillis() - currentTime.getTimeInMillis())/ DAY_IN_MILLIS) + 1);
		}catch(ParseException e){
			e.printStackTrace();
			System.out.println("I FAILED to parse date");
		}
		return days;
	}
	

	
	/**
	 * Get days before due date until this task becomes urgent
	 * @return
	 */
	public int getTimeThreshold(){
		return timeThreshold;
	}
	
	/**
	 * Set days before due date until this task becomes urgent
	 * @param days
	 */
	public void setTimeThreshold(int days){
		this.timeThreshold = days;
	}
	
	
	/**
	 * @return whether or not the task is expanded or collapsed
	 */
	public boolean getIsExpanded(){
		return isExpanded;
	}
	
	
	/**
	 * @param status - set the task as expanded or collapsed
	 */
	public void setIsExpanded(boolean status){
		this.isExpanded = status;
	}
	
	/**
	 * @param dueDate - set the date that this task is due
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * @param archiveStatus - set the task to archived (true) or active (false)
	 */
	public void setIsArchived(boolean archiveStatus){
		this.isArchived = archiveStatus;
	}
	
	/**
	 * @return true if archived, false otherwise
	 */
	public boolean getIsArchived(){
		return this.isArchived;
	}
	
		
	/**
	 * Converts the task to a JSON string
	 * @return a JSON string representation of the task 
	 */
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, TaskModel.class);
		return json;
	}
	
	
	/**
	 * Converts the given list of tasks to a JSON string
	 * @param taskList -  a list of tasks
	 * @return a string in JSON representing the list of tasks
	 */
	public static String toJSON(TaskModel[] taskList) {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(taskList, TaskModel.class);
		return json;
	}
	
	/**
	 * @param json - the json obejct to convert back to a TaskModel
	 * @return
	 */
	public static TaskModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, TaskModel.class);
	}
	
	
	   /**
	 * @param json - the json to deserialize
	 * @return - the list of deserialized tasks
	 */
	public static TaskModel[] fromJsonArray(String json) {
	        return new Gson().fromJson(json, TaskModel[].class);
	    }

	/* (non-Javadoc)
	 * returns true if this task id matches the one provided
	 */
	@Override
	public Boolean identify(Object o) {
		Boolean returnValue = false;
		if(o instanceof TaskModel && id == ((TaskModel) o).getID()) {
			returnValue = true;
		}
		if(o instanceof String && Integer.toString(id).equals(o)) {
			returnValue = true;
		}
		return returnValue;
	}
	
	//Not yet implemented...
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}
	
	/* (non-Javadoc)
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
        
        TaskModel other = (TaskModel) obj;
        
        return this.id == other.getID();
	}

	/**
	 * @return the activities
	 */
	public ActivityListModel getActivities() {
		return activities;
	}
	
	/**
	 * Set the activities for this task
	 * @param newActivities
	 */
	public void setActivities(ActivityListModel newActivities) {
		this.activities = newActivities;
	}

	/**
	 * Add an activity to the task
	 * @param activity
	 */
	public void addActivity(ActivityModel activity){
		activities.addActivity(activity);
	}
	
	/**
	 * 
	 * @return the category color of the task
	 */
	public Color getCatColor(){
		return this.CatColor;
	}
	
	
	/**
	 * set the category color of the task
	 * @param catColor
	 */
	public void setCatColor(Color catColor){
		this.CatColor =  catColor;
	}
	
	/**
	 * get the category color index selection that the user made
	 * @return the selected color index
	 */
	public int getCatID() {
		return this.catID;
	}
	
	/**
	 * set the category color index selection that the user made
	 * @param index
	 */
	public void setCatID(int index) {
		this.catID  = index;
	}
	
	/**
	 * Get the current task snapshot. Used for statistic logging
	 * @return the snapshot of this task
	 */
	public TaskSnapshot getCurrentSnapshot() {
		return DataLoggerController.getDataModel().returnCurrentSnapshot(this);
	}
	
	/**
	 * Get the previous snapshot of this task
	 * @return
	 */
	public TaskSnapshot getPreviousSnapshot() {
		return DataLoggerController.getDataModel().returnPreviousSnapshot(getCurrentSnapshot());
	}
	
	/**
	 * Set the associated requirement of this task
	 * @param req
	 */
	public void setAssociatedRequirement(String req) {
		this.associatedRequirement = req;
	}
	
	
	/**
	 * Get the associated requirement of this task
	 * @return the string name of the requirement
	 */
	public String getAssociatedRequirement() {
		return associatedRequirement;
	}

}
