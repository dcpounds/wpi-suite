package edu.wpi.cs.wpisuitetng.modules.taskmanager.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


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
	
	/** Returns the most recent snapshot associated with the task passed as an argument
	 * @param taskSnapshot the snapshot to find the most recent previous match for
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
	
	/** Returns a string array of changes made between two snapshots
	 * @param snap1, the first (older) snapshot
	 * @param snap2, the second (newer) snapshot
	 */
	public int trackChanges(TaskSnapshot snap1, TaskSnapshot snap2) {
		int changes = 0;
		
		List<String> changelog = new LinkedList<String>();
		

				
		if (snap1.getActualEffort()!=snap2.getActualEffort())
		{
			changelog.add("Changed actual effort from " + snap1.getActualEffort() + " to " + snap2.getActualEffort()
							+ ".");
		}
		if (!(snap1.getCatColor().equals(snap2.getCatColor())))
		{
			changelog.add("Changed the category from " + snap1.colorToString(snap1.getCatColor()) + " to " + 
							snap2.colorToString(snap2.getCatColor()) + ".");
		}
		if (!(snap1.getColor().equals(snap2.getColor())))
		{
			changes++;
		}
		if (!(snap1.getCreationDate().equals(snap2.getCreationDate())))
		{
			changes++;
		}
		if (!(snap1.getDescription().equals(snap2.getDescription())))
		{
			changes++;
		}
		if (!(snap1.getDueDate().equals(snap2.getDueDate())))
		{
			
			changes++;
		}
		if (snap1.getEstimatedEffort()!=snap2.getEstimatedEffort())
		{
			changes++;
		}
		if(snap1.getStageID()!=snap2.getStageID())
		{
			changes++;
		}
		if(!(snap1.getTitle().equals(snap2.getTitle())))
		{
			changes++;
		}
		
		

		return changes;
		
	}
	
	
	

}
