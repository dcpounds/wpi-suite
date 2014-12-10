package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.reports.DataLoggerModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class DeleteDataLoggerRequestObserver implements RequestObserver{

	DataLoggerController controller;
	
	public DeleteDataLoggerRequestObserver(DataLoggerController controller){
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
        DataLoggerModel stage = DataLoggerModel.fromJson(iReq.getResponse().getBody());
        //controller.deleteStage(stage);
		
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
