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

import java.util.HashMap;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;

/**
 * @author Nathaniel Jefferson
 *
 */
public class StageModelTest {

	/**
	 * Test all methods in StageModel
	 */
	@Test
	public void stageTest() {
		StageModel stage = new StageModel();
		TaskModel task = new TaskModel();
		stage.setTitle("Stage");
		stage.setID(2);
		stage.setIsArchived(true);
		
		StageModel stage1 = new StageModel("stage1", 0);
		stage1.copyFrom(stage);
		
		assertTrue("Stage".equals(stage.getTitle()));
		assertTrue(stage.equals(stage1));
		assertTrue(stage.equals(stage));
		assertFalse(stage.equals(task));
		assertFalse(stage.equals(null));
		assertTrue(stage.getIsArchived());
	}
	
	
	@Test
	public void addTaskModel() {
		StageModel stage = new StageModel();
		HashMap<Integer, TaskModel> hashMap = new HashMap<Integer, TaskModel>();
		TaskModel task = new TaskModel();
		TaskModel task2 = new TaskModel();
		TaskModel task3 = new TaskModel();
		
		hashMap.put(task.getID(), task);
		hashMap.put(task2.getID(), task2);
		hashMap.put(task3.getID(), task3);
		stage.addTaskModel(task);
		stage.addTaskModel(task2);
		stage.addTaskModel(task3);
		
		assertEquals(hashMap, stage.getTaskModelList());
		
	}
	
	@Test
	public void removeTask() {
		StageModel stage = new StageModel();
		HashMap<Integer, TaskModel> hashMap = new HashMap<Integer, TaskModel>();
		TaskModel task = new TaskModel();
		TaskModel task2 = new TaskModel();
		TaskModel task3 = new TaskModel();
		
		hashMap.put(task.getID(), task);
		hashMap.put(task2.getID(), task2);
		hashMap.put(task3.getID(), task3);
		stage.addTaskModel(task);
		stage.addTaskModel(task2);
		stage.addTaskModel(task3);
		
		assertEquals(hashMap, stage.getTaskModelList());
		
		stage.removeTask(task2);
		hashMap.remove(task2.getID());
		
		assertEquals(hashMap, stage.getTaskModelList());
	}

@Test
public void setIndexTest()
{
	StageModel stage = new StageModel();
	StageModel stage2 = new StageModel();
	StageModel stage3 = new StageModel();
	HashMap<Integer, TaskModel> hashMap = new HashMap<Integer, TaskModel>();
	TaskModel task = new TaskModel();
	TaskModel task2 = new TaskModel();
	TaskModel task3 = new TaskModel();
	
	hashMap.put(task.getID(), task);
	hashMap.put(task2.getID(), task2);
	hashMap.put(task3.getID(), task3);
	stage.addTaskModel(task);
	stage.addTaskModel(task2);
	stage.addTaskModel(task3);
	
	stage.setIndex(3);
	stage2.setIndex(0);
	stage3.setIndex(2);
	
	assertEquals(3, stage.getIndex());
	assertEquals(0, stage2.getIndex());
	assertEquals(3, stage3.getIndex());
	
}

@Test
public void setClosableTest()
{
	StageModel stage = new StageModel();
	StageModel stage2 = new StageModel();
	StageModel stage3 = new StageModel();
	HashMap<Integer, TaskModel> hashMap = new HashMap<Integer, TaskModel>();
	TaskModel task = new TaskModel();
	TaskModel task2 = new TaskModel();
	TaskModel task3 = new TaskModel();
	
	hashMap.put(task.getID(), task);
	hashMap.put(task2.getID(), task2);
	hashMap.put(task3.getID(), task3);
	stage.addTaskModel(task);
	stage.addTaskModel(task2);
	stage.addTaskModel(task3);
	
	stage.setClosable(true);
	stage2.setClosable(false);
	stage2.setClosable(true);
	stage3.setClosable(false);
	
	assertEquals(false, stage.getClosable());
	assertEquals(true, stage2.getClosable());
	assertEquals(false, stage3.getClosable());
	
}


	
}
