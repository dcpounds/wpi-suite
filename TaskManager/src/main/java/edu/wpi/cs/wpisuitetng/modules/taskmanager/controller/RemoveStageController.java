package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

/**
 * @author Alec
 * Removes a workflow
 */
public class RemoveStageController implements ActionListener{
	private WorkflowModel workflowModel;
	private WorkflowView workflowView;
	private StageView stageView;
	private StageModel stageModel;
	
	public RemoveStageController(StageView stageView, StageModel stageModel, WorkflowView workflowView){

		
		this.stageView = stageView;
		this.stageModel = stageModel;
		this.workflowModel = WorkflowController.getInstance().getWorkflowModel();
		this.workflowView = workflowView;
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		workflowModel.removeStageModel(stageModel);
		workflowView.removeStageView(stageView);
		workflowView.remove(stageView);
	}

}
