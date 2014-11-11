package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewCardView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ClosableTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewWorkflowView;
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
        if(newTabPane instanceof NewTaskView){
        	addNewTab("New Task", new NewTaskView(view) );
        } else if(newTabPane instanceof NewCardView){
        	addNewTab("New Card", new NewCardView(view) );
        } else{
        	addNewTab("New Workflow", new NewWorkflowView(view) );
        }
    }
    
    public void addNewTab(String tabTitle, Component pane) {
    	//add a tab containing the newTabPane as a view
    	view.addTab("", pane ); 
    	//Store the index of this tab
    	int index = view.indexOfComponent(pane); 
    	//Instantiate a new closable tab model
    	ClosableTabModel tabModel = new ClosableTabModel("New Task", index);
    	//Add the closable tab to the correct tab
    	view.setTabComponentAt(index, new ClosableTabView(view, tabModel, pane) );
    	view.setSelectedIndex(index);
    	
    }
   
    public TaskManagerTabView getMainView() {
        return view;
    }
    
}
