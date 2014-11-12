package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;

/**
 * @author Alec
 * This controller is responsible for adding and removing users from the assign/unassign lists
 */
public class AssignUnassignUserController implements ActionListener {
	UserModel userModel;
	JList<UserModel> addToThisList;
	JList <UserModel> removeFromThisList;
	
	public AssignUnassignUserController(UserModel userModel, JList<UserModel> addToThisList, JList<UserModel> removeFromThisList){
		
		this.userModel = userModel;
		this.addToThisList = addToThisList;
		this.removeFromThisList = removeFromThisList;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
