package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class UpdateTaskController implements ActionListener {
	private TaskModel taskModel;
	private UpdateTaskRequestObserver observer;
	private WorkflowModel workflowModel;
	private WorkflowView workflowView;
	
	public UpdateTaskController(WorkflowView workflowView, TaskModel taskModel) {
		this.taskModel = taskModel;
		this.observer = new UpdateTaskRequestObserver(this);
		this.workflowModel = WorkflowController.getWorkflowModel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//get the id of the taskModel that got passed in
		//views need update method
		//find the appropriate view and run update(taskModel) on it
		//workflowModels need a list of stageViews
		
		
		//Send a request to update the entity in the database
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.POST); // POST == update
		request.setBody(taskModel.toJson()); // put the new stage in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send();
	}
	
	public void updateTask(TaskModel task){
		
	}

}
