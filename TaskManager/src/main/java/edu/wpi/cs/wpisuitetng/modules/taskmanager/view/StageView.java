package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;

/**
 * This view is responsible for rendering a stage that can be placed inside a workflow.
 *
 */
public class StageView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7765491802045400161L;
	private String title;
	private JPanel stagePane;
	private JScrollPane scrollPane;
	private static int defaultHeight = 250;
	private static int defaultWidth = 600;

	/**
	 * Constructs a new Stage based off the given model
	 * @param stageModel - the model the view is based off of
	 */
	public StageView(StageModel stageModel) {
		this.stagePane = new JPanel();
		setPreferredSize(new Dimension( defaultHeight, defaultWidth) );
		stagePane.setPreferredSize( new Dimension( defaultHeight - 20, defaultWidth - 20) );
		scrollPane = new JScrollPane(stagePane);
		stagePane.setLayout(new BoxLayout(stagePane, BoxLayout.Y_AXIS));
		add(scrollPane);
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	/**
	 * Updates the preferred size of the stagePanel so that  scrollbars will appear if necessary
	 */
	public void updatePreferredDimensions() {
		revalidate();
		int heightNeeded = 0;
		
		//Go through each component in the stageView
		for( Component childComponent : stagePane.getComponents() ){
			if( childComponent instanceof TaskContainerView ){
				heightNeeded += ((TaskContainerView) childComponent).getVerticalSpaceNeeded();
			}
			
			//Set the new preferred height if the box is too small
			if(heightNeeded > defaultHeight)
				stagePane.setPreferredSize(new Dimension(defaultHeight - 20, heightNeeded));
		}
	}

	/**
	 * gets the title of the stage
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the title of the stage
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * adds a task to the stage
	 * 
	 * @param taskView
	 */
	public void addTaskView(TaskContainerView taskView) {
		stagePane.add(taskView);
		revalidate();
		repaint();
	}
	
	/**
	 * removes a task from the stage
	 * 
	 * @param taskView
	 */
	public void removeTaskView(TaskContainerView taskView) {
		stagePane.remove(taskView);
		revalidate();
		repaint();	
	}

}