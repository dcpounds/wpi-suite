/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.ArchiveController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.ExpandTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop.DragTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabType;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;

/**
 * @author Alec
 * An accordian style expandable task view that can be inserted into the stages
 */
/**
 * @author dave
 *
 */
public class TaskView extends DragTaskPanel{
	private static final long serialVersionUID = 6517799529927334536L;
	private TaskModel taskModel;
	private JPanel taskContents;
	private JPanel titlePanel;
	private JLabel lblNewTask;
	private JScrollPane taskContentPane;
	private JButton btnEdit;
	private JButton btnActivities;
	private JTextArea dateArea;
	private JLabel lblEstimatedEffort;
	private JLabel lblActualEffort;
	private JLabel lblDue;
	private JTextArea descriptionField;
	private DefaultListModel<String> assignedListModel;
	private StageView stageView;
	private int id;
	private static final int openSize = 250;
	private static final int closeSize = 45;
	private JLabel statusLabel;
	private JPanel catPanel;
	private JButton btnRestore;
	private JButton closeButton;
	
	public TaskView(TaskModel taskModel, StageView stageView){
		setLayout(new MigLayout("", "[][grow][][]", "[][][][grow][]"));
		setForeground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.stageView = stageView;
		this.taskModel = taskModel;
		this.id = taskModel.getID();
		
		
		//Sets the parameters of the panel that holds the title
		titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBackground(Color.LIGHT_GRAY);
		setBackground(Color.LIGHT_GRAY);
		addMouseListener(  new ExpandTaskController(this, taskModel) );
		add(titlePanel, "cell 0 0 5 1,growx,aligny top");
		titlePanel.setLayout(new MigLayout("", "[30.00][10.00,grow][][grow][][]", "[][grow]"));
		
		catPanel = new JPanel();
		catPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		titlePanel.add(catPanel, "cell 0 0,alignx left,aligny center");
		catPanel.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout = (FlowLayout) catPanel.getLayout();
		

		statusLabel = new JLabel("!!!");
		statusLabel.setFont(new Font("Impact", Font.BOLD, 18));
		titlePanel.add(statusLabel, "cell 1 0,alignx left,aligny top");
		
		//Sets the title of the task
		lblNewTask = new JLabel(taskModel.getTitle());
		lblNewTask.putClientProperty("html.disable", Boolean.TRUE);
		lblNewTask.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titlePanel.add(lblNewTask, "cell 2 0,alignx leading,aligny top");
		
		
		
		//The beginning of the taskContents section
		//The scrollPane that the task contents are surrounded by
		this.taskContents = new JPanel(){
			public Dimension getPreferredSize() {
            	return new Dimension(this.getParent().getWidth()-30,super.getPreferredSize().height);
	    }};
	    
		this.taskContentPane = new JScrollPane(taskContents,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskContents.setBackground(Color.WHITE);
		taskContentPane.setViewportView(taskContents);
		taskContents.setLayout(new BoxLayout(taskContents, BoxLayout.Y_AXIS));
		
		lblDue = new JLabel();
		lblDue.setVerticalAlignment(SwingConstants.TOP);
		lblDue.setAlignmentY(Component.TOP_ALIGNMENT);
		lblDue.setHorizontalAlignment(SwingConstants.LEFT);
		lblDue.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblDue);
		
		this.lblEstimatedEffort = new JLabel();
		lblEstimatedEffort.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstimatedEffort.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblEstimatedEffort);
		
		lblActualEffort = new JLabel();
		lblActualEffort.setHorizontalAlignment(SwingConstants.LEFT);
		lblActualEffort.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblActualEffort);
		
		JSeparator separator = new JSeparator();
		taskContents.add(separator);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblDescription);
		
		this.descriptionField = new JTextArea();
		descriptionField.putClientProperty("html.disable", Boolean.TRUE);
		descriptionField.setWrapStyleWord(true);
		descriptionField.setAlignmentX(Component.LEFT_ALIGNMENT);
		descriptionField.setLineWrap(true);
		descriptionField.setEditable(false);
		descriptionField.setMargin(new Insets(0, 0, 0, 0));
		taskContents.add(descriptionField);
		
		JSeparator separator_1 = new JSeparator();
		taskContents.add(separator_1);
		
		JLabel lblAssignedTo = new JLabel("Assigned To:");
		lblAssignedTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblAssignedTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblAssignedTo);
		
		this.assignedListModel = new DefaultListModel<String>();
		JList<String> assignedListComponent = new JList<String>( assignedListModel );
		assignedListComponent.setEnabled(false);
		assignedListComponent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		assignedListComponent.setAlignmentX(Component.LEFT_ALIGNMENT);
		taskContents.add(assignedListComponent);
		TaskView tv = this;
		this.add(taskContentPane, "cell 0 2 3 2,grow");
		
		//Set up the edit button
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addUniqueTab(TabType.TASK, taskModel);
			}
		});
		add(btnEdit, "cell 2 4");

		
		
		//Set up the close button to remove the task
		closeButton = new JButton("\u2716");
		titlePanel.add(closeButton, "cell 4 0,alignx center, flowx, aligny top");
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		closeButton.setFont(closeButton.getFont().deriveFont((float) 8));
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				activateArchiveView();
				StageController.archiveTask(tv);
			}
		});

		closeButton.setHorizontalAlignment(SwingConstants.TRAILING);
				
		
		//Set up the activities button
		btnActivities = new JButton("Activities");
		btnActivities.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addUniqueTab(TabType.ACTIVITIES, taskModel);
			}
		});
		add(btnActivities, "cell 0 4");
		
		
		btnRestore = new JButton("Restore");
		btnRestore.setMargin(new Insets(0, 0, 0, 0));
		btnRestore.setFont(closeButton.getFont().deriveFont((float) 8));
		btnRestore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				deactivateArchiveView();
				StageController.dearchiveTask(tv);
			}
		});
		titlePanel.add(btnRestore, "cell 4 0, alignx center, flowx, aligny top");
		btnRestore.setVisible(false);
		
		if(taskModel.getIsArchived()){
			activateArchiveView();
			setVisible(false);
		}
		

		this.setContents(taskModel);
	}
	
	/**
	 * activates the archive view mode of a task view
	 * 
	 */
	public void activateArchiveView(){
		setBorder(BorderFactory.createLineBorder(Color.red, 3));
		closeButton.setVisible(false);
		btnRestore.setVisible(true);
		btnActivities.setEnabled(false);
		btnEdit.setEnabled(false);
	}
	
	
	/**
	 * deactivates the archive view mode of a task view
	 * 
	 */
	public void deactivateArchiveView(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		closeButton.setVisible(true);
		btnRestore.setVisible(false);
		btnActivities.setEnabled(true);
		btnEdit.setEnabled(true);
	}
	
	
	/**
	 * This method will override the default getPreferredSize and resize the task based on the size of its parent
	 */
	public Dimension getPreferredSize() {
		boolean isExpanded = taskModel.getIsExpanded();
		Component parent = this.getParent();
		
		if( parent == null)
			return super.getPreferredSize();
		else{	
			//If the task is expanded, set the preferred size to the parent width and the openSize height
			Dimension parentSize = parent.getSize();
			truncateTitle(parentSize.width);
			if(isExpanded){
				this.setMaximumSize(new Dimension(parentSize.width,openSize));
				return new Dimension(parentSize.width,openSize);	
			//If the task is collapsed, set the preferred size to the parent width and the closedSize height
			} else{
				this.setMaximumSize(new Dimension(parentSize.width,closeSize));
				return new Dimension(parentSize.width,closeSize);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getHeight()
	 */
	public int getHeight() {
		if( taskModel.getIsExpanded() )
			return openSize;
		else
			return closeSize;
	}
	
	/**
	 * Expand the task container to show details about the task
	 */
	public void showDetails(){
		taskContentPane.setVisible(true);
		btnEdit.setVisible(true);
		taskModel.setIsExpanded(true);
		stageView.updatePreferredDimensions();
		revalidate();
	}
	
	
	/**
	 * Collapse the task container and hide details about the task
	 */
	public void hideDetails(){
		taskContentPane.setVisible(false);
		btnEdit.setVisible(false);
		taskModel.setIsExpanded(false);
		stageView.updatePreferredDimensions();
		revalidate();
	}
	
	/**
	 * given the task model, set the users this task is assigned to
	 * @param task - the taskModel that contains the assigned users
	 */
	public void addAssignedUsers(TaskModel task){
		assignedListModel.removeAllElements();
		for( String username : task.getUsersAssignedTo() ){
			assignedListModel.addElement( username );
		}
	}
	
	/**
	 * @return the stageView associated with this task
	 */
	public StageView getStageView(){
		return stageView;
	}
	
	
	/**
	 * 
	 * @param stageView - sets the stageView of the taskView
	 */
	public void setStageView(StageView stageView){
		this.stageView = stageView;
	}
	
	
	/**
	 * sets the categorypanel color with paramter color
	 * @param color
	 * @return 
	 */
	public void setCategoryColor(Color color){
		catPanel.setBackground(color);
	}
	
	/**
	 * @return the scrollPane that holds the task contents
	 */
	public JScrollPane getScrollPane(){
		return taskContentPane;
	}

	/**
	 * returns whether or not the corresponding task model to this taskview has been archived
	 * 
	 * @return
	 */
	public boolean isTaskModelArchived(){
		return taskModel.getIsArchived();
	}
	
	/**
	 * @return the ID of this task
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * Truncates the task title if it is too long
	 * @param title
	 * @return
	 */
	public void truncateTitle(int width){
		int numChars = (int) Math.pow(width, 1.6)/500;
		String truncatedTitle;
		if(taskModel.getTitle().length() >= numChars)
			truncatedTitle =  taskModel.getTitle().substring(0,numChars) + "...";
		else
			truncatedTitle = taskModel.getTitle();
		lblNewTask.setText(truncatedTitle);
	}
	
	/**
	 * Updates the contents of the taskView when given a new task model
	 * @param newTaskModel
	 */
	public void setContents(TaskModel task){
		this.taskModel.setTitle(task.getTitle());
		this.taskModel.setAssociatedRequirement(task.getAssociatedRequirement());
		
		task.setDueDate(task.getDueDate());
		this.lblDue.setText("Due: " + task.getDueDate());
		
		this.taskModel.setDescription(task.getDescription());
		this.descriptionField.setText(task.getDescription());
		
		this.setCategoryColor(task.getCatColor());
		taskModel.setCatColor(task.getCatColor());
		taskModel.setCatID(task.getCatID());
		
		this.taskModel.setEstimatedEffort(task.getEstimatedEffort());
		this.lblEstimatedEffort.setText("Estimated Effort: " + task.getEstimatedEffort());
		this.taskModel.setActualEffort(task.getActualEffort());
		this.lblActualEffort.setText("Actual Effort: " + task.getActualEffort());
		this.addAssignedUsers(task);
		
		if(task.getIsArchived()){
			activateArchiveView();
		} else {
			deactivateArchiveView();
		}
		
		toggleTaskViewColor(task.getColor());
		revalidate();
		repaint();
	}
	
	/**
	 * - when the color toggle button is pressed, change the color of the stage
	 * @param color - the color to set the stage to
	 */
	public void toggleTaskViewColor(Color color){
		if(WorkflowController.getWorkflowModel().getToggleColor()){
			statusLabel.setVisible(false);
			this.setBackground(color);
		}else{
			statusLabel.setVisible(true);
			statusLabel.setForeground(color);
			this.setBackground(Color.LIGHT_GRAY);
		}
	}
}
