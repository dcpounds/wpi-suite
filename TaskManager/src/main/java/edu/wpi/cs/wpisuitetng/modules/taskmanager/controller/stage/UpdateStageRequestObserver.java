/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * @author Alec
 * This is a request observer for updating stages
 *
 */
public class UpdateStageRequestObserver implements RequestObserver{

	StageController controller;
	
	public UpdateStageRequestObserver(StageController stageController){
		controller = stageController;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
        StageModel stage = StageModel.fromJson(iReq.getResponse().getBody());
        controller.updateStage(stage);
		
	}

	@Override
	public void responseError(IRequest iReq) {
		//Intentionally left blank	
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		//Intentionally left blank		
	}

}
