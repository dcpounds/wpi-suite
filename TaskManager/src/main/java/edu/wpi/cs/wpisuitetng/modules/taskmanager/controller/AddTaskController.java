package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskContainerView;

/**
 * A contoller that is used to add a task to the workflow
 * TODO: update the main workflow model
 * 
 */
public class AddTaskController implements ActionListener {
	private TabView tabView;
	private WorkflowModel workflowModel;
	private StageView stageView;
	private TaskContainerView taskView;
	private AssignUsersView assignUsersView;
	private NewTaskTab taskCreationView;
	private int stageIndex;
	

	public AddTaskController(NewTaskTab taskCreationView, AssignUsersView assignUsersView, int stageIndex){
		tabView = TabController.getTabView();
		workflowModel = WorkflowController.getWorkflowModel();
		//Tab the request was made on
		this.taskCreationView = taskCreationView;
		//the pane that is used for adding users
		this.assignUsersView = assignUsersView;
		this.stageIndex = stageIndex;
		
	}

	/**
	 * action performed when submit button is hit
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.stageView = tabView.getWorkflowView().getStageViewList().get(stageIndex);
		TaskModel taskModel = new TaskModel(taskCreationView.getTitleLabelText(), taskCreationView.getDescriptionText());
		taskModel.setUsersAssignedTo( this.getAssignedUsers() );
		this.taskView = new TaskContainerView(taskModel, stageView);
		stageView.updatePreferredDimensions();
		stageView.addTaskView(taskView);
		
	}
	
	//returns the final list of assigned 
	public ArrayList<UserModel> getAssignedUsers() {
		ArrayList<UserModel> assignedUsers = new ArrayList<UserModel>();
		DefaultListModel<String> assignedListModel = assignUsersView.getAssignedListModel();
		
		int size = assignedListModel.getSize();
		for(int index = 0; index < size; index++) {
			UserModel userModel = new UserModel( assignedListModel.getElementAt(index) );
			assignedUsers.add( userModel );
		}
		return assignedUsers;
	}

}
