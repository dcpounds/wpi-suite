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
 * responsible for getting all of the dataloggers from the database
 */
public class GetDataLoggerRequestObserver implements RequestObserver{

	DataLoggerController controller;
	
	public GetDataLoggerRequestObserver(DataLoggerController controller){
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
		DataLoggerModel stages[] = DataLoggerModel.fromJsonArray(iReq.getResponse().getBody());
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
