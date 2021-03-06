/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.sql.Date;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;

/**
 * @author Team 4
 * The workflow controller is responsible for polling the database and managing the workflow model
 */
public class WorkflowController {
	private static WorkflowController instance = new WorkflowController();
	private static WorkflowModel model;


	private WorkflowController () {
		model = new WorkflowModel("main");		
		Thread thread = new Thread() {
			public void run() {
			
				while (true) {
					try {
						sleep(4000);
						new StageController(new StageModel(), ActionType.GET).act();
						DataLoggerController.sendGetRequest( DataLoggerController.getDataModel());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * Gets the current instance of the workflow controller
	 * @return
	 */
	public static WorkflowController getInstance(){
		return instance;
	}

	
	/**
	 * @return the singleton workflow model
	 */
	public static WorkflowModel getWorkflowModel(){
		return model;
	}
		
}
