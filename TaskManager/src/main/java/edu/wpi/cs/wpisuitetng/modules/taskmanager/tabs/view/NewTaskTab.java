package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTabController;

public class NewTaskTab extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8772773694939459349L;
	private JTextField taskTitleField;
	private JLabel taskDescriptionLabel;
	private JTextArea taskDescriptionField;
    private final WorkflowListModel workflowList;
	
	
	/**
	 * @param taskManagerTabView - the main view that holds tabs
	 * @param workflowList - the list of current workflows
	 */
	public NewTaskTab(TaskManagerTabView taskManagerTabView,  WorkflowListModel workflowList) {
		this.workflowList = workflowList;
		
    	//TODO: FIX THIS SO THAT USERS ARE LOADED FROM THE ACTIVE WORKFLOW
		//FOR NOW I AM USING 0 TO SPECIFY THE DEFAULT WORKFLOW
		WorkflowModel workflowModel = workflowList.getWorkflowModel(0); 

		setLayout(new MigLayout("", "[][][][grow][]", "[][][][][][][grow][][][grow]"));
		setPreferredSize(new Dimension(800, 600));

		
		JLabel newTaskLabel = new JLabel("Create a New Task");
		add(newTaskLabel, "cell 1 0 3 1,alignx center");
		
		JLabel taskTitleLabel = new JLabel("Task Title");
		add(taskTitleLabel, "flowx,cell 1 2");
		
		taskTitleField = new JTextField();
		taskTitleField.setText("New Task");
		add(taskTitleField, "flowx,cell 1 3,alignx left");
		taskTitleField.setColumns(50);
		
		taskDescriptionLabel = new JLabel("Task Description");
		add(taskDescriptionLabel, "cell 1 5");
		
		taskDescriptionField = new JTextArea();
		taskDescriptionField.setLineWrap(true);
		taskDescriptionField.setColumns(50);
		taskDescriptionField.setRows(15);
		add(taskDescriptionField, "cell 1 6 1 2,alignx left,aligny top");
		
		AssignUsersView assignUsersView = new AssignUsersView(taskManagerTabView, workflowModel);
		
		JButton makeTaskButton = new JButton("Create");
		makeTaskButton.addActionListener( new AddTaskController(taskManagerTabView, this, assignUsersView, 0));
		makeTaskButton.addActionListener( new RemoveTabController(taskManagerTabView, this));
		
		add(assignUsersView, "cell 3 3 1 5,alignx left");
		add(makeTaskButton, "cell 1 8");
	}
	
	public String getTitleLabelText(){
		return taskTitleField.getText();
	};
	
	public String getDescriptionText(){
		return taskDescriptionField.getText();
	}

}
