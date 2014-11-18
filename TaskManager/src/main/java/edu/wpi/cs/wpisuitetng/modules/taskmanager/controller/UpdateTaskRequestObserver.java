package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class UpdateTaskRequestObserver implements RequestObserver{
	
	private UpdateTaskController controller;
	
	public UpdateTaskRequestObserver(UpdateTaskController controller) {
		this.controller = controller;
	}

	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the taskModel to the given request
		final ResponseModel response = iReq.getResponse();
		
		// Parse the taskModel out of the response body
		final TaskModel task = TaskModel.fromJson(response.getBody());	
	}

	@Override
	public void responseError(IRequest iReq) {
		System.err.println(iReq.getResponse().getStatusMessage());
		System.err.println("There was an error updating the task.");
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to update a task failed.");
	}

}
