package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class GetStageRequestObserver implements RequestObserver{

	StageController controller;
	
	public GetStageRequestObserver(StageController controller){
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
		StageModel stages[] = StageModel.fromJsonArray(iReq.getResponse().getBody());
		ArrayList<StageModel> stageArrayList = new ArrayList<StageModel>(Arrays.asList(stages));
		Collections.sort(stageArrayList);
        controller.syncStages(stageArrayList);
		
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
