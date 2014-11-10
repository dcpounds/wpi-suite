package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.db4o.User;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;



/**
 * @author alec
 *This view is responsible for visually representing a task
 */
public class TaskView extends JPanel{
	
	private final JLabel taskTitle;
	private final JLabel description;
	private final JLabel createdUsername;
	private TaskModel taskModel;
	
	public TaskView(String title, String desc, User createdBy){
		this.setOpaque(true);
		this.setBackground( Color.GRAY );
		this.setLayout(new GridLayout() );
		this.setPreferredSize(new Dimension(280, 200) );
		
		this.taskTitle = new JLabel(title);
		this.description = new JLabel(desc);
		this.createdUsername = new JLabel(createdBy.toString());
		
		this.add(taskTitle);
		this.add(description);
		this.add(createdUsername);
	}
	
	public TaskView( TaskModel taskModel ){
		this.taskModel = taskModel;
		this.taskTitle = new JLabel( taskModel.getTitle() );
		this.description = new JLabel( taskModel.getDescription() );
		this.createdUsername = new JLabel( taskModel.getCreator().toString() );
		
		this.setOpaque(true);
		this.setBackground( Color.GRAY );
		this.setLayout(new GridLayout() );
		this.setPreferredSize(new Dimension(280, 200) );
		
		this.add(taskTitle);
		this.add(description);
		this.add(createdUsername);
	}
}
