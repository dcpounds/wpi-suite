package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

public class AssignUsersView extends JPanel{
	ArrayList<UserModel> userList;
	WorkflowModel workflowModel;
	
	public AssignUsersView(TaskManagerTabView tabView, WorkflowModel workflowModel) {
		this.workflowModel = workflowModel;
		userList = workflowModel.getUserList();
		setLayout(new MigLayout("", "[61px][grow][][][][grow][]", "[14px][][grow][]"));
		
		JLabel lblAssignUsers = new JLabel("Assign Users To This Task");
		add(lblAssignUsers, "cell 1 0 5 1,alignx center,aligny top");
		
		JLabel lblUsersNotAssigned = new JLabel("Users not assigned:");
		lblUsersNotAssigned.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUsersNotAssigned, "cell 1 1");
		
		JLabel lblUsersAssigned = new JLabel("Users assigned");
		lblUsersAssigned.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUsersAssigned, "cell 5 1");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 2,grow");
		
		//List<UserModel> = tabView.getWorkflowView()
		
		//List of unassigned users
		JList unassignedListComponent = new JList<String>( this.getUsernameList() );
		scrollPane.setViewportView(unassignedListComponent);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 5 2,grow");
		
		//List of assigned users
		DefaultListModel assignedList = new DefaultListModel();
		JList assignedListComponent = new JList();
		scrollPane_1.setViewportView(assignedListComponent);
		
		JButton btnAssign = new JButton("Assign >>");
		add(btnAssign, "cell 1 3,alignx center");
		
		JButton buttonUnassign = new JButton("<< Unassign");
		add(buttonUnassign, "cell 5 3,alignx center");
	}
	
	/**
	 * @return an array of userName strings from a list of userModels
	 */
	private String[] getUsernameList() {
		String[] userNameList = new String[]{""};
		
		if(userList.size() == 0){
			return userNameList;
		} else{
			for(int index = 0; index < userList.size(); index++ )
				userNameList[index] = userList.get(index).getUsername();	
		}
		return userNameList;
	}
	

}
