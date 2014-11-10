package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

/**
 * @author alec
 *  This controller is responsible for performing the action
 *  that closes a tab
 */
public class ClosableTabController implements ActionListener {
	private TaskManagerTabView view;
	private int index;
	
	public ClosableTabController(TaskManagerTabView view, ClosableTabModel tab) {
		this.view = view;
		this.index = tab.getTabIndex();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.remove(index);
	}

}
