package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * This view is responsible for rendering a stage that can be placed inside a workflow.
 *
 */
public class StageView extends JPanel {
	private static final long serialVersionUID = 7765491802045400161L;
	private String title;
	private JPanel stagePane;
	private JScrollPane scrollPane;
	private WorkflowView workflowView;


	/**
	 * Constructs a new Stage based off the given model
	 * @param stageModel - the model the view is based off of
	 */
	public StageView(StageModel stageModel, WorkflowView workflowView) {
		this.stagePane = new JPanel();
		this.workflowView = workflowView;
		
		JLabel lblStageTitle = new JLabel( stageModel.getTitle() );
		lblStageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblStageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblStageTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(lblStageTitle);
		scrollPane = new JScrollPane(stagePane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		stagePane.setLayout(new BoxLayout(stagePane, BoxLayout.Y_AXIS));
		add(scrollPane);
		setBackground(new Color(135, 206, 250));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		stagePane.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		updatePreferredDimensions();
	}
	
	
	/**
	 * Overrides the getPreferredSize method to make task boxes scale dynamically
	 */
	public Dimension getPreferredSize() {
		Component parent = this.getParent();
		Dimension parentSize = parent.getSize();
		final int parentWidth = workflowView.getScrollPane().getViewport().getSize().width;
		
		if( parent == null ){
			return new Dimension(300, 600);
		} else {
			int stagePreferredHeight = stagePane.getPreferredSize().height;
			int stageCount = workflowView.getStageViewList().size();
			int stageWidth = (int) parentWidth/( stageCount < 5 ? stageCount : 5);
			stagePane.setPreferredSize(new Dimension(this.getWidth(), stagePreferredHeight));
			return new Dimension( stageWidth, (int)(parentSize.height * 0.95) );
		}
	}
	
	
	/**
	 * Updates the preferred dimensions of the panel that houses the task views
	 */
	public void updatePreferredDimensions() {
		int currentStageHeight = this.getHeight();
		int heightNeeded = 0;
		
		//Go through each component in the stageView
		for( Component childComponent : stagePane.getComponents() ){
			if( childComponent instanceof TaskContainerView ){
				heightNeeded += ((TaskContainerView) childComponent).getHeight();
			}
			
			stagePane.setPreferredSize(new Dimension(this.getWidth(), heightNeeded));
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
		updatePreferredDimensions();
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
		updatePreferredDimensions();
		revalidate();
		repaint();	
	}

}