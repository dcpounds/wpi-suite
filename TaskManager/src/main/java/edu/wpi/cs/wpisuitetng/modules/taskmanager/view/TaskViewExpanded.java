package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import net.miginfocom.swing.MigLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.TaskManager;

import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTextArea;

import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



/**
 *  This view is responsible for visually representing a task model
 */
public class TaskViewExpanded extends JPanel{

	private static final long serialVersionUID = -4932681028640603728L;
	private TaskModel taskModel;
	private JPanel assignedToPane;
	
	/**
	 * creates a new task view based off the given model
	 * 
	 * @param stageView -stage view for the task to be added to
	 * @param taskModel -model which the view is based off of
	 */
	public TaskViewExpanded(StageView stageView, TaskModel taskModel ){
		setLayout(new MigLayout("", "[grow]", "[][][][][][grow][][][grow]"));
		/* makes a label for the date, and then it
		 * gets the due date from the model and places it to view
		 */
		JLabel lblDue = new JLabel("Due:");
		lblDue.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblDue, "cell 0 0,alignx left");
		
		JTextArea dateArea = new JTextArea( taskModel.getDueDate() );
		dateArea.setLineWrap(true);
		dateArea.setEditable(false);
		add(dateArea, "cell 0 0, alignx right");
		
		JLabel lblEstimatedEffort = new JLabel("Estimated Effort: " + taskModel.getEstimatedEffort());
		lblEstimatedEffort.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblEstimatedEffort, "cell 0 1,alignx left");
		
		JLabel lblActualEffort = new JLabel("Actual Effort: " + taskModel.getActualEffort());
		lblActualEffort.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblActualEffort, "cell 0 2,alignx left");
		
		JSeparator separator = new JSeparator();
		add(separator, "cell 0 3");
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblDescription, "cell 0 4,alignx left");
		
		JTextArea textArea = new JTextArea( taskModel.getDescription() );
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		add(textArea, "cell 0 5,grow");
		
		JSeparator separator_1 = new JSeparator();
		add(separator_1, "cell 0 6");
		
		JLabel lblAssignedTo = new JLabel("Assigned To:");
		lblAssignedTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblAssignedTo, "cell 0 7");
		

		JList assignedToList = new JList();
		add(assignedToList, "flowy, cell 0 8,grow");
	}
	
	
	/**
	 *  Populates the task with the list of assigned users
	 */
	public void addAssignedUserViews(){
		for( UserModel userModel : taskModel.getUsersAssignedTo() ){
			UserIconView iconView = new UserIconView(userModel);
			this.assignedToPane.add(iconView);
		}
	}
}
