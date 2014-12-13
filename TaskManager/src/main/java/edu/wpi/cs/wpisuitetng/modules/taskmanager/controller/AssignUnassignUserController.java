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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignRemoveEnum;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;

/**
 * @author Alec
 * This controller is responsible for adding and removing users from the assign/unassign lists
 */
public class AssignUnassignUserController implements ActionListener {
	JList<String> assignedListComponent;
	JList<String> unassignedListComponent;
	DefaultListModel<String> unassignedListModel;
	DefaultListModel<String> assignedListModel;
	AssignUsersView view;
	AssignRemoveEnum action;
	
	
	public AssignUnassignUserController(AssignUsersView view, AssignRemoveEnum action){
		this.view = view;
		this.action = action;
		addExistingUsers();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	/**
	 * This is responsible for moving users between the assigned and unnassigned lists
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int selectedIndex;
		String selectedName;
		
		this.assignedListModel = view.getAssignedListModel();
		this.unassignedListModel = view.getUnassignedListModel();
		this.assignedListComponent = view.getAssignedListComponent();
		this.unassignedListComponent = view.getUnssignedListComponent();
		
		
		
		switch(action){	
			case ASSIGN:
				selectedIndex = view.getAssignedListSelectedIndex();
				selectedName = view.getAssignedListSelectedName();
				assignUser(selectedIndex, selectedName);
				break;
			case UNASSIGN:
				selectedIndex = view.getUnassignedListSelectedIndex();
				selectedName = view.getUnassignedListSelectedName();
				unassignUser(selectedIndex, selectedName);
				break;
		}
	}
	
	public void assignUser(int selectedIndex, String selectedName){		
		//don't add the user if already assigned
		if(!assignedListModel.contains(selectedName) && selectedIndex >= 0 ){
			unassignedListModel.remove(selectedIndex);
			assignedListModel.addElement(selectedName);
		}
	}
	
	public void unassignUser(int selectedIndex, String selectedName){
		//don't add the user if already unassigned
		if(!unassignedListModel.contains(selectedName) && selectedIndex >= 0){
			assignedListModel.remove(selectedIndex);
			unassignedListModel.addElement(selectedName);
		}
	}
	
	public void addExistingUsers(){
		TaskModel taskModel = view.getTaskModel();
		for (String assigneduser : taskModel.getUsersAssignedTo()){
				if (!assignedListModel.contains(assigneduser)){
					assignedListModel.addElement(assigneduser);
			}
		}
	}

}
