package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import com.google.gson.Gson;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * the main model representing the workflow. 
 * contains all the stages where tasks are moved around in
 *
 */
public class WorkflowModel extends AbstractModel {
	final private String name;
	public ArrayList<StageModel> stageList;
	private ArrayList<UserModel> userList;

	/**
	 * construct the main workflow based off a given list of stages
	 * 
	 * @param name - the name of the workflow (usually "main")
	 * @param stageList
	 */
	public WorkflowModel(String name, ArrayList<StageModel> stageList){
		this.name = name;
		this.stageList = stageList;
		this.userList = new ArrayList<UserModel>();		
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
		this.userList = new ArrayList<UserModel>();
		
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
	 * @param user - the user to add to the list
	 * @return the list of users
	 */
	public ArrayList<UserModel> addUserToList(UserModel user) {
		if(this.userList == null){
			System.out.println("Warning: WorkflowModel had a null userList!");
			this.userList = new ArrayList<UserModel>();
		}
		this.userList.add(user);
		return this.userList;
	}
	
	/**
	 * @return the list of users that can be assigned to tasks in this workflow
	 */
	public ArrayList<UserModel> getUserList() {
		if( userList == null || userList.size() == 0){
			System.out.println("Warning: WorkflowModel has no users assigned!");
			return new ArrayList<UserModel>();
		}
		return userList;
	}
	
	
	/**
	 * adds the default list of stages to the main workflow
	 */
	private void addBaseStages(){
		stageList.add(new StageModel("New", new ArrayList<TaskModel>()));
		stageList.add(new StageModel("In Progress", new ArrayList<TaskModel>()));
		stageList.add(new StageModel("Scheduled", new ArrayList<TaskModel>()));
		stageList.add(new StageModel("Completed", new ArrayList<TaskModel>()));
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
