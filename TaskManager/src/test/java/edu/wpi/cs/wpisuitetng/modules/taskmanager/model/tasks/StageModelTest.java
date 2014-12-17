package edu.wpi.cs.wpisuitetng.modules.taskmanager.model.tasks;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
/**
 * @author Ashton Kim
 *
 */
public class StageModelTest {

		StageModel stage = new StageModel();

		@Test
		public void StageCreationTest(){
			
			
			
			
		}
		
		/**
		 * 
		 * Tests the state changes of the StageModel
		 * 	
		 */
		@Test
		public void stateTest(){
			stage.setIsArchived(true);
			assertTrue(stage.getIsArchived());
			stage.setIsArchived(false);
			assertFalse(stage.getIsArchived());
			stage.setClosable (true);
			assertTrue(stage.getClosable());
			stage.setClosable(false);
			assertFalse(stage.getClosable());
		}
		/**
		 * 
		 * Tests the stateComp function for different indices.
		 * 	
		 */
		@Test
		public void stateCompTest(){
			StageModel stage1 = new StageModel("stage1",1);
			StageModel stage2 = new StageModel("stage2",2);

			
			assertEquals(0,stage1.compareTo(stage1));
			assertEquals(-1,stage1.compareTo(stage2));
			assertEquals(1,stage2.compareTo(stage1));
			
			
			
		}
		
		@Test
		public void titleTaste(){
			StageModel stage1 = new StageModel("",0);
			StageModel stage2 = new StageModel("StageTitle",2);
			
			assertEquals("",stage1.getTitle());
			assertEquals("StageTitle",stage2.getTitle());
			stage1.setTitle("NewStageTitle");
			assertEquals("NewStageTitle",stage1.getTitle());
			
		}
		
}
