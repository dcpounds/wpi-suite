package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class AddTaskRequestObserver implements RequestObserver{

	TaskController controller;
	
	public AddTaskRequestObserver(TaskController controller){
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
        TaskModel task = TaskModel.fromJson(iReq.getResponse().getBody());
        controller.addTask(task);
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