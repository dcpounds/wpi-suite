package edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.IDisplayModel;

/** Model to represent a task **/
public class TaskModel extends AbstractModel implements IDisplayModel {
	private int estimatedEffort, actualEffort;
	private int id;
	private String title, description;
	private String creatorName;
	private ArrayList<String> usersAssignedTo;
	private Date creationDate;
	private String dueDate;
	private int stageID;
	private boolean isExpanded;
	private boolean editState;
	private boolean activitiesOpened;
	private boolean isArchived;
	private ActivityListModel activities;
	
	/** The default constructor for a Task **/
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
		this.isExpanded = false;
		this.isArchived = false;
		this.activities = new ActivityListModel();
	}
	
	/** Copies the contents of updatedStage into this one
	 * @param updatedTask the stage to copy from
	 */
	public void copyFrom(TaskModel updatedTask) {
		id = updatedTask.getID();
		estimatedEffort = updatedTask.getEstimatedEffort();
		actualEffort = updatedTask.getActualEffort();
		title = updatedTask.getTitle();
		creatorName = updatedTask.getCreatorName();
		description = updatedTask.getDescription();
		creationDate = updatedTask.getCreationDate();
		usersAssignedTo = updatedTask.getUsersAssignedTo();
		dueDate = updatedTask.getDueDate();	
		isExpanded = updatedTask.getIsExpanded();
		stageID = updatedTask.getStageID();
		isArchived = updatedTask.getIsArchived();
		activities = updatedTask.getActivities();
	}
	
	
	/**
	 * @return the ID of this task
	 */
	public int getID() {
		return id;
	}
	
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
		this.usersAssignedTo = usernameList;
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
	
	
	public boolean getIsExpanded(){
		return isExpanded;
	}
	
	
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
	 * @return- get whether the tab is being edited
	 */
	public boolean getEditState() {
		return this.editState;	
	}
	
	
	/**
	 * @param creator - set the user that created this task
	 */
	public void setEditState(boolean editState) {
		this.editState = editState;
	}
	
	/**
	 * @param archiveStatus - set the task to archived (true) or active (false)
	 */
	public void setIsArchived(boolean archiveStatus){
		this.isArchived = archiveStatus;
	}
	
	/**
	 * @return
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
	
	public void setActivities(ActivityListModel newActivities) {
		activities = newActivities;
	}

	/**
	 * @return the areActivitiesOpened
	 */
	public boolean isActivitiesOpened() {
		return activitiesOpened;
	}

	/**
	 * @param activitiesOpened the areActivitiesOpened to set
	 */
	public void setActivitiesOpened(boolean activitiesOpened) {
		this.activitiesOpened = activitiesOpened;
	}

	/**
	 * @param activity
	 */
	public void addActivity(ActivityModel activity){
		activities.addActivity(activity);
	}
	
}
