package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;


/**
 *  This view is responsible for visually representing a task model
 */
public class TaskViewExpanded extends JPanel{

	private static final long serialVersionUID = -4932681028640603728L;
	private TaskModel taskModel;
	private JPanel assignedToPane;
	private TaskView taskView;
	
	/**
	 * creates a new task view based off the given model
	 * 
	 * @param stageView -stage view for the task to be added to
	 * @param taskModel -model which the view is based off of
	 */
	public TaskViewExpanded(StageView stageView, TaskModel taskModel, TaskView taskView){
		setLayout(new MigLayout("", "[grow]", "[][][][][][][][][grow]"));
		this.taskView = taskView;
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
		textArea.setMargin(new Insets(0, 0, 0, 0));
		add(textArea, "cell 0 5,grow");
		
		JSeparator separator_1 = new JSeparator();
		add(separator_1, "cell 0 6");
		
		JLabel lblAssignedTo = new JLabel("Assigned To:");
		lblAssignedTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblAssignedTo, "cell 0 7");
		

		JList assignedToList = new JList();
		add(assignedToList, "flowy, cell 0 8,grow");
	}
	
	public Dimension getPreferredSize() {
		Component parent = this.getParent();
		Dimension parentSize = taskView.getScrollPane().getViewport().getSize();
		if( parent == null ){
			return super.getPreferredSize();
		}
		return new Dimension(parentSize.width - 10, super.getPreferredSize().height );
	}
	
	
	/**
	 *  Populates the task with the list of assigned users
	 */
	public void addAssignedUsers(){
		for( User user : taskModel.getUsersAssignedTo() ){
			System.out.println("Added user " + user.getUsername());
		}
	}
}