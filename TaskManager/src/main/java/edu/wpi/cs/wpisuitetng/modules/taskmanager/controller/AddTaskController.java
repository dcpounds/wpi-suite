package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskView;
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
	private TaskManagerTabView tabView;
	private NewTaskView taskCreationView;
	

	/**
	 * @param tabView - the main JTabbedPane that holds the tab
	 * @param componentToClose - the component to close when the action is called
	 * @param taskModel - the taskModel to add to the card
	 * @param cardIndex - the index of the card to remove
	 */
	public AddTaskController(TaskManagerTabView tabView, NewTaskView taskCreationView, int cardIndex){
		//Parent tab pane
		this.tabView = tabView;
		//Tab the request was made on
		this.taskCreationView = taskCreationView;
		//Get the card with the given cardIndex
		cardView = tabView.getWorkflowView().getCardViewList().get(cardIndex);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		TaskModel taskModel = new TaskModel( taskCreationView.getTitleLabelText(), taskCreationView.getDescriptionText(), taskCreationView.getStatusText() );
		this.taskView = new TaskView(taskModel);
		
		cardView.addTaskView(taskView);
	}

}
