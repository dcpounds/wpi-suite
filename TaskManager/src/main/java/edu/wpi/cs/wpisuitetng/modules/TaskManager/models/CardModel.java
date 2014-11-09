package edu.wpi.cs.wpisuitetng.modules.TaskManager.models;

import java.util.ArrayList;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

public class CardModel extends AbstractModel {
	
	private String title;
	private ArrayList<TaskModel> taskList;

	public CardModel(String title, ArrayList<TaskModel> taskList) {
		this.setTitle(title);
		this.setTaskList(taskList);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
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
	

}
