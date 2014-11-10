package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

/**
 * This view creates a tabbed window that can contain other views. 
 * It is also responsible for creating the default workflow tab
 */
public class TaskManagerTabView extends JTabbedPane{
	
	//creates a new tab called "Card Overview"
	public TaskManagerTabView() {
		this.setLayout( new BorderLayout() );
		setTabPlacement(TOP);
		setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
		addTab("Workflow", new ImageIcon(), new WorkflowView("Workflow"),
		       "An overview of the task workflow");
	}

}
