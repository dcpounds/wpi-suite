package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;

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
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			}
		};
		
		thread.setName("poll");
		thread.setDaemon(true);
		thread.start();
	}

	public static WorkflowController getInstance(){
		return instance;
	}

	public static WorkflowModel getWorkflowModel(){
		return model;
	}
		
}
