package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Dimension;



import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.GetWorkflowsController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.SwitchWorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

/**
 * @author alec && dave
 * This is the pane that contains a list of workflows to switch between
 */
public class WorkflowListView extends JPanel {
	private final JList<WorkflowListModel> workflowList;
	private final WorkflowListModel workflowListModel;
	private final JScrollPane workflowListPane;
	private final JButton btnRefresh;
	private final TaskManagerTabView taskManagerTabView;
	
	public WorkflowListView(WorkflowListModel workflowListModel, TaskManagerTabView taskManagerTabView) {
		this.taskManagerTabView = taskManagerTabView;
		
		//The model for the list of workflows
		this.workflowListModel = workflowListModel;
		
		//Create a new JList of workflow models
		this.workflowList = new JList<WorkflowListModel>(workflowListModel);
		
		//Set the font of the list
		this.workflowList.setFont(workflowList.getFont().deriveFont(11));
		
		// Notify a change in selection
        this.workflowList.addListSelectionListener(new SwitchWorkflowController(taskManagerTabView, workflowList, workflowListModel));
		
		
		// Construct the refresh button and add it to this panel
        btnRefresh = new JButton("Refresh");
        
        // Add the get messages controller to the button
        GetWorkflowsController getWorkflowsController = new GetWorkflowsController(workflowListModel);
        btnRefresh.addActionListener(getWorkflowsController);
        
		//Create the scrollPane to put the list inside
		this.workflowListPane = new JScrollPane(workflowList); //add the list of workflows as a parameter later
		workflowListPane.setPreferredSize(new Dimension( 250, 500));
		
		
		//Set the workflow manager of this panel that controls the positions of the components
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		JLabel title = new JLabel("Workflows");
		this.add(title);
		add(Box.createVerticalStrut(20)); // leave a 20 pixel gap
		this.add(btnRefresh);
		add(Box.createVerticalStrut(20)); // leave a 20 pixel gap
		this.add(workflowListPane);
	}
	
	
    JList<WorkflowListModel> getWorkflowList() {
        return this.workflowList;
    }
   
    WorkflowListModel getWorkflowListModel() {
        return this.workflowListModel;
    }
    
    /**
     * @return the pane that contains the list of workflows
     */
    JScrollPane getWorkflowListPane() {
        return this.workflowListPane;
    }
   

}
