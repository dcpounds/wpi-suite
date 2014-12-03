/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team What? We Thought This Was Bio!
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

public class ExpandTaskController implements MouseListener{
	TaskModel taskModel;
	TaskView taskContainerView;
	StageView stageView;
	
	public ExpandTaskController(TaskView taskContainerView, TaskModel taskModel){
		this.taskModel = taskModel;
		this.taskContainerView = taskContainerView;	
		this.stageView = taskContainerView.getStageView();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean isExpanded = taskModel.getIsExpanded();
		System.out.println("Toggled expanded state of task view, expanded view set to " + isExpanded);
		if(isExpanded == true)
			taskContainerView.hideDetails();
		else
			taskContainerView.showDetails();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
