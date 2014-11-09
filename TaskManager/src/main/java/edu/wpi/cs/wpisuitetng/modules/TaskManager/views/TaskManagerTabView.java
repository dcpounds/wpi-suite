package edu.wpi.cs.wpisuitetng.modules.TaskManager.views;

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
		setTabPlacement(TOP);
		setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
		setBorder(BorderFactory.createEmptyBorder(5, 3, 3, 3));
		addTab("Card Overview", new ImageIcon(), new WorkflowView("Workflow"),
		       "An overview of the task workflow");
	}

}
