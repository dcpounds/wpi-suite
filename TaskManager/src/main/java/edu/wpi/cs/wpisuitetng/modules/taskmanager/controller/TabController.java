package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ClosableTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewStageTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;

public class TabController {
	private static TabController instance = new TabController();
	private static TabView tabView;

	private TabController () {
		tabView = new TabView();
	}

	public static TabController getInstance(){
		return instance;
	}

	public static TabView getTabView(){
		return tabView;
	}
	
	
    public void addTab(TabType tabType, Object model) {
    	String tabName = null;
    	Component newTab = null;
    	
    	switch(tabType){
    	case TASK:
        	tabName = "New Task ";
        	newTab = new NewTaskTab((TaskModel) model);
        	break;
    	case STAGE:
        	tabName = "New Stage ";
        	newTab = new NewStageTab((StageModel) model);
        }
    	addTabHelper(tabName, newTab);
    }
    
    public void removeTab(Component tabComponent){
    	if(tabComponent instanceof NewTaskTab){
    		((NewTaskTab) tabComponent).getTaskModel().setEditState(false); 		
    	}
    	tabView.remove(tabComponent);
    }
    
    /**
     * helper function for adding tab
     * 
     * @param tabTitle -name of tab
     * @param pane -
     */
    private void addTabHelper(String tabTitle, Component pane) {
    	//add a tab containing the newTabPane as a view
    	tabView.addTab("", pane); 
    	//Store the index of this tab
    	int index = tabView.indexOfComponent(pane); 
    	//Instantiate a new closable tab model
    	ClosableTabModel tabModel = new ClosableTabModel(tabTitle, index);
    	//Add the closable tab to the correct tab
    	tabView.setTabComponentAt(index, new ClosableTabView(tabModel, pane));
    	tabView.setSelectedIndex(index);
    	
    }

}
