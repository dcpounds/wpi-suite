/*******************************************************************************
* Copyright (c) 2014 WPI-Suite
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors: Team What? We Thought This Was Bio!
*******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

/**
 * controller for toggling the view of every archived task
 *
 */
public class ArchiveController implements ChangeListener {
	private static ArchiveController instance = new ArchiveController();
	private static WorkflowView workflowView;
	private static boolean isPressed;

	/**
	 * creates the archive controller based on the workflow model
	 * private since class is a singleton
	 */
	private ArchiveController(){
		workflowView = TabController.getTabView().getWorkflowView();
	}
	
	/**
	 * get the instance of the class
	 * @return
	 */
	public static ArchiveController getInstance(){
		return instance;
	}
	
	
		
	/**
	 * activates or deactivates views of all archived tasks depending on state of archive button
	 * 
	 *  (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if(!isPressed){
			isPressed = true;
			makeArchiveViewsVisible();
		} else {
			isPressed = false;
			makeArchiveViewsInvisible();
		}
		
	}
	
	/**
	 * Get whether the archive button is pressed or not
	 * 
	 * @return
	 */
	public static boolean getIsPressed(){
		return isPressed;
	}
	
	/**
	 * makes all the archived views visible
	 */
	private static void makeArchiveViewsVisible() {
		for(StageView stageView : workflowView.getStageViewList().values()){
			for(TaskView taskView : stageView.getTaskViewList().values()){
				if(taskView.isTaskModelArchived()){
					taskView.setVisible(true);
				}
			}
			stageView.repaint();
		}
	}
	
	/**
	 * makes all the archived views invisible
	 */
	private static void makeArchiveViewsInvisible() {
		for(StageView stageView : workflowView.getStageViewList().values()){
			for(TaskView taskView : stageView.getTaskViewList().values()){
				if(taskView.isTaskModelArchived()){
					taskView.setVisible(false);
				}
			}
			stageView.repaint();
		}
	}

}
