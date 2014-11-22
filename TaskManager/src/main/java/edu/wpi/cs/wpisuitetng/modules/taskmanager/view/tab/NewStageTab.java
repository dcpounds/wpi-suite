package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.TaskController;
import net.miginfocom.swing.MigLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingConstants;

/**
 * this is a tab for creating stages
 *
 */
public class NewStageTab extends JPanel implements KeyListener{
	private static final long serialVersionUID = 7394421664708095366L;
	
	private JLabel titleLabel;
	private JTextField stageTitleField;
	private JButton sbmtStageButton;
    private final WorkflowModel workflowModel;
    private boolean validTitle = false;
    private JLabel stageTitleError;
    private StageModel model;
    private ActionType action;
    private NewStageTab thisTab;
	
	//add a combo box here for task status
    
	/**
	 * create a new tab for making a new stage
	 * 
	 * @param taskManagerTabView - the main view that holds tabs
	 */
	public NewStageTab(StageModel model, ActionType action) {
		if(model == null){
			this.model = new StageModel();
			this.action = ActionType.CREATE;
		} else{
			this.model = model;
			this.action = ActionType.EDIT;
		}
		
		
    	this.workflowModel = WorkflowController.getWorkflowModel();
		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
    	
		titleLabel = new JLabel("Stage Title(*)");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(titleLabel, "cell 0 0,alignx left, aligny top");
		
		this.stageTitleField = new JTextField();
		stageTitleField.setColumns(30);
		stageTitleField.addKeyListener(this);
		add(stageTitleField, "flowx,cell 0 1,alignx left,aligny top");
		
		sbmtStageButton = new JButton("Submit");
		this.thisTab = this;
		sbmtStageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StageModel stage = buildStage();
				new StageController(stage, action).act();
				TabController.getInstance().removeTab(thisTab);
			}	
		});
		sbmtStageButton.setEnabled(false);
		add(sbmtStageButton, "cell 0 2,alignx left, aligny top");
		
		stageTitleError = new JLabel("Must enter a title for the stage");
		stageTitleError.setForeground(Color.red);
		add(stageTitleError, "cell 0 1,alignx left,aligny top");
	}
	
	public WorkflowModel getWorkflowModel() {
        return this.workflowModel;
    }
	
	public String getStageTitle(){
		return stageTitleField.getText();
	}
	
	
	public StageModel buildStage(){
		StageModel stageModel = new StageModel(this.getStageTitle(), true);
		return stageModel;
	}

	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		validTitle = stageTitleField.getText().length() > 0 && stageTitleField.getText() != null  ? true : false;
		sbmtStageButton.setEnabled(validTitle);
		stageTitleError.setVisible(!validTitle);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
