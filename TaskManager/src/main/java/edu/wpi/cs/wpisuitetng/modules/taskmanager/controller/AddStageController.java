package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewStageTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddStageController implements ActionListener {
	private NewStageTab newStageTab;
	private WorkflowView workflowView;
	private WorkflowModel workflowModel;
	private AddStageRequestObserver observer;
	
	public AddStageController(NewStageTab newStageTab) {
		this.newStageTab = newStageTab;
		this.workflowView = TabController.getTabView().getWorkflowView();
		this.workflowModel = newStageTab.getWorkflowModel();
		this.observer = new AddStageRequestObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String stageName = newStageTab.getStageTitle();
		StageModel stageModel = new StageModel(stageName);
		
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.PUT); // PUT == create
		request.setBody(stageModel.toJson()); // put the new stage in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send();
		
	}
	
	public void addStage(StageModel receivedStage) {
		System.out.println("Added a stage to the db");
        // Empty the local model to eliminate duplications
    	if(receivedStage != null){
    		StageView stageView = new StageView(receivedStage, workflowView);
    		workflowView.addStageView(stageView);
    		workflowModel.addStage(receivedStage);
    	}
    }

}
