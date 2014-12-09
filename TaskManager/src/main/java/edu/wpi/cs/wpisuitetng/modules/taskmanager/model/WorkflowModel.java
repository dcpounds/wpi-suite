package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;

/**
 * the main model representing the workflow. 
 * contains all the stages where tasks are moved around in
 *
 */
public class WorkflowModel extends AbstractModel {
	final private String name;
	private HashMap<Integer,StageModel> stageModelList;
	private static ArrayList<User> userList; 
	private static boolean toggleColor;
	private static ArrayList<String> requirementsList;

	/**
	 * construct the main workflow based off a given list of stages
	 * @param name - the name of the workflow (usually "main")
	 * @param stageList
	 */
	public WorkflowModel(String name, HashMap<Integer,StageModel> stageList){
		this.name = name;
		this.stageModelList = stageList;
		this.userList = new ArrayList<User>();
		this.toggleColor = false;
		this.requirementsList = new ArrayList<String>();
	}
	
	
	/**
	 * Construct the main workflow
	 * @param name - the name of the workflow (usually "main")
	 */
	public WorkflowModel(String name){
		this.name = name;
		this.stageModelList = new HashMap<Integer, StageModel>();
		this.toggleColor = false;
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
	public HashMap<Integer, StageModel> getStageModelList() {
		return stageModelList;
	}
	
	
	/**
	 * @param stage - a StageModel to add to the workflow
	 * @return the updated list of stages in the workflow
	 */
	public HashMap<Integer, StageModel> addStage(StageModel stage) {
		stageModelList.put(stage.getID(),stage);
		return stageModelList;
	}
	
	
	/**
	 * @param stage - a StageModel to add to the workflow
	 * @return the updated list of stages in the workflow
	 */
	public HashMap<Integer, StageModel> removeStageModel(StageModel stage) {
		stageModelList.remove(stage.getID());
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
	public String[] getRequirementsList(){
		if (requirementsList == null){
			System.out.println("Theres no requirementsOptions");
			return new String[]{};
			}
		System.out.println("theres requirements");
		return requirementsList.toArray(new String[requirementsList.size()]);
	}
	
	public void toggleColor(){
		WorkflowModel.toggleColor = !WorkflowModel.toggleColor;
	}
	
	public boolean getToggleColor(){
		return WorkflowModel.toggleColor;
	}
	
	
	/**
	 * @param userList - the list of assignable users to set within the workflow
	 */
	public void setUserList(User[] userList){
		 WorkflowModel.userList = new ArrayList<User>(Arrays.asList(userList));
	}
	
	
	/**
	 * @param id - id of the task to look for
	 * @return the taskModel if successful, null otherwise
	 */
	public TaskModel getTaskModelByID(int id){
		for( StageModel stageModel : stageModelList.values()){
			TaskModel task = stageModel.getTaskModelList().get(id);
			if(task != null)
				return task;
		}
		return null;
	}
	
	
	/**
	 * @param id - id of the stage to look for
	 * @return - the stageModel if successful, nul otherwise
	 */
	public StageModel getStageModelByID(int id){
		return stageModelList.get(id);
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


	public void setRequirementsList(Requirement[] requirements) {
		// TODO Auto-generated method stub
		WorkflowModel.requirementsList = new ArrayList<String>();
		for (Requirement req : requirements)
			requirementsList.add(req.getName());
		System.out.print("requirements:");
		System.out.println(requirementsList);
	}

}
