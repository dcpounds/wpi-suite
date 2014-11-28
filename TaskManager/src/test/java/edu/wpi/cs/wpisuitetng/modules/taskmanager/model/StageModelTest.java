package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;

/**
 * @author Nathaniel Jefferson
 *
 */
public class StageModelTest {

	StageModel stage = new StageModel();
	TaskModel task = new TaskModel();
	/**
	 * Test all methods in StageModel
	 */
	@Test
	public void stageTest() {
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

}
