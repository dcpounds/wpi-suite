package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
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
				selectedIndex = view.getUnassignedListSelectedIndex();
				selectedName = view.getUnassignedListSelectedName();
				
				//don't add the user if already assigned
				if(!assignedListModel.contains(selectedName) && selectedIndex >= 0 ){
					unassignedListModel.remove(selectedIndex);
					assignedListModel.addElement(selectedName);
				}
				break;
			case UNASSIGN:
				selectedIndex = view.getAssignedListSelectedIndex();
				selectedName = view.getAssignedListSelectedName();
				
				//don't add the user if already unassigned
				if(!unassignedListModel.contains(selectedName) && selectedIndex >= 0){
					assignedListModel.remove(selectedIndex);
					unassignedListModel.addElement(selectedName);
				}
				break;
		}
	}

}
