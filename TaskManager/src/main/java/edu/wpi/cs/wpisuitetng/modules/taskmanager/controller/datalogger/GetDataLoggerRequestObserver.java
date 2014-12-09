package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class GetDataLoggerRequestObserver implements RequestObserver{

	DataLoggerController controller;
	
	public GetDataLoggerRequestObserver(DataLoggerController controller){
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
		StageModel stages[] = StageModel.fromJsonArray(iReq.getResponse().getBody());
        //controller.syncStages(stages);
		
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
