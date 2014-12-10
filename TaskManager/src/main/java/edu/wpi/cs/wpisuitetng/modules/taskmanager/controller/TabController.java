
/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Component;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.IDisplayModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActivitiesTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ClosableTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.IHashableTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.NewStageTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ReportsTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabView;

/**
 * @author Team 4
 * This class is responsible for handling tab behavior
 * It is used for adding and removing tabs and truncating long tab titles
 */
public class TabController {
	private static TabController instance = new TabController();
	private static TabView tabView;
	private TabType tabType;
	private static Map<String, Component> uniqueTabs;

	private TabController () {
		tabView = new TabView();
		uniqueTabs = new HashMap<String, Component>();
	}

	public static TabController getInstance(){
		return instance;
	}

	public static TabView getTabView(){
		return tabView;
	}
	
	/**
	 * 
	 * Adds a unique tab based off the given tabType and id of the model given
	 * If there is already a tab for the given model, it will set the current selected tab to that model
	 * 
	 * @param tabType
	 * @param model
	 */
	public void addUniqueTab(TabType tabType, IDisplayModel model){
		Component pane;
		String tabKey = generateTabKey(tabType, model.getID());
		if(uniqueTabs.containsKey(tabKey)){
	    	pane = uniqueTabs.get(tabKey);
	    	int index = tabView.indexOfComponent(pane); 
	    	tabView.setSelectedIndex(index);
		} else {
	    	String tabTitle;
	    	tabTitle = generateTabTitle(tabType, model);
	    	pane = generateTab(tabType, model);
	    	uniqueTabs.put(tabKey, pane);
	    	tabView.addTab("", pane); 
	    	int index = tabView.indexOfComponent(pane); 
	    	ClosableTabModel tabModel = new ClosableTabModel(tabTitle, index);
	    	tabView.setTabComponentAt(index, new ClosableTabView(tabModel, (IHashableTab) pane, tabType));
	    	tabView.setSelectedIndex(index);
		}
	}
	
	
    public void addTab(TabType tabType, IDisplayModel model) {
    	String tabTitle = generateTabTitle(tabType, model);
    	Component pane = generateTab(tabType, model);

    	//add a tab containing the newTabPane as a view
    	tabView.addTab("", pane); 
    	//Store the index of this tab
    	int index = tabView.indexOfComponent(pane); 
    	//Instantiate a new closable tab model
    	ClosableTabModel tabModel = new ClosableTabModel(tabTitle, index);
    	//Add the closable tab to the correct tab
    	tabView.setTabComponentAt(index, new ClosableTabView(tabModel, (IHashableTab) pane, tabType));
    	tabView.setSelectedIndex(index);
    }
    

    
    public void removeTab(IHashableTab tabComponent){
    	String tabKey = generateTabKey(tabComponent.getTabType(), tabComponent.getModelID());
    	if(uniqueTabs.containsKey(tabKey)){
    		uniqueTabs.remove(tabKey);
    	}
    	tabView.remove((Component) tabComponent);
    }
    
    private Component generateTab(TabType tabType, IDisplayModel model){
    	Component newTab = null;
    	switch(tabType){
    		case TASK:
	        	newTab = new NewTaskTab((TaskModel) model);
	        	break;
    		case STAGE:
    			newTab = new NewStageTab((StageModel) model, ActionType.CREATE);
    			break;
    		case ACTIVITIES:
	        	newTab = new ActivitiesTab((TaskModel) model);
    			break;
    		case REPORTS:
    			newTab = new ReportsTab("Reports");
    			break;
        }
    	return newTab;
    }
    
    private String generateTabTitle(TabType tabType, IDisplayModel model){
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
	    				
	    				tabName = "Edit " + tabName + "...   ";
	    			} else {
	    				tabName = "Edit " + tabName + "   ";
	    			}
	    		}else{
	    			tabName = "New Task   ";
	    		}
	        	break;
    		case STAGE:
    			tabName = "New Stage   ";
    			break;
    		case ACTIVITIES:
    			if(model.getTitle().length() > 34)
    				tabName =  (model.getTitle().substring(0,34) + "... Activities   ");
    			else
    				tabName = (model.getTitle() + " Activities   ");
    			break;
    		case REPORTS:
    			tabName = "Reports	";
    			break;
        }
    	return tabName;
    }
    
    
    private String generateTabKey(TabType tabType, int ID){
    	return tabType.toString() + ID;
    }
    
}
