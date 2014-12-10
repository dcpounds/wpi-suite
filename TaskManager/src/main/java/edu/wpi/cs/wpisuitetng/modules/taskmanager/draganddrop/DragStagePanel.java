/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop;

import java.awt.Point;
import java.awt.dnd.DropTarget;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

/**
 * @author Alec
 * An extendable class that provides drag/drop functionality to stages
 */
public class DragStagePanel extends JPanel{
	private static final long serialVersionUID = 6616674170936811473L;

	/**
	 * An extendable JPanel that can accept dropped tasks
	 */
	public DragStagePanel(){
		this.setTransferHandler(new TaskTransferHandler());
		this.setDropTarget(new DropTarget(this, new DragStageController(this)));
	}
	
	/**
	 * @param taskPanel - the taskPanel that is being dropped
	 * @param dropPoint - the point withint the stage that the task was dropped
	 */
	public void placeTask(Point dropPoint, DragTaskPanel taskPanel) {
		WorkflowModel workflowModel = WorkflowController.getWorkflowModel();
		TaskView taskView = (TaskView)taskPanel;
		
		TaskModel taskModel = workflowModel.getTaskModelByID(taskView.getID());
		
		//The original stage the task was in
		StageView originalStageView = taskView.getStageView();
		StageModel originalStageModel = workflowModel.getStageModelByID(originalStageView.getID());
		originalStageModel.removeTask(taskModel);
		originalStageView.removeTaskView(taskView);
		StageController.sendUpdateRequest(originalStageModel);
		
		//Add the stage to the new spot
		StageView newStageView = (StageView)this;
		StageModel newStageModel = workflowModel.getStageModelByID(newStageView.getID());
		newStageModel.addTaskModel(taskModel);
		newStageView.addTaskView(taskView);
		
		taskView.setStageView(newStageView);
		taskModel.setStageID(newStageView.getID());
		System.out.println("Adding a taskModel to stage " + newStageModel.getTitle());
		
	
		StageController.sendUpdateRequest(newStageModel);
		
		//Generate an activity reflecting the change
		DataLoggerController.getDataModel().addSnapshot(taskModel);
		
		ActivityModel message;
		String messageString;
		
		for (int i=0; i<DataLoggerController.getDataModel().trackChanges(taskModel.getPreviousSnapshot(), 
				 taskModel.getCurrentSnapshot()).size(); i++)
		{
			messageString = DataLoggerController.getDataModel().trackChanges(taskModel.getPreviousSnapshot(), 
														 taskModel.getCurrentSnapshot()).get(i);
			message = new ActivityModel(messageString);
			taskModel.addActivity(message);
		}

	}
}
