package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskContainerView;

/**
 * A contoller that is used to add a task to the workflow
 * TODO: update the main workflow model
 * 
 */
public class AddTaskController implements ActionListener{
	private StageView stageView;
	private TaskContainerView taskView;
	private AssignUsersView assignUsersView;
	private TaskManagerTabView tabView;
	private NewTaskTab taskCreationView;
	private int stageIndex;
	

	/**
	 * construct the controller
	 * 
	 * @param tabView - the main JTabbedPane that holds the tab
	 * @param taskModel - the taskModel to add to the stage
	 * @param stageIndex - the index of the stage to remove
	 */
	public AddTaskController(TaskManagerTabView tabView, NewTaskTab taskCreationView, AssignUsersView assignUsersView, int stageIndex){
		//Parent tab pane
		this.tabView = tabView;
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
		TaskModel taskModel = new TaskModel(taskCreationView.getTitleLabelText(), taskCreationView.getDescriptionText(), taskCreationView.getDateText());
		taskModel.setUsersAssignedTo( this.getAssignedUsers() );
		this.taskView = new TaskContainerView(taskModel, stageView);
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
