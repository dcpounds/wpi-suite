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
		
		StageModel stage1 = new StageModel("stage1");
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

}
