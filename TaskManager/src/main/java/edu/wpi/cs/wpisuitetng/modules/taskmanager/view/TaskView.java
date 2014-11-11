package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
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
		this.taskTitle = new JLabel(title);
		this.description = new JLabel(desc);
		this.createdUsername = new JLabel(createdBy.toString());
		
		formatTaskView();
	}
	
	public TaskView( TaskModel taskModel ){
		this.taskModel = taskModel;
		this.taskTitle = new JLabel( taskModel.getTitle() );
		this.description = new JLabel( taskModel.getDescription() );
		this.createdUsername = new JLabel( taskModel.getCreator().toString());
		
		formatTaskView();
	}
	
	private void formatTaskView(){
		this.setOpaque(true);
		this.setBackground( Color.GRAY );
		this.setLayout(new BorderLayout() );
		this.setPreferredSize(new Dimension(240, 200) );
		
		this.add(taskTitle, BorderLayout.NORTH);
		this.add(description, BorderLayout.CENTER);
		this.add(createdUsername, BorderLayout.CENTER);
	}
}
