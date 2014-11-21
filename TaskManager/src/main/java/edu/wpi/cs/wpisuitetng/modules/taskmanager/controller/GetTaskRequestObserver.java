package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class GetTaskRequestObserver implements RequestObserver{

	TaskController controller;
	
	public GetTaskRequestObserver(TaskController controller){
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
        TaskModel tasks[] = TaskModel.fromJsonArray(iReq.getResponse().getBody());
        controller.getTasks(tasks);
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
