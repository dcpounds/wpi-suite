package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignRemoveEnum;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssociatedRequirementsView;

/**
 * @author Lillian
 * Copied from AssignUnassignUserController
 * This controller is responsible for adding and removing requirements from the assign/unassign lists
 */
public class RequirementAddRemoveController implements ActionListener {
	JList<String> assignedListComponent;
	JList<String> unassignedListComponent;
	DefaultListModel<String> unassignedListModel;
	DefaultListModel<String> assignedListModel;
	AssociatedRequirementsView view;
	AssignRemoveEnum action;
	
	
	public RequirementAddRemoveController(AssociatedRequirementsView view, AssignRemoveEnum action){
		this.view = view;
		this.action = action;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int selectedIndex;
		String selectedName;
		
		this.assignedListModel = view.getassociatedListModel();
		this.unassignedListModel = view.getunassociatedListModel();
		this.assignedListComponent = view.getassociatedListComponent();
		this.unassignedListComponent = view.getUnassignedListComponent();
		
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
