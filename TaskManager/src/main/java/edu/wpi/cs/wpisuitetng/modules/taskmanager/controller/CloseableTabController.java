package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

public class CloseableTabController implements ActionListener {
	private TaskManagerTabView view;
	private int index;
	
	public CloseableTabController(TaskManagerTabView view, int index) {
		this.view = view;
		this.index = index;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.remove(index);
	}

}
