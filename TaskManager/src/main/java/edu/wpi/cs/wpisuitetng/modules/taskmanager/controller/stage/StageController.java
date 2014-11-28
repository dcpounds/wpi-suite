package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


/**
 * @author Alec
 * Handles stage and task persistence between the database and the user
 */
public class StageController implements ActionListener{
	private static WorkflowModel workflowModel;
	private StageModel stage;
	private ActionType action;
	private static AddStageRequestObserver addObserver;
	private static GetStageRequestObserver getObserver;
	private static UpdateStageRequestObserver updateObserver;
	private static DeleteStageRequestObserver deleteObserver;
	
	public StageController(StageModel stage, ActionType action){
		this.action = action;
		StageController.addObserver = new AddStageRequestObserver(this);
		StageController.updateObserver = new UpdateStageRequestObserver(this);
		StageController.deleteObserver = new DeleteStageRequestObserver(this);
		StageController.getObserver = new GetStageRequestObserver(this);
		this.workflowModel = WorkflowController.getWorkflowModel();
		this.stage = stage;
	}
	
	/**
	 * Depending on the specified action, either create, edit, delete, or get stageModel(s) from the database
	 */
	public void act(){
		switch(action){
			case CREATE: 
				sendAddRequest(stage);
				break;
			case EDIT:
				sendUpdateRequest(stage);
				break;
			case DELETE:
				sendDeleteRequest(stage);	
				break;
			case GET:
				sendGetRequest(stage);
				break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		act();
	}
	
	/**
	 * @param stage - the stage to update in the database
	 */
	public static void sendUpdateRequest(StageModel stage){
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.POST); // POST == update
		request.setBody(stage.toJson()); // put the new stage in the body of the request
		request.addObserver(updateObserver); // add an observer to process the response
		request.send();
	}
	
	
	/**
	 * @param stage - the stage to archive in the database
	 */
	public static void sendDeleteRequest(StageModel stage){
		stage.setIsArchived(true);
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.POST); // POST == update
		request.setBody(stage.toJson()); // put the new stage in the body of the request
		request.addObserver(deleteObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * @param stageModel - the stage to add to the database
	 */
	public static void sendAddRequest(StageModel stageModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.PUT); // PUT == create
		request.setBody(stageModel.toJson()); // put the new stage in the body of the request
		request.addObserver(addObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * get a list of all stages from the database
	 */
	public static void sendGetRequest (StageModel stageModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.GET); // PUT == create
		request.addObserver(getObserver); // add an observer to process the response
		request.send();
	}

	/**
	 * Adds the stage that was just stored in the database to the workflow
	 * @param stage - the stage that was just added to the database
	 */
	public void addStage(StageModel stage) {
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		StageView stageView = new StageView(stage, workflowView);
		workflowView.addStageView(stageView);
		this.syncTaskViews(stage, stageView);
		workflowModel.addStage(stage);
	}
	
	/**
	 * The stage that was saved in the database
	 * @param stage - the stage that just got updated in the database
	 */
	public void updateStage(StageModel stage) {
		boolean closable = TabController.getTabView().getWorkflowView().getStageViewList().size() <= 1 ? false : true;
		stage.setClosable(closable);
		System.out.println("Updating a stage with id " + stage.getID());
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		StageView stageView = workflowView.getStageViewByID(stage.getID());
		this.syncTaskViews(stage, stageView);
		stageView.updateContents(stage);
	}
	
	/**
	 * Given a stage, update/add all the task views
	 * @param stageModel - the stageModel to sync the views of
	 * @param stage - the stageView to add the taskViews to
	 */
	public void syncTaskViews(StageModel stageModel, StageView stage){
		for( TaskModel task : stageModel.getTaskModelList()){
			//Skip if the task is archived
			boolean matched = false;
			for(TaskView taskView : stage.getTaskViewList()){
				//If we found a taskView with the same ID as the taskModel (a match)
				if(taskView.getID() == task.getID()){
					//update this taskView and continue
					taskView.setContents(task);
					matched = true;
					break;
				}
			}
			if(!matched){
				//If we found no match, add the task to the stageView
				TaskView newTaskView = new TaskView(task, stage);
				stage.addTaskView(newTaskView);
				newTaskView.getPreferredSize();
			}
		}
	}
	
	/**
	 * Removes a task from a stage
	 * @param taskView - the taskView to delete 
	 * @param stageView - the stageView to delete from
	 */
	public static void deleteTask(TaskView taskView, StageView stageView){
		StageModel stageModel = workflowModel.getStageModelByID(stageView.getID());
		try{
			for( TaskModel task : stageModel.getTaskModelList()){
				if(task.getID() == taskView.getID()){
					stageModel.removeTask(task);
					stageView.removeTaskView(taskView);
					StageController.sendUpdateRequest(stageModel);
					stageView.repaint();
					return;
				}
			}
		} catch(Exception e){
			System.out.println("Could not remove. Either the task could not be found"
					+ " or the stage could not be found");
			e.printStackTrace();
		}
	}
	
	/**
	 * Given a list of stageModels that are contained within the database, make sure that they are up to date in the local workflow
	 * @param stages - the list of stages returned from the database
	 */
	public void syncStages(StageModel[] stages) {
		if(stages.length == 0)
			saveBaseStages();
		
		for(StageModel stage : stages ){
			boolean exists = workflowModel.getStageModelByID( stage.getID())  == null ? false : true;
			if(exists){
				if( stage.getIsArchived()){
					workflowModel.removeStageModel(stage);
					continue;
				} else{
					updateStage(stage);
					continue;
				}
			}
			if(stage.getIsArchived())
				continue;
			addStage(stage);
		}
	}
	
	
	/**
	 * Removes a stage from the workflow
	 * @param stage - after we sent the deleteRequest, the database found this to be the stage we wanted deleted
	 */
	public void deleteStage(StageModel stage) {
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		StageView sv = workflowView.getStageViewByID(stage.getID());
		workflowModel.removeStageModel(stage);
		workflowView.removeStageView(sv);
	}
	
	
	/**
	 * Puts the four base stages into the database. This should really only happen once
	 */
	public void saveBaseStages(){
		StageModel newStage = new StageModel("New");
		StageModel scheduledStage = new StageModel("Scheduled");
		StageModel inProgressStage = new StageModel("In Progress");
		StageModel completedStage = new StageModel("Completed");
		
		StageController.sendAddRequest(newStage);
		StageController.sendAddRequest(scheduledStage);
		StageController.sendAddRequest(inProgressStage);
		StageController.sendAddRequest(completedStage);
	}
}
