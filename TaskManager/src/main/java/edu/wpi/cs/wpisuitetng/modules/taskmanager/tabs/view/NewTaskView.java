package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;

public class NewTaskView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394421664708095366L;
	private JLabel titleLabel;
	private JTextField taskTitleField;
	
	private JLabel descriptionLabel;
	private JTextArea taskDescriptionField;
	private final MouseAdapter mouseListener;
	
	private JButton makeTaskButton;
	
	//add a combo box here for task status
	
	public NewTaskView(TaskManagerTabView taskManagerTabView) {
		this.titleLabel = new JLabel("Task Title:");
		titleLabel.setPreferredSize(new Dimension(100,0));
		this.taskTitleField = new JTextField("New Task");
		
		this.descriptionLabel = new JLabel("Task Description: ");
		this.taskDescriptionField = new JTextArea("Enter a description for the task");
		
        //Empty the text field when a user clicks on it
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                taskDescriptionField.setText("");
            }
        };
		
		this.makeTaskButton = new JButton("Create this Task!");
		TaskModel taskModel = new TaskModel("Test","Testing", new User("alec", "alec12094", "blah", 0));
		makeTaskButton.addActionListener( new AddTaskController(taskManagerTabView,this,taskModel, 0) );
		makeTaskButton.addActionListener( new RemoveTabController(taskManagerTabView, this));
		add(titleLabel);
		add(taskTitleField);
		add(descriptionLabel);
		add(taskDescriptionField);
		add(makeTaskButton);
		
	}
}
