package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddStageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * this is a tab for creating stages
 *
 */
public class NewStageTab extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394421664708095366L;
	
	private JLabel titleLabel;
	private JTextField stageTitleField;
	private JButton makeStageButton;
    private final WorkflowModel workflowModel;
	
	//add a combo box here for task status
    
	/**
	 * create a new tab for making a new stage
	 * 
	 * @param taskManagerTabView - the main view that holds tabs
	 * @param workflowModel - the main workflow model
	 */
	public NewStageTab(TaskManagerTabView taskManagerTabView, WorkflowModel workflowModel) {
    	this.workflowModel = workflowModel;
		this.titleLabel = new JLabel("Title: ");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		add(titleLabel, "cell 0 0,alignx left,aligny center");
		this.stageTitleField = new JTextField();
		add(stageTitleField, "flowy,cell 0 1,growx,aligny center");
		this.makeStageButton = new JButton("Create this Stage!");
		makeStageButton.addActionListener(new AddStageController(taskManagerTabView, this));
		makeStageButton.addActionListener( new RemoveTabController(taskManagerTabView, this));
		add(makeStageButton, "cell 0 1,alignx center,aligny top");
		
	}
	
	public WorkflowModel getWorkflowModel() {
        return this.workflowModel;
    }
	
	public String getStageTitle(){
		return stageTitleField.getText();
	}
}
