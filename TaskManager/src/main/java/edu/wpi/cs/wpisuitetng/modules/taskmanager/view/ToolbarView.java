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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.SearchController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.ArchiveController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabType;

import java.awt.Component;

import net.miginfocom.swing.MigLayout;

import javax.swing.JCheckBox;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class representing the view of the toolbar at the top of the gui
 */
public class ToolbarView extends JPanel {
    
    private static final long serialVersionUID = 6568533963473785570L;
    private final JButton newStageButton;
    private final JButton newTaskButton;
    private final JToggleButton archiveButton;
    private final JButton reportsButton;
    private final WorkflowModel workflowModel;
    private JTextField searchBox;
	private JButton gitButton;
	private JCheckBox caseSensitivityToggle;
	private boolean caseSensitive = false;
	private JPanel catPanel;
	private JCheckBox greenBox;
	private JCheckBox whiteBox;
	private JCheckBox brownBox;
	private JCheckBox grayBox;
	private JCheckBox redBox;
	private JCheckBox pinkBox;
	private JCheckBox orangeBox;
	private JCheckBox yellowBox;
	private JCheckBox blueBox;
	private JCheckBox purpleBox;
	private JCheckBox chckbxFilter;
   
    /**
     * Creates a new tool bar based off the main workflow model
     * 
     * @param taskManagerTabView - tab panel containing all the tabs
     */
    public ToolbarView() {
        this.workflowModel = WorkflowController.getWorkflowModel();
        
        ImageIcon taskIcon = new ImageIcon(this.getClass().getResource("new_itt.png"));
        ImageIcon archiveIcon = new ImageIcon(this.getClass().getResource("recycle_bin.png"));
        ImageIcon stageIcon = new ImageIcon(this.getClass().getResource("new_req.png"));
        ImageIcon reportsIcon = new ImageIcon(this.getClass().getResource("new_report.png"));
        ImageIcon categoryIcon = new ImageIcon(this.getClass().getResource("Colors.png"));
        ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("search.png"));
        ImageIcon gitIcon = new ImageIcon(this.getClass().getResource("github.png"));
        
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
        
        
		
		newTaskButton = new JButton("New Task");
		newTaskButton.setIcon(taskIcon);
        newTaskButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addTab(TabType.TASK, null);
			}
		});
        newTaskButton.setMargin(new Insets(0,0,0,0));
        
        add(Box.createHorizontalStrut(20));
        add(newTaskButton);
        
        reportsButton = new JButton("Reports");
        reportsButton.setIcon(reportsIcon);
        reportsButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addTab(TabType.REPORTS, null);
			}
		});
        reportsButton.setMargin(new Insets(0,0,0,0));
        
        add(Box.createHorizontalStrut(20));
        add(reportsButton);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        add(horizontalStrut);
        
        JButton toggleColor = new JButton("Toggle Category Coloring");
        toggleColor.setIcon(categoryIcon);
        toggleColor.setMargin(new Insets(0, 0, 0, 0));
        toggleColor.addActionListener(new ActionListener(){
        	 @Override
     		public void actionPerformed(ActionEvent e) {
     			workflowModel.toggleColor();
     			StageController.locallyUpdateAllStages();
     		}
        });
        add(toggleColor);
        
        archiveButton = new JToggleButton("View Archives");
        archiveButton.setIcon(archiveIcon);
        archiveButton.addChangeListener(ArchiveController.getInstance());
        archiveButton.setMargin(new Insets(0,0,0,0));
        add(Box.createHorizontalStrut(20));
        add(archiveButton);
        
        add(Box.createHorizontalStrut(20));
        
        gitButton = new JButton("Import from GitHub");
        gitButton.setIcon(gitIcon);
        gitButton.setMargin(new Insets(0,0,0,0));
        gitButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addTab(TabType.GIT, null);
			}
		});
        add(gitButton);
        
        
        Component horizontalStrut2 = Box.createHorizontalStrut(20);
        add(horizontalStrut2);
        JLabel searchLabel = new JLabel();
        searchLabel.setIcon(searchIcon);
        
        add(searchLabel);
        
        searchBox = new JTextField();
        searchBox.setMaximumSize(new Dimension(300, 38));
        searchBox.setOpaque(true);
        
        Font searchFont = new Font("Tahoma",Font.PLAIN,17);
        
        searchBox.setFont(searchFont);
        add(searchBox);
        
        searchBox.addKeyListener(new SearchController(this));
        
                caseSensitivityToggle = new JCheckBox("Case Sensitive");
        caseSensitivityToggle.setSelected(false);
        caseSensitivityToggle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		caseSensitive = !caseSensitive;
        		SearchController.search();
        	}
        });
        add(caseSensitivityToggle);
        
        add(Box.createHorizontalStrut(20));
        
        catPanel = new JPanel();
        catPanel.setAlignmentY(0.45f);
        catPanel.setMaximumSize(new Dimension(135, 60));
        add(catPanel);
        catPanel.setLayout(new MigLayout("", "[][][][][]", "[][][]"));
        
        greenBox = new JCheckBox("");
        greenBox.setToolTipText("GREEN");
        greenBox.setBackground(new Color(0x82CA9D));
        catPanel.add(greenBox, "cell 0 0");
        
        brownBox = new JCheckBox("");
        brownBox.setToolTipText("BROWN");
        brownBox.setBackground(new Color(0xA67C52));
        catPanel.add(brownBox, "cell 1 0");
        
        redBox = new JCheckBox("");
        redBox.setBackground(new Color(0xF7977A));
        redBox.setToolTipText("RED");
        catPanel.add(redBox, "cell 2 0");
        
        pinkBox = new JCheckBox("");
        pinkBox.setBackground(new Color(0xF49AC2));
        pinkBox.setToolTipText("PINK");
        catPanel.add(pinkBox, "cell 3 0");
        
        orangeBox = new JCheckBox("");
        orangeBox.setToolTipText("ORANGE");
        orangeBox.setBackground(new Color(0xFDC68A));
        catPanel.add(orangeBox, "cell 4 0");
        
        whiteBox = new JCheckBox("");
        whiteBox.setBackground(Color.WHITE);
        whiteBox.setToolTipText("WHITE");
        catPanel.add(whiteBox, "cell 0 1");
        
        yellowBox = new JCheckBox("");
        yellowBox.setBackground(new Color(0xFFF79A));
        yellowBox.setToolTipText("YELLOW");
        catPanel.add(yellowBox, "cell 1 1");
        
        blueBox = new JCheckBox("");
        blueBox.setToolTipText("BLUE");
        blueBox.setBackground(new Color(0x8493CA));
        catPanel.add(blueBox, "cell 2 1");
        
        purpleBox = new JCheckBox("");
        purpleBox.setBackground(new Color(0xA187BE));
        purpleBox.setToolTipText("PURPLE");
        catPanel.add(purpleBox, "cell 3 1");
        
        grayBox = new JCheckBox("");
        grayBox.setBackground(Color.LIGHT_GRAY);
        grayBox.setToolTipText("GRAY");
        catPanel.add(grayBox, "cell 4 1");
        
        chckbxFilter = new JCheckBox("filter");
        chckbxFilter.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent e) {
		        //insert method
		      }
		    });
        add(chckbxFilter);
        
        
    }

    static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    /**
     * Retrieve the searchbox component
     * @return
     */
    public JTextField getSearchBox(){
    	return searchBox;
    }
    
    /**
     * Retrieve the case sensitivity status
     * @return caseSensitive
     */
    public boolean getCaseSensitive() {
    	return caseSensitive;
    }
    
    /**
     * Retrieve the JCheckBox component
     * @return filter checkbox
     */
    public JCheckBox getCatFilter(){
    	return chckbxFilter;
    }
    
    /**
     * @return ArrayList of checked category selection boxes
     */
    public ArrayList<Color> getSelectedColorArray(){
    	ArrayList<Color> colors = new ArrayList<Color>();
    	if(grayBox.isSelected())
    		colors.add(grayBox.getBackground());
    	if(whiteBox.isSelected())
    		colors.add(whiteBox.getBackground());
    	if(brownBox.isSelected())
    		colors.add(brownBox.getBackground());
    	if(redBox.isSelected())
    		colors.add(redBox.getBackground());
    	if(pinkBox.isSelected())
    		colors.add(pinkBox.getBackground());
    	if(orangeBox.isSelected())
    		colors.add(orangeBox.getBackground());
    	if(yellowBox.isSelected())
    		colors.add(yellowBox.getBackground());
    	if(greenBox.isSelected())
    		colors.add(greenBox.getBackground());
    	if(blueBox.isSelected())
    		colors.add(blueBox.getBackground());
    	if(purpleBox.isSelected())
    		colors.add(purpleBox.getBackground());
    	return colors;
    }
    
}