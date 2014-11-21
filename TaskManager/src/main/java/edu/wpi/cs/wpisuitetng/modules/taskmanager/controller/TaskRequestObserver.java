package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class TaskRequestObserver implements RequestObserver{

	TaskController controller;
	
	public TaskRequestObserver(TaskController controller){
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
        TaskModel task = TaskModel.fromJson(iReq.getResponse().getBody());
        controller.processResponse(task);
	}

	@Override
	public void responseError(IRequest iReq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
		
	}

}
