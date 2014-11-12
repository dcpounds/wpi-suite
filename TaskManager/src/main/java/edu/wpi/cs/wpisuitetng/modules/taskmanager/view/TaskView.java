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

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Component;



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
	private JLabel lblTaskTitle;
	private JTextArea txtrDescription;
	private JLabel lblStatus;
	private JLabel lblDescription;
	private JScrollPane scrollPane;
	private JSeparator separator;
	private JSeparator separator_1;
	
	public TaskView( TaskModel taskModel ){
		this.setOpaque(true);
		this.setBackground(Color.LIGHT_GRAY);
		this.taskModel = taskModel;
		this.setPreferredSize(new Dimension(240, 200) );
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		lblTaskTitle = new JLabel(taskModel.getTitle());
		lblTaskTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTaskTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblTaskTitle);
		
		separator_1 = new JSeparator();
		add(separator_1);
		
		separator = new JSeparator();
		add(separator);
		
		lblDescription = new JLabel("Description");
		lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblDescription);
		
		scrollPane = new JScrollPane();
		add(scrollPane);
		
		txtrDescription = new JTextArea();
		txtrDescription.setTabSize(6);
		txtrDescription.setEditable(false);
		txtrDescription.setRows(1);
		txtrDescription.setColumns(4);
		txtrDescription.setLineWrap(true);
		txtrDescription.setText(taskModel.getDescription());
		add(txtrDescription);
		
		lblStatus = new JLabel("Status: " + taskModel.getStatus() );
		lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblStatus);
	}
}
