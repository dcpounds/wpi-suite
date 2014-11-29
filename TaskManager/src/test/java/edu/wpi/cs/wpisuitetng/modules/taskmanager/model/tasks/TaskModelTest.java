package edu.wpi.cs.wpisuitetng.modules.taskmanager.model.tasks;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;

/**
 * @author Nathaniel Jefferson
 *
 */
public class TaskModelTest {
	TaskModel task = new TaskModel();
	StageModel stage = new StageModel();
	
	

	/**
	 * Test all methods in TaskModel
	 * @throws ParseException 
	 */
	@Test
	public void taskTest() throws ParseException {
		task.setID(5);
		task.setTitle("Task");
		task.setEstimatedEffort(16);
		task.setActualEffort(20);
		task.setDescription("Test Description");
		task.setDueDate("11-30-2014");
		task.setCreationDate(new Date());
		task.setIsExpanded(true);
		task.setExpanded(true);
		task.setEditState(true);
		task.setIsArchived(true);
		TaskModel task1 = new TaskModel();
		StageModel stage = new StageModel();
		task1.copyFrom(task);
		task1.setUsersAssignedTo(null);
		stage.setTitle("Hello");
		
		
		TaskModel task4 = null;
		
		assertEquals(5,task.getID());
		assertTrue("Task".equals(task.getTitle()));
		assertEquals(16,task.getEstimatedEffort());
		assertEquals(20,task.getActualEffort());
		assertTrue("Test Description".equals(task.getDescription()));
		assertTrue("11-30-2014".equals(task.getDueDate()));
		assertEquals(2,task.daysUntilDue());
		assertTrue(task.getIsExpanded());
		assertTrue(task.identify(task1));
		assertTrue(task.getEditState());
		assertTrue(task.equals(task));
		assertTrue(task.equals(task1));
		assertTrue(task.getIsArchived());
		assertFalse(task.equals(stage));
		assertFalse(task.equals(task4));
		assertTrue(task.getUsersAssignedTo().equals(new ArrayList<String>()));
		
		task1.setDueDate("12-15-2015");
		assertEquals(5,task1.getID());
		assertTrue("Task".equals(task1.getTitle()));
		assertEquals(16,task1.getEstimatedEffort());
		assertEquals(20,task1.getActualEffort());
		assertTrue("Test Description".equals(task1.getDescription()));
		assertTrue("12-15-2015".equals(task1.getDueDate()));
		assertEquals(382,task1.daysUntilDue());
		assertTrue(task1.getIsExpanded());
	}
	
}
