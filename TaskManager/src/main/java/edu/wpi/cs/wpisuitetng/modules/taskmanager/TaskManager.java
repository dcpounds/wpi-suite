/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team What? We Thought This Was Bio!
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabView;


/**
 * Main class for the view. Sets up the toolbar and main view with the tabs
 *
 */
public class TaskManager implements IJanewayModule {
	
	MainView mainView;
	private  TabView mainTabView;
	private List<JanewayTabModel> tabs;
	
	
	/**
	 * Construct the main taskmanager view
	 */
	public TaskManager(){
		mainView = new MainView();
		tabs = new ArrayList<JanewayTabModel>();
		
		//Create both the toolbar and main panel panes
		ToolbarView toolbarPanel = new ToolbarView();
		
		//Create a panel for the toolbar at the top
		JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolbarPanel, mainView);
		
		tabs.add(tab1);
	}
	
	/**
	 * (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getName()
	 */
	public String getName(){
		return "Task Manager";
	}

	/**
	 * (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getTabs()
	 */
	public List<JanewayTabModel> getTabs(){
		return tabs;
	}
	

}
