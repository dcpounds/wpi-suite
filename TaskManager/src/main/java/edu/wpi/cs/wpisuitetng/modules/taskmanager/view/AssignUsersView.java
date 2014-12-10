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

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AssignUnassignUserController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.CoreUserController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;

/**
 * @author Alec
 * A view that is used to assign viewers between two lists
 */
public class AssignUsersView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3447866744304683605L;
	private User[] userList;
	private WorkflowModel workflowModel;
	
	private JList<String> unassignedListComponent;
	private JList<String> assignedListComponent;
	private DefaultListModel<String> unassignedListModel;
	private DefaultListModel<String> assignedListModel;
	
	public AssignUsersView() {
		
		//Call the controller responsible for making 
		//the call to the database for fetching core users
		new CoreUserController(this).requestUserList();
		this.workflowModel = WorkflowController.getWorkflowModel();
		this.userList = workflowModel.getUserList();
		setLayout(new MigLayout("", "[][][]", "[][][grow][]"));
		
		JLabel lblAssignUsers = new JLabel("Assign Users To This Task");
		add(lblAssignUsers, "cell 0 0 5 1,alignx left,aligny top");
		
		JLabel lblUsersNotAssigned = new JLabel("Users not assigned:");
		lblUsersNotAssigned.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblUsersNotAssigned, "cell 0 1");
		
		JLabel lblUsersAssigned = new JLabel("Users assigned");
		lblUsersAssigned.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblUsersAssigned, "cell 4 1");
		
		JScrollPane unassignedScrollPane = new JScrollPane();
		add(unassignedScrollPane, "cell 0 2,growy");
		
		//List of unassigned users
		unassignedListModel = new DefaultListModel<String>();
		unassignedListComponent = new JList<String>( unassignedListModel );
		unassignedListComponent.setFixedCellWidth(150);
		unassignedScrollPane.setViewportView(unassignedListComponent);
		
		JScrollPane assignedScrollPane = new JScrollPane();
		add(assignedScrollPane, "cell 4 2,growy");
		
		//List of assigned users
		assignedListModel = new DefaultListModel<String>();
		assignedListComponent = new JList<String>( assignedListModel );
		assignedListComponent.setFixedCellWidth(150);
		assignedScrollPane.setViewportView(assignedListComponent);
		
		JButton btnAssign = new JButton("Assign >>");
		btnAssign.addActionListener( new AssignUnassignUserController(this, AssignRemoveEnum.ASSIGN) );
		add(btnAssign, "cell 0 3,alignx center");
		
		JButton buttonUnassign = new JButton("<< Unassign");
		buttonUnassign.addActionListener( new AssignUnassignUserController(this, AssignRemoveEnum.UNASSIGN) );
		add(buttonUnassign, "cell 4 3,alignx center");
	
	}
	
	/**
	 * @return an array of userName strings from the provided list of userModels
	 */	
	private String[] getUsernameList() {
		ArrayList<String> userNames = new ArrayList<String>();
		for( User user : workflowModel.getUserList() ){
			String userName = user.getUsername();
			userNames.add(userName);
		}
		return userNames.toArray(new String[userList.length]);
	}
	
	
	/**
	 * 
	 * @return the name of the user that is selected in the unassigned list
	 */
	public String getUnassignedListSelectedName() {
		return this.unassignedListComponent.getSelectedValue();
	}
	
	/**
	 * 
	 * @return the name of the user that is selected in the unassigned list
	 */
	public String getAssignedListSelectedName() {
		return this.assignedListComponent.getSelectedValue();
	}
	
	/**
	 * 
	 * @return the index of the selected element in the unassigned list
	 */
	public int getUnassignedListSelectedIndex() {
		int index = this.unassignedListComponent.getSelectedIndex();
		return index;
	}
	
	/**
	 * 
	 * @return the index of the selected element in the assigned list
	 */
	public int getAssignedListSelectedIndex() {
		int index = this.assignedListComponent.getSelectedIndex();
		return index;
	}
	
	//returns the final list of assigned 
	public ArrayList<String> getAssignedUsers() {
		ArrayList<String> assignedUsers = new ArrayList<String>();
		int size = assignedListModel.getSize();
		for(int index = 0; index < size; index++) {
			String userName = assignedListModel.getElementAt(index);
			assignedUsers.add( userName );
		}
		return assignedUsers;
	}
	
	public JList<String> getAssignedListComponent() {
		return assignedListComponent;
	}
	
	public JList<String> getUnssignedListComponent() {
		return unassignedListComponent;
	}
	
	public DefaultListModel<String> getAssignedListModel() {
		return assignedListModel;
	}
	
	public DefaultListModel<String> getUnassignedListModel() {
		return unassignedListModel;
	}	
}
