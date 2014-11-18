package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.ExpandTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.NewTaskTab;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;


import java.awt.Color;

/**
 * @author Alec
 * An accordian style expandable task view that can be inserted into the stages
 */
public class TaskContainerView extends JPanel{
	TaskModel taskModel;
	JPanel taskContents;
	JPanel titlePanel;
	TaskView taskView;
	JScrollPane taskContentPane;
	StageView stageView;

	private static Dimension openSize = new Dimension(250, 240);
	private static Dimension closedSize = new Dimension(250, 40);
	
	public TaskContainerView(TaskManagerTabView taskManagerTabView, WorkflowModel workflowModel, TaskModel taskModel, StageView stageView) {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setMaximumSize(closedSize);
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.stageView = stageView;
		this.taskModel = taskModel;
		this.taskView = new TaskView(stageView, taskModel);
		
		setLayout(new MigLayout("", "[grow][]", "[][grow][]"));
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.LIGHT_GRAY);
		titlePanel.addMouseListener(  new ExpandTaskController(this, taskModel) );
		add(titlePanel, "cell 0 0 3 1,alignx center,aligny top");
		
		JLabel lblNewTask = new JLabel("New Task");
		lblNewTask.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titlePanel.add(lblNewTask);
		
		this.taskContentPane = new JScrollPane();
		
		this.taskContents = new JPanel();
		
		JButton closeButton = new JButton("\u2716");
		closeButton.setFont(closeButton.getFont().deriveFont((float) 8));
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		
		
		closeButton.addActionListener(new RemoveTaskController(stageView, this));
		closeButton.setHorizontalAlignment(SwingConstants.TRAILING);
		add(closeButton, "cell 1 0");
		
		JButton btnEdit = new JButton("Edit");

		btnEdit.addActionListener(new AddTabController(taskManagerTabView, TabType.TASK, workflowModel, taskModel));
		add(btnEdit, "cell 0 2");
		
		
		taskContentPane.setViewportView(taskContents);
	}
	
	public void showDetails(){
		taskContents.add( taskView );
		add(taskContentPane, "cell 0 1 3 1,grow");
		setMaximumSize(openSize);
		revalidate();
		repaint();
	}
	
	public void hideDetails(){
		taskContents.remove( taskView );
		this.remove(taskContentPane);
		setMaximumSize( closedSize );
		revalidate();
		repaint();
	}
	
	public StageView getStageView(){
		return stageView;
	}
	
	public int getVerticalSpaceNeeded() {
		boolean isExpanded = taskModel.getIsExpanded();
		if( isExpanded )
			return openSize.height;
		else return closedSize.height;
	}
	
	

}
