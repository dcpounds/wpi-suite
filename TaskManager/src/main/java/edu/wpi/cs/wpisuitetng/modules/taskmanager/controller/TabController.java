package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Component;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.IDisplayModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActivitiesTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ClosableTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.NewStageTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabView;

public class TabController {
	private static TabController instance = new TabController();
	private static TabView tabView;
	private TabType tabType;

	private TabController () {
		tabView = new TabView();
	}

	public static TabController getInstance(){
		return instance;
	}

	public static TabView getTabView(){
		return tabView;
	}
	
	
    public void addTab(TabType tabType, IDisplayModel model) {
    	String tabName = null;
    	Component newTab = null;
    	this.tabType = tabType;
    	switch(tabType){
    		case TASK:
	    		if(model != null){
	    			tabName = model.getTitle();
	    			
	    			//Get ready to abbreviate if necessary
	    			AffineTransform affineTransform = new AffineTransform();
	    			FontRenderContext frc = new FontRenderContext(affineTransform,true,true);
	    			Font font = new Font("Tahoma", Font.PLAIN, 12);
	    			int nameWidth = (int)(font.getStringBounds(tabName,frc).getWidth());
	    			int ellipsisLength = (int)(font.getStringBounds("...", frc).getWidth());
	    			int editLength = (int)(font.getStringBounds("Edit ", frc).getWidth());
	    			
	    			if (nameWidth > 222) {    // Abbreviate if too long
	    				while ((int)(font.getStringBounds(tabName, frc).getWidth()) > 222 - ellipsisLength - editLength) {
	    					tabName = tabName.substring(0,tabName.length()-2);
	    				}
	    				
	    				tabName = "Edit " +tabName + "...   ";
	    			} else {
	    				tabName = tabName + "   ";
	    			}
	    		}else{
	    			tabName = "New Task   ";
	    		}
	        	newTab = new NewTaskTab((TaskModel) model);
	        	break;
    		case STAGE:
    			tabName = "New Stage   ";
    			newTab = new NewStageTab((StageModel) model, ActionType.CREATE);
    			break;
    		case ACTIVITIES:
    			tabName =  (model.getTitle() + " Activities   ");
	        	newTab = new ActivitiesTab((TaskModel) model);
    			break;
        }
    	addTabHelper(tabName, newTab);
    }
    
    public void removeTab(Component tabComponent){
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
    	tabView.setTabComponentAt(index, new ClosableTabView(tabModel, pane, tabType));
    	tabView.setSelectedIndex(index);
    	
    }

}
