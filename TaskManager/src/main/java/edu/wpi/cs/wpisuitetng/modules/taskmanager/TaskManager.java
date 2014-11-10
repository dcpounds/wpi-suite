package edu.wpi.cs.wpisuitetng.modules.taskmanager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;


/**
 * This is the file to edit when you want to add base components.
 *
 */
public class TaskManager implements IJanewayModule {
	
	WorkflowListModel workflowListModel;
	MainView mainView;
	private TaskManagerTabView mainTabView;
	private List<JanewayTabModel> tabs;
	
	
	public TaskManager(){
		this.mainTabView = new TaskManagerTabView(); 
		this.workflowListModel = new WorkflowListModel();
		this.mainView = new MainView( this.workflowListModel, this.mainTabView );
		tabs = new ArrayList<JanewayTabModel>();
		
		//Create both the toolbar and main panel panes
		ToolbarView toolbarPanel = new ToolbarView(workflowListModel, mainTabView);
		
		//Create a panel for the toolbar at the top
		JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolbarPanel, mainView);
		tabs.add(tab1);
	}
	
	public String getName(){
		return "Task Manager";
	}

	public List<JanewayTabModel> getTabs(){
		return tabs;
	}
}
