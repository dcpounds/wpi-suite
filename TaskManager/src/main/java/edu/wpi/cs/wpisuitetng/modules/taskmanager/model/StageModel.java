package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import com.google.gson.Gson;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * Model for respresenting a stage that holds individual tasks
 *
 */
public class StageModel extends AbstractModel {
	
	private static final long serialVersionUID = 7869886695945683209L;
	private String title;
	private ArrayList<TaskModel> taskList;

	public StageModel(StageModel updatedStage) {
		this(updatedStage.getTitle(), updatedStage.getTaskList());
	}
	
	/**
	 * constructs a new stage
	 * 
	 * @param title - title of the stage
	 * @param taskList - list of tasks to be added
	 */
	public StageModel(String title, ArrayList<TaskModel> taskList) {
		this.title = title;
		this.taskList = taskList;
	}
	
	/**
	 * constructs a new empty stage
	 * 
	 * @param title - title of the stage
	 */
	public StageModel(String title) {
		this.title = title;
		taskList = new ArrayList<TaskModel>();
	}
    
    /**
     * @param newTask - the task to add to the list of cards
     */
    public void addTask(TaskModel newTask) {
    	this.taskList.add(newTask);
    }
    
    /**
     * @param newTasks - the list of tasks to add to the card
     */
    public void addTasks(TaskModel[] newTasks) {
    	for (int i = 0; i < newTasks.length; i++) {
    		this.taskList.add( newTasks[i] );
    	}
    }
    
    /**
     * @param newTask - the task to add to the list of cards
     */
    public void removeTask(TaskModel task) {
    	this.taskList.remove(task);
    }
    
    /**
     * @param newTasks - the list of tasks to add to the card
     */
    public void removeTasks(TaskModel[] tasks) {
    	for (int i = 0; i < tasks.length; i++) {
    		this.taskList.remove( tasks[i] );
    	}
    }

	/**
	 * @return the title of the card
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title - set the title of the string
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the size of the list of tasks
	 */
	public int getSize() {
		return taskList.size();
	}

	/**
	 * @return a list of taskModels that are contained in this card
	 */
	public ArrayList<TaskModel> getTaskList() {
		return taskList;
	}

	/**
	 * @param taskList - sets the list of tasks that are contained in this card
	 */
	public void setTaskList(ArrayList<TaskModel> taskList) {
		this.taskList = taskList;
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
        
        StageModel other = (StageModel) obj;
        
        return this.title == other.getTitle();
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
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, StageModel.class);
		return json;
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param json
	 * @return - the JSON version of the object
	 */
	public static StageModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, StageModel.class);
	}
	
	
    /**
     * @param json
     * @return - an array of JSON stageModels
     */
    public static StageModel[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, StageModel[].class);
    }
	

}
