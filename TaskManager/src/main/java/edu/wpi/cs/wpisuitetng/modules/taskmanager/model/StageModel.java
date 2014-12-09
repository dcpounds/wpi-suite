/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.Comparator;
import java.util.HashMap;
import com.google.gson.Gson;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

/**
 * Model for representing a stage that holds individual tasks
 */
public class StageModel extends AbstractModel implements Comparable<StageModel>, IDisplayModel {
	
	private static final long serialVersionUID = 7869886695945683209L;
	private String title;
	private boolean closable;
	private boolean isArchived;
	private int id;
	private int index;
	private HashMap<Integer, TaskModel> taskModelList;
	
	/**
	 * constructs a new stage
	 * @param title - title of the stage
	 * @param taskList - list of tasks to be added
	 */
	public StageModel(String title, int index) {
		this.title = title;
		this.closable = WorkflowController.getWorkflowModel().getStageModelList().size() <= 1 ? false : true;
		this.id = this.hashCode();
		this.taskModelList = new HashMap<Integer, TaskModel>();
		this.index = index;
	}
	
	
	/**
	 * Blank stage constructor since the 
	 * database needs a class to identify the type to fetch
	 */
	public StageModel() {
		this.taskModelList = new HashMap<Integer, TaskModel>();
		this.closable = WorkflowController.getWorkflowModel().getStageModelList().size() <= 1 ? false : true;
		this.index = -1;
	}

	
	/**
	 * @param updatedStage - copies the 
	 */
	public void copyFrom(StageModel updatedStage) {
		this.title = updatedStage.getTitle();
		this.closable = updatedStage.getClosable();
		this.id = updatedStage.getID();
		this.taskModelList = updatedStage.taskModelList;
		this.isArchived = updatedStage.isArchived;
		this.index = updatedStage.getIndex();
	}
	
	/**
	 * @param task - the task to add to the list of tasks
	 * @return - the list of tasks
	 */
	public HashMap<Integer,TaskModel> addUpdateTaskModel(TaskModel task){
		if( taskModelList.get(task.getID()) != null ){
			taskModelList.put(task.getID(), task);
			StageController.sendUpdateRequest(this);
			return this.getTaskModelList();
		}
			
		taskModelList.put(task.getID(),task);
		StageController.sendUpdateRequest(this);
		StageView stageView = TabController.getTabView().getWorkflowView().getStageViewByID(task.getStageID());
		TaskView taskView = new TaskView(task, stageView);
		stageView.addTaskView(taskView);
		return taskModelList;
	}
	
	/**
	 * @param index - the position in the taskList to add the task to
	 * @param task - the task to add to the list of taskModels
	 * @return the updated list of taskModels
	 */
	public HashMap<Integer,TaskModel> addTaskModel(TaskModel task){
		taskModelList.put(task.getID(), task);
		return taskModelList;
	}
	
	
	/**
	 * @param task - the task to remove from the list of tasks
	 * @return - the updated list of tasks
	 */
	public HashMap<Integer,TaskModel> removeTask(TaskModel task){
		System.out.println("Trying to remove task with from " + this.getTitle());
		taskModelList.remove(task.getID());
		return taskModelList;
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
	 * @return - the ID of this object
	 */
	public int getID(){
		return this.id;
	}
	
	
	/**
	 * @return - the list of taskModels in this stage
	 */
	public HashMap<Integer,TaskModel> getTaskModelList(){
		return this.taskModelList;
	}
	
	/**
	 * @param id - the id to set the stage to
	 */
	public void setID(int id){
		this.id = id;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
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
        
        return this.title.equals(other.getTitle());
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

    
	/**
	 * @return whether or not the stage can be closed
	 */
	public boolean getClosable() {
		return closable;
	}
	
	
	/**
	 * @param closable - set whether or not this stage may be deleted
	 */
	public void setClosable(boolean closable){
		this.closable = closable;
	}


	/**
	 * @param archived - set the stage as archived or not
	 */
	public void setIsArchived(boolean archived) {
		this.isArchived = archived;
		
	}
	
	/**
	 * @return - whether or not the stage is in an archived state
	 */
	public boolean getIsArchived(){
		return this.isArchived;
	}


	@Override
	public int compareTo(StageModel other) {
		return index < other.index ? -1 : index > other.index ? 1 : 0;
	}
}

