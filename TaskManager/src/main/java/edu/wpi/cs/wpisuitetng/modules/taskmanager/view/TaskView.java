package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.db4o.User;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;



/**
 * @author alec
 *This view is responsible for visually representing a task
 */
public class TaskView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4932681028640603728L;
	private final JTextField taskTitle;
	private final JTextArea description;
	private final JTextField createdUsername;
	private TaskModel taskModel;
	
	public TaskView( TaskModel taskModel ){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.taskModel = taskModel;
		this.taskTitle = new JTextField( taskModel.getTitle() );
		this.description = new JTextArea(taskModel.getDescription() );
		this.createdUsername = new JTextField( taskModel.getCreator().toString() );
		this.setPreferredSize(new Dimension(240, 200) );
		
		this.add(taskTitle);
		this.add(description);
		this.add(createdUsername);
		
		taskTitle.setEditable(false);
		description.setEditable(false);
		description.setLineWrap(true);
		createdUsername.setEditable(false);
	}
}
