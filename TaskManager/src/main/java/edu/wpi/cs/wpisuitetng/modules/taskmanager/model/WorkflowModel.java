package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.CoreUserController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;

/**
 * the main model representing the workflow. 
 * contains all the stages where tasks are moved around in
 *
 */
public class WorkflowModel extends AbstractModel {
	final private String name;
	private ArrayList<StageModel> stageModelList;
	private static ArrayList<User> userList; 

	/**
	 * construct the main workflow based off a given list of stages
	 * @param name - the name of the workflow (usually "main")
	 * @param stageList
	 */
	public WorkflowModel(String name, ArrayList<StageModel> stageList){
		this.name = name;
		this.stageModelList = stageList;
		this.userList = new ArrayList<User>();
		addBaseStages();
	}
	
	
	/**
	 * Construct the main workflow
	 * @param name - the name of the workflow (usually "main")
	 */
	public WorkflowModel(String name){
		this.name = name;
		this.stageModelList = new ArrayList<StageModel>();
		addBaseStages();
	}
	
	
	
	/**
	 * Moves a taskModel from one stage to another and puts it at the given index within the stage
	 * @param task - the task to move
	 * @param stageIndex - the index of the stage to move to
	 * @param taskIndex - the index within the stage to put the task into
	 * @throws IndexOutOfBoundsException
	 */
	public void moveTask(TaskModel task, int toStageIndex, int toTaskIndex) throws IndexOutOfBoundsException{
		if(toStageIndex < 0 || toStageIndex > stageModelList.size() ){
			System.out.println("You tried to add a task to an invalid stage position");
			throw new IndexOutOfBoundsException();
		}
		StageModel oldStage = this.getStageModelByID( task.getStageID() );
		StageModel newStage = stageModelList.get(toStageIndex);
		
		
		if(toTaskIndex < 0 || toTaskIndex > newStage.getTaskModelList().size() ){
			System.out.println("You tried to add a task to an invalid position in the stage");
			throw new IndexOutOfBoundsException();
		}
		oldStage.removeTask(task);
		newStage.addTaskModelAtIndex(toTaskIndex, task);
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
	public ArrayList<StageModel> getStageModelList() {
		return stageModelList;
	}
	
	
	/**
	 * @param stage - a StageModel to add to the workflow
	 * @return the updated list of stages in the workflow
	 */
	public ArrayList<StageModel> addStage(StageModel stage) {
		stageModelList.add(stage);
		return stageModelList;
	}
	
	/**
	 * @param index - the index to put the stage into
	 * @param stage - the stage to add
	 * @return
	 */
	public ArrayList<StageModel> addStage(int index, StageModel stage){
		stageModelList.add(index, stage);
		return stageModelList;
	}
	
	
	/**
	 * @param stage - a StageModel to add to the workflow
	 * @return the updated list of stages in the workflow
	 */
	public ArrayList<StageModel> removeStageModel(StageModel stage) {
		stageModelList.remove(stage);
		return stageModelList;
	}
	
	/**
	 * @return the list of users that can be assigned to tasks in this workflow
	 */
	public User[] getUserList() {
		if(userList == null)
			return new User[]{};
		return userList.toArray(new User[userList.size()]);
	}
	
	
	/**
	 * @param userList - the list of assignable users to set within the workflow
	 */
	public void setUserList(User[] userList){
		 WorkflowModel.userList = new ArrayList<User>(Arrays.asList(userList));
	}
	
	
	/**
	 * adds the default list of stages to the main workflow
	 */
	private void addBaseStages(){
		stageModelList.add(new StageModel("New", true));
		stageModelList.add(new StageModel("Scheduled", true));
		stageModelList.add(new StageModel("In Progress", true));
		stageModelList.add(new StageModel("Completed", true));
	}
	
	
	/**
	 * @param id - id of the task to look for
	 * @return the taskModel if successful, null otherwise
	 */
	public TaskModel getTaskModelByID(int id){
		for( StageModel stageModel : stageModelList ){
			for( TaskModel task : stageModel.getTaskModelList() ){
				if(task.getID() == id)
					return task;
			}
		}
		return null;
	}
	
	
	/**
	 * @param id - id of the stage to look for
	 * @return - the stageModel if successful, nul otherwise
	 */
	public StageModel getStageModelByID(int id){
		for( StageModel stage : stageModelList ){
			if( stage.getID() == id)
				return stage;
		}
		return null;
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
		this.stageModelList = other.stageModelList;
	}

}
