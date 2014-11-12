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
	private JTextArea txtrDescription;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private JLabel lblTaskTitle;
	private JTextField textField;
	private JPanel assignedToPane;
	private JScrollPane assignedToScrollPane;
	
	public TaskView(CardView cardView, TaskModel taskModel ){
		this.taskModel = taskModel;
		setLayout(new MigLayout("", "[grow][grow][]", "[][][grow][][][grow][]"));
		setBorder(new LineBorder(Color.BLUE, 1, true));
		setPreferredSize(new Dimension(240, 250));
		
		lblTaskTitle = new JLabel( taskModel.getTitle() );
		lblTaskTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTaskTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTaskTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTaskTitle, "cell 1 0,alignx center");
		
		JButton btnClose = new JButton("X");
		btnClose.addActionListener(new RemoveTaskController(cardView, this));
		add(btnClose, "cell 2 0");
		
		JSeparator separator = new JSeparator();
		add(separator, "cell 0 1 3 1,growx");
		
		JScrollPane descriptionScrollPane = new JScrollPane();
		add(descriptionScrollPane, "cell 0 2 3 2,grow");
		
		JTextArea descriptionPane = new JTextArea( taskModel.getDescription() );
		descriptionPane.setLineWrap(true);
		descriptionPane.setEditable(false);
		descriptionScrollPane.setViewportView(descriptionPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		add(scrollPane_1, "cell 0 5 3 1,grow");
		this.assignedToPane = new JPanel();
		scrollPane_1.setViewportView(assignedToPane);
		
		JLabel lblAssignedTo = new JLabel("Assigned To:");
		lblAssignedTo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblAssignedTo, "cell 1 4,alignx center");
		addAssignedUserViews();
		
		JButton btnEdit = new JButton("Edit");
		add(btnEdit, "cell 2 6");
	}
	
	public void addAssignedUserViews(){
		for( UserModel userModel : taskModel.getUsersAssignedTo() ){
			UserIconView iconView = new UserIconView(userModel);
			this.assignedToPane.add(iconView);
		}
	}
}
