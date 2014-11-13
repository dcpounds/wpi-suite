package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewCardTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ClosableTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

public class AddTabController implements ActionListener{
   
    private final TaskManagerTabView view;
    private TabType tabType;
    private final WorkflowModel workflowModel;
   
    public AddTabController(TaskManagerTabView view, TabType tabType, WorkflowModel workflowModel){
    	this.workflowModel = workflowModel;
        this.view = view;
        this.tabType = tabType;
    }
   
    /**
     * This is usually run on a button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	String tabName = null;
    	Component newTab = null;
    	
    	switch(tabType){
    	case TASK:
        	tabName = "New Task";
        	newTab = new NewTaskTab(view, workflowModel);
        	break;
    	case CARD:
        	tabName = "New Card";
        	newTab = new NewCardTab(view, workflowModel);
        	break;
        }
    	addNewTab(tabName, newTab);
    }
    
    public void addNewTab(String tabTitle, Component pane) {
    	//add a tab containing the newTabPane as a view
    	view.addTab("", pane); 
    	//Store the index of this tab
    	int index = view.indexOfComponent(pane); 
    	//Instantiate a new closable tab model
    	ClosableTabModel tabModel = new ClosableTabModel(tabTitle, index);
    	//Add the closable tab to the correct tab
    	view.setTabComponentAt(index, new ClosableTabView(view, tabModel, pane));
    	view.setSelectedIndex(index);
    	
    }
   
    public TaskManagerTabView getMainView() {
        return view;
    }

    WorkflowModel getWorkflowModel() {
        return this.workflowModel;
    }
}
