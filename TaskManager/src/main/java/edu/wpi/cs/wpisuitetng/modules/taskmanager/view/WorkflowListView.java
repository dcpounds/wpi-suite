package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;

/**
 * @author alec && dave
 * This is the pane that contains a list of workflows to switch between
 */
public class WorkflowListView extends JPanel {
	private final JList<WorkflowListModel> workflowList;
	private final WorkflowListModel workflowListModel;
	private final JScrollPane workflowListPane;
	
	public WorkflowListView(WorkflowListModel workflowListModel) {
		
		//The model for the list of workflows
		this.workflowListModel = workflowListModel;
		
		//Create a new JList of workflow models
		this.workflowList = new JList<WorkflowListModel>(workflowListModel);
		
		//Set the font of the list
		this.workflowList.setFont(workflowList.getFont().deriveFont(11));
		
		//Create the scrollPane to put the list inside
		this.workflowListPane = new JScrollPane(workflowList); //add the list of workflows as a paramter later
		workflowListPane.setPreferredSize(new Dimension( 200, 600));
		
		//Set the workflow manager of this panel that controls the positions of the components
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		JLabel title = new JLabel("Workflows");
		this.add(title);
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
