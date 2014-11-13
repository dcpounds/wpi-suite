package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;

public class NewCardTab extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394421664708095366L;
	
	private JLabel titleLabel;
	private JTextField cardTitleField;
	private JButton makeCardButton;
    private final WorkflowModel workflowModel;
	
	//add a combo box here for task status
    
	public NewCardTab(TaskManagerTabView taskManagerTabView, WorkflowModel workflowModel) {
    	this.workflowModel = workflowModel;
		this.titleLabel = new JLabel("Title: ");
		this.cardTitleField = new JTextField();
		this.makeCardButton = new JButton("Create this Card!");
		
		add(titleLabel);
		add(cardTitleField);
		add(makeCardButton);
		
		setLayout(new FlowLayout() );
		
	}
	
	public WorkflowModel getWorkflowListModel() {
        return this.workflowModel;
    }
}
