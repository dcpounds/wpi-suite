package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StatusModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;

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
	 */

	public NewTaskTab(TaskModel taskmodel) {
		this.workflowModel = WorkflowController.getWorkflowModel();
		
		setLayout(new MigLayout("", "[][][][grow]", "[][][][][]"));
			
		JLabel taskTitleLabel = new JLabel("Task Title");
		add(taskTitleLabel, "flowx,cell 1 2");
			
		JLabel stageLabel = new JLabel("Stage");
		add(stageLabel, "cell 3 2");
			
		taskTitleField = new JTextField(taskmodel.getTitle());
		add(taskTitleField, "flowx,cell 1 3,alignx left");
		taskTitleField.setColumns(35);
			
		stageBox = new JComboBox<String>();
		stageBox.setToolTipText("Select a status for this task");
		stageBox.setModel(new DefaultComboBoxModel<String>(new StatusModel().getStatusNames() ) );
		//stageBox.setSelectedIndex(0);
		add(stageBox, "cell 3 3");
		taskDescriptionLabel = new JLabel("Task Description");
		add(taskDescriptionLabel, "cell 1 5");
			
		taskDescriptionField = new JTextArea();
		taskDescriptionField.setLineWrap(true);
		taskDescriptionField.setColumns(50);
		taskDescriptionField.setRows(10);
		add(taskDescriptionField, "cell 1 6,alignx left,growy");
			
		AssignUsersView assignUsersView = new AssignUsersView();
			
		JButton makeTaskButton = new JButton("Create");
		makeTaskButton.addActionListener( new AddTaskController(this, assignUsersView, 0));
		NewTaskTab thisTab = this;
		makeTaskButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().removeTab(thisTab);
			}
		});
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
	
	public int getStageSelectionIndex() {
		return this.stageBox.getSelectedIndex();
	}
	
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
