package edu.wpi.cs.wpisuitetng.modules.TaskManager.views;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

/**
 * This class is used to represent a tabbed window with a Workflow view inside of it
 *
 */
public class TaskManagerTabView extends JTabbedPane{
	
	//creates a new tab called "Card Overview"
	public TaskManagerTabView() {
		this.setLayout( new BorderLayout() );
		setTabPlacement(TOP);
		setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
		addTab("Card Overview", new ImageIcon(), new WorkflowView("Workflow"),
		       "An overview of the task workflow");
	}

}
