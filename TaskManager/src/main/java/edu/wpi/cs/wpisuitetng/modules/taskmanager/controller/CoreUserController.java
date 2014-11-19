package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public void requestUserList() {
		final Request request = Network.getInstance().makeRequest("core/user", HttpMethod.GET); // PUT == create
		request.addObserver(observer); // add an observer to process the response
		request.send();	
	}
	
	public void addUsersToList(User[] users) {
		for( User user : users)
			view.getUnassignedListModel().addElement(user.getName());
		view.revalidate();
		view.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		requestUserList();
	}
}