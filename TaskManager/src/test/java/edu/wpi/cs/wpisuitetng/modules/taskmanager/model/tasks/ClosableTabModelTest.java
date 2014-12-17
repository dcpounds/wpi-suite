package edu.wpi.cs.wpisuitetng.modules.taskmanager.model.tasks;


import static org.junit.Assert.*;



import javax.swing.ImageIcon;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;

public class ClosableTabModelTest {
			ImageIcon icon = new ImageIcon("archive.png");

			ClosableTabModel newmodel1 = new ClosableTabModel("newmodel1", icon, 1); 
			ClosableTabModel newmodel2 = new ClosableTabModel("newmodel2", 2);
			ClosableTabModel newmodel3 = new ClosableTabModel("newmodel3");
			ClosableTabModel newmodel4 = new ClosableTabModel();
				
		@Test 
		public void getSetTest()
		{
			assertEquals("newmodel1",newmodel1.getTabTitle());
			assertEquals(icon,newmodel1.getImageIcon());
			assertEquals(1,newmodel1.getTabIndex());
			assertEquals("newmodel2",newmodel2.getTabTitle());
			assertEquals(2,newmodel2.getTabIndex());
			assertEquals("newmodel3",newmodel3.getTabTitle());
	
			
			assertEquals(null,newmodel4.getTabTitle());
			newmodel1.setTabTitle("new");
			newmodel1.setTabIndex(2);
			newmodel2.setImageIcon("archive.png");
			
			assertEquals("new",newmodel1.getTabTitle());
			assertEquals(2,newmodel1.getTabIndex());
			
		}
		
		
	
		

}
