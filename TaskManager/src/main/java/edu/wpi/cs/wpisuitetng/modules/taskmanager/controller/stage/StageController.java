/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.SearchController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.ArchiveController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


/**
 * @author Alec
 * Handles stage and task persistence between the database and the user
 */
public class StageController implements ActionListener{
	private static WorkflowModel workflowModel;
	private StageModel stage;
	private ActionType action;
	private static AddStageRequestObserver addObserver;
	private static GetStageRequestObserver getObserver;
	private static UpdateStageRequestObserver updateObserver;
	private static DeleteStageRequestObserver deleteObserver;
	
	public StageController(StageModel stage, ActionType action){
		this.action = action;
		StageController.addObserver = new AddStageRequestObserver(this);
		StageController.updateObserver = new UpdateStageRequestObserver(this);
		StageController.deleteObserver = new DeleteStageRequestObserver(this);
		StageController.getObserver = new GetStageRequestObserver(this);
		this.workflowModel = WorkflowController.getWorkflowModel();
		this.stage = stage;
	}
	
	/**
	 * Depending on the specified action, either create, edit, delete, or get stageModel(s) from the database
	 */
	public void act(){
		switch(action){
			case CREATE: 
				sendAddRequest(stage);
				break;
			case EDIT:
				sendUpdateRequest(stage);
				break;
			case DELETE:
				{
					sendDeleteRequest(stage);	
					locallyUpdateAllStages();
					break;
				}
			case GET:
				sendGetRequest(stage);
				break;
		}
	}
	
	/**
	 * If called not statically, this method will determine the correct request to send based on the action that the controller is given
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		act();
	}
	
	/**
	 * @param stage - the stage to update in the database
	 */
	public static void sendUpdateRequest(StageModel stage){
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.POST); // POST == update
		request.setBody(stage.toJson()); // put the new stage in the body of the request
		request.addObserver(updateObserver); // add an observer to process the response
		request.send();
	}
	
	
	/**
	 * @param stage - the stage to archive in the database
	 */
	public static void sendDeleteRequest(StageModel stage){
		stage.setIsArchived(true);
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.POST); // POST == update
		request.setBody(stage.toJson()); // put the new stage in the body of the request
		request.addObserver(deleteObserver); // add an observer to process the response
		request.send();
		for(StageModel stage2 : workflowModel.getStageModelList().values())
		{
			stage2.setClosable(false);
			sendUpdateRequest(stage2);
		}
	}
	
	/**
	 * @param stageModel - the stage to add to the database
	 */
	public static void sendAddRequest(StageModel stageModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.PUT); // PUT == create
		request.setBody(stageModel.toJson()); // put the new stage in the body of the request
		request.addObserver(addObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * get a list of all stages from the database
	 */
	public static void sendGetRequest (StageModel stageModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/stage", HttpMethod.GET); // PUT == create
		request.addObserver(getObserver); // add an observer to process the response
		request.send();
	}

	/**
	 * Adds the stage that was just stored in the database to the workflow
	 * @param stage - the stage that was just added to the database
	 */
	public void addStage(StageModel stage) {
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		StageView stageView = new StageView(stage, workflowView);
		workflowView.addStageView(stage.getIndex(), stageView);
		workflowModel.addStage(stage);
		this.syncTaskViews(stage, stageView);
	}
	
	/**
	 * Locates the stage that a task exists in, null otherwise
	 * @param taskID
	 * @return the stage the task exists in, null otherwise
	 */
	public static StageModel locateTaskStage(int taskID){
		for(StageModel stageModel : workflowModel.getStageModelList().values())
			if(stageModel.getTaskModelList().get(taskID) != null)
				return stageModel;
		return null;
	}
	
	/**
	 * The stage that was saved in the database
	 * @param stage - the stage that just got updated in the database
	 */
	public void updateStage(StageModel stage) {
		if(workflowModel.getIsDraggingStage())
			return;
		
		System.out.println("Stage " + stage.getTitle() + " has index " + stage.getIndex());
		HashMap<Integer,StageView> stageViewList = TabController.getTabView().getWorkflowView().getStageViewList();
		boolean closable = stageViewList.size() <= 1 ? false : true;
		stage.setClosable(closable);
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		StageView stageView = workflowView.getStageViewByID(stage.getID());
		
		workflowView.addStageView(stage.getIndex(), stageView);
		
		if(!stage.getIsArchived() && stageView != null){
			this.syncTaskViews(stage, stageView);
			stageView.updateContents(stage);
		}
		SearchController.search();
	}
	
	/**
	 * Refreshes all the stageViews on the local client
	 * this method will not send any changes to the database
	 */
	public static void locallyUpdateAllStages(){
		for(StageModel stage : workflowModel.getStageModelList().values()){
			StageView stageView = TabController.getTabView().getWorkflowView().getStageViewByID(stage.getID());
			syncTaskViews(stage, stageView);
		}
	}
	
	/**
	 * Given a stage, update/add all the task views
	 * @param stageModel - the stageModel to sync the views of
	 * @param stage - the stageView to add the taskViews to
	 */
	public static void syncTaskViews(StageModel stageModel, StageView stage){
		HashMap<Integer, TaskModel> taskModelList = stageModel.getTaskModelList();
		HashMap<Integer, TaskView> taskViewList = stage.getTaskViewList();
		
		if(taskViewList.size() != taskModelList.size()){
			stage.clearAllStages();
		}
			
		for( TaskModel task : taskModelList.values() ){
			//System.out.println("Stage " + stageModel.getTitle() + " has task " + task.getTitle()); 
			TaskView taskView = taskViewList.get(task.getID());
			//If we found a matching taskView...
			if(taskView != null){
				taskView.setContents(task);
			} else{
				//If we found no match, add the task to the stageView
				TaskView newTaskView = new TaskView(task, stage);
				stage.addTaskView(newTaskView);
			}
		}
	}
	
	
	/**
	 * Removes a task from a stage
	 * @param taskView - the taskView to delete
	 */
	public static void deleteTask(TaskView taskView){
		StageView stageView = taskView.getStageView();
		StageModel stageModel = workflowModel.getStageModelByID(stageView.getID());
		try{
			TaskModel task = stageModel.getTaskModelList().get(taskView.getID());
			stageModel.removeTask(task);
			stageView.removeTaskView(taskView);
			StageController.sendUpdateRequest(stageModel);
			stageView.repaint();
			return;
		} catch(Exception e){
			System.out.println("Could not remove. Either the task could not be found"
					+ " or the stage could not be found.");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Archives a task
	 * @param taskView - the taskView to archive
	 */
	public static void archiveTask(TaskView taskView){
		StageView stageView = taskView.getStageView();
		StageModel stageModel = workflowModel.getStageModelByID(stageView.getID());
		try{
			TaskModel task = stageModel.getTaskModelList().get(taskView.getID());
			task.setIsArchived(true);
			if(!ArchiveController.getIsPressed()){
				taskView.setVisible(false);
			}
			StageController.sendUpdateRequest(stageModel);
			stageView.repaint();
			return;
		} catch(Exception e){
			System.out.println("Could not archive. Either the task could not be found"
					+ " or the stage could not be found.");
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Dearchives a task
	 * @param taskView - the taskView to dearchive 
	 */
	public static void dearchiveTask(TaskView taskView){
		StageView stageView = taskView.getStageView();
		StageModel stageModel = workflowModel.getStageModelByID(stageView.getID());
		try{
			TaskModel task = stageModel.getTaskModelList().get(taskView.getID());
			task.setIsArchived(false);
			taskView.setVisible(true);
			StageController.sendUpdateRequest(stageModel);
			stageView.repaint();
			return;
		} catch(Exception e){
			System.out.println("Could not dearchive. Either the task could not be found"
					+ " or the stage could not be found.");
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Given a list of stageModels that are contained within the database, make sure that they are up to date in the local workflow
	 * @param stages - the list of stages returned from the database
	 */
	public void syncStages(ArrayList<StageModel> stages) {
		if(stages.size() == 0)
			saveBaseStages();
		
		for(StageModel stage : stages ){
			boolean exists = workflowModel.getStageModelByID( stage.getID())  == null ? false : true;
			if(exists){
				if( stage.getIsArchived()){
					workflowModel.removeStageModel(stage);
					continue;
				} else{
					updateStage(stage);
					continue;
				}
			}
			if(stage.getIsArchived()){
				continue;
			}
			addStage(stage);
		}
	}
	
	
	/**
	 * Removes a stage from the workflow
	 * @param stage - after we sent the deleteRequest, the database found this to be the stage we wanted deleted
	 */
	public void deleteStage(StageModel stage) {
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		StageView sv = workflowView.getStageViewByID(stage.getID());
		workflowModel.removeStageModel(stage);
		workflowView.removeStageView(sv);
		
		int index = stage.getIndex();
		for(StageModel otherStage : workflowModel.getStageModelList().values()){
			if(otherStage.getIndex() > index ){
				otherStage.setIndex( otherStage.getIndex() - 1);
				StageController.sendUpdateRequest(otherStage);
			}
		}
	}
	
	
	/**
	 * Puts the four base stages into the database. This should really only happen once
	 */
	public void saveBaseStages(){
		StageModel newStage = new StageModel("New", 0);
		StageModel scheduledStage = new StageModel("Scheduled", 1);
		StageModel inProgressStage = new StageModel("In Progress", 2);
		StageModel completedStage = new StageModel("Completed", 3);
		
		StageController.sendAddRequest(newStage);
		StageController.sendAddRequest(scheduledStage);
		StageController.sendAddRequest(inProgressStage);
		StageController.sendAddRequest(completedStage);
	}
}
