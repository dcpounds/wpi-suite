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

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author Alec
 * Responsible for making a call to the database to get core Users
 */
public class CoreUserController implements ActionListener {
	private CoreUserRequestObserver observer;
	private AssignUsersView view;
	
	public CoreUserController(AssignUsersView view) {
		this.observer = new CoreUserRequestObserver(this);
		this.view = view;
	}

	/**
	 * Request all users from the db
	 */
	public void requestUserList() {
		final Request request = Network.getInstance().makeRequest("core/user", HttpMethod.GET); // PUT == create
		request.addObserver(observer); // add an observer to process the response
		request.send();	
	}
	
	/**
	 * Add users to the list of users in the new task view
	 * called by request observer
	 * @param users
	 */
	public void addUsersToList(User[] users) {
		DefaultListModel<String> unassignedList = view.getUnassignedListModel();
		for( User user: users)
			if (!view.getAssignedListModel().contains(user.getUsername()))
				unassignedList.addElement( user.getUsername() );
		view.revalidate();
		view.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		requestUserList();
	}
}