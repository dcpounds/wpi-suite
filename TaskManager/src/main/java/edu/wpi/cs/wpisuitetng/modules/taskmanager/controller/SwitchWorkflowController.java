/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

/**
 * @author dave
 *
 */
public class SwitchWorkflowController implements ListSelectionListener {
	TaskManagerTabView taskManagerTabView;
	JList<WorkflowListModel> workflowList;
	WorkflowListModel workflowListModel;
	
	public SwitchWorkflowController(TaskManagerTabView taskManagerTabView, JList<WorkflowListModel> workflowList, WorkflowListModel workflowListModel){
		this.taskManagerTabView = taskManagerTabView;
		this.workflowList = workflowList;
		this.workflowListModel = workflowListModel;
	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index = workflowList.getSelectedIndex();
		if(index >= 0){
			WorkflowModel workflowModel = workflowListModel.getWorkflowModel(index);
			WorkflowView workflowView = new WorkflowView(workflowModel);
			taskManagerTabView.setWorkflowView(workflowView);
			
		}
	}

}
