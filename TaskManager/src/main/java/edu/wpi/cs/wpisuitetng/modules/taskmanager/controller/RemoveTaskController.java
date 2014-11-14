package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskContainerView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

public class RemoveTaskController implements ActionListener {
	
	private StageView view;
	private TaskContainerView taskView;
	
	/**
	 * @param view - the parent pane that contains all tabs
	 * @param tab - the tab pane that is being removed
	 */
	public RemoveTaskController(StageView view, TaskContainerView taskView){
		this.view = view;
		this.taskView = taskView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.removeTaskView(taskView);
	}
}
