package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JScrollPane;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.ExpandTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabType;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Alec
 * An accordian style expandable task view that can be inserted into the stages
 */
public class TaskView extends JPanel{
	private static final long serialVersionUID = 6517799529927334536L;
	private TaskModel taskModel;
	private JPanel taskContents;
	private JPanel titlePanel;
	private JLabel lblNewTask;
	private JScrollPane taskContentPane;
	private JButton btnEdit;
	private JTextArea dateArea;
	private JLabel lblEstimatedEffort;
	private JLabel lblActualEffort;
	private JTextArea descriptionField;
	private DefaultListModel<String> assignedListModel;
	private StageView stageView;
	private int id;
	private static final int openSize = 250;
	private static final int closeSize = 40;
	
	public TaskView(TaskModel taskModel, StageView stageView) {
		setLayout(new MigLayout("", "[grow][][]", "[][][grow][]"));
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.stageView = stageView;
		this.taskModel = taskModel;
		this.id = taskModel.getID();
		
		
		//Sets the parameters of the panel that holds the title
		titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBackground(Color.LIGHT_GRAY);
		titlePanel.addMouseListener(  new ExpandTaskController(this, taskModel) );
		add(titlePanel, "cell 0 0 4 1,alignx center,aligny top");
		titlePanel.setLayout(new MigLayout("", "[grow][]", "[]"));
		
		//Sets the title of the task
		lblNewTask = new JLabel();
		lblNewTask.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titlePanel.add(lblNewTask, "cell 0 0,alignx left,aligny top");
		
		//The beginning of the taskContents section
		//The scrollPane that the task contents are surrounded by
		this.taskContentPane = new JScrollPane();
		this.taskContents = new JPanel();
		taskContents.setBackground(Color.WHITE);
		taskContents.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][grow]"));
		taskContentPane.setViewportView(taskContents);
		
		JLabel lblDue = new JLabel("Due:");
		lblDue.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblDue, "cell 0 0,alignx left");
		
		dateArea = new JTextArea( taskModel.getDueDate() );
		dateArea.setLineWrap(true);
		dateArea.setEditable(false);
		taskContents.add(dateArea, "cell 0 0, alignx right");
		
		this.lblEstimatedEffort = new JLabel();
		lblEstimatedEffort.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblEstimatedEffort, "cell 0 1,alignx left");
		
		lblActualEffort = new JLabel();
		lblActualEffort.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblActualEffort, "cell 0 2,alignx left");
		
		JSeparator separator = new JSeparator();
		taskContents.add(separator, "cell 0 3");
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblDescription, "cell 0 4,alignx left");
		
		this.descriptionField = new JTextArea();
		descriptionField.setLineWrap(true);
		descriptionField.setEditable(false);
		descriptionField.setMargin(new Insets(0, 0, 0, 0));
		taskContents.add(descriptionField, "cell 0 5,grow");
		
		JSeparator separator_1 = new JSeparator();
		taskContents.add(separator_1, "cell 0 6");
		
		JLabel lblAssignedTo = new JLabel("Assigned To:");
		lblAssignedTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		taskContents.add(lblAssignedTo, "cell 0 7");
		
		this.assignedListModel = new DefaultListModel<String>();
		JList<String> assignedListComponent = new JList<String>( assignedListModel );
		taskContents.add(assignedListComponent, "flowy, cell 0 8,grow");
		
		
		//Set up the close button to remove the task
		JButton closeButton = new JButton("\u2716");
		closeButton.setFont(closeButton.getFont().deriveFont((float) 8));
		TaskView tv = this;
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				StageController.deleteTask(tv, stageView);
			}
		});
		
		closeButton.setHorizontalAlignment(SwingConstants.TRAILING);
		add(closeButton, "cell 2 0,alignx trailing");
		this.setContents(taskModel);
		this.add(taskContentPane, "cell 0 1 3 2,grow");
		
		//Set up the edit button
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!taskModel.getEditState())
					TabController.getInstance().addTab(TabType.TASK, taskModel);
				else
					System.out.println("REfused to edit task");
			}
		});
		add(btnEdit, "cell 2 3");
	}
	
	/**
	 * This method will override the default getPreferredSize and resize the task based on the size of its parent
	 * THIS METHOD IS ALSO RESPONSIBLE FOR RESIZING THE TASK WHEN YOU CLICK ON IT
	 */
	public Dimension getPreferredSize() {
		boolean isExpanded = taskModel.getIsExpanded();
		Component parent = this.getParent();
		Dimension parentSize = parent.getSize();
		
		if( parent == null )
			return new Dimension(600,300);
		else{
			
			//If the task is expanded, set the preferred size to the parent width and the openSize height
			if(isExpanded){
				this.setMaximumSize(new Dimension(parentSize.width,openSize));
				return new Dimension(parentSize.width,openSize);
				
			//If the task is collapsed, set the preferred size to the parent width and the closedSize height
			} else{
				this.setMaximumSize(new Dimension(parentSize.width,closeSize));
				return new Dimension(parentSize.width,closeSize);
			}
		}
	}
	
	public int getHeight() {
		if( taskModel.getIsExpanded() )
			return openSize;
		else
			return closeSize;
	}
	
	/**
	 * Expand the task container to show details about the task
	 */
	public void showDetails(){
		taskContents.setVisible(true);
		taskModel.setIsExpanded(true);
		stageView.updatePreferredDimensions();
		revalidate();
	}
	
	
	/**
	 * Collapse the task container and hide details about the task
	 */
	public void hideDetails(){
		taskContents.setVisible(false);
		taskModel.setIsExpanded(false);
		stageView.updatePreferredDimensions();
		revalidate();
	}
	
	public StageView getStageView(){
		return stageView;
	}
	
	public JScrollPane getScrollPane(){
		return taskContentPane;
	}
	
	public int getID(){
		return id;
	}
	
	/**
	 * Updates the contents of the taskView when given a new task model
	 * @param newTaskModel
	 */
	public void setContents(TaskModel task){
		this.taskModel.setEditState(task.getEditState());
		
		this.taskModel.setTitle(task.getTitle());
		this.lblNewTask.setText(task.getTitle());
		
		this.taskModel.setDescription(task.getDescription());
		this.descriptionField.setText(task.getDescription());
		
		this.taskModel.setEstimatedEffort(task.getEstimatedEffort());
		this.lblEstimatedEffort.setText("Estimated Effort: " + task.getEstimatedEffort());
		this.taskModel.setActualEffort(task.getActualEffort());
		this.lblActualEffort.setText("Actual Effort: " + task.getActualEffort());
		
		revalidate();
		repaint();
	}
}
