package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
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
	TaskModel taskModel;
	JPanel taskContents;
	JPanel titlePanel;
	TaskView taskView;
	JScrollPane taskContentPane;
	StageView stageView;
	private static Dimension openSize = new Dimension(250, 240);
	private static Dimension closedSize = new Dimension(250, 40);
	
	public TaskContainerView(TaskModel taskModel, StageView stageView) {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setMaximumSize(closedSize);
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
	 * Expand the task container to show details about the task
	 */
	public void showDetails(){
		taskContents.add( taskView );
		add(taskContentPane, "cell 0 1 3 1,grow");
		setMaximumSize(openSize);
		taskModel.setIsExpanded(true);
		revalidate();
		repaint();
	}
	
	
	/**
	 * Collapse the task container and hide details about the task
	 */
	public void hideDetails(){
		taskContents.remove( taskView );
		this.remove(taskContentPane);
		setMaximumSize( closedSize );
		taskModel.setIsExpanded(false);
		revalidate();
		repaint();
	}
	
	public StageView getStageView(){
		return stageView;
	}
	
	
	/**
	 * @return the amount of space that the entire container takes up. 
	 * This is used so that the preferred size of the stage can be set (so scrollbars will appear when necessary)
	 */
	public int getVerticalSpaceNeeded() {
		boolean isExpanded = taskModel.getIsExpanded();
		if( isExpanded )
			return openSize.height;
		else return closedSize.height;
	}
	
	

}
