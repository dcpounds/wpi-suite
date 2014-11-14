package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

/**
 * controller for removing a tab when the close button is pressed
 *
 */
public class RemoveTabController implements ActionListener {
	
	private TaskManagerTabView view;
	private Component tabComponent;
	
	/**
	 * creates the controller
	 * 
	 * @param view - the parent pane that contains all tabs
	 * @param tab - the tab pane that is being removed
	 */
	public RemoveTabController(TaskManagerTabView view, Component tabComponent){
		this.view = view;
		this.tabComponent = tabComponent;
	}

	/**action that is performed when close button is hit.
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		view.remove(tabComponent);
	}
}
