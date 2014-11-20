package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;
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
	private TaskModel saveTask;
	private AssignUsersView assignUsersView;
	private NewTaskTab newTaskTabView;
	private int stageIndex;
	private AddTaskRequestObserver observer;
	private boolean isEditing;
	


	public AddTaskController(NewTaskTab newTaskTabView, TaskModel saveTask, boolean isEditing){
		tabView = TabController.getTabView();
		this.assignUsersView = newTaskTabView.getAssignUserView();
		this.saveTask = saveTask;
		workflowModel = WorkflowController.getWorkflowModel();
		this.newTaskTabView = newTaskTabView;
		this.observer = new AddTaskRequestObserver(this);
		this.isEditing = isEditing;
	}

	/**
	 * action performed when submit button is hit
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		TaskModel taskToSend = buildTask();
		if(isEditing){
			sendUpdateRequest(taskToSend);
		}else{
			sendAddRequest(taskToSend);
		}
	}
	
	public TaskModel buildTask() {
		TaskModel taskModel = new TaskModel();
		taskModel.setID( saveTask.getID() );
		String creatorName = ConfigManager.getConfig().getUserName();
		taskModel.setCreator( creatorName );
		taskModel.setTitle(newTaskTabView.getTitleLabelText());
		taskModel.setDescription(newTaskTabView.getDescriptionText());
		taskModel.setDueDate(newTaskTabView.getDateText());
		taskModel.setUsersAssignedTo( assignUsersView.getAssignedUsers());
		taskModel.setEstimatedEffort(newTaskTabView.getEstimatedEffort());
		taskModel.setActualEffort(newTaskTabView.getActualEffort());
		taskModel.setDueDate(newTaskTabView.getDateText());
		taskModel.setStatus(newTaskTabView.getStatusText());
		return taskModel;
	}
	
	public void processResponse(TaskModel task){
		TaskView taskView = tabView.getWorkflowView().getTaskViewByID(task.getID());
		if(taskView == null){
			WorkflowView workflowView = tabView.getWorkflowView();
			this.stageIndex = newTaskTabView.getStageSelectionIndex();
			this.stageView = workflowView.getStageViewList().get(stageIndex);
			taskView = new TaskView(task, stageView);
			
			workflowModel.addTask(task);
			workflowView.getStageViewList().get(stageIndex).addTaskView(taskView);
			
			stageView.updatePreferredDimensions();
			stageView.addTaskView(taskView);
		}else{
			taskView.updateContents(task);
		}
	}
	
	public void sendUpdateRequest(TaskModel task){
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.POST); // POST == update
		request.setBody(task.toJson()); // put the new stage in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send();
	}
	
	public void sendAddRequest(TaskModel taskModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT); // PUT == create
		request.setBody(taskModel.toJson()); // put the new stage in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send();
	}


}
