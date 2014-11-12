package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewCardTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ClosableTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewWorkflowTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

public class AddTabController implements ActionListener{
   
    private final TaskManagerTabView view;
    private Component newTabPane;
   
    public AddTabController(TaskManagerTabView view, Component tab){
        this.view = view;
        this.newTabPane = tab;
    }
   
    /**
     * This is usually run on a button press.
     * TODO: move this functionality into different methods in this class and call them accordingly
     * TODO: add functionality to get the right index
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	String tabName;
        if(newTabPane instanceof NewTaskTab){
        	tabName = "New Task";
        } else if(newTabPane instanceof NewCardTab){
        	tabName = "New Card";
        } else{
        	tabName = "New Workflow";
        }
    	addNewTab(tabName, newTabPane);
    }
    
    public void addNewTab(String tabTitle, Component pane) {
    	//add a tab containing the newTabPane as a view
    	view.addTab("", pane ); 
    	//Store the index of this tab
    	int index = view.indexOfComponent(pane); 
    	//Instantiate a new closable tab model
    	ClosableTabModel tabModel = new ClosableTabModel(tabTitle, index);
    	//Add the closable tab to the correct tab
    	view.setTabComponentAt(index, new ClosableTabView(view, tabModel, pane) );
    	view.setSelectedIndex(index);
    	
    }
   
    public TaskManagerTabView getMainView() {
        return view;
    }

	//public ITabType getNewTab() {
	//	return (ITabType) newTabPane;
	//}
}
