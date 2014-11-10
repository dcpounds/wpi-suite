package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * This class is used to display a workflow in the GUI. Right now the cards are hard-coded in,
 *  but in the future we will be able to dynamically add and remove them through
 *
 */
public class WorkflowView extends JPanel {
	
	public WorkflowView(String title) {
		this.setLayout( new BorderLayout() );
		JPanel workflowPanel = new JPanel();
		JScrollPane scrollBar = new JScrollPane(workflowPanel);
		
		//Create the four base cards
		CardView newCard = new CardView("New");
		CardView scheduledCard  = new CardView("Scheduled");
		CardView inProgressCard = new CardView("In Progress");
		CardView completedCard = new CardView("Completed");
		
		//Add them to the workflow panel
		workflowPanel.add(newCard);
		workflowPanel.add(scheduledCard);
		workflowPanel.add(inProgressCard);
		workflowPanel.add(completedCard);
		this.add(scrollBar, BorderLayout.CENTER);
		
	}

}
