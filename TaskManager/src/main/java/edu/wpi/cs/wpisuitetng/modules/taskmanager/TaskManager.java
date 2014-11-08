package edu.wpi.cs.wpisuitetng.modules.taskmanager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;

public class TaskManager implements IJanewayModule {
	/**
     * A list of tabs owned by this module
     */
    List<JanewayTabModel> tabs;
    
    /**
     * Variables, used for tests
     */
    LayoutListModel layoutListModel;
    ToolbarView toolbarView;
    MainView mainView;
    JanewayTabModel tab1;
    
    /**
     * Constructs the main views for this module. Namely one tab, containing
     * a toolbar and a main content panel.
     */
    public TaskManager() {
        
        // Initialize the model that holds the messages
        this.layoutListModel = new LayoutListModel();
        
        // Initialize the list of tabs (however, this module has only one tab)
        this.tabs = new ArrayList<JanewayTabModel>();
        
        // Create a JPanel to hold the toolbar for the tab
        this.toolbarView = new ToolbarView(this.layoutListModel);
        
        // Constructs and adds the MainPanel
        this.mainView = new MainView(this.layoutListModel);
        
        // Create a tab model that contains the toolbar panel and the main content panel
        this.tab1 = new JanewayTabModel(getName(), new ImageIcon(), this.toolbarView, this.mainView);
        
        // Add the tab to the list of tabs owned by this module
        this.tabs.add(tab1);
    }
	
	public String getName(){
		return "TaskManager";
	}

	public List<JanewayTabModel> getTabs(){
		return tabs;
	}
}
