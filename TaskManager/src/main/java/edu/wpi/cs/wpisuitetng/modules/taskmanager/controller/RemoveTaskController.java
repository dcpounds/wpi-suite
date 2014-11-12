package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.CardView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

public class RemoveTaskController implements ActionListener {
	
	private CardView view;
	private TaskView taskView;
	
	/**
	 * @param view - the parent pane that contains all tabs
	 * @param tab - the tab pane that is being removed
	 */
	public RemoveTaskController(CardView view, TaskView taskView){
		this.view = view;
		this.taskView = taskView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.removeTaskView(taskView);
	}
}
