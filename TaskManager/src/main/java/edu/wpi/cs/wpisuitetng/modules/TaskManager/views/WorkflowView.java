package edu.wpi.cs.wpisuitetng.modules.TaskManager.views;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorkflowView extends JPanel {
	
	public WorkflowView(String title) {
		JLabel workflowTitle = new JLabel(title);
		this.add(workflowTitle);
		
		CardView requirementsCard = new CardView("Requirements");
		CardView toDoCard = new CardView("To Do");
		CardView doingCard = new CardView("Doing");
		CardView completedCard = new CardView("CompletedCard");
		
		this.add(requirementsCard);
		this.add(toDoCard);
		this.add(doingCard);
		this.add(completedCard);
		
	}

}
