package edu.wpi.cs.wpisuitetng.modules.taskmanager.reports;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;


/**
 * @author Joe
 * This class is used to take a snapshot of a task's state at a particular time. 
 * Comparing two snapshots are used to drive changes in the activity list
 * These snapshots are compiled to form the program's statistical output
 */
public class TaskSnapshot {
	private int estimatedEffort, actualEffort;
	private long id;	//starts at zero, increments in order of creation
	private int taskID;	//only variable which isn't a 1:1 correspondance to TaskModel. ID is unique to the snapshot
	private String title, description, creatorName;
	private ArrayList<String> usersAssignedTo;
	private Date creationDate;
	private String dueDate;
	private int stageID;
	private boolean isArchived;
	private Color color;
	private Color CatColor;
	private ActivityListModel activities;
	/** The default constructor for a Task Snapshot **/
	public TaskSnapshot(TaskModel task, int id)
	{
		this.id = DataLoggerController.getDataModel().getTaskSnapID();
		DataLoggerController.getDataModel().incrementTaskSnapID();
		this.taskID = task.getID();
		this.estimatedEffort = task.getEstimatedEffort();
		this.actualEffort = task.getActualEffort();
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.creatorName = task.getCreatorName();
		this.creationDate = task.getCreationDate();
		this.usersAssignedTo = task.getUsersAssignedTo();
		this.dueDate = task.getDueDate();
		this.stageID = task.getStageID();
		this.isArchived = task.getIsArchived();
		this.activities = task.getActivities();
		this.color = task.getColor();
		this.CatColor = task.getCatColor();
	}
	public int getTaskID()
	{
		return taskID;
	}

}
