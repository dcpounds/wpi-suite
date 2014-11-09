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
import edu.wpi.cs.wpisuitetng.modules.TaskManager.views.WorkflowView;


/**
 * This is the file to edit when you want to add base components.
 *
 */
public class TaskManager implements IJanewayModule {
	private List<JanewayTabModel> tabs;
	
	public TaskManager(){
		tabs = new ArrayList<JanewayTabModel>();
		
		//Create a new tab view to contain the workflow
		SplitPaneView splitPane = new SplitPaneView();
		
		//Creaate a panel for the toolbar at the top
		JPanel toolbarPanel = new JPanel();
		toolbarPanel.add(new JLabel("TaskManager toolbar placeholder"));
		toolbarPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
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
