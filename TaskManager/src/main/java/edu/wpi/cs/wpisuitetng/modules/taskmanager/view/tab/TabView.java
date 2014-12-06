package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

/**
 * This view creates a tabbed window that can contain other views. 
 * It is also responsible for creating the default workflow tab
 */
public class TabView extends JTabbedPane{
	private static final long serialVersionUID = -8772129104939459349L;
	WorkflowModel workflowModel;
	WorkflowView workflowView;
	
	
	/**
	 * constructs the tabbed pane for viewing all the tabs.
	 * creates tab for the main workflow at index 0
	 * 
	 */
	public TabView() {
		workflowModel = WorkflowController.getWorkflowModel();
		workflowView = new WorkflowView(workflowModel);
		
		this.setLayout( new BorderLayout() );
		setTabPlacement(TOP);
		setTabLayoutPolicy(SCROLL_TAB_LAYOUT);
		addTab("Workflow", new ImageIcon(), workflowView,
		       "An overview of the task workflow");
	}
	
	/**
	 * @return the view that contains the workflow
	 */
	public WorkflowView getWorkflowView(){
		return workflowView;
	}
	
	/**
	 * @param workflowView - set the workflowView
	 */
	public void setWorkflowTab(WorkflowView workflowView) {
		//this.setTabComponentAt(0, workflowView);
	}

}
