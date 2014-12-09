/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

/**
 * This view creates a tabbed window that can contain other views. 
 * It is also responsible for creating the default workflow tab
 */
public class TabView extends JTabbedPane{
	private static final long serialVersionUID = -8772129104939459349L;
	WorkflowModel workflowModel;
	WorkflowView workflowView;
	
	
	/**
	 * constructs the tabbed pane for viewing all the tabs.
	 * creates tab for the main workflow at index 0
	 * 
	 */
	public TabView() {
		workflowModel = WorkflowController.getWorkflowModel();
		workflowView = new WorkflowView(workflowModel);
		
		this.setLayout( new BorderLayout() );
		setTabPlacement(TOP);
		setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
		addTab("Workflow", new ImageIcon(), workflowView,
		       "An overview of the task workflow");
	}
	
	/**
	 * @return the view that contains the workflow
	 */
	public WorkflowView getWorkflowView(){
		return workflowView;
	}

}
