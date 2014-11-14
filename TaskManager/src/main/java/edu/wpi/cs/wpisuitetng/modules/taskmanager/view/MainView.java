package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.InitializeWorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

/**
 * MainView is a wrapper class that holds all of the JPanels in the main section of the interface
 */
public class MainView extends JPanel{


	private static final long serialVersionUID = -2682288714623307153L;
	
	WorkflowModel workflowModel;
	
	/**
	 * Constructs the main view
	 * 
	 * @param model -the main workflow model
	 * @param mainTabView -the panel containing all the tabs
	 */
	public MainView(WorkflowModel model, TaskManagerTabView mainTabView){
		setLayout(new BorderLayout());
		workflowModel = model;
		workflowModel.addUserToList(new UserModel());
		
		//InitializeWorkflowController initializeController = new InitializeWorkflowController(workflowModel);
		//initializeController.waitForResponse();

		WorkflowView view = new WorkflowView(workflowModel);
		mainTabView.setWorkflowTab(view);
		add(mainTabView, BorderLayout.CENTER);
	}

}
