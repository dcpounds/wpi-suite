package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddStageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import net.miginfocom.swing.MigLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton sbmtStageButton;
    private final WorkflowModel workflowModel;
	
	//add a combo box here for task status
    
	/**
	 * create a new tab for making a new stage
	 * 
	 * @param taskManagerTabView - the main view that holds tabs
	 */
	public NewStageTab(StageModel stageModel) {
    	this.workflowModel = WorkflowController.getWorkflowModel();

		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
    	
		titleLabel = new JLabel("Title: ");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(titleLabel, "cell 0 0,alignx left, aligny top");
		
		stageTitleField = new JTextField();
		stageTitleField.setColumns(30);
		add(stageTitleField, "cell 0 1,alignx left, aligny top");
		
		sbmtStageButton = new JButton("Submit");
		sbmtStageButton.addActionListener(new AddStageController(this));
		NewStageTab thisTab = this;
		sbmtStageButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().removeTab(thisTab);
			}
		});
		add(sbmtStageButton, "cell 0 2,alignx left, aligny top");
		
	}
	
	public WorkflowModel getWorkflowModel() {
        return this.workflowModel;
    }
	
	public String getStageTitle(){
		return stageTitleField.getText();
	}
}
