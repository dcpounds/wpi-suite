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
		switch(action){	
			case ASSIGN:
				selectedIndex = view.getUnassignedListSelectedIndex();
				selectedName = view.getUnassignedListSelectedName();
				assignUser(selectedIndex, selectedName);
				break;
			case UNASSIGN:
				selectedIndex = view.getAssignedListSelectedIndex();
				selectedName = view.getAssignedListSelectedName();
				unassignUser(selectedIndex, selectedName);
				break;
		}
	}
	/**
	 * Assigns Users based on selected Index and selected name(string)
	 * remove Users from unassigned list and move to assigned list.
	 * @param selectedIndex
	 * @param selectedName
	 * 
	 */
	public void assignUser(int selectedIndex, String selectedName){	
		this.unassignedListModel = view.getUnassignedListModel();
		this.assignedListModel = view.getAssignedListModel();
		this.assignedListComponent = view.getAssignedListComponent();
		this.unassignedListComponent = view.getUnssignedListComponent();
		
		//don't add the user if already assigned
		if(!assignedListModel.contains(selectedName) && selectedIndex >= 0 ){
			unassignedListModel.remove(selectedIndex);
			assignedListModel.addElement(selectedName);
		}
	}
	/**
	 * Unassigns Users based on selected Index and selected name(string)
	 * remove Users from assigned list and move to unassigned list.
	 * @param selectedIndex
	 * @param selectedName
	 * 
	 */
	public void unassignUser(int selectedIndex, String selectedName){
		this.unassignedListModel = view.getUnassignedListModel();
		this.assignedListModel = view.getAssignedListModel();
		this.assignedListComponent = view.getAssignedListComponent();
		this.unassignedListComponent = view.getUnssignedListComponent();
		
		//don't add the user if already unassigned
		if(!unassignedListModel.contains(selectedName) && selectedIndex >= 0){
			assignedListModel.remove(selectedIndex);
			unassignedListModel.addElement(selectedName);
		}
	}

}
