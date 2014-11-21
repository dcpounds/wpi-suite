package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabView;
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
	


	/**
	 * @param saveTask - the task to interact with the database wit
	 * @param action - the action to take as specified by the ActionType enum
	 */
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
	 * Depending on the specified action, either create, edit, delete, or get taskModel(s) from the database
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
	
	/**
	 * @param task - the response task. Add this to the list of tasks
	 * if the task is already there, just update the contents of it
	 */
	public void addTask(TaskModel task){
		TaskView taskView = tabView.getWorkflowView().getTaskViewByID(task.getID());
		
		//If there is no such stage with the id 
		//already in the database, then make a new view for it
		if(taskView == null)
			taskView = new TaskView(task, stageView);
		else
			updateTask(task);
			//will also want some code here to put the task in the appropriate spot in the stage
			
		this.stageIndex = task.getStageIndex();
		this.stageView = workflowView.getStageViewList().get(stageIndex);
		taskView = new TaskView(task, stageView);
		
		workflowModel.getStageModelList().get(stageIndex).addTask(task);
		workflowView.getStageViewList().get(stageIndex).addTaskView(taskView);
		stageView.updatePreferredDimensions();
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
			if(task.getIsArchived())
				continue;
			else
				System.out.println("Got task from the database" + task.getTitle());
				addTask(task);
		}
	}
	
	/**
	 * @param task - the task that was just archived in the database
	 */
	public void deleteTask(TaskModel task){
		TaskView taskView = tabView.getWorkflowView().getTaskViewByID(task.getID());
		workflowModel.removeTaskModel(task);
		workflowView.removeTaskView(taskView);
	}
	
	
	
	/**
	 * @param task - the task to update in the database
	 */
	public void sendUpdateRequest(TaskModel task){
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.POST); // POST == update
		request.setBody(task.toJson()); // put the new stage in the body of the request
		request.addObserver(updateObserver); // add an observer to process the response
		request.send();
	}
	
	
	/**
	 * @param task - the task to archive in the database
	 */
	public void sendDeleteRequest(TaskModel task){
		task.setIsArchived(true);
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.POST); // POST == update
		request.setBody(task.toJson()); // put the new stage in the body of the request
		request.addObserver(deleteObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * @param taskModel - the task to add to the database
	 */
	public void sendAddRequest(TaskModel taskModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT); // PUT == create
		request.setBody(taskModel.toJson()); // put the new stage in the body of the request
		request.addObserver(addObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * get a list of all tasks from the database
	 */
	public void sendGetRequest (TaskModel taskModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.GET); // PUT == create
		request.addObserver(getObserver); // add an observer to process the response
		request.send();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		act();
	}


}
