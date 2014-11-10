package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewWorkflowTab extends JPanel implements ITabType{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394421664708095366L;
	
	private JLabel titleLabel;
	private JTextField workflowTitleField;
	
	private JButton makeWorkflowButton;
	
	//add a combo box here for task status
	
	public NewWorkflowTab() {
		this.titleLabel = new JLabel("Title: ");
		this.workflowTitleField = new JTextField();
		this.makeWorkflowButton = new JButton("Create this Workflow!");
		
		add(titleLabel);
		add(workflowTitleField);
		add(makeWorkflowButton);
		
		setLayout(new FlowLayout() );
		
	}
}
