package edu.wpi.cs.wpisuitetng.modules.TaskManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.TaskManager.views.SplitPaneView;
import edu.wpi.cs.wpisuitetng.modules.TaskManager.views.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.TaskManager.views.TaskManagerToolbarView;
import edu.wpi.cs.wpisuitetng.modules.TaskManager.views.WorkflowView;


/**
 * This is the file to edit when you want to add base components.
 *
 */
public class TaskManager implements IJanewayModule {
	private List<JanewayTabModel> tabs;
	
	public TaskManager(){
		tabs = new ArrayList<JanewayTabModel>();
		
		//Create both the toolbar and main panel panes
		SplitPaneView splitPane = new SplitPaneView();
		TaskManagerToolbarView toolbarPanel = new TaskManagerToolbarView();
		
		//Creaate a panel for the toolbar at the top
		JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolbarPanel, splitPane);
		tabs.add(tab1);
	}
	
	public String getName(){
		return "Task Manager";
	}

	public List<JanewayTabModel> getTabs(){
		return tabs;
	}
}
