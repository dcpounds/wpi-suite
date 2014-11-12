package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StatusModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTabController;

public class NewTaskView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8772773694939459349L;
	private JTextField taskTitleField;
	private JComboBox<String> taskStatusBox;
	private JLabel taskDescriptionLabel;
	private JTextArea taskDescriptionField;
	
	
	public NewTaskView(TaskManagerTabView taskManagerTabView) {
		
		
		setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][][][][grow]"));
		
		JLabel newTaskLabel = new JLabel("Create a New Task");
		add(newTaskLabel, "cell 1 0 3 1,alignx center");
		
		JLabel taskTitleLabel = new JLabel("Task Title");
		add(taskTitleLabel, "flowx,cell 1 2");
		
		JLabel taskStatusLabel = new JLabel("Task Status");
		add(taskStatusLabel, "cell 3 2");
		
		taskTitleField = new JTextField();
		taskTitleField.setText("New Task");
		add(taskTitleField, "flowx,cell 1 3,alignx left");
		taskTitleField.setColumns(35);
		
		taskStatusBox = new JComboBox<String>();
		taskStatusBox.setToolTipText("Select a status for this task");
		taskStatusBox.setModel(new DefaultComboBoxModel<String>(new StatusModel().getStatusNames() ) );
		taskStatusBox.setSelectedIndex(0);
		add(taskStatusBox, "cell 3 3");
		
		taskDescriptionLabel = new JLabel("Task Description");
		add(taskDescriptionLabel, "cell 1 5");
		
		taskDescriptionField = new JTextArea();
		taskDescriptionField.setLineWrap(true);
		taskDescriptionField.setColumns(50);
		taskDescriptionField.setRows(10);
		add(taskDescriptionField, "cell 1 6 3 1,alignx left,aligny top");
		
		JButton makeTaskButton = new JButton("Create");
		makeTaskButton.addActionListener( new AddTaskController(taskManagerTabView, this, 0) );
		makeTaskButton.addActionListener( new RemoveTabController(taskManagerTabView, this));
		add(makeTaskButton, "cell 1 8");
	}
	
	public String getTitleLabelText(){
		return taskTitleField.getText();
	};
	
	public String getDescriptionText(){
		return taskDescriptionField.getText();
	}
	
	public String getStatusText() {
		return (String)taskStatusBox.getSelectedItem();
	}

}
