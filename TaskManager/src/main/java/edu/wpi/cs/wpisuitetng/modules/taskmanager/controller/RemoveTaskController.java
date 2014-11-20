package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;


/**
 * controller for deleting a task when the delete task button is hit
 * TODO: make it so it updates the models also
 *
 */
public class RemoveTaskController implements ActionListener {
	private WorkflowModel workflowModel;
	private WorkflowView workflowView;
	private TaskModel taskModel;
	private StageView stageView;
	private TaskView taskView;
	
	/**
	 * construct the controller
	 * 
	 * @param view -stage view that contains the task view
	 * @param taskView 
	 */
	public RemoveTaskController(TaskModel taskModel, TaskView taskView){
		TabView tabView = TabController.getTabView();
		this.workflowView = tabView.getWorkflowView();
		workflowModel = WorkflowController.getWorkflowModel();
		this.taskModel = taskModel;
		this.taskView = taskView;
	}

	/** action performed when delete task button is hit
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		workflowModel.removeTask(taskModel);
		workflowView.removeTaskView(taskView);
	}
}
