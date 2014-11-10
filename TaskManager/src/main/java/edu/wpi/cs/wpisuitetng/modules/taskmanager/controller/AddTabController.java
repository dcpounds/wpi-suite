package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
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
	private ITabType tabType;
	
	public AddTabController(TaskManagerTabView view, ITabType tabType ){
		this.view = view;
		this.tabType = tabType;
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
		
		if(tabType instanceof NewTaskTab){
			tabModel.setTabTitle("New Task");
	        view.addTab("",new NewTaskTab(view) );
	        view.setTabComponentAt(tabModel.getTabIndex(), new ClosableTabView(view, tabModel) );
	        
		} else if(tabType instanceof NewCardTab){
			tabModel.setTabTitle("New Card");
			view.addTab("", new NewCardTab(view) );
			view.setTabComponentAt(tabModel.getTabIndex(), new ClosableTabView(view, tabModel) );
		} else{
			tabModel.setTabTitle("New Workflow");
			view.addTab("", new NewWorkflowTab(view) );
			view.setTabComponentAt(tabModel.getTabIndex(), new ClosableTabView(view, tabModel) );
		}
	}

	public ITabType getTabType() {
		return tabType;
	}
	
	public TaskManagerTabView getMainView() {
		return view;
	}
}
