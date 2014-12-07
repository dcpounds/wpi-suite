package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssociatedRequirementsView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author Lillian
 * Responsible for making a call to the database to get requirements
 */
public class RequirementsController implements ActionListener {
	private RequirementsRequestObserver observer;
	//private AssociatedRequirementsView view;
	
	public RequirementsController() {
		this.observer = new RequirementsRequestObserver(this);
		//this.view = view;
	}

	public void requestRequirementsList() {
		final Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.GET); // PUT == create
		request.addObserver(observer); // add an observer to process the response
		request.send();	
	}
	
//	public void addRequirementsToList(Requirement[] requirements) {
//		DefaultListModel<String> unassignedList = view.getunassociatedListModel();
//		for( Requirement req: requirements)
//			unassignedList.addElement( req.getName() );
//		System.out.println(view.getunassociatedListModel());
//		view.revalidate();
//		view.repaint();
//	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		requestRequirementsList();
	}
}