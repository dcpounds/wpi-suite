package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.ExpandTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

/**
 * Model for respresenting a stage that holds individual tasks
 *
 */
public class StageModel extends AbstractModel {
	
	private static final long serialVersionUID = 7869886695945683209L;
	private String title;
	private boolean closable;
	private boolean isArchived;
	private int id;
	private ArrayList<TaskModel> taskModelList;
	
	/**
	 * constructs a new stage
	 * @param title - title of the stage
	 * @param taskList - list of tasks to be added
	 */
	public StageModel(String title) {
		this.title = title;
		this.closable = WorkflowController.getWorkflowModel().getStageModelList().size() <= 1 ? false : true;
		this.id = this.hashCode();
		this.taskModelList = new ArrayList<TaskModel>();
	}
	
	
	/**
	 * Blank stage constructor since the 
	 * database needs a class to identify the type to fetch
	 */
	public StageModel() {
		this.taskModelList = new ArrayList<TaskModel>();
		this.closable = WorkflowController.getWorkflowModel().getStageModelList().size() <= 1 ? false : true;
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
	}
	
	/**
	 * @param task - the task to add to the list of tasks
	 * @return - the list of tasks
	 */
	public ArrayList<TaskModel> addUpdateTaskModel(TaskModel task){
		int index = 0;
		for(TaskModel existingTask : this.getTaskModelList()){
			if(existingTask.getID() == task.getID()){
				this.getTaskModelList().set(index,task);
				StageController.sendUpdateRequest(this);
				return this.getTaskModelList();
				
			}
			index++;
		}
		taskModelList.add(task);
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
	public ArrayList<TaskModel> addTaskModelAtIndex(int index, TaskModel task){
		taskModelList.add(index, task);
		return taskModelList;
	}
	
	
	/**
	 * @param task - the task to remove from the list of tasks
	 * @return - the updated list of tasks
	 */
	public ArrayList<TaskModel> removeTask(TaskModel task){
		taskModelList.remove(task);
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
	public ArrayList<TaskModel>getTaskModelList(){
		return this.taskModelList;
	}
	
	/**
	 * @param id - the id to set the stage to
	 */
	public void setID(int id){
		this.id = id;
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
	

}
