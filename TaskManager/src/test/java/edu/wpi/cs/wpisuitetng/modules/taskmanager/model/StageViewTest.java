
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

public class StageViewTest {
	
	@Test
	public void addStageViewTest() {
		StageModel stageModel = new StageModel();
		LinkedHashMap<Integer, StageModel> stageList = new LinkedHashMap<Integer, StageModel>();
		LinkedHashMap<Integer, StageView> compareHashmap = new LinkedHashMap<Integer, StageView>();
		WorkflowModel workflowModel = new WorkflowModel("string",stageList);
		WorkflowView workflowView = new WorkflowView(workflowModel);
		StageView stageView= new StageView(stageModel,workflowView);
		workflowView.addStageView(0,stageView);
		compareHashmap.put(0,stageView);
		assertEquals(compareHashmap, workflowView.getStageViewList());
	}
	@Test
	public void removeStageViewTest(){
		StageModel stageModel = new StageModel();
		LinkedHashMap<Integer, StageModel> stageList = new LinkedHashMap<Integer, StageModel>();
		LinkedHashMap<Integer, StageView> compareHashmap = new LinkedHashMap<Integer, StageView>();
		WorkflowModel workflowModel = new WorkflowModel("string",stageList);
		WorkflowView workflowView = new WorkflowView(workflowModel);
		StageView stageView1= new StageView(stageModel,workflowView);
		stageView1.setTitle("stage1");
		workflowView.addStageView(0,stageView1);
		workflowView.removeStageView(stageView1);
		assertEquals(compareHashmap, workflowView.getStageViewList());
		
	}
	@Test
	public void getViewByIDTest(){
		StageModel stageModel = new StageModel();
		LinkedHashMap<Integer, StageModel> stageList = new LinkedHashMap<Integer, StageModel>();
		WorkflowModel workflowModel = new WorkflowModel("string",stageList);
		WorkflowView workflowView = new WorkflowView(workflowModel);
		StageView stageView= new StageView(stageModel,workflowView);
		workflowView.addStageView(0,stageView);
		assertEquals(stageView, workflowView.getStageViewByID(0));
		
		
		
		
	}
	
}
