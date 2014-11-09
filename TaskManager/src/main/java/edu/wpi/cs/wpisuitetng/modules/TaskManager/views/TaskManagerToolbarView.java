package edu.wpi.cs.wpisuitetng.modules.TaskManager.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * This view is responsible for holding all panels within the toolbar ribbon
 */
public class TaskManagerToolbarView extends JPanel {
	
	public TaskManagerToolbarView() {
		setLayout( new FlowLayout() );
		
		JButton newWorkflowButton = new JButton("New Workflow");
		JButton newTaskButton = new JButton("New Task");
		JButton newCardButton = new JButton("New Card");
		
		add(newWorkflowButton);
		add(newTaskButton);
		add(newCardButton);
	}

}
