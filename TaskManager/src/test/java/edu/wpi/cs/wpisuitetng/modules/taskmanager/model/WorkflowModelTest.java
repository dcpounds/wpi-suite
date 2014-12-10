/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import static org.junit.Assert.*;
import java.util.LinkedHashMap;
import org.junit.Test;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;

public class WorkflowModelTest {

	@Test
	public void test() {
		WorkflowModel workflow = new WorkflowModel("workflow",new LinkedHashMap<Integer,StageModel>());
		WorkflowModel workflow1 = new WorkflowModel("workflow1");
		
		assertTrue("workflow".equals(workflow.getName()));
		assertFalse(workflow.equals(workflow1));
		assertFalse(workflow.equals(null));
		assertTrue(workflow.equals(workflow));
	}
	
	@Test
	public void getStageModelByIDTest(){
		WorkflowModel workflow = new WorkflowModel("workflow",new LinkedHashMap<Integer,StageModel>());
		StageModel stageModel = new StageModel("Test", 0);
		StageModel stageModel2 = new StageModel("Test2", 0);
		StageModel stageModel3 = new StageModel("Test3", 0);
		StageModel stageModel4 = new StageModel("Test4", 0);

		workflow.addStage(stageModel);
		workflow.addStage(stageModel2);
		workflow.addStage(stageModel3);
		workflow.addStage(stageModel4);
		
		int stageID = stageModel.getID();
		int stageID2 = stageModel2.getID();
		int stageID3 = stageModel3.getID();
		int stageID4 = stageModel4.getID();
		
		assertEquals(stageModel, workflow.getStageModelByID(stageID));
		assertEquals(stageModel2, workflow.getStageModelByID(stageID2));
		assertEquals(stageModel3, workflow.getStageModelByID(stageID3));
		assertEquals(stageModel4, workflow.getStageModelByID(stageID4));
		
	}
	
	@Test
	public void getTaskModelByIDTest(){
		WorkflowModel workflow = new WorkflowModel("workflow",new LinkedHashMap<Integer,StageModel>());
		StageModel stageModel = new StageModel("Test", 0);
		StageModel stageModel2 = new StageModel("Test2", 0);
		StageModel stageModel3 = new StageModel("Test3", 0);
		StageModel stageModel4 = new StageModel("Test4", 0);
		
		TaskModel taskModel = new TaskModel();
		TaskModel taskModel2 = new TaskModel();
		TaskModel taskModel3 = new TaskModel();
		TaskModel taskModel4 = new TaskModel();
		TaskModel taskModel5 = new TaskModel();
		
		stageModel2.addTaskModel(taskModel);
		stageModel2.addTaskModel(taskModel3);
		stageModel4.addTaskModel(taskModel4);
		
		stageModel4.addTaskModel(taskModel2);
		stageModel4.addTaskModel(taskModel5);

		workflow.addStage(stageModel);
		workflow.addStage(stageModel2);
		workflow.addStage(stageModel3);
		workflow.addStage(stageModel4);
		
		int taskID1 = taskModel.getID();
		int taskID2 = taskModel2.getID();
		int taskID3 = taskModel3.getID();
		int taskID4 = taskModel4.getID();
		int taskID5 = taskModel5.getID();
		
		assertEquals(taskModel, workflow.getTaskModelByID(taskID1));
		assertEquals(taskModel2, workflow.getTaskModelByID(taskID2));
		assertEquals(taskModel3, workflow.getTaskModelByID(taskID3));
		assertEquals(taskModel4, workflow.getTaskModelByID(taskID4));
		assertEquals(taskModel5, workflow.getTaskModelByID(taskID5));
		
	}
	
	

}
