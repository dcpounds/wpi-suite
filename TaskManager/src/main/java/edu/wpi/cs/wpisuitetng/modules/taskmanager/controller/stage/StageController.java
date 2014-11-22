package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


/**
 * @author Alec
 * Removes a workflow
 */
public class StageController implements ActionListener{
	private WorkflowModel workflowModel;
	private WorkflowView workflowView;
	private StageView stageView;
	private StageModel stage;
	private ActionType action;
	private AddStageRequestObserver addObserver;
	private GetStageRequestObserver getObserver;
	private UpdateStageRequestObserver updateObserver;
	private DeleteStageRequestObserver deleteObserver;
	
	public StageController(StageModel stage, ActionType action){
		this.action = action;
		this.addObserver = new AddStageRequestObserver(this);
		this.updateObserver = new UpdateStageRequestObserver(this);
		this.deleteObserver = new DeleteStageRequestObserver(this);
		this.getObserver = new GetStageRequestObserver(this);
		this.workflowModel = WorkflowController.getWorkflowModel();
		this.workflowView = TabController.getTabView().getWorkflowView();
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
	public void sendUpdateRequest(StageModel stage){
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.POST); // POST == update
		request.setBody(stage.toJson()); // put the new stage in the body of the request
		request.addObserver(updateObserver); // add an observer to process the response
		request.send();
	}
	
	
	/**
	 * @param stage - the stage to archive in the database
	 */
	public void sendDeleteRequest(StageModel stage){
		stage.setIsArchived(true);
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.POST); // POST == update
		request.setBody(stage.toJson()); // put the new stage in the body of the request
		request.addObserver(deleteObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * @param stageModel - the stage to add to the database
	 */
	public void sendAddRequest(StageModel stageModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.PUT); // PUT == create
		request.setBody(stageModel.toJson()); // put the new stage in the body of the request
		request.addObserver(addObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * get a list of all stages from the database
	 */
	public void sendGetRequest (StageModel stageModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.GET); // PUT == create
		request.addObserver(getObserver); // add an observer to process the response
		request.send();
	}

	/**
	 * Adds the stage that was just stored in the database to the workflow
	 * @param stage - the stage that was just added to the database
	 */
	public void addStage(StageModel stage) {
		StageView stageView = new StageView(stage, workflowView);
		workflowView.addStageView(stageView);
		workflowModel.addStage(stage);
	}
	
	public void updateStage(StageModel stage) {
		StageView stageView = workflowView.getStageViewByID(stage.getID());
		stageView.updateContents(stage);
	}
	
	public void syncStages(StageModel[] stages) {
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
	
	public void deleteStage(StageModel stage) {
		workflowModel.removeStageModel(stage);
		workflowView.removeStageView(stageView);
		workflowView.remove(stageView);
	}
	
	
	
	

}
