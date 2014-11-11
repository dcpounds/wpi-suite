package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewWorkflowView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394421664708095366L;
	
	private JLabel titleLabel;
	private JTextField workflowTitleField;
	
	private JButton makeWorkflowButton;
	
	//add a combo box here for task status
	
	public NewWorkflowView(TaskManagerTabView taskManagerTabView) {
		this.titleLabel = new JLabel("Title: ");
		this.workflowTitleField = new JTextField();
		this.makeWorkflowButton = new JButton("Create this Workflow!");
		
		add(titleLabel);
		add(workflowTitleField);
		add(makeWorkflowButton);
		
		setLayout(new FlowLayout() );
		
	}
}
