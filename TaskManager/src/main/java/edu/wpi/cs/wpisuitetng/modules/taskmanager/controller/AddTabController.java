package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ITabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewCardTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
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
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(tabType instanceof NewTaskTab){
			String tabName = new String("New Task");
	        view.addTab(tabName,new NewTaskTab() );
		} else if(tabType instanceof NewCardTab){
			String tabName = new String("New Card");
	        view.addTab(tabName,new NewCardTab() );
		} else{
			String tabName = new String("New Workflow");
	        view.addTab(tabName,new NewCardTab() );
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
