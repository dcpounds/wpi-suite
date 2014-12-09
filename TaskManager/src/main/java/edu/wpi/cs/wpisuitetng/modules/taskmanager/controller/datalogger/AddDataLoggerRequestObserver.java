package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.reports.DataLoggerModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class AddDataLoggerRequestObserver implements RequestObserver{

	DataLoggerController controller;
	
	public AddDataLoggerRequestObserver(DataLoggerController dataLoggerController){
		this.controller = dataLoggerController;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
        DataLoggerModel dataLoggerModel = DataLoggerModel.fromJson(iReq.getResponse().getBody());
        //controller.addStage(stage);
		
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
