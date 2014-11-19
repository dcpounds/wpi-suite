package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sun.security.krb5.Config;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.janeway.config.Configuration;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

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
	private AddTaskRequestObserver observer;
	


	public AddTaskController(NewTaskTab newTaskTabView, AssignUsersView assignUsersView, int stageIndex){
		tabView = TabController.getTabView();
		workflowModel = WorkflowController.getWorkflowModel();
		//Tab the request was made on
		this.newTaskTabView = newTaskTabView;
		//the pane that is used for adding users
		this.assignUsersView = assignUsersView;
		this.observer = new AddTaskRequestObserver(this);
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
		String creatorName = ConfigManager.getConfig().getUserName();
		TaskModel taskModel = new TaskModel(newTaskTabView.getTitleLabelText(), newTaskTabView.getDescriptionText(), newTaskTabView.getDateText());
		taskModel.setUsersAssignedTo( assignUsersView.getAssignedUsers());
		taskModel.setCreator( creatorName );
		taskModel.setEstimatedEffort(newTaskTabView.getEstimatedEffort());
		taskModel.setActualEffort(newTaskTabView.getActualEffort());
		taskModel.setDueDate(newTaskTabView.getDateText());
		taskModel.setStatus(newTaskTabView.getStatusText());
		//TODO: set users assigned to this task
		
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT); // PUT == create
		request.setBody(taskModel.toJson()); // put the new stage in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send();
	}
	
	public void addTask(TaskModel task){
		this.stageIndex = newTaskTabView.getStageSelectionIndex();
		this.stageView = tabView.getWorkflowView().getStageViewList().get(stageIndex);
		
		//IMPORTANT: workflow model must be updated before creating task so that proper id can be set
		workflowModel.addTask(newTaskTabView.getStatusText(), task);
		this.taskView = new TaskView(task, stageView);
		stageView.updatePreferredDimensions();
		stageView.addTaskView(taskView);
	}
	


}
