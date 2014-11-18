package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewStageTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

public class AddStageController implements ActionListener {
	private NewStageTab newStageTab;
	private WorkflowView workflowView;
	private WorkflowModel workflowModel;
	
	public AddStageController(TaskManagerTabView tabView, NewStageTab newStageTab) {
		this.newStageTab = newStageTab;
		this.workflowView = tabView.getWorkflowView();
		this.workflowModel = newStageTab.getWorkflowModel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String stageName = newStageTab.getStageTitle();
		StageModel stageModel = new StageModel(stageName);
		StageView stageView = new StageView(stageModel, workflowView);
		workflowView.addStageView(stageView);
		workflowModel.addStage(stageModel);
		
		// TODO Auto-generated method stub
		
	}

}
