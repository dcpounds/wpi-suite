package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

public class RemoveTabController implements ActionListener {
	
	private TaskManagerTabView view;
	private Component tabComponent;
	
	/**
	 * @param view - the parent pane that contains all tabs
	 * @param tab - the tab pane that is being removed
	 */
	public RemoveTabController(TaskManagerTabView view, Component tabComponent){
		this.view = view;
		this.tabComponent = tabComponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.remove(tabComponent);
	}
}
