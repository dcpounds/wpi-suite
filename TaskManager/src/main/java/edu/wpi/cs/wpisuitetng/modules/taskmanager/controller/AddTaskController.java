package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.CardView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

/**
 * @author alec
 * A contoller that is used to add a task to the workflow
 */
public class AddTaskController implements ActionListener {
	private CardView cardView;
	private TaskView taskView;
	
	
	/**
	 * @param tabView - the taskManagerTabView that contains the workFlow to add the task to
	 * @param taskModel - the Task item to add to the workflow.
	 * @param cardIndex - the index of the card to add the task to
	 * 
	 * TODO: do checks to make sure that the user provided a valid index
	 */
	public AddTaskController(TaskManagerTabView tabView, TaskModel taskModel,int cardIndex){
		 cardView = tabView.getWorkflowView().getCardViewList().get(cardIndex);
		 taskView = new TaskView(taskModel);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		cardView.add(taskView);
	}

}
