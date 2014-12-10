/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.reports;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Hashtable;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.*;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;


/**
 * @author Joe
 * This class is used to accumulate and report data on the use of the program over time, which
 * cannot be derived from simply reading the current list of taskModels
 */

public class DataLoggerModel extends AbstractModel 
	{
	private int taskSnapID;
	private int db_id;
	private List<TaskSnapshot> taskSnapList = new LinkedList<TaskSnapshot>();
	
	public DataLoggerModel() {
		taskSnapID = 0;
		this.db_id=this.hashCode();
	}
	
	/**
	 * Updates a data logger model based on more recent values pulled from the database
	 */
	public void copyFrom(DataLoggerModel updatedDataLogger)
	{
		this.taskSnapID = updatedDataLogger.getTaskSnapID();
		this.db_id = updatedDataLogger.getDb_id();
		this.taskSnapList = updatedDataLogger.getTaskSnapList();
		
	}
	
	/**
	 * @return the db_id
	 */
	public int getDb_id() {
		return db_id;
	}

	/**
	 * @param db_id the db_id to set
	 */
	public void setDb_id(int db_id) {
		this.db_id = db_id;
	}

	/**
	 * @return the taskSnapList
	 */
	public List<TaskSnapshot> getTaskSnapList() {
		return taskSnapList;
	}

	/**
	 * @param taskSnapList the taskSnapList to set
	 */
	public void setTaskSnapList(List<TaskSnapshot> taskSnapList) {
		this.taskSnapList = taskSnapList;
	}

	/**
	 * @param taskSnapID the taskSnapID to set
	 */
	public void setTaskSnapID(int taskSnapID) {
		this.taskSnapID = taskSnapID;
	}

	/**
	 * Increment the task snapshot ID
	 */
	public void incrementTaskSnapID(){
		taskSnapID++;
	}
	
	/**
	 * @return the task snapshot ID
	 */
	public int getTaskSnapID() {
		return taskSnapID;
	}
	
	/**
	 * Add a new snapshot given a taskModel
	 * @param taskmodel
	 */
	public void addSnapshot(TaskModel taskmodel)
	{
		taskSnapList.add(new TaskSnapshot(taskmodel, taskSnapID));
	}
	
	
	/**
	 * Return the current task snapshot
	 * @param taskModel
	 * @return
	 */
	public TaskSnapshot returnCurrentSnapshot(TaskModel taskModel)
	{
		for (int i = taskSnapList.size(); i>=0; i=i-1)
		{
			if (taskSnapList.get(i-1).getTaskID() == taskModel.getID())
			{
				return taskSnapList.get(i-1);
			}
			System.out.print("Did a loop");
		}
		return null;


	}
	
	/** Returns the most recent snapshot associated with the task snapshot passed as an argument
	 * @param taskSnapshot the snapshot to find the most recent previous match for
	 */
	public TaskSnapshot returnPreviousSnapshot(TaskSnapshot taskSnapshot) {
		//checks each task for an ID match, starting from the most recent tasks
		boolean firstTrip = true;
		for (int i=taskSnapList.size(); i>=0; i=i-1) 
		{
			if (taskSnapList.get(i-1).getTaskID() == taskSnapshot.getTaskID())
			{
				if (firstTrip)
				{
					firstTrip = false;
				}
				else
				{
					return taskSnapList.get(i-1);
				}
			}
		}
		//executed only if the for loop reaches the end, indicating that only one snapshot has been taken of the task
		return null;
	}
	
	/** Returns a string array of changes made between two snapshots
	 * @param snap1, the first (older) snapshot
	 * @param snap2, the second (newer) snapshot
	 */
	public List<String> trackChanges(TaskSnapshot snap1, TaskSnapshot snap2) {
		int changes = 0;
		
		List<String> changelog = new LinkedList<String>();
		

				
		if (snap1.getActualEffort()!=snap2.getActualEffort())
		{
			changelog.add("Changed actual effort from " + snap1.getActualEffort() + " to " + snap2.getActualEffort()
							+ ".");
		}
		if (!(snap1.colorToString(snap1.getCatColor()).equals(snap2.colorToString(snap2.getCatColor()))))
		{
			changelog.add("Changed the category from " + snap1.colorToString(snap1.getCatColor()) + " to " + 
							snap2.colorToString(snap2.getCatColor()) + ".");
		}
		if (!(snap1.getColor().equals(snap2.getColor())))
		{
			
		}
		if (!(snap1.getCreationDate().equals(snap2.getCreationDate())))
		{
			
		}
		if (!(snap1.getDescription().equals(snap2.getDescription())))
		{
			changelog.add("Changed the description.");
		}
		if (!(snap1.getDueDate().equals(snap2.getDueDate())))
		{
			changelog.add("Changed the due date from " + snap1.getDueDate() + " to " + snap2.getDueDate()+ ".");
		}
		if (snap1.getEstimatedEffort()!=snap2.getEstimatedEffort())
		{
			changelog.add("Changed estimated effort from " + snap1.getEstimatedEffort() + " to " + snap2.getEstimatedEffort()
					+ ".");
		}
		if(snap1.getStageID()!=snap2.getStageID())
		{
			changelog.add("Moved from " + WorkflowController.getWorkflowModel().getStageModelByID(snap1.getStageID()).getTitle() +
							" to " + WorkflowController.getWorkflowModel().getStageModelByID(snap2.getStageID()).getTitle() + ".");
		}
		if(!(snap1.getTitle().equals(snap2.getTitle())))
		{
			changelog.add("Changed the title from " + snap1.getTitle() + " to " + snap2.getTitle() + ".");
		}
		if(!(snap1.getUsersAssignedTo().equals(snap2.getUsersAssignedTo())))
		{
			ArrayList<String> addList;
			String addString = null;
			ArrayList<String> removeList;
			String removeString = null;
			
			addList = snap2.getUsersAssignedTo();
			addList.removeAll(snap1.getUsersAssignedTo());
			if (addList.size()>=0)
			{
				for(int i=0;i<addList.size();i++)
				{
					addString+=addList.get(i);
					if (i==addList.size()-2)
					{
						addString+=", and ";
					}
					else if (i==addList.size()-1)
					{
						addString+=".";
					}
					else
					{
						addString+=", ";
					}
				}
			}
			
			
			removeList = snap1.getUsersAssignedTo();
			removeList.removeAll(snap2.getUsersAssignedTo());
			if (removeList.size()>=0)
			{
				for(int i=0;i<removeList.size();i++)
				{
					removeString+=removeList.get(i);
					if (i==removeList.size()-2)
					{
						removeString+=", and ";
					}
					else if (i==removeList.size()-1)
					{
						removeString+=".";
					}
					else
					{
						removeString+=", ";
					}
				}
			}
			
			
			if (!addList.isEmpty())
			{
				changelog.add("Added " + addList + " to this task.");
			}
			if (!removeList.isEmpty())
			{
				changelog.add("Removed " + removeList + " from this task.");
			}
		}

		return changelog;
		
	}
	
	/**
	 * Export all categories as a hashtable
	 * @return
	 */
	public Hashtable exportAllCategories()
	{
		Hashtable<String, Integer> output = new Hashtable();
		output.put("GRAY", 0);
		output.put("WHITE", 0);
		output.put("BROWN", 0);
		output.put("RED", 0);
		output.put("PINK", 0);
		output.put("ORANGE", 0);
		output.put("YELLOW", 0);
		output.put("GREEN", 0);
		output.put("BLUE", 0);
		output.put("PURPLE", 0);		
		
		if (taskSnapList.size()>0)
		{
			for (int i = taskSnapList.size(); i>0; i=i-1)
			{
	
				switch (taskSnapList.get(i-1).getCatColorString()) {
				case "GRAY":
					output.replace("GRAY", output.get("GRAY")+1);
					break;
				case "WHITE":
					output.replace("WHITE", output.get("WHITE")+1);
					break;
				case "BROWN":
					output.replace("BROWN", output.get("BROWN")+1);
					break;
				case "RED":
					output.replace("RED", output.get("RED")+1);
					break;
				case "PINK":
					output.replace("PINK", output.get("PINK")+1);
					break;
				case "ORANGE":
					output.replace("ORANGE", output.get("ORANGE")+1);
					break;
				case "YELLOW":
					output.replace("YELLOW", output.get("YELLOW")+1);
					break;
				case "GREEN":
					output.replace("GREEN", output.get("GREEN")+1);
					break;
				case "BLUE":
					output.replace("BLUE", output.get("BLUE")+1);
					break;
				case "PURPLE":
					output.replace("PURPLE", output.get("PURPLE")+1);
					break;
				
				}
			}
		}
		return output;
	}
	
	
	/**
	 * Format the provided date into a string
	 * @param date
	 * @return
	 */
	public String formatDateString(String date)
	{
		return (date.substring(0, 1)+"/"+date.substring(2,3)+"/"+date.substring(4,7));
	}

	/**
	 * Converts the task to a JSON string
	 * @return a JSON string representation of the task 
	 */
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, DataLoggerModel.class);
		return json;
	}
	
	
	/**
	 * Converts the given list of tasks to a JSON string
	 * @param taskList -  a list of tasks
	 * @return a string in JSON representing the list of tasks
	 */
	public static String toJSON(DataLoggerModel[] dataLoggerList) {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(dataLoggerList, TaskModel.class);
		return json;
	}
	
	/**
	 * @param json - the json obejct to convert back to a TaskModel
	 * @return
	 */
	public static DataLoggerModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, DataLoggerModel.class);
	}
	
	
	   /**
	 * @param json - the json to deserialize
	 * @return - the list of deserialized tasks
	 */
	public static DataLoggerModel[] fromJsonArray(String json) {
	        return new Gson().fromJson(json, DataLoggerModel[].class);
	    }

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
