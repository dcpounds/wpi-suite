package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.janeway.config.Configuration;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

/**
 * A contoller that is used to add a task to the workflow
 * TODO: update the main workflow model
 * 
 */
public class AddTaskController implements ActionListener {
	private TabView tabView;
	private WorkflowModel workflowModel;
	private StageView stageView;
	private TaskView taskView;
	private AssignUsersView assignUsersView;
	private NewTaskTab newTaskTabView;
	private int stageIndex;
	


	public AddTaskController(NewTaskTab newTaskTabView, AssignUsersView assignUsersView, int stageIndex){
		tabView = TabController.getTabView();
		workflowModel = WorkflowController.getWorkflowModel();
		//Tab the request was made on
		this.newTaskTabView = newTaskTabView;
		//the pane that is used for adding users
		this.assignUsersView = assignUsersView;
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
		this.stageIndex = newTaskTabView.getStageSelectionIndex();
		this.stageView = tabView.getWorkflowView().getStageViewList().get(stageIndex);
		Configuration config = ConfigManager.getConfig();
		
		TaskModel taskModel = new TaskModel(newTaskTabView.getTitleLabelText(), newTaskTabView.getDescriptionText(), newTaskTabView.getDateText());
		taskModel.setUsersAssignedTo( this.getAssignedUsers());
		taskModel.setCreator(new User(config.getUserName(), config.getUserName(), "password", -1));
		taskModel.setEstimatedEffort(newTaskTabView.getEstimatedEffort());
		taskModel.setActualEffort(newTaskTabView.getActualEffort());
		taskModel.setDueDate(newTaskTabView.getDateText());
		taskModel.setStatus(newTaskTabView.getStatusText());
		
		//IMPORTANT: workflow model must be updated before creating task so that proper id can be set
		workflowModel.addTask(newTaskTabView.getStatusText(), taskModel);
		this.taskView = new TaskView(taskModel, stageView);
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
