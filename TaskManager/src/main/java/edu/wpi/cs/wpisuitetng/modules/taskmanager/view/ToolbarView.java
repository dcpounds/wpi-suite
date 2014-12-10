/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.SearchController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabType;

import java.awt.Component;

/**
 * Class representing the view of the toolbar at the top of the gui
 */
public class ToolbarView extends JPanel {
    
    private static final long serialVersionUID = 6568533963473785570L;
    private final JButton newStageButton;
    private final JButton newTaskButton;
    private final WorkflowModel workflowModel;
    private JTextField searchBox;
   
    /**
     * Creates a new tool bar based off the main workflow model
     * 
     * @param taskManagerTabView - tab panel containing all the tabs
     */
    public ToolbarView() {
        this.workflowModel = WorkflowController.getWorkflowModel();
        
        ImageIcon stageIcon = new ImageIcon("../TaskManager/src/main/resources/new_req.png");
        ImageIcon taskIcon = new ImageIcon("../TaskManager/src/main/resources/new_itt.png");
        ImageIcon searchIcon = new ImageIcon("../TaskManager/src/main/resources/search.png");
        
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        newStageButton = new JButton("New Stage", stageIcon);
        newStageButton.addActionListener( new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		TabController.getInstance().addTab(TabType.STAGE ,null);
        	}
        });
        newStageButton.setMargin(new Insets(0,0,0,0));
        add(Box.createHorizontalStrut(20));
        add(newStageButton);
		
		newTaskButton = new JButton("New Task", taskIcon);
        newTaskButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addTab(TabType.TASK, null);
			}
		});
        newTaskButton.setMargin(new Insets(0,0,0,0));
        
        add(Box.createHorizontalStrut(20));
        add(newTaskButton);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        add(horizontalStrut);
        
        JButton toggleColor = new JButton("Toggle Urgency Coloring", new ImageIcon("../TaskManager/src/main/resources/urgent.png"));
        toggleColor.setMargin(new Insets(0, 0, 0, 0));
        toggleColor.addActionListener(new ActionListener(){
        	 @Override
     		public void actionPerformed(ActionEvent e) {
     			workflowModel.toggleColor();
     			StageController.locallyUpdateAllStages();
     		}
        });
        add(toggleColor);
        
        Component horizontalStrut2 = Box.createHorizontalStrut(20);
        add(horizontalStrut2);
        JLabel searchLabel = new JLabel();
        searchLabel.setIcon(searchIcon);
        
        add(searchLabel);
        
        searchBox = new JTextField();
        searchBox.setMaximumSize(new Dimension(300, 38));
        
        Font searchFont = new Font("Tahoma",Font.PLAIN,17);
        
        searchBox.setFont(searchFont);
        add(searchBox);
        
        searchBox.addKeyListener(new SearchController(this));
    }

    static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    public JTextField getSearchBox(){
    	return searchBox;
    }
}