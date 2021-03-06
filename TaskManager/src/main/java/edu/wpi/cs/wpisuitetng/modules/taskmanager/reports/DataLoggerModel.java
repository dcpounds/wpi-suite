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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Hashtable;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;
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
	protected int taskSnapID;
	private int db_id;
	protected List<TaskSnapshot> taskSnapList = new LinkedList<TaskSnapshot>();
	
	/**
	 * Constructor for DataLoggerModel.
	 */
	public DataLoggerModel() {
		taskSnapID = 0;
		db_id=this.hashCode();
	}
	
	
	/**
	 * Updates a data logger model based on more recent values pulled from the database
	 * @param updatedDataLogger DataLoggerModel
	 */
	public void copyFrom(DataLoggerModel updatedDataLogger)
	{
		taskSnapID = updatedDataLogger.getTaskSnapID();
		db_id = updatedDataLogger.getDb_id();
		taskSnapList = updatedDataLogger.getTaskSnapList();
		
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
		DataLoggerController.sendUpdateRequest(this);
	}
	
	
	/**
	 * Add the given snapshot to the task snapshot list
	
	 * @param tasksnapshot TaskSnapshot
	 */
	public void appendSnapshot(TaskSnapshot tasksnapshot)
	{
		taskSnapList.add(tasksnapshot);
	}
	

	
	
	
	
	/**
	 * Return the current task snapshot based on a task model
	
	
	 * @param taskModel TaskModel
	 * @return TaskSnapshot
	 */
	public TaskSnapshot returnCurrentSnapshot(TaskModel taskModel)
	{

		for (int i = taskSnapList.size(); i > 0; i=i - 1)

		{
			if (taskSnapList.get(i-1).getTaskID() == taskModel.getID())
			{
				return taskSnapList.get(i-1);
			}
		}
		return null;


	}
	
	/**
	 * Return the current task snapshot based on task ID value
	
	
	 * @param taskID int
	 * @return TaskSnapshot
	 */
	public TaskSnapshot returnCurrentSnapshot(int taskID)
	{
		for (int i = taskSnapList.size(); i>0; i=i-1)
		{
			if (taskSnapList.get(i-1).getTaskID() == taskID)
			{
				return taskSnapList.get(i-1);
			}
		}
		return null;


	}
	
	/**
	 * Return the oldest task snapshot based on task ID value
	
	
	 * @param taskID int
	 * @return TaskSnapshot
	 */
	public TaskSnapshot returnOldestSnapshot(int taskID)
	{
		TaskSnapshot buffer = null;
		for (int i = taskSnapList.size(); i>0; i=i-1)
		{
			if (taskSnapList.get(i-1).getTaskID() == taskID)
			{
				buffer = taskSnapList.get(i-1);
			}
		}
		return buffer;


	}
	
	
	
	
	
	
	
	
	
	
	
	public TaskSnapshot SnapshotWithID(long id)
	{
		
		for(int i=taskSnapList.size(); i>0; i--)
		{
			if (taskSnapList.get(i-1).getID() == id)
			{
				return taskSnapList.get(i-1);
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	/** Returns the most recent snapshot associated with the task snapshot passed as an argument
	 * @param taskSnapshot the snapshot to find the most recent previous match for
	 * @return TaskSnapshot
	 */
	public TaskSnapshot returnPreviousSnapshot(TaskSnapshot taskSnapshot) {
		//checks each task for an ID match, starting from the most recent tasks
		boolean firstTrip = true;
		
		for (int i=taskSnapList.size(); i > 0; i=i-1) 

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
	
	
	/** Returns the most recent snapshot associated with the task snapshot passed as an argument
	
	 * @param id int
	 * @return TaskSnapshot
	 */
	public TaskSnapshot returnPreviousSnapshot(int id) {
		//checks each task for an ID match, starting from the most recent tasks
		boolean firstTrip = true;
		for (int i=taskSnapList.size(); i>0; i=i-1) 
		{
			if (taskSnapList.get(i-1).getTaskID() == id)
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
	
	
	
	/** Returns the most recent snapshot associated with the task snapshot passed as an argument
	 * @param taskSnapshot the snapshot to find the most recent previous match for
	 * @return TaskSnapshot
	 */
	public TaskSnapshot returnNextSnapshot(TaskSnapshot taskSnapshot) {
		//checks each task for an ID match, starting from the most recent tasks
		TaskSnapshot buffer = null;
		for (int i=taskSnapList.size(); i>0; i=i-1) 
		{
			if (taskSnapList.get(i-1).getTaskID() == taskSnapshot.getTaskID())
			{
				if (taskSnapList.get(i-1).getID() == taskSnapshot.getID())
				{
					return buffer;
				}
				else
				{
					buffer = taskSnapList.get(i-1);
				}
			}
		}
		//executed only if the for loop reaches the end, indicating that only one snapshot has been taken of the task
		return null;
	}
	
	/** Returns the most recent snapshot associated with the task snapshot passed as an argument
	 * @param taskSnapshot the snapshot to find the most recent previous match for
	 * @return TaskSnapshot
	 */
	public TaskSnapshot returnNextSnapshot(long id) {
		//checks each task for an ID match, starting from the most recent tasks
		TaskSnapshot buffer = null;
		for (int i=taskSnapList.size(); i>0; i=i-1) 
		{
			if (taskSnapList.get(i-1).getTaskID() == id)
			{
				if (taskSnapList.get(i-1) == SnapshotWithID(id))
				{
					return buffer;
				}
				else
				{
					buffer = taskSnapList.get(i-1);
				}
			}
		}
		//executed only if the for loop reaches the end, indicating that only one snapshot has been taken of the task
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	/** Returns the estimated effort at a certain date. If the task did not exist before the specified date,
	 * the oldest estimated effort will be return. 
	
	
	 * @param task TaskModel
	 * @param date Date
	 * @return int
	 */
	public int estEffortAtDate(TaskModel task, Date date)
	{
		for (int i=taskSnapList.size(); i>0; i--)
		{
			if (taskSnapList.get(i-1).getTaskID() == task.getID())
			{
				
				//get the most recent snapshot created before the given date
				if (taskSnapList.get(i-1).getTimeStamp().before(date))
				{
					return (taskSnapList.get(i-1).getEstimatedEffort());
				}
			}
		}
		//only reached if it does not find a previous date. Returns the oldest valid snapshot of the task
		TaskSnapshot bufferSnap = task.getCurrentSnapshot();
		for (int i=taskSnapList.size(); i>0; i--)
		{
			if (taskSnapList.get(i-1).getTaskID() == task.getID())
			{
				bufferSnap = taskSnapList.get(i-1);
			}
		}
		return bufferSnap.getEstimatedEffort();
	}
	
	/**
	 * Method containsID.
	 * @param list List<TaskSnapshot>
	 * @param task TaskSnapshot
	 * @return boolean
	 */
	public boolean containsID(List<TaskSnapshot> list, TaskSnapshot task)
	{
		for (int i=list.size(); i>0; i--)
		{
			if (list.get(i-1).getID() == task.getTaskID())
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Filters the complete task list to only show unique tasks, and changes in estimated effort
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByEstimatedEffort ()
	{
		SnapshotSubList filteredList = new SnapshotSubList();

		for (int i=taskSnapList.size(); i>0; i--)
		{
			if (!filteredList.listContainsID(taskSnapList.get(i-1).getTaskID()))
			{
				filteredList.appendSnapshot(taskSnapList.get(i-1));
			}
			else if (filteredList.returnCurrentSnapshot(taskSnapList.get(i-1).getTaskID()).getEstimatedEffort() != 
					filteredList.returnPreviousSnapshot(taskSnapList.get(i-1).getTaskID()).getEstimatedEffort())
			{
				filteredList.appendSnapshot(taskSnapList.get(i-1));
			}
		}
		
		return filteredList;
	}
	
	
	/**
	 * Returns a sublist of the most recent snapshot for every task. Used for estimated effort on velocity
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByID ()
	{
		SnapshotSubList filteredList = new SnapshotSubList();

		for (int i=taskSnapList.size(); i>0; i--)
		{
			if (!filteredList.listContainsID(taskSnapList.get(i-1).getTaskID()))
			{
				filteredList.appendSnapshot(taskSnapList.get(i-1));
			}
		}
		
		return filteredList;
	}
	
	
	
	/**
	 * Returns a sublist of the most recent snapshot for every task. Used for estimated effort on velocity
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByID (SnapshotSubList list)
	{
		SnapshotSubList filteredList = new SnapshotSubList();

		for (int i=0; i<list.taskSnapList.size(); i++)
		{
			if (!filteredList.listContainsID(list.taskSnapList.get(i).getTaskID()))
			{
				filteredList.appendSnapshot(list.taskSnapList.get(i));
			}
		}
		
		return filteredList;
	}
	
	
	
	
	
	
	public SnapshotSubList snapAtDate (Date date)
	{
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i=taskSnapList.size(); i>0; i--)
		{
			if (!filteredList.listContainsID(taskSnapList.get(i-1).getTaskID()))
			{
				if (taskSnapList.get(i-1).getTimeStamp().before(date))
				{
					filteredList.appendSnapshot(taskSnapList.get(i-1));
				}
			}
		}
		return filteredList;
	}
	
	
	/**
	 * Filters out all entries on a sublist whose timestamp does not match the given date
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByDay (SnapshotSubList list, Date date)
	{
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i=list.taskSnapList.size(); i>0; i--)
		{
			if (list.taskSnapList.get(i-1).getTimeStamp().equals(date))
			{
				filteredList.appendSnapshot(list.taskSnapList.get(i-1));
			}
		}
		return filteredList;
	}
	
	/**
	 * Filters the complete task list to only show unique tasks, and changes in estimated effort
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByCategory ()
	{
		SnapshotSubList filteredList = new SnapshotSubList();

		//loop is reversed from others so that filtered list fills in the correct order
		for (int i=0; i<taskSnapList.size(); i++)
		{
			if (!filteredList.listContainsID(taskSnapList.get(i).getTaskID()))
			{
				filteredList.appendSnapshot(taskSnapList.get(i));
			}
			else if (filteredList.returnCurrentSnapshot(taskSnapList.get(i).getTaskID())!=null)
			

			{
				if (!filteredList.returnCurrentSnapshot(taskSnapList.get(i).getTaskID()).getCatColor().equals(
					taskSnapList.get(i).getCatColor()))
					{
						filteredList.appendSnapshot(taskSnapList.get(i));
					}
						

			}
		}
		
		return filteredList;
	}
	
	/**
	 * Filters a sublist over a date range, including only snapshots taken between the start and end date
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByDateRange(SnapshotSubList list, Date startdate, Date enddate)
	{
		String startDate = startdate.toString();
		String endDate = enddate.toString();
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i = list.taskSnapList.size(); i>0 ; i--)
		{
			String stampDate = list.taskSnapList.get(i-1).getTimeStamp().toString();
			if (!list.taskSnapList.get(i-1).getTimeStamp().after(enddate))
			{
				if ((!list.taskSnapList.get(i-1).getTimeStamp().before(startdate)) || 
					list.containsID(filteredList.taskSnapList, list.taskSnapList.get(i-1)))
				{
					filteredList.appendSnapshot(list.taskSnapList.get(i-1));
				}
			}
		}
		
		return filteredList;
	}
	
	
	
	/**
	 * Filters a sublist over a date range, including only snapshots taken between the start and end date
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByDateRange(Date startdate, Date enddate)
	{
		String startDate = startdate.toString();
		String endDate = enddate.toString();
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i =  taskSnapList.size(); i>0 ; i--)
		{
			String stampDate =  taskSnapList.get(i-1).getTimeStamp().toString();
			if (!taskSnapList.get(i-1).getTimeStamp().after(enddate))
			{
				if ((!taskSnapList.get(i-1).getTimeStamp().before(startdate))|| 
						containsID(filteredList.taskSnapList, taskSnapList.get(i-1)))
				{
					filteredList.appendSnapshot(taskSnapList.get(i-1));
				}
			}
		}
		
		return filteredList;
	}
	
	
	
	/**
	 * Filters a sublist over a date range, including only snapshots due between the start and end date
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByDueDate(SnapshotSubList list, Date startdate, Date enddate)
	{
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i = list.taskSnapList.size(); i>0 ; i--)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = null;
			try {
				date = dateFormat.parse(list.taskSnapList.get(i-1).getDueDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (!date.after(enddate))
			{
				if (!date.before(startdate) || 
					list.containsID(filteredList.taskSnapList, list.taskSnapList.get(i-1)))
				{
					filteredList.appendSnapshot(list.taskSnapList.get(i-1));
				}
			}
		}
		
		return filteredList;
	}
	
	
	
	/**
	 * Filters a sublist over a date range, including only snapshots due between the start and end date
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByDueDate(Date startdate, Date enddate)
	{
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i = taskSnapList.size(); i>0 ; i--)
		{
			
			
			String bufferString = taskSnapList.get(i-1).getDueDate();
			
			
			
			
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = null;
			try {
				date = dateFormat.parse(taskSnapList.get(i-1).getDueDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (!date.after(enddate))
			{
				if (!date.before(startdate) || 
					containsID(filteredList.taskSnapList, taskSnapList.get(i-1)))
				{
					filteredList.appendSnapshot(taskSnapList.get(i-1));
				}
			}
		}
		
		return filteredList;
	}
	
	
	
	
	
	public SnapshotSubList reverseList(SnapshotSubList list)
	{
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i = list.taskSnapList.size(); i>0 ; i--)
		{
			filteredList.appendSnapshot(list.taskSnapList.get(i-1));
		}
		return filteredList;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Filters a sublist, returning only the snapshots where a snapshot was moved into the complete stage
	 * @param list, the list to perform the operation on
	 * @param stageID, the stage id used as the complete stage
	 * @return SnapshotSubList
	 */
	public SnapshotSubList returnCompleteSnapshots(SnapshotSubList list, int stageID)
	{
		
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i = list.taskSnapList.size(); i>0 ; i--)
		{

				if (list.taskSnapList.get(i-1).getStageID() == stageID)
				{
					filteredList.appendSnapshot(list.taskSnapList.get(i-1));
				}
			
		}
		return filteredList;
		
	}
	
	
	
	/**
	 * Filters a sublist, returning only the snapshots where a snapshot was moved into the complete stage
	 * @param list, the list to perform the operation on
	 * @param stageID, the stage id used as the complete stage
	 * @return SnapshotSubList
	 */
	public SnapshotSubList returnCompleteSnapshots(int stageID)
	{
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i = taskSnapList.size(); i>0 ; i--)
		{
			if (returnPreviousSnapshot(taskSnapList.get(i-1))!=null)
			{
				if (taskSnapList.get(i-1).getStageID() == stageID && returnPreviousSnapshot(taskSnapList.get(i-1)).getStageID() != stageID)
				{
					filteredList.appendSnapshot(taskSnapList.get(i-1));
				}
			}
		}
		return filteredList;
		
	}
	
	
	
	
	
	/**
	 * Filters a sublist, returning only the snapshots not included in the complete stage
	 * @param list, the list to perform the operation on
	 * @param stageID, the stage id used as the complete stage
	 * @return SnapshotSubList
	 */
	public SnapshotSubList filterByStage(SnapshotSubList list, int stageID)
	{
		SnapshotSubList filteredList = new SnapshotSubList();
		for (int i = list.taskSnapList.size(); i>0 ; i--)
		{
			if (list.taskSnapList.get(i-1).getStageID() != stageID)
			{
				filteredList.appendSnapshot(list.taskSnapList.get(i-1));
			}
		}
		return filteredList;
		
	}
	
	
	
	
	public int AccumulateActualEffort(SnapshotSubList list)
	{
		int accumulator = 0;
		for (int i = list.taskSnapList.size(); i>0; i--)
		{
			accumulator = accumulator + list.taskSnapList.get(i-1).getActualEffort();
		}
		return accumulator;
	}
	
	public int AccumulateEstimatedEffort(SnapshotSubList list)
	{
		int accumulator = 0;
		for (int i = list.taskSnapList.size(); i>0; i--)
		{
			accumulator = accumulator + list.taskSnapList.get(i-1).getEstimatedEffort();
		}
		return accumulator;
	}
	
	
	
	

	
	
	
	/** Returns a string array of changes made between two snapshots
	
	
	 * @param snap1 TaskSnapshot
	 * @param snap2 TaskSnapshot
	 * @return List<String>
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
	
	public Hashtable<Integer, Double> exportEstmatedVelocity()
	{
		Hashtable<Integer, Double> output = new Hashtable<Integer, Double>();
		int weeks = 0;
		long startDateMS = WorkflowController.getWorkflowModel().getStartDate().getTime();
		while (true)
		{
			if (startDateMS+604800000 < WorkflowController.getWorkflowModel().getEndDate().getTime())
			{
				weeks++;
				startDateMS = startDateMS+604800000;	//number of milliseconds in a week
			}
			else
			{
				weeks++;
				break;
			}
		}
		SnapshotSubList filteredList;
		for (int i = 0; i<weeks; i++)
		{
			filteredList = filterByDueDate(new Date(WorkflowController.getWorkflowModel().getStartDate().getTime()+604800000*i),
											new Date(WorkflowController.getWorkflowModel().getStartDate().getTime()+604800000+604800000*i));
			filteredList = filterByID(filteredList);
			double estimatedEffort = AccumulateEstimatedEffort(filteredList);
			output.put(i+1, estimatedEffort);
		}
		return output;
	}
	
	public Hashtable<Integer, Double> exportDailyEffortStamps()
	{
		Hashtable<Integer, Double> output = new Hashtable<Integer, Double>();
		int days = 0;
		long startDateMS = WorkflowController.getWorkflowModel().getStartDate().getTime();
		while (true)
		{
			if (startDateMS+86400000 < WorkflowController.getWorkflowModel().getOverrideDate().getTime())
			{
				days++;
				startDateMS = startDateMS+86400000;	//number of milliseconds in a week
			}
			else
			{
				days++;
				break;
			}
		}
		SnapshotSubList filteredList;
		for (int i = 0; i<days; i++)
		{
			filteredList = snapAtDate(new Date(WorkflowController.getWorkflowModel().getStartDate().getTime()+86400000L*i));
			filteredList = filterByStage(filteredList, WorkflowController.getWorkflowModel().getCompleteStageID());

			double estimatedEffort = AccumulateEstimatedEffort(filteredList);
			output.put(i, estimatedEffort);
		}
		return output;
	}
	
	
	
	
	public Hashtable<Integer, Double> exportActualVelocity()
	{
		Hashtable<Integer, Double> output = new Hashtable<Integer, Double>();
		int weeks = 0;
		long startDateMS = WorkflowController.getWorkflowModel().getStartDate().getTime();
		while (true)
		{
			if (startDateMS+604800000 < WorkflowController.getWorkflowModel().getEndDate().getTime())
			{
				weeks++;
				startDateMS = startDateMS+604800000;	//number of milliseconds in a week
			}
			else
			{
				weeks++;
				break;
			}
		}
		SnapshotSubList filteredList;
		for (int i = 0; i<weeks; i++)
		{
			filteredList = filterByDateRange(new Date(WorkflowController.getWorkflowModel().getStartDate().getTime()+604800000*i),
											new Date(WorkflowController.getWorkflowModel().getStartDate().getTime()+604800000+604800000*i));
			filteredList = returnCompleteSnapshots(filteredList, WorkflowController.getWorkflowModel().getCompleteStageID());
			double actualEffort = AccumulateActualEffort(filteredList);
			output.put(i+1, actualEffort);
		}
		return output;
	}
	
	
	
	
	
	/**
	 * Export all categories as a hashtable
	
	 * @return Hashtable
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
		
		
		SnapshotSubList filteredList = filterByCategory();
		filteredList = filterByDateRange(filteredList, WorkflowController.getWorkflowModel().getStartDate(), 
										WorkflowController.getWorkflowModel().getEndDate());
		if (filteredList.taskSnapList.size()>0)
		{
			for (int i = filteredList.taskSnapList.size(); i>0; i=i-1)
			{
	
				switch (filteredList.taskSnapList.get(i-1).getCatColorString()) {
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
	
	 * @return String
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
	
	
	 * @param dataLoggerList DataLoggerModel[]
	 * @return a string in JSON representing the list of tasks */
	public static String toJSON(DataLoggerModel[] dataLoggerList) {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(dataLoggerList, TaskModel.class);
		return json;
	}
	
	/**
	 * @param json - the json obejct to convert back to a TaskModel
	
	 * @return DataLoggerModel
	 */
	public static DataLoggerModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, DataLoggerModel.class);
	}
	
	
	   /**
	 * @param json - the json to deserialize
	
	 * @return - the list of deserialized tasks */
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
