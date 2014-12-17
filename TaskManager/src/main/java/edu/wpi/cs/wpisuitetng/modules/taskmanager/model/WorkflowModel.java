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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;

/**
 * the main model representing the workflow. 
 * contains all the stages where tasks are moved around in
 */
public class WorkflowModel extends AbstractModel {
	private static final int YEAR = 0;
	final private String name;
	private LinkedHashMap<Integer,StageModel> stageModelList;
	private static ArrayList<User> userList; 
	private static boolean toggleColor;
	private static boolean isDraggingStage;
	private static ArrayList<String> requirementsList;
	private Date overrideDate;
	private Date startDate;
	private Date endDate;
	private String overrideString;
	private String startString;
	private String endString;
	private int completeStageID;
	

	/**
	 * construct the main workflow based off a given list of stages
	 * @param name - the name of the workflow (usually "main")
	 * @param stageList
	 */
	public WorkflowModel(String name, LinkedHashMap<Integer,StageModel> stageList){
		this.name = name;
		stageModelList = stageList;
		WorkflowModel.userList = new ArrayList<User>();
		WorkflowModel.toggleColor = false;
		WorkflowModel.isDraggingStage = false;
		WorkflowModel.setRequirementsList(new ArrayList<String>());
		
	    overrideDate = new Date();
	    startDate = new Date(2014,12,1);
	    endDate = new Date (2014,12,31);
	    overrideString = overrideDate.toString();
	    startString = startDate.toString();
	    endString = endDate.toString();
	    completeStageID=0;
	    initializeCompleteStageModel();
	}
	
	
	/**
	 * Construct the main workflow
	 * @param name - the name of the workflow (usually "main")
	 */
	public WorkflowModel(String name){
		this.name = name;
		stageModelList = new LinkedHashMap<Integer, StageModel>();
		WorkflowModel.toggleColor = false;
		WorkflowModel.isDraggingStage = false;
		
	    overrideDate = new Date();
	    
	    
	    try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2014-12-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2014-12-31");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	    overrideString = overrideDate.toString();
	    startString = startDate.toString();
	    endString = endDate.toString();
	    
	    completeStageID=0;
	    initializeCompleteStageModel();
	    
	}
	
	
	/**
	 * @return the overrideDate
	 */
	public Date getOverrideDate() {
		return overrideDate;
	}


	/**
	 * @param overrideDate the overrideDate to set
	 */
	public void setOverrideDate(Date overrideDate) {
		this.overrideDate = overrideDate;
		this.overrideString = overrideDate.toString();
	}


	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		this.startString = startDate.toString();
	}


	/**
	 * @return the completeStageID
	 */
	public int getCompleteStageID() {
		return completeStageID;
	}


	/**
	 * @param completeStageID the completeStageID to set
	 */
	public void setCompleteStageID(int completeStageID) {
		this.completeStageID = completeStageID;
	}

	
	
	
    public void initializeCompleteStageModel(){
		for (StageModel stage : getStageModelList().values()) {
			if (stage.getTitle().equals("Completed"))
				setCompleteStageID(stage.getID());
		}
    }

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		this.endString = endDate.toString();
	}


	/**
	 * @return the string name of the workflow
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * @return a list of stages in the workflow
	 */
	public LinkedHashMap<Integer, StageModel> getStageModelList() {
		return stageModelList;
	}
	
	
	/**
	 * @param stage - a StageModel to add to the workflow
	 * @return the updated list of stages in the workflow
	 */
	public LinkedHashMap<Integer, StageModel> addStage(StageModel stage) {
		stageModelList.put(stage.getID(),stage);
		return stageModelList;
	}
	
	
	/**
	 * @param stage - a StageModel to add to the workflow
	 * @return the updated list of stages in the workflow
	 */
	public LinkedHashMap<Integer, StageModel> removeStageModel(StageModel stage) {
		if(stageModelList.remove(stage.getID()) == null){
			System.out.println("Failed to remove stage " + stage.getTitle() + " from stageModelList");
		} else{
			System.out.println("Successfully removed stage " + stage.getTitle() + " from stageModelList");
		}
		return stageModelList;
	}
	
	/**
	 * @return the list of users that can be assigned to tasks in this workflow
	 */
	public User[] getUserList() {
		if(userList == null)
			return new User[]{};
		return userList.toArray(new User[userList.size()]);
	}
	
	
	/**
	 * Toggles the value of the urgency view button
	 */
	public void toggleColor(){
		WorkflowModel.toggleColor = !WorkflowModel.toggleColor;
	}
	
	/**
	 * Gets the current value of the urgency color button
	 * @return true if toggled on, false otherwise
	 */
	public boolean getToggleColor(){
		return WorkflowModel.toggleColor;
	}
	
	/**
	 * Sets the flag to indicate that the user is dragging a task.
	 * We should not update a stage while this is happening
	 * @param bool
	 */
	public void setIsDraggingTask(boolean bool){
		WorkflowModel.isDraggingStage = bool;
	}
	
	/**
	 * Get whether or not a user is dragging a stage
	 * @return true if dragging, false otherwise
	 */
	public boolean getIsDraggingStage(){
		return WorkflowModel.isDraggingStage;
	}
	
	/**
	 * @param userList - the list of assignable users to set within the workflow
	 */
	public void setUserList(User[] userList){
		 WorkflowModel.userList = new ArrayList<User>(Arrays.asList(userList));
	}
	
	public static ArrayList<String> getRequirementsList() {
		return requirementsList;
	}


	public static void setRequirementsList(ArrayList<String> requirementsList) {
		WorkflowModel.requirementsList = requirementsList;
	}
	
	/**
	 * @param id - id of the task to look for
	 * @return the taskModel if successful, null otherwise
	 */
	public TaskModel getTaskModelByID(int id){
		for( StageModel stageModel : stageModelList.values()){
			TaskModel task = stageModel.getTaskModelList().get(id);
			if(task != null)
				return task;
		}
		return null;
	}
	
	
	/**
	 * @param id - id of the stage to look for
	 * @return - the stageModel if successful, nul otherwise
	 */
	public StageModel getStageModelByID(int id){
		return stageModelList.get(id);
	}
	
	
	/********************Database Helpers**************************
	 * The rest are functions for the main model to interact with the database,
	 * They should generally be left untouched unless messing with the database stuff
	 */
	@Override
	public void save() {
	}

	@Override
	public void delete() {
	}

	@Override
	public String toJson() {
        return new Gson().toJson(this, WorkflowModel.class);
	}
	

    public static WorkflowModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkflowModel.class);
    }

    public static WorkflowModel[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkflowModel[].class);
    }
    
	@Override
	public Boolean identify(Object o) {
        return null;
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
        
        WorkflowModel other = (WorkflowModel) obj;
        
        return name.equals(other.getName());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return name;
	}
	
	public void copyFrom(WorkflowModel other){
		stageModelList = other.stageModelList;
		overrideDate = other.overrideDate;
		startDate = other.startDate;
		endDate = other.endDate;
	}


	

}
