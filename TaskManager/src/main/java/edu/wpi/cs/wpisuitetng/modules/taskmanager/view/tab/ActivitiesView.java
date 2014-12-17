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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ActivityListCellRenderer;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import net.miginfocom.swing.MigLayout;


/**
 * @author Dave
 * This tab view displays the activities log that each task contains. 
 */
public class ActivitiesView extends AbstractTab {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7944220332816331243L;
	private WorkflowModel workflowModel;
	private TaskModel taskModel;
	private JList<ActivityModel> activitiesBoard;
	private JScrollPane activitiesScrollPane;
	private JTextArea newCommentTxt;
	private JButton btnSubmit;
	private JScrollPane newCommentScrollPane;
	private JLabel activitiesLabel;
	private JLabel newCommentLabel;

	public ActivitiesView(TaskModel model){
		taskModel = model;
		workflowModel = WorkflowController.getWorkflowModel();
		setLayout(new MigLayout("", "[grow]", "[][grow][][][]"));
     
		activitiesLabel = new JLabel("Activities");
        add(activitiesLabel, "cell 0 0");
		
        activitiesBoard = new JList<ActivityModel>(taskModel.getActivities());
        activitiesBoard.setCellRenderer(new ActivityListCellRenderer());
        activitiesScrollPane = new JScrollPane(activitiesBoard);
        activitiesScrollPane.setPreferredSize(new Dimension(500, 400));
        add(activitiesScrollPane, "cell 0 1");
        
        newCommentLabel = new JLabel("New Comment");
        add(newCommentLabel, "cell 0 2");
        
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
        newCommentScrollPane = new JScrollPane();
        newCommentScrollPane.setViewportView(newCommentTxt);
        add(newCommentScrollPane, "cell 0 3");
        
        
        
        //make the submit button
        btnSubmit = new JButton("Post");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSubmit.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!newCommentTxt.getText().equals("Enter a comment here.") && !newCommentTxt.getText().equals(""))
					addActivity();
			}
		});
        add(btnSubmit, "cell 0 4,alignx left");
	}
	
	/**
	 * Given the input that the user provided, construct the task
	 * @return - the task that has been built with the fields that the user entered
	 */
	public void addActivity() {
		taskModel.addActivity(new ActivityModel(newCommentTxt.getText()));
		newCommentTxt.setText("");
	}
	
	/**
	 * @return the taskModel that was passed in
	 */
	public TaskModel getTaskModel(){
		return taskModel;
	}
	
	public boolean hasBeenModified(){
		if(newCommentTxt.getText().trim().isEmpty()){
			return false;
		}
		else{ return true;}
	}
}
