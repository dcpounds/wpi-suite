package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;


/**
 * This class is used to display a workflow in the GUI. Right now the stages are hard-coded in,
 *  but in the future we will be able to dynamically add and remove them through
 *
 */
public class WorkflowView extends JPanel {
	
	private ArrayList<StageModel> stageModelList;
	private ArrayList<StageView> stageViewList = new ArrayList<StageView>();
	private JPanel workflowPanel;
	
	/**
	 * constructs a view for the main workflow based off the given workflow model
	 *
	 * @param workflowModel -model the view is based off of
	 */
	public WorkflowView(WorkflowModel workflowModel){
		this.stageModelList = workflowModel.getStageList();
		
		this.workflowPanel = new JPanel();
		JScrollPane scrollBar = new JScrollPane(workflowPanel);
		
		this.setLayout( new BorderLayout() );
		
		for(StageModel stageModel : stageModelList){
			StageView stageView = new StageView(stageModel);
			stageViewList.add(stageView);
			workflowPanel.add(stageView);
		}
		
		this.add(scrollBar, BorderLayout.CENTER);
	
	}
	
	/**
	 * get the list of stage views in the workflow 
	 *
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
	 * 
	 * @param stageModel - model for which
	 */
	public void addStageToWorkflow(StageModel stageModel){
		
		StageView stageView = new StageView(stageModel);
		workflowPanel.add(stageView);
		revalidate();
		repaint();
	}
	
	
	/**
	 * 
	 * sets the current stage views for the given workflow view
	 * 
	 * @param stageViewList - a list of stageViews to put in the list of stage views
	 */
	public void setStageViewList( ArrayList<StageView> stageViewList ) {
		this.stageViewList = stageViewList;
	}

}
