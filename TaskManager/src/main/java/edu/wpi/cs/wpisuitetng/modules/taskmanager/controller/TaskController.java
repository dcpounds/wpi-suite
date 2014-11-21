package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ActionType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
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
public class TaskController implements ActionListener {
	private TabView tabView;
	private WorkflowModel workflowModel;
	private StageView stageView;
	private int stageIndex;
	private AddTaskRequestObserver addObserver;
	private GetTaskRequestObserver getObserver;
	private UpdateTaskRequestObserver updateObserver;
	private DeleteTaskRequestObserver deleteObserver;
	private ActionType action;
	private TaskModel saveTask;
	private WorkflowView workflowView; 
	


	public TaskController(TaskModel saveTask, ActionType action){
		this.addObserver = new AddTaskRequestObserver(this);
		this.updateObserver = new UpdateTaskRequestObserver(this);
		this.deleteObserver = new DeleteTaskRequestObserver(this);
		this.getObserver = new GetTaskRequestObserver(this);
		
		tabView = TabController.getTabView();
		this.workflowView = tabView.getWorkflowView();
		this.saveTask = saveTask;
		workflowModel = WorkflowController.getWorkflowModel();
		this.action = action;
	}

	/**
	 * do the specified action with the model sent over
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void act(){
		switch(action){
			case CREATE: 
				sendAddRequest(saveTask);
				break;
			case EDIT:
				sendUpdateRequest(saveTask);
				break;
			case DELETE:
				sendDeleteRequest(saveTask);	
				break;
			case GET:
				sendGetRequest(saveTask);
				break;
		}
	}
	
	public void addTask(TaskModel task){
		TaskView taskView = tabView.getWorkflowView().getTaskViewByID(task.getID());
		this.stageIndex = task.getStageIndex();
		this.stageView = workflowView.getStageViewList().get(stageIndex);
		taskView = new TaskView(task, stageView);
		
		workflowModel.getStageModelList().get(stageIndex).addTask(task);
		workflowView.getStageViewList().get(stageIndex).addTaskView(taskView);
		
		stageView.updatePreferredDimensions();
		stageView.addTaskView(taskView);
	}
	
	/**
	 * @param task - the task that was updated
	 */
	public void updateTask(TaskModel task){
		TaskView taskView = tabView.getWorkflowView().getTaskViewByID(task.getID());
		taskView.updateContents(task);
	}
	
	
	/**
	 * @param tasks - the list of tasks retrieved from the database
	 */
	public void getTasks(TaskModel[] tasks){
		for(TaskModel task : tasks){
			if(task.getIsArchived() == false){
				System.out.println("Got task from the database" + task.getTitle());
				addTask(task);
			}
		}
	}
	
	public void deleteTask(TaskModel task){
		TaskView taskView = tabView.getWorkflowView().getTaskViewByID(task.getID());
		task.setIsArchived(true);
		workflowModel.removeTaskModel(task);
		workflowView.removeTaskView(taskView);
	}
	
	
	public void sendUpdateRequest(TaskModel task){
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.POST); // POST == update
		request.setBody(task.toJson()); // put the new stage in the body of the request
		request.addObserver(updateObserver); // add an observer to process the response
		request.send();
	}
	
	public void sendDeleteRequest(TaskModel task){
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.POST); // POST == update
		request.setBody(task.toJson()); // put the new stage in the body of the request
		request.addObserver(deleteObserver); // add an observer to process the response
		request.send();
	}
	
	public void sendAddRequest(TaskModel taskModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT); // PUT == create
		request.setBody(taskModel.toJson()); // put the new stage in the body of the request
		request.addObserver(addObserver); // add an observer to process the response
		request.send();
	}
	
	public void sendGetRequest (TaskModel taskModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.GET); // PUT == create
		request.setBody(taskModel.toJson()); // put the new stage in the body of the request
		request.addObserver(getObserver); // add an observer to process the response
		request.send();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		act();
	}


}
