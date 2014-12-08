package edu.wpi.cs.wpisuitetng.modules.taskmanager.reports;

import java.util.ArrayList;

/**
 * @author Joe
 * This class is used to accumulate and report data on the use of the program over time, which
 * cannot be derived from simply reading the current list of taskModels
 */

public class DataLoggerModel {
	private int taskSnapID;
	private ArrayList<TaskSnapshot> taskSnapList;
	
	public DataLoggerModel() {
		taskSnapID = 0;
	}
	
	public void incrementTaskSnapID(){
		taskSnapID++;
	}
	
	public int getTaskSnapID() {
		return taskSnapID;
	}
	
	/** Returns the most recent 
	 * @param updatedTask the stage to copy from
	 */
	public TaskSnapshot returnPreviousSnapshot(TaskSnapshot taskSnapshot) {
		//checks each task for an ID match, starting from the most recent tasks
		for (int i=taskSnapID; i==0; i--) 
		{
			if (taskSnapList.get(i).getTaskID() == taskSnapshot.getTaskID())
			{
				return taskSnapList.get(i);
			}
		}
		//executed only if the for loop reaches the end, indicating that only one snapshot has been taken of the task
		return null;
	}
	
	
	

}
