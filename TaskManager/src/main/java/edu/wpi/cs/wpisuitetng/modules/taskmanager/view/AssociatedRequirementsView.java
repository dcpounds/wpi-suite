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
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RequirementAddRemoveController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.CoreUserController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RequirementsController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;

/**
 * @author Lillian
 * based on AssignUsersView
 * A view that is used to assign associated Requirements to a task
 */
public class AssociatedRequirementsView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3447866744304683605L;
	private User[] userList;
	private WorkflowModel workflowModel;
	
	private JList<String> unassociatedListComponent;
	private JList<String> associatedListComponent;
	private DefaultListModel<String> unassociatedListModel;
	private DefaultListModel<String> associatedListModel;
	
	public AssociatedRequirementsView() {
		
		//Call the controller responsible for making 
		//the call to the database for fetching core users
		new RequirementsController().requestRequirementsList();
		this.workflowModel = WorkflowController.getWorkflowModel();
		this.userList = workflowModel.getUserList();
		setLayout(new MigLayout("", "[][][]", "[][][grow][]"));
		
		JLabel lblAssignUsers = new JLabel("Assign Requirements To This Task");
		add(lblAssignUsers, "cell 0 0 5 1,alignx left,aligny top");
		
		JLabel lblUsersNotAssigned = new JLabel("Requirements not assigned:");
		lblUsersNotAssigned.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblUsersNotAssigned, "cell 0 1");
		
		JLabel lblUsersAssigned = new JLabel("Requirements assigned");
		lblUsersAssigned.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblUsersAssigned, "cell 4 1");
		
		JScrollPane unassignedScrollPane = new JScrollPane();
		add(unassignedScrollPane, "cell 0 2,growy");
		
		//List of unassigned users
		unassociatedListModel = new DefaultListModel<String>();
		unassociatedListComponent = new JList<String>( unassociatedListModel );
		unassociatedListComponent.setFixedCellWidth(150);
		unassignedScrollPane.setViewportView(unassociatedListComponent);
		
		JScrollPane assignedScrollPane = new JScrollPane();
		add(assignedScrollPane, "cell 4 2,growy");
		
		//List of assigned users
		associatedListModel = new DefaultListModel<String>();
		associatedListComponent = new JList<String>( associatedListModel );
		associatedListComponent.setFixedCellWidth(150);
		assignedScrollPane.setViewportView(associatedListComponent);
		
		JButton btnAssign = new JButton("Add >>");
		btnAssign.addActionListener( new RequirementAddRemoveController(this, AssignRemoveEnum.ASSIGN) );
		add(btnAssign, "cell 0 3,alignx center");
		
		JButton buttonUnassign = new JButton("<< Remove");
		buttonUnassign.addActionListener( new RequirementAddRemoveController(this, AssignRemoveEnum.UNASSIGN) );
		add(buttonUnassign, "cell 4 3,alignx center");
	
	}
	
	/**
	 * @return an array of userName strings from the provided list of userModels
	 */	
	private Requirement[] getRequirementsList() {
		Requirement[] requirements = workflowModel.getRequirementsList();
		return requirements;
	}
	
	
	/**
	 * 
	 * @return the name of the user that is selected in the unassigned list
	 */
	public String getUnassignedListSelectedName() {
		return this.unassociatedListComponent.getSelectedValue();
	}
	
	/**
	 * 
	 * @return the name of the user that is selected in the unassigned list
	 */
	public String getAssignedListSelectedName() {
		return this.associatedListComponent.getSelectedValue();
	}
	
	/**
	 * 
	 * @return the index of the selected element in the unassigned list
	 */
	public int getUnassignedListSelectedIndex() {
		int index = this.unassociatedListComponent.getSelectedIndex();
		return index;
	}
	
	/**
	 * 
	 * @return the index of the selected element in the assigned list
	 */
	public int getAssignedListSelectedIndex() {
		int index = this.associatedListComponent.getSelectedIndex();
		return index;
	}
	
	//returns the final list of assigned 
	public ArrayList<String> getAssignedUsers() {
		ArrayList<String> assignedUsers = new ArrayList<String>();
		int size = associatedListModel.getSize();
		for(int index = 0; index < size; index++) {
			String userName = associatedListModel.getElementAt(index);
			assignedUsers.add( userName );
		}
		return assignedUsers;
	}
	
	public JList<String> getassociatedListComponent() {
		return associatedListComponent;
	}
	
	public JList<String> getUnassignedListComponent() {
		return unassociatedListComponent;
	}
	
	public DefaultListModel<String> getassociatedListModel() {
		return associatedListModel;
	}
	
	public DefaultListModel<String> getunassociatedListModel() {
		return unassociatedListModel;
	}	
}
