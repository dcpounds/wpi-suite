package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		setLayout( new BoxLayout(this,BoxLayout.Y_AXIS) );
		this.titleLabel = new JLabel("Task Title:");
		this.taskTitleField = new JTextField("New Task");
		this.descriptionLabel = new JLabel("Task Description: ");
		this.taskDescriptionField = new JTextArea("Enter a description for the task");
		JScrollPane scrollPane = new JScrollPane(taskDescriptionField);
		scrollPane.setPreferredSize(new Dimension(300,300 ));
		
        //Empty the text field when a user clicks on it
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                taskDescriptionField.setText("");
            }
        };
		
		this.makeTaskButton = new JButton("Create this Task!");
		makeTaskButton.addActionListener( new AddTaskController(taskManagerTabView, this, 0) );
		makeTaskButton.addActionListener( new RemoveTabController(taskManagerTabView, this));
		add(titleLabel);
		add(taskTitleField);
		add(descriptionLabel);
		add(scrollPane);
		add(makeTaskButton);
		
	}
	
	public String getTitleLabelText(){
		System.out.println("Got" + taskTitleField.getText());
		return taskTitleField.getText();
	};
	
	public String getDescriptionText(){
		System.out.println("Got" + taskDescriptionField.getText());
		return taskDescriptionField.getText();
	}
}
