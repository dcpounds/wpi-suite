package edu.wpi.cs.wpisuitetng.modules.taskmanager.model.tasks;

import static org.junit.Assert.assertEquals;





import java.util.Date;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityModel;
/**
 * @author Kwan Yeong Kim
 *
 */
public class ActivityModelTest {

	String comment = "comment";
	Date date = new Date(1992, 2,6);
	@Test
	public void constructActivityTest(){
		ActivityModel aModel = new ActivityModel(comment);
		Date newdate = new Date();
		aModel.setDate(newdate);
		assertEquals("comment",aModel.getMessage());
		assertEquals(new Date(), aModel.getDate());
		aModel.setDate(date);
		assertEquals(new Date(1992,2,6), aModel.getDate());
	}
}
