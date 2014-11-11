package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ITabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewCardTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ClosableTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewWorkflowTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

public class AddTabController implements ActionListener{
	
	private final TaskManagerTabView view;
	private Component newTab;
	
	public AddTabController(TaskManagerTabView view, Component tab){
		this.view = view;
		this.newTab = tab;
	}
	
	/**
	 * This is usually run on a button press. 
	 * TODO: move this functionality into different methods in this class and call them accordingly
	 * TODO: add functionality to get the right index
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ClosableTabModel tabModel = new ClosableTabModel();
		tabModel.setTabIndex( view.getTabCount() );
		
		if(newTab instanceof NewTaskTab){
			tabModel.setTabTitle("New Task");
	        view.addTab("", newTab);
	        view.setTabComponentAt(tabModel.getTabIndex(), new ClosableTabView(view, tabModel) );
	        
		} else if(newTab instanceof NewCardTab){
			tabModel.setTabTitle("New Card");
			view.addTab("", newTab);
			view.setTabComponentAt(tabModel.getTabIndex(), new ClosableTabView(view, tabModel) );
		} else{
			tabModel.setTabTitle("New Workflow");
			view.addTab("", newTab);
			view.setTabComponentAt(tabModel.getTabIndex(), new ClosableTabView(view, tabModel) );
		}
	}

	public ITabType getNewTab() {
		return (ITabType) newTab;
	}
	
	public TaskManagerTabView getMainView() {
		return view;
	}
}
