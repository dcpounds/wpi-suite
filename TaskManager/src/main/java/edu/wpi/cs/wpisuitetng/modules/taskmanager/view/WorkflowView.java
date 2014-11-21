package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.janeway.config.Configuration;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;

import javax.swing.BoxLayout;


/**
 * This class is used to display a workflow in the GUI. Right now the stages are hard-coded in,
 *  but in the future we will be able to dynamically add and remove them through
 *
 */
public class WorkflowView extends JPanel {
	private static final long serialVersionUID = -3276090208342185552L;
	private ArrayList<StageView> stageViewList;
	private JPanel workflowPanel;
	private JScrollPane scrollBar;
	
	/**
	 * constructs a view for the main workflow based off the main workflow model
	 */
	public WorkflowView(WorkflowModel workflowModel){
		
		//Code for getting the name of the project need to work on placement of the label
		Configuration configuration = ConfigManager.getConfig();
		String projectName = configuration.getProjectName();
		workflowModel = WorkflowController.getWorkflowModel();
		
		this.workflowPanel = new JPanel();
		scrollBar = new JScrollPane(workflowPanel);
		add(scrollBar);
		
		stageViewList = new ArrayList<StageView>();
		for(StageModel stageModel : workflowModel.getStageModelList() ){
			StageView stageToAdd = new StageView(stageModel, this);
			addStageView( stageToAdd );
		}
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));	
	}
	
	/**
	 * get the list of stage views in the workflow 
	 * @return the list of StageViews in the workflow view
	 */
	public ArrayList<StageView> getStageViewList() {
		if(stageViewList.size() == 0){
			return new ArrayList<StageView>();
		}
		return this.stageViewList;
	}
	
	
	/**
	 * Adds a stage view to the work flow view based off of the given stage model
	 * @param stageModel - model for which
	 */
	public void addStageView(StageView stageView){
		workflowPanel.add(stageView);
		stageViewList.add(stageView);
		revalidate();
		repaint();
	}
	
	
	/**
	 * @param stageView - the view to add to the list
	 */
	public void removeStageView(StageView stageView){
		workflowPanel.remove(stageView);
		stageViewList.remove(stageView);
		revalidate();
		repaint();
	}
	
	
	/**
	 * @param stageName - the title of the stage to look for
	 * @return - the stage with the given title, null otherwise
	 */
	public StageView getStageViewByID(int id) {
		for( StageView stageView : stageViewList ){
			if( stageView.getID() == id)
				return stageView;
		}
		return null;
	}
	
	public void clearAllTaskViews(){
		for ( StageView stageView : stageViewList ){
			
		}
	}
	
	/**
	 * @return the JScrollPane scrollbar that the workflow holds everything in
	 */
	public JScrollPane getScrollPane(){
		return scrollBar;
	}

}
