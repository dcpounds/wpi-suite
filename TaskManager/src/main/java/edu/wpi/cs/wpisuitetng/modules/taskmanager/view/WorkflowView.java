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
	
	public WorkflowView(WorkflowModel workflowModel) {
		this.setLayout( new BorderLayout() );
		this.workflowPanel = new JPanel();
		JScrollPane scrollBar = new JScrollPane(workflowPanel);
		this.stageModelList = workflowModel.getStageList();
		
		for(StageModel stageModel : stageModelList){
			StageView stageView = new StageView(stageModel);
			stageViewList.add(stageView);
			workflowPanel.add(stageView);
		}
		
		this.add(scrollBar, BorderLayout.CENTER);
		
	}
	
	
	/**
	 * @return the list of StageViews in the workflow view
	 */
	public ArrayList<StageView> getStageViewList() {
		
		if(stageViewList.size() == 0){
			return new ArrayList<StageView>();
		}
		
		return this.stageViewList;
	}
	
	/**
	 * 
	 */
	public void addStageToWorkflow(StageModel stageModel){
		
		StageView stageView = new StageView(stageModel);
		workflowPanel.add(stageView);
		revalidate();
		repaint();
	}
	
	
	/**
	 * @param stageViewList - a list of stageViews to put in the list of stage views
	 */
	public void setStageViewList( ArrayList<StageView> stageViewList ) {
		this.stageViewList = stageViewList;
	}

}
