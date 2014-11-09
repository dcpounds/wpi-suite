package edu.wpi.cs.wpisuitetng.modules.TaskManager.views;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

public class TaskManagerToolbarView extends JTabbedPane{
	
	public TaskManagerToolbarView() {
			setTabPlacement(TOP);
			setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
			setBorder(BorderFactory.createEmptyBorder(5, 3, 3, 3));
			addTab("Dashboard", new ImageIcon(), new WorkflowView("Workflow"),
			      "This is where your workflow is");
	}

}
