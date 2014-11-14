package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

/**
 * This view is responsible for rendering a stage that can be placed inside a workflow.
 *
 */
public class StageView extends JPanel {
	private static final long serialVersionUID = 7765491802045400161L;
	private String title;
	private JPanel stagePane;
	private JScrollPane scrollPane;
	private static Dimension defaultSize = new Dimension(300, 620);

	/**
	 * Constructs a new Stage based off the given model
	 * @param stageModel - the model the view is based off of
	 */
	public StageView(StageModel stageModel) {
		this.stagePane = new JPanel();
		setPreferredSize(new Dimension( defaultSize.width, defaultSize.height) );
		
		JLabel lblStageTitle = new JLabel( stageModel.getTitle() );
		lblStageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblStageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblStageTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(lblStageTitle);
		stagePane.setPreferredSize( new Dimension( defaultSize.width - 20, defaultSize.height - 40) );
		scrollPane = new JScrollPane(stagePane);
		stagePane.setLayout(new BoxLayout(stagePane, BoxLayout.Y_AXIS));
		add(scrollPane);
		setBackground(new Color(135, 206, 250));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	
	/**
	 * Overrides the getPreferredSize method to make task boxes scale dynamically
	 */
	public Dimension getPreferredSize() {
		Component parent = this.getParent();
		Dimension parentSize = parent.getSize();
		final int parentWidth = parentSize.width;
		
		if( parent == null ){
			return new Dimension(300, 600);
		} else return new Dimension( (int) (parentWidth/4.1), (int)(parentSize.height * 0.95) );
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
			if(heightNeeded > defaultSize.height)
				stagePane.setPreferredSize(new Dimension(defaultSize.width - 40, heightNeeded));
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
	
	
	
	public Dimension getStageSize(){
		return defaultSize;
	}

}