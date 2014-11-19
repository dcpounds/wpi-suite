package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/** Model to represent a task **/
public class TaskModel extends AbstractModel implements IDisplayModel {
	private int id, estimatedEffort, actualEffort;
	private String title, description;
	private String creatorName;
	private ArrayList<String> usersAssignedTo;
	private Date creationDate;
	private String dueDate;
	private String status;
	private boolean isExpanded;
	private boolean editState;
	private int editIndex;
	
	/** The default constructor for a Task **/
	public TaskModel(){
		id = -1;
		estimatedEffort = 0;
		actualEffort = 0;
		title = description = "";
		creatorName = "";
		creationDate = new Date();
		usersAssignedTo = new ArrayList<String>();
		dueDate = new String();	
		status = "";
		this.isExpanded = false;
	}
	
	/** The default constructor for a Task **/
	public TaskModel(String title, String description){
		id = -1;
		estimatedEffort = 0;
		actualEffort = 0;
		this.title = title;
		this.creatorName = "";
		this.description = description;
		creationDate = new Date();
		usersAssignedTo = new ArrayList<String>();
		dueDate = new String();	
		this.isExpanded = false;
	}
	
	public TaskModel(TaskModel updatedStage) {
		this.id = updatedStage.id;
		this.estimatedEffort = updatedStage.estimatedEffort;
		this.actualEffort = updatedStage.actualEffort;
		this.title = updatedStage.title;
		this.creatorName = updatedStage.getCreatorName();
		this.description = updatedStage.description;
		creationDate = updatedStage.creationDate;
		usersAssignedTo = updatedStage.usersAssignedTo;
		dueDate = updatedStage.dueDate;	
		this.isExpanded = updatedStage.isExpanded;
	}
	
	/**
	 * @param id - the id of the task
	 * @param title - the title of the task
	 * @param description - the description of the task
	 * @param creator - the creator of the task (User)
	 * @param usersAssignedTo - the List users this task is assigned to
	 */
	public TaskModel(int id, int estimatedEffort, int actualEffort, String title, String description, 
			String creatorName, ArrayList<String> usersAssignedTo) {
		this.id = id;
		this.estimatedEffort = estimatedEffort;
		this.actualEffort = actualEffort;
		this.title = title;
		this.description = description;
		this.creatorName = creatorName;
		this.usersAssignedTo = usersAssignedTo;
		this.isExpanded = false;
	}
	
	public TaskModel(String title, String description, String dueDate){
		id = -1;
		estimatedEffort = 0;
		actualEffort = 0;
		this.title = title;;
		this.creatorName = "";
		this.description = description;
		creationDate = new Date();
		usersAssignedTo = new ArrayList<String>();
		this.dueDate = dueDate;	
		this.isExpanded = false;
	}
	/**
	 * @return the ID of this task
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id - set the id of the task
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the status of this task
	 */
	public String getStatus(){
		return status;
	}
	
	/**
	 * @param status - set the status of this task
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return- get whether the tab is being editted
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

	/* (non-Javadoc)
	 * returns true if this task id matches the one provided
	 */
	@Override
	public Boolean identify(Object o) {
		Boolean returnValue = false;
		if(o instanceof TaskModel && id == ((TaskModel) o).getId()) {
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
        
        return this.id == other.getId();
	}

	public static TaskModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, TaskModel.class);
	}

	public void setEditIndex(int editIndex) {
		this.editIndex = editIndex;
	}
}
