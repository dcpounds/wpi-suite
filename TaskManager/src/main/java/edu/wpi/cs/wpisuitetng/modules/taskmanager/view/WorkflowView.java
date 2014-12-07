package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.janeway.config.Configuration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
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
	private LinkedHashMap<Integer,StageView> stageViewList;
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
		
		stageViewList = new LinkedHashMap<Integer,StageView>();
		for(StageModel stageModel : workflowModel.getStageModelList().values() ){
			StageView stageToAdd = new StageView(stageModel, this);
			addStageView( stageModel.getIndex(), stageToAdd );
		}
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));	
	}
	
	/**
	 * get the list of stage views in the workflow 
	 * @return the list of StageViews in the workflow view
	 */
	public LinkedHashMap<Integer,StageView> getStageViewList() {
		if(stageViewList.size() == 0){
			return new LinkedHashMap<Integer,StageView>();
		}
		return this.stageViewList;
	}
	
	
	/**
	 * Adds a stage view to the work flow view based off of the given stage model
	 * @param stageModel - model for which
	 */
	public void addStageView(int index, StageView stageView){
		System.out.println("Adding stage " + stageView.getTitle() + " to index " + index + " stage view size is " + stageViewList.size());
		
		if(index > (stageViewList.size())){
			workflowPanel.add(stageView);
			stageViewList.put(stageView.getID(),stageView);
			revalidate();
			repaint();
			return;
		}

		
		workflowPanel.add(stageView, index);
		stageViewList.put(stageView.getID(),stageView);
		revalidate();
		repaint();
	}
	
	
	/**
	 * @param stageView - the view to add to the list
	 */
	public void removeStageView(StageView stageView){
		workflowPanel.remove(stageView);
		stageViewList.remove(stageView.getID());
		revalidate();
		repaint();
	}
	
	/**
	 * Gets the stageView at the given index
	 * @param index
	 * @return
	 */
	public StageView getStageAtIndex(int index){
		if(index > stageViewList.size())
			return null;
		try{
			return (StageView) workflowPanel.getComponent(index);
		}catch(Exception e){
			System.out.println("Component at this index is not a stageView");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @param stageName - the title of the stage to look for
	 * @return - the stage with the given title, null otherwise
	 */
	public StageView getStageViewByID(int id) {
		return stageViewList.get(id);
	}
	
	/**
	 * @return the JScrollPane scrollbar that the workflow holds everything in
	 */
	public JScrollPane getScrollPane(){
		return scrollBar;
	}

}
