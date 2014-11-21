package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.TaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;

public class WorkflowController {
	private static WorkflowController instance = new WorkflowController();
	private static WorkflowModel model;
	boolean hasReceivedGetResponse;

	private WorkflowController () {
		model = new WorkflowModel("main");
		hasReceivedGetResponse = false;
	}

	public static WorkflowController getInstance(){
		return instance;
	}

	public static WorkflowModel getWorkflowModel(){
		return model;
	}
	
	Thread thread = new Thread() {
		public void run() {
			while (true) {
				try {
					sleep(5000);
					new TaskController(new TaskModel(), ActionType.GET).act();
				} catch (Exception e) {
					e.printStackTrace();
				}
				thread.setName("poll");
				thread.setDaemon(true);
				thread.start();
				
			}
		}
	};

	
}
