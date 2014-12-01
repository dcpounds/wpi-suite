package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;

public class WorkflowModelTest {

	@Test
	public void test() {
		WorkflowModel workflow = new WorkflowModel("workflow",new HashMap<Integer,StageModel>());
		WorkflowModel workflow1 = new WorkflowModel("workflow1");
		
		assertTrue("workflow".equals(workflow.getName()));
		assertFalse(workflow.equals(workflow1));
		assertFalse(workflow.equals(null));
		assertTrue(workflow.equals(workflow));
	}

}
