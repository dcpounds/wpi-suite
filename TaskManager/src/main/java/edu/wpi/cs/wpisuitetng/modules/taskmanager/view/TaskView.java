package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.Font;



/**
 *  This view is responsible for visually representing a task model
 */
public class TaskView extends JPanel{

	private static final long serialVersionUID = -4932681028640603728L;
	private TaskModel taskModel;
	private JPanel assignedToPane;
	
	/**
	 * creates a new task view based off the given task model
	 * 
	 * @param stageView -stage view for the task to be added to
	 * @param taskModel -model which the view is based off of
	 */
	public TaskView(StageView stageView, TaskModel taskModel ){
		setLayout(new MigLayout("", "[grow][grow][]", "[][][][][][grow][][][grow]"));
		
		JLabel lblDue = new JLabel("Due:");
		lblDue.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblDue, "cell 0 0,alignx left");
		
		JLabel lblEstimatedEffort = new JLabel("Estimated Effort:");
		lblEstimatedEffort.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblEstimatedEffort, "cell 0 1");
		
		JLabel lblActualEffort = new JLabel("Actual Effort:");
		lblActualEffort.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblActualEffort, "cell 0 2");
		
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
		add(assignedToList, "cell 0 8,grow");

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
