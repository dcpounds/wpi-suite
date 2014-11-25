package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Nathaniel Jefferson
 *
 */
public class TaskModelTest {
	TaskModel task = new TaskModel();
	StageModel stage = new StageModel("Test");
	
	

	/**
	 * Test all methods in TaskModel
	 */
	@Test
	public void taskTest() {
		task.setId(5);
		task.setTitle("Task");
		task.setStatus("new");
		task.setEstimatedEffort(16);
		task.setActualEffort(20);
		task.setDescription("Test Description");
		task.setDueDate("01/15/2015");
		task.setEditIndex(2);
		task.setIsExpanded(true);
		task.setEditState(true);
		
		TaskModel task1 = new TaskModel("Task1","Description 1");
		TaskModel task2 = new TaskModel("Task2","Description 2","11/21/2014");
		TaskModel task3 = new TaskModel(3,4,5,"Task3","Description 3",null,null);
		TaskModel task4 = null;
		
		assertEquals(5,task.getId());
		assertEquals(3,task3.getId());
		assertTrue("Task".equals(task.getTitle()));
		assertTrue("new".equals(task.getStatus()));
		assertEquals(16,task.getEstimatedEffort());
		assertEquals(4,task3.getEstimatedEffort());
		assertEquals(20,task.getActualEffort());
		assertEquals(5,task3.getActualEffort());
		assertTrue("Test Description".equals(task.getDescription()));
		assertTrue("Description 1".equals(task1.getDescription()));
		assertTrue("Description 2".equals(task2.getDescription()));
		assertTrue("Description 3".equals(task3.getDescription()));
		assertTrue("01/15/2015".equals(task.getDueDate()));
		assertTrue("11/21/2014".equals(task2.getDueDate()));
		assertTrue(task1.equals(task1));
		assertTrue(!task1.equals(task4));
		assertTrue(!task1.equals(task2));
		assertEquals(2,task.getEditIndex());
		assertTrue(task.getIsExpanded());
		assertTrue(task.getEditState());
	}
	
	/**
	 * Test all methods in StageModel
	 */
	@Test
	public void stageTest() {
		stage.setTitle("Stage");
		
		assertTrue("Stage".equals(stage.getTitle()));
	}

}
