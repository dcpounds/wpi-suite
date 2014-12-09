package edu.wpi.cs.wpisuitetng.modules.taskmanager.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.*;


/**
 * @author Joe
 * This class is used to accumulate and report data on the use of the program over time, which
 * cannot be derived from simply reading the current list of taskModels
 */

public class DataLoggerModel {
	private int taskSnapID;
	private List<TaskSnapshot> taskSnapList = new LinkedList<TaskSnapshot>();
	
	public DataLoggerModel() {
		taskSnapID = 0;
	}
	
	public void incrementTaskSnapID(){
		taskSnapID++;
	}
	
	public int getTaskSnapID() {
		return taskSnapID;
	}
	
	public void addSnapshot(TaskModel taskmodel)
	{
		taskSnapList.add(new TaskSnapshot(taskmodel, taskSnapID));
	}
	
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
			changelog.add("Changed estimated effort from " + snap1.getActualEffort() + " to " + snap2.getActualEffort()
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

		return changelog;
		
	}
	
	
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
	
	

}
