package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewTaskTab extends JPanel implements ITabType{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394421664708095366L;
	
	private JLabel titleLabel;
	private JTextField taskTitleField;
	
	private JLabel descriptionLabel;
	private JTextArea taskDescriptionField;
	
	private JButton makeTaskButton;
	
	//add a combo box here for task status
	
	public NewTaskTab() {
		setLayout(new BorderLayout() );
		this.titleLabel = new JLabel("Title: ");
		this.taskTitleField = new JTextField();
		taskTitleField.setPreferredSize(new Dimension(200, 100));
		
		this.descriptionLabel = new JLabel("Description: ");
		this.taskDescriptionField = new JTextArea();
		taskDescriptionField.setPreferredSize(new Dimension(400, 400));
		
		this.makeTaskButton = new JButton("Create this Task!");
		
		add(titleLabel, BorderLayout.NORTH);
		add(taskTitleField);
		add(descriptionLabel);
		add(makeTaskButton, BorderLayout.SOUTH);
		
	}
}
