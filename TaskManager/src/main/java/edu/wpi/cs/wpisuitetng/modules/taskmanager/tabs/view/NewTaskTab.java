package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StatusModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTabController;

/**
 * This is a tab for creating new tasks
 *
 */
public class NewTaskTab extends JPanel{
	
	private static final long serialVersionUID = -8772773694939459349L;
	private JTextField taskTitleField;
	private JComboBox<String> stageBox;
	private JComboBox<String> workFlowBox;
	private JLabel taskDescriptionLabel;
	private JTextArea taskDescriptionField;
    private final WorkflowModel workflowModel;
	
	
	/**
	 * contructs a tab for creating tasks
	 * 
	 * @param taskManagerTabView - the main view that holds tabs
	 * @param workflowModel - the main workflow model
	 */
	public NewTaskTab(TaskManagerTabView taskManagerTabView,  WorkflowModel workflowModel) {
		this.workflowModel = workflowModel;
		
    	//TODO: FIX THIS SO THAT USERS ARE LOADED FROM THE ACTIVE WORKFLOW
		//FOR NOW I AM USING 0 TO SPECIFY THE DEFAULT WORKFLOW
		//WorkflowModel workflowModel = workflowModel.getWorkflowModel(0); 
		
		
		setLayout(new MigLayout("", "[][][][grow]", "[][][][][]"));
		
		JLabel taskTitleLabel = new JLabel("Task Title");
		add(taskTitleLabel, "flowx,cell 1 2");
		
		JLabel stageLabel = new JLabel("Stage");
		add(stageLabel, "cell 3 2");
		
		taskTitleField = new JTextField();
		taskTitleField.setText("New Task");
		add(taskTitleField, "flowx,cell 1 3,alignx left");
		taskTitleField.setColumns(35);
		
		stageBox = new JComboBox<String>();
		stageBox.setToolTipText("Select a status for this task");
		stageBox.setModel(new DefaultComboBoxModel<String>(new StatusModel().getStatusNames() ) );
		stageBox.setSelectedIndex(0);
		add(stageBox, "cell 3 3");
		
		taskDescriptionLabel = new JLabel("Task Description");
		add(taskDescriptionLabel, "cell 1 5");
		
		taskDescriptionField = new JTextArea();
		taskDescriptionField.setLineWrap(true);
		taskDescriptionField.setColumns(50);
		taskDescriptionField.setRows(10);
		add(taskDescriptionField, "cell 1 6,alignx left,growy");
		
		AssignUsersView assignUsersView = new AssignUsersView(taskManagerTabView, workflowModel);
		
		JButton makeTaskButton = new JButton("Create");
		makeTaskButton.addActionListener( new AddTaskController(taskManagerTabView, this, assignUsersView, 0));
		makeTaskButton.addActionListener( new RemoveTabController(taskManagerTabView, this));
		
		add(assignUsersView, "cell 3 6,grow");
		add(makeTaskButton, "cell 1 8");
		
	}
	
	/**
	 * get the current string in the title field
	 * 
	 * @return
	 */
	public String getTitleLabelText(){
		return taskTitleField.getText();
	};
	
	/**
	 * get the current string in the description field
	 * 
	 * @return
	 */
	public String getDescriptionText(){
		return taskDescriptionField.getText();
	}
	
	/**
	 * get the string representing the name of the current selected stage
	 * 
	 * @return
	 */
	public String getStatusText() {
		return (String)stageBox.getSelectedItem();
	}

}