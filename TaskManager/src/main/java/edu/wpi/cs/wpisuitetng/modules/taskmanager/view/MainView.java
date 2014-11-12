package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

/**
 * @author alec
 * MainView is a wrapper class that holds all of the JPanels in the main section of the interface
 */
public class MainView extends JPanel{


	private static final long serialVersionUID = -2682288714623307153L;
	
	WorkflowListModel workflowListModel;
	WorkflowModel defaultWorkflowModel;
	SplitPaneView splitPane;
	
	public MainView(WorkflowListModel workflowListModel, TaskManagerTabView mainTabView){
		setLayout(new BorderLayout() );
		this.workflowListModel = workflowListModel;
		defaultWorkflowModel = new WorkflowModel("Default Workflow");
		workflowListModel.addWorkflow(defaultWorkflowModel);
		
		this.splitPane = new SplitPaneView(workflowListModel, mainTabView);
		this.add(splitPane, BorderLayout.CENTER);
	}

}
