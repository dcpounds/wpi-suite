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

import java.util.Date;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.AddStageRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.DeleteStageRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.GetStageRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.UpdateStageRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.reports.DataLoggerModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author Joe
 * This class is used to accumulate and report data on the use of the program over time, which
 * cannot be derived from simply reading the current list of taskModels
 */

public class DataLoggerController {
	private static DataLoggerController instance = new DataLoggerController();
	private static DataLoggerModel dataLoggerModel;
	private ActionType action;
	private static AddDataLoggerRequestObserver addObserver;
	private static GetDataLoggerRequestObserver getObserver;
	private static UpdateDataLoggerRequestObserver updateObserver;
	private static DeleteDataLoggerRequestObserver deleteObserver;
	
    private Date overrideDate;
    private Date startDate;
    private Date endDate;
    
    
    
    
    DataLoggerController() {
		dataLoggerModel = new DataLoggerModel();
		action = action;
		DataLoggerController.addObserver = new AddDataLoggerRequestObserver(this);
		DataLoggerController.updateObserver = new UpdateDataLoggerRequestObserver(this);
		DataLoggerController.getObserver = new GetDataLoggerRequestObserver(this);
		DataLoggerController.deleteObserver = new DeleteDataLoggerRequestObserver(this);

	}









	/**
	 * Depending on the specified action, either create, edit, delete, or get stageModel(s) from the database
	 */
	public void act(){
		switch(action){
			case CREATE: 
				sendAddRequest(dataLoggerModel);
				break;
			case EDIT:
				sendUpdateRequest(dataLoggerModel);
				break;
			case DELETE:
				{
					sendDeleteRequest(dataLoggerModel);	
					break;
				}
			case GET:
				sendGetRequest(dataLoggerModel);
				break;
		}
	}
	

	
	/**
	 * @return the dataLogger model that was passed in
	 */
	public static DataLoggerModel getDataModel() {
		return dataLoggerModel;
	}
	
	/**
	 * @param DataLoggerModel - the DataLogger to add to the database
	 */
	public static void sendAddRequest(DataLoggerModel dataLoggerModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/datalogger", HttpMethod.PUT); // PUT == create
		request.setBody(dataLoggerModel.toJson()); // put the new DataLogger in the body of the request
		request.addObserver(addObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * get a list of all DataLoggers from the database
	 */
	public static void sendGetRequest (DataLoggerModel dataLoggerModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/datalogger", HttpMethod.GET); // PUT == create
		request.addObserver(getObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * Send a request to update the data logger in the db
	 * @param dataLoggerModel
	 */
	public static void sendUpdateRequest(DataLoggerModel dataLoggerModel){
		final Request request = Network.getInstance().makeRequest("taskmanager/datalogger", HttpMethod.POST); // POST == update
		request.setBody(dataLoggerModel.toJson()); // put the new stage in the body of the request
		request.addObserver(updateObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * @param stage - the stage to archive in the database
	 */
	public static void sendDeleteRequest(DataLoggerModel dataLoggerModel){
		final Request request = Network.getInstance().makeRequest("taskmanager/datalogger", HttpMethod.POST); // POST == update
		request.setBody(dataLoggerModel.toJson()); // put the new data logger in the body of the request
		request.addObserver(deleteObserver); // add an observer to process the response
		request.send();

	}

}
