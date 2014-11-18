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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTabController;

/**
 * This is a tab for creating new tasks
 *
 *  11/16 Further Progress on utilizing this class for editting functionality aswell.
 * 		Authors  Guillermo, Ashton;
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
	public NewTaskTab(TaskManagerTabView taskManagerTabView, WorkflowModel workflowModel, TaskModel taskmodel) {
		this.workflowModel = workflowModel;
		setLayout(new MigLayout("", "[][][][grow]", "[][][][][]"));
			
		JLabel taskTitleLabel2 = new JLabel("Task Title");
		add(taskTitleLabel2, "flowx,cell 1 2");
			
		JLabel stageLabel2 = new JLabel("Stage");
		add(stageLabel2, "cell 3 2");
			
		taskTitleField = new JTextField(taskmodel.getTitle());
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
			
		AssignUsersView assignUsersView2 = new AssignUsersView(taskManagerTabView, workflowModel);
			
		JButton makeTaskButton2 = new JButton("Create");
		makeTaskButton2.addActionListener( new AddTaskController(taskManagerTabView, this, assignUsersView2, 0));
		makeTaskButton2.addActionListener( new RemoveTabController(taskManagerTabView, this));
			
		add(assignUsersView2, "cell 3 6,grow");
		add(makeTaskButton2, "cell 1 8");
		
		
		
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