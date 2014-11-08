package edu.wpi.cs.wpisuitetng.modules.TaskManager.models;
import java.util.Date;
import com.google.gson.Gson;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/** Model to represent a task **/
public class Task extends AbstractModel {
	private int id, estimatedEffort, actualEffort;
	private String title, description;
	private User creator, assignee;
	private Date creationDate, dueDate;
	
	/** The default constructor for a Task **/
	public Task(){
		id = -1;
		estimatedEffort = 0;
		actualEffort = 0;
		title = description = "";
		creator = new User("", "", "", -1);
		creationDate = new Date();
		dueDate = new Date();	
	}
	
	/**
	 * @param id - the id of the task
	 * @param title - the title of the task
	 * @param description - the description of the task
	 * @param creator - the creator of the task (User)
	 */
	public Task(int id, int estimatedEffort, int actualEffort, String title, String description, User creator) {
		this.id = id;
		this.estimatedEffort = estimatedEffort;
		this.actualEffort = actualEffort;
		this.title = title;
		this.description = description;
		this.creator = creator;
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
	public User getCreator() {
		return creator;
	}
	
	/**
	 * @param creator - set the user that created this task
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	/**
	 * @return the user that created the task
	 */
	public User getAssignee(){
		return assignee;
	}
	
	/**
	 * @param assignee - set the user assigned to this task
	 */
	public void setAssignee(User assignee) {
		this.assignee = assignee;
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
	public Date getDueDate() {
		return dueDate;
	}
	
	/**
	 * @param dueDate - set the date that this task is due
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Converts the task to a JSON string
	 * @return a JSON string representation of the task 
	 */
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, Task.class);
		return json;
	}
	
	/**
	 * Converts the given list of Defects to a JSON string
	 * @param dlist a list of Defects
	 * @return a string in JSON representing the list of Defects
	 */
	public static String toJSON(Task[] taskList) {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(taskList, Task.class);
		return json;
	}

	/* (non-Javadoc)
	 * returns true if this task id matches the one provided
	 */
	@Override
	public Boolean identify(Object o) {
		Boolean returnValue = false;
		if(o instanceof Task && id == ((Task) o).getId()) {
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
}
