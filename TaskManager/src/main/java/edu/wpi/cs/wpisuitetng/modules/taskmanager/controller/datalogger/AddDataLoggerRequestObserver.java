/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.reports.DataLoggerModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * @author Joe
 * the controller to add data loggers to the database
 */
public class AddDataLoggerRequestObserver implements RequestObserver{

	DataLoggerController controller;
	
	public AddDataLoggerRequestObserver(DataLoggerController dataLoggerController){
		controller = dataLoggerController;
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
