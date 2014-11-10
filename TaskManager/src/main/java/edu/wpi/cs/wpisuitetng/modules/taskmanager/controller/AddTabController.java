package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ITabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewCardTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

public class AddTabController implements ActionListener{
	
	private final TaskManagerTabView view;
	private String tabName;
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
		int index = view.getTabCount();
		
		if(tabType instanceof NewTaskTab){
			String tabName = new String("New Task");
	        view.addTab(tabName,new NewTaskTab(view) );
	        view.setTabComponentAt(index, new TabView(view, tabName) );
	        
		} else if(tabType instanceof NewCardTab){
			String tabName = new String("New Card");
	        view.addTab(tabName,new NewCardTab(view) );
		} else{
			String tabName = new String("New Workflow");
	        view.addTab(tabName,new NewCardTab(view) );
		}
	}

	public ITabType getTabType() {
		return tabType;
	}
	
	public TaskManagerTabView getMainView() {
		return view;
	}
	
	public String getTabName(){
		return tabName;
	}
	

}
