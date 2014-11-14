package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskContainerView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;


/**
 * controller for deleting a task when the delete task button is hit
 * TODO: make it so it updates the models also
 *
 */
public class RemoveTaskController implements ActionListener {
	
	private StageView view;
	private TaskContainerView taskView;
	
	/**
	 * construct the controller
	 * 
	 * @param view -stage view that contains the task view
	 * @param taskView 
	 */
	public RemoveTaskController(StageView view, TaskContainerView taskView){
		this.view = view;
		this.taskView = taskView;
	}

	/** action performed when delete task button is hit
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		view.removeTaskView(taskView);
	}
}
