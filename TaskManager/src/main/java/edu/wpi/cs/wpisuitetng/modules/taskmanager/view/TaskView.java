package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.db4o.User;



/**
 * @author alec
 *This view is responsible for visually representing a task
 */
public class TaskView extends JPanel{
	
	private final JLabel taskTitle;
	private final JLabel description;
	private final JLabel createdUsername;
	
	public TaskView(String title, String description, User createdBy){
		this.setOpaque(true);
		this.setBackground( Color.GRAY );
		this.setLayout(new GridLayout() );
		this.setPreferredSize(new Dimension(280, 200) );
		
		this.taskTitle = new JLabel(title);
		this.description = new JLabel(description);
		this.createdUsername = new JLabel(createdBy.toString());
		
		
	}
}
