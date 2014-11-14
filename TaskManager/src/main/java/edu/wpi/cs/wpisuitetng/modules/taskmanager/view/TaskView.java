package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.db4o.User;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import java.awt.Font;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;



/**
 * @author alec
 *This view is responsible for visually representing a task
 */
public class TaskView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4932681028640603728L;
	private TaskModel taskModel;
	private JPanel assignedToPane;
	
	public TaskView(StageView stageView, TaskModel taskModel ){
		setLayout(new MigLayout("", "[grow][grow][]", "[][][][][][grow][][][grow]"));
		
		JLabel lblDue = new JLabel("Due:");
		add(lblDue, "cell 0 0,alignx left");
		
		JLabel lblEstimatedEffort = new JLabel("Estimated Effort:");
		add(lblEstimatedEffort, "cell 0 1");
		
		JLabel lblActualEffort = new JLabel("Actual Effort:");
		add(lblActualEffort, "cell 0 2");
		
		JSeparator separator = new JSeparator();
		add(separator, "cell 0 3");
		
		JLabel lblDescription = new JLabel("Description");
		add(lblDescription, "cell 0 4,alignx left");
		
		JTextPane textPane = new JTextPane();
		add(textPane, "cell 0 5 3 1,grow");
		
		JSeparator separator_1 = new JSeparator();
		add(separator_1, "cell 0 6");
		
		JLabel lblAssignedTo = new JLabel("Assigned To:");
		add(lblAssignedTo, "cell 0 7");
		
		JList list = new JList();
		add(list, "cell 0 8,grow");

	}
	
	public void addAssignedUserViews(){
		for( UserModel userModel : taskModel.getUsersAssignedTo() ){
			UserIconView iconView = new UserIconView(userModel);
			this.assignedToPane.add(iconView);
		}
	}
}
