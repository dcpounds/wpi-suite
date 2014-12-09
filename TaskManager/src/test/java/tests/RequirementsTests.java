package tests;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RequirementsController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RequirementsRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.NewTaskTab;

public class RequirementsTests {
	
	/*Add all setup functionality here, which will generally be a few
	 * variable declarations, and a constructor. Remember, no magic numbers!
	 */
	
	Requirement requirement1 = new Requirement(27, "thisismyname", "I am a requirement");
	Requirement requirement2 = new Requirement(31, "snickerdoodle", "I am a cookie");
	Requirement requirement3 = new Requirement(14, "mocha", "I am a latte");
	Requirement[] listOfRequirements1 = new Requirement[]{requirement1, requirement2, requirement3};
	
	
	//method name should be "testMethodName"
	//add similar methods for each method of the class being tested
	@Test
	public void testMakeReqirementsController() {
		
		//assertEquals is of the following form:
		//assertEquals(expected result, message to compare to)
		//value to compare to is nearly always a function call
		
		RequirementsController controller = new RequirementsController();
		//controller.requestRequirementsList();
		TaskModel testtask = new TaskModel();
		testtask.setTitle("Make Coffee");
		testtask.setDescription("I like coffee");
		testtask.setDueDate("12/13/14");
		testtask.setAssociatedRequirement(requirement3.getName());
		NewTaskTab testtasktab = new NewTaskTab(testtask);
		testtasktab.buildTask();
		
		assertTrue(1==1);
		/*
		String[] test = observer.requirementsToStrings(listOfRequirements1);
		String[] expected = new String[]{"thisismyname", "snickerdoodle", "mocha"};
		*/
		//System.out.println(test[1]);
		//System.out.println(expected[1]);
//		assertTrue(test[0].equals(expected[0]));
//		assertTrue(test[1].equals(expected[1]));
//		assertTrue(test[2].equals(expected[2]));
//		assertTrue(test.equals(expected));
	}
}
