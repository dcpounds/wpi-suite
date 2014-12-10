/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package tests;


import org.junit.Test;
import static org.junit.Assert.assertTrue;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;

public class RequirementsTests {
	
	/*Add all setup functionality here, which will generally be a few
	 * variable declarations, and a constructor. Remember, no magic numbers!
	 */
	
	Requirement requirement1 = new Requirement(27, "thisismyname", "I am a requirement");
	Requirement requirement2 = new Requirement(31, "snickerdoodle", "I am a cookie");
	Requirement requirement3 = new Requirement(14, "mocha", "I am a latte");
	Requirement[] listOfRequirements1 = new Requirement[]{requirement1, requirement2, requirement3};
	StageModel stage1 = new StageModel("Favorite Things", 0);
	WorkflowModel workflow = new WorkflowModel("basic");
	
	//method name should be "testMethodName"
	//add similar methods for each method of the class being tested
	@Test
	public void testSetAssociatedRequirement() {
		
		//RequirementsController controller = new RequirementsController();
		//controller.requestRequirementsList();
		TaskModel testtask = new TaskModel();
		testtask.setTitle("Make Coffee");
		testtask.setDescription("I like coffee");
		testtask.setDueDate("12/13/14");
		testtask.setAssociatedRequirement(requirement3.getName());
		assertTrue(testtask.getAssociatedRequirement().equals(requirement3.getName()));
		
	}
	
}
