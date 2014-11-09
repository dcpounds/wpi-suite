package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.JPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;

/**
 * @author alec
 * MainView is a wrapper class that holds all of the JPanels in the main section of the interface
 */
public class MainView extends JPanel{


	private static final long serialVersionUID = -2682288714623307153L;
	
	WorkflowListModel workflowListModel;
	SplitPaneView splitPane;
	
	public MainView(WorkflowListModel workflowListModel ){
		this.workflowListModel = workflowListModel;
		 this.splitPane = new SplitPaneView(workflowListModel);
	}

}
