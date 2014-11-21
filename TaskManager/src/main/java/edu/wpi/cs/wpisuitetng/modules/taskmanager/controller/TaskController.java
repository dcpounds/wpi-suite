package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.ActionType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
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
public class TaskController {
	private TabView tabView;
	private WorkflowModel workflowModel;
	private StageView stageView;
	private NewTaskTab newTaskTabView;
	private int stageIndex;
	private TaskRequestObserver observer;
	private ActionType action;
	private TaskModel saveTask;
	


	public TaskController(TaskModel saveTask, ActionType action){
		tabView = TabController.getTabView();
		this.saveTask = saveTask;
		workflowModel = WorkflowController.getWorkflowModel();
		this.observer = new TaskRequestObserver(this);
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
		}
	}
	
	public void processResponse(TaskModel task){
		TaskView taskView = tabView.getWorkflowView().getTaskViewByID(task.getID());
		if(taskView == null){
			WorkflowView workflowView = tabView.getWorkflowView();
			//TODO: store this selection in the actual tab
			this.stageIndex = task.getStageIndex();
			this.stageView = workflowView.getStageViewList().get(stageIndex);
			taskView = new TaskView(task, stageView);
			
			workflowModel.getStageModelList().get(stageIndex).addTask(task);
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
	
	public void sendDeleteRequest(TaskModel task){
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.DELETE); // POST == update
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
