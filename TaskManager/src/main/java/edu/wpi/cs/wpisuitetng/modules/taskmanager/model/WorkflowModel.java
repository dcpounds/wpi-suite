package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.CoreUserController;

/**
 * the main model representing the workflow. 
 * contains all the stages where tasks are moved around in
 *
 */
public class WorkflowModel extends AbstractModel {
	final private String name;
	public ArrayList<StageModel> stageList;
	private static ArrayList<User> userList; 

	/**
	 * construct the main workflow based off a given list of stages
	 * 
	 * @param name - the name of the workflow (usually "main")
	 * @param stageList
	 */
	public WorkflowModel(String name, ArrayList<StageModel> stageList){
		this.name = name;
		this.stageList = stageList;
		this.userList = new ArrayList<User>();
		addBaseStages();
	}
	
	/**
	 * 
	 * Construct the main workflow
	 * 
	 * @param name - the name of the workflow (usually "main")
	 */
	public WorkflowModel(String name){
		this.name = name;
		this.stageList = new ArrayList<StageModel>();
		addBaseStages();
	}
	
	/**
	 * @return the string name of the workflow
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @return a list of stages in the workflow
	 */
	public ArrayList<StageModel> getStageList() {
		return stageList;
	}
	
	/**
	 * @param stageList - set the list of stages to add to the workflow
	 */
	public void setStageList(ArrayList<StageModel> stageList) {
		this.stageList = stageList;
	}
	
	/**
	 * @param stage - a StageModel to add to the workflow
	 * @return the updated list of stages in the workflow
	 */
	public ArrayList<StageModel> addStage(StageModel stage) {
		stageList.add(stage);
		return stageList;
	}
	
	/**
	 * @param stage - a StageModel to add to the workflow
	 * @return the updated list of stages in the workflow
	 */
	public ArrayList<StageModel> removeStage(StageModel stage) {
		stageList.remove(stage);
		return stageList;
	}
	
	/**
	 * get a stage model by its name
	 * 
	 * @param name
	 * @return
	 */
	public StageModel getStageByName(String name){
		for(StageModel stageModel : stageList){
			if(stageModel.getTitle() == name){
				return stageModel;
			}
		}
		return null;	
	}
	
	/**
	 * add a task to a specific stage
	 * 
	 * @param stageName - name of the stage
	 * @param task - model of task to be added
	 */
	public void addTask(String stageName, TaskModel task) {
		StageModel stage = getStageByName(stageName);
		for(StageModel stageModel : stageList){
			if(stageModel == stage){
				task.setId(getNextTaskId());
				stageModel.addTask(task);
				return;
			}
		}
	}
	
	/**
	 * @param stage - a TaskModel to add to the workflow
	 */
	public void removeTask(TaskModel task) {
		StageModel stageToBeUpdated = null;
		TaskModel taskToBeRemoved = null;
		for(StageModel stageModel : stageList){
			for(TaskModel taskModel : stageModel.getTaskList()){
				if(taskModel == task){
					stageToBeUpdated = stageModel;
					taskToBeRemoved = taskModel;
				}
			}
		}
		if(stageToBeUpdated != null){
			stageToBeUpdated.removeTask(taskToBeRemoved);
		}
	}
	
	private int getNextTaskId() {
		int highestId = 0;
		for(StageModel stageModel : stageList){
			for(TaskModel taskModel : stageModel.getTaskList()){
				if(taskModel.getId() > highestId){
					highestId = taskModel.getId();
				}
			}
		}
		return highestId + 1;
	}
	
	
	/**
	 * @return the list of users that can be assigned to tasks in this workflow
	 */
	public User[] getUserList() {
		if(userList == null)
			return new User[]{};
		return userList.toArray(new User[userList.size()]);
	}
	
	public void setUserList(User[] userList){
		 WorkflowModel.userList = new ArrayList<User>(Arrays.asList(userList));
	}
	
	
	/**
	 * adds the default list of stages to the main workflow
	 */
	private void addBaseStages(){
		stageList.add(new StageModel("New", new ArrayList<TaskModel>(), false));
		stageList.add(new StageModel("In Progress", new ArrayList<TaskModel>(), false));
		stageList.add(new StageModel("Scheduled", new ArrayList<TaskModel>(), false));
		stageList.add(new StageModel("Completed", new ArrayList<TaskModel>(), false));
	}
	
	
	/********************Database Helpers**************************
	 * The rest are functions for the main model to interact with the database,
	 * They should generally be left untouched unless messing with the database stuff
	 */
	@Override
	public void save() {
	}

	@Override
	public void delete() {
	}

	@Override
	public String toJson() {
        return new Gson().toJson(this, WorkflowModel.class);
	}
	

    public static WorkflowModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkflowModel.class);
    }

    public static WorkflowModel[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkflowModel[].class);
    }
    
	@Override
	public Boolean identify(Object o) {
        return null;
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
        
        WorkflowModel other = (WorkflowModel) obj;
        
        return this.name == other.getName();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.name;
	}
	
	public void copyFrom(WorkflowModel other){
		this.stageList = other.stageList;
	}

}
