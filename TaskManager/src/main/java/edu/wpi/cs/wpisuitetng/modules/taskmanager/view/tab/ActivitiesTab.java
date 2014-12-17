/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ActivityListCellRenderer;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import net.miginfocom.swing.MigLayout;

import java.awt.ScrollPane;


/**
 * @author Dave
 * This tab view displays the activities log that each task contains. 
 */
public class ActivitiesTab extends AbstractTab implements IHashableTab, KeyListener {

	
	private WorkflowModel workflowModel;
	private TaskModel taskModel;
	private JList<ActivityModel> activitiesBoard;
	private JScrollPane activitiesScrollPane;
	private JTextArea newCommentTxt;
	private JButton btnSubmit;
	private JScrollPane commentScrollPane;

	public ActivitiesTab(TaskModel model){
		taskModel = model;
		workflowModel = WorkflowController.getWorkflowModel();
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
     
		//make
        activitiesBoard = new JList<ActivityModel>(taskModel.getActivities());
        activitiesBoard.setCellRenderer(new ActivityListCellRenderer());
        activitiesScrollPane = new JScrollPane(activitiesBoard);
        activitiesScrollPane.setPreferredSize(new Dimension(500, 400));
        add(activitiesScrollPane, "cell 0 0");
        
        //make the txt for new comments
        newCommentTxt = new JTextArea("Enter a comment here.");
        newCommentTxt.setLineWrap(true);
        newCommentTxt.setWrapStyleWord(true);
        newCommentTxt.setRows(9);
        newCommentTxt.setColumns(30);
        newCommentTxt.addMouseListener(
    		new MouseAdapter() {
    			public void mouseClicked(MouseEvent e) {
    				newCommentTxt.setText("");
    	}});
        
        commentScrollPane = new JScrollPane();
        commentScrollPane.setViewportView(newCommentTxt);
        add(commentScrollPane, "cell 0 1");
        
        
        
        //make the submit button
        btnSubmit = new JButton("Submit");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSubmit.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!newCommentTxt.getText().equals("Enter a comment here.") && !newCommentTxt.getText().equals(""))
					addActivity();
			}
		});
        add(btnSubmit, "cell 0 1,alignx left");
	}
	
	/**
	 * Given the input that the user provided, construct the task
	 * @return - the task that has been built with the fields that the user entered
	 */
	public void addActivity() {
		StageModel stageModel = null;
		for(StageModel stage : workflowModel.getStageModelList().values()){
			if(stage.getID() == taskModel.getStageID())
				stageModel = stage;
		}
		
		taskModel.addActivity(new ActivityModel(newCommentTxt.getText()));
		newCommentTxt.setText("");
		//Updates task on the stage model
		StageController.sendUpdateRequest(stageModel);
	}
	
	/**
	 * @return the taskModel that was passed in
	 */
	public TaskModel getTaskModel(){
		return taskModel;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.IHashableTab#getModelID()
	 */
	@Override
	public int getModelID() {
		return taskModel.getID();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.IHashableTab#getTabType()
	 */
	@Override
	public TabType getTabType() {
		// TODO Auto-generated method stub
		return TabType.ACTIVITIES;
	}
	
	public boolean hasBeenModified(){
		if(newCommentTxt.getText().trim().isEmpty()){
			return false;
		}
		else{ return true;}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		hasBeenModified();
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		hasBeenModified();
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		hasBeenModified();
		
	}
}
