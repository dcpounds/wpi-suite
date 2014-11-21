package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class AddStageRequestObserver implements RequestObserver{

	AddStageController controller;
	
	public AddStageRequestObserver(AddStageController controller){
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
        StageModel stage = StageModel.fromJson(iReq.getResponse().getBody());
        controller.addStage(stage);
		
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
