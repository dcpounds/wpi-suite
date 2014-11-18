package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractListModel;

/**
 * Model for respresenting a stage that holds individual tasks
 *
 */
public class StageModel extends AbstractListModel {
	
	private static final long serialVersionUID = 7869886695945683209L;
	private String title;
	private ArrayList<TaskModel> taskList;

	/**
	 * constructs a new stage
	 * 
	 * @param title - title of the stage
	 * @param taskList - list of tasks to be added
	 */
	public StageModel(String title, ArrayList<TaskModel> taskList) {
		this.setTitle(title);
		this.setTaskList(taskList);
	}
	
	public StageModel(String title) {
		this.setTitle(title);
		this.setTaskList( new ArrayList<TaskModel>() );
	}
	
	/**
     * Removes all tasks from this card
     * NOTE: One cannot simply construct a new instance of
     * the model, because other classes in this module have
     * references to it. Hence, we manually remove each work flow
     * from the model.
     */
    public void emptyModel() {
        int oldSize = getSize();
        Iterator<TaskModel> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
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
	
	/**
	 * @param task - adds a task to the card
	 */
	public void addTaskToCard(TaskModel task) {
		taskList.add(task);
	}

	/**
	 * @param index - the spot in the list of tasks to retrieve
	 * @return the task at the given index
	 */
	@Override
	public TaskModel getElementAt(int index) {
		TaskModel task = taskList.get(index);
		return task;
	}
	

}
