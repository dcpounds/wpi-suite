package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewStageTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ClosableTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

/**
 * controller for adding a new tab to the tab pane
 *
 */
public class AddTabController implements ActionListener{
   
    private final TaskManagerTabView view;
    private TabType tabType;
    private final WorkflowModel workflowModel;
   
    /**
     * construct the controller
     * 
     * @param view -main tab view
     * @param tabType -type of tab
     * @param workflowModel -main workflow model
     */
    public AddTabController(TaskManagerTabView view, TabType tabType, WorkflowModel workflowModel, String id){
    	this.workflowModel = workflowModel;
        this.view = view;
        this.tabType = tabType;
    }
   
    /**
     * This is run on a button press for the creation of a tab.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	String tabName = null;
    	Component newTab = null;
    	
    	switch(tabType){
    	case TASK:
        	tabName = "New Task ";
        	newTab = new NewTaskTab(view, workflowModel, (TaskModel) model);
        	break;
    	case STAGE:
        	tabName = "New Stage ";
        	newTab = new NewStageTab(view, workflowModel, (StageModel) model);
        	break;
        }
    	addNewTab(tabName, newTab);
    }
    
    /**
     * helper function for adding tab
     * 
     * @param tabTitle -name of tab
     * @param pane -
     */
    private void addNewTab(String tabTitle, Component pane) {
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
   
    /**
     * get main view of tabs
     * 
     * @return
     */
    public TaskManagerTabView getMainView() {
        return view;
    }

    /**
     * get main workflow model
     * 
     * @return
     */
    WorkflowModel getWorkflowModel() {
        return this.workflowModel;
    }
}
