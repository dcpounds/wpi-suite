package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.ExpandTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;

import java.awt.Color;

/**
 * @author Alec
 * An accordian style expandable task view that can be inserted into the stages
 */
public class TaskContainerView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6517799529927334536L;
	private TaskModel taskModel;
	private JPanel taskContents;
	private JPanel titlePanel;
	private TaskView taskView;
	private JScrollPane taskContentPane;
	private StageView stageView;
	private static final int openSize = 250;
	private static final int closeSize = 40;
	
	public TaskContainerView(TaskModel taskModel, StageView stageView) {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.stageView = stageView;
		this.taskModel = taskModel;
		this.taskView = new TaskView(stageView, taskModel);
		
		setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.LIGHT_GRAY);
		titlePanel.addMouseListener(  new ExpandTaskController(this, taskModel) );
		add(titlePanel, "cell 0 0 3 1,alignx center,aligny top");
		
		JLabel lblNewTask = new JLabel("New Task");
		lblNewTask.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titlePanel.add(lblNewTask);
		
		this.taskContentPane = new JScrollPane();
		
		this.taskContents = new JPanel();
		taskContentPane.setViewportView(taskContents);
	}
	
	/**
	 * This method will override the default getPreferredSize and resize the task based on the size of its parent
	 * THIS METHOD IS ALSO RESPONSIBLE FOR RESIZING THE TASK WHEN YOU CLICK ON IT
	 */
	public Dimension getPreferredSize() {
		boolean isExpanded = taskModel.getIsExpanded();
		Component parent = this.getParent();
		Dimension parentSize = parent.getSize();
		
		if( parent == null ){
			return new Dimension(300, 600);
		} else{
			
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
		repaint();
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
		repaint();
	}
	
	public StageView getStageView(){
		return stageView;
	}
}
