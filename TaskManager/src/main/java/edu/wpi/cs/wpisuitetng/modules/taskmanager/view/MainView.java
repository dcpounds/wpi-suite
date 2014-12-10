/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabView;

/**
 * MainView is a wrapper class that holds all of the JPanels in the main section of the interface
 */
public class MainView extends JPanel{


	private static final long serialVersionUID = -2682288714623307153L;
	
	WorkflowModel workflowModel;
	TabView mainTabView;
	/**
	 * Constructs the main view based off the main workflow model
	 * 
	 * @param mainTabView -the panel containing all the tabs
	 */
	public MainView(){
		workflowModel = WorkflowController.getWorkflowModel();
		mainTabView = TabController.getTabView();
		
		setLayout(new BorderLayout());
		add(mainTabView, BorderLayout.CENTER);
	}

}
