package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class CoreUserController implements ActionListener {
	private CoreUserRequestObserver observer;
	
	public CoreUserController() {
		this.observer = new CoreUserRequestObserver(this);
	}

	public void requestUserList() {
		final Request request = Network.getInstance().makeRequest("core/user", HttpMethod.GET); // PUT == create
		request.addObserver(observer); // add an observer to process the response
		request.send();	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		requestUserList();
	}
}