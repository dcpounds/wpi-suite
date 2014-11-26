package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

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
	private TaskViewExpanded taskView;
	private JLabel lblNewTask;
	private JScrollPane taskContentPane;
	private JButton btnEdit;
	private StageView stageView;
	private int id;
	private static final int openSize = 250;
	private static final int closeSize = 40;
	
	public TaskView(TaskModel taskModel, StageView stageView) {
		setLayout(new MigLayout("", "[grow][][]", "[][][grow]"));
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.stageView = stageView;
		this.taskModel = taskModel;
		this.id = taskModel.getID();
		this.taskView = new TaskViewExpanded(stageView, taskModel, this);
		
		
		//Sets the parameters of the panel that holds the title
		titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBackground(Color.LIGHT_GRAY);
		titlePanel.addMouseListener(  new ExpandTaskController(this, taskModel) );
		add(titlePanel, "cell 0 0 4 1,alignx center,aligny top");
		titlePanel.setLayout(new MigLayout("", "[grow][]", "[]"));
		
		//Sets the title of the task
		lblNewTask = new JLabel( taskModel.getTitle() );
		lblNewTask.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titlePanel.add(lblNewTask, "cell 0 0,alignx left,aligny top");
		
		//The scrollPane that the task contents are surrounded by
		this.taskContentPane = new JScrollPane();
		this.taskContents = new JPanel();
		taskContentPane.setViewportView(taskContents);
		
		if( taskModel.getIsExpanded() ){
			showDetails();
		}
			
		
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
		add(closeButton, "cell 1 0");
		
		//Set up the edit button
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!taskModel.getEditState())
					TabController.getInstance().addTab(TabType.TASK, taskModel);
				else{
				}
			}
		});
		add(btnEdit, "cell 0 2");
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
		taskContents.add( taskView );
		add(taskContentPane, "cell 0 1 3 1,grow");
		taskModel.setIsExpanded(true);
		stageView.updatePreferredDimensions();
		revalidate();
	}
	
	
	/**
	 * Collapse the task container and hide details about the task
	 */
	public void hideDetails(){
		taskContents.remove( taskView );
		this.remove(taskContentPane);
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
	
	public void updateContents(TaskModel newTaskModel){
		taskView.updateTaskContents(newTaskModel);
		this.lblNewTask.setText(newTaskModel.getTitle());
		this.taskModel = newTaskModel;
		titlePanel.addMouseListener(  new ExpandTaskController(this, newTaskModel) );
		revalidate();
	}
}
