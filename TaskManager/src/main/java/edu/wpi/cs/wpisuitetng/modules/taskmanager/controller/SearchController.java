/**
 * 
 */

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

public class SearchController implements ActionListener, KeyListener {
	private WorkflowModel workflowModel;
	private JTextField searchBox;
	private ToolbarView toolbarView;
	
	public SearchController(ToolbarView toolbarView) {
		this.toolbarView = toolbarView;
		this.workflowModel = WorkflowController.getWorkflowModel();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		this.searchBox = toolbarView.getSearchBox();
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		String searchText = searchBox.getText();
		System.out.println("string " + searchText);
			for (StageModel stage : workflowModel.getStageModelList().values()) {
				for(TaskModel task : stage.getTaskModelList().values()){
					int stageID = stage.getID();
					int taskID = task.getID();
					TaskView taskView = workflowView.getStageViewList().get(stageID).getTaskViewList().get(taskID);
					if (task.getTitle().contains(searchText) && searchText.length() > 0) {
						//highlight dat shit
						Border bueHighlight = BorderFactory.createLineBorder(Color.red, 3);
						taskView.setBorder(bueHighlight);
					} else {
						Border blackHighlight = BorderFactory.createLineBorder(Color.black);
						taskView.setBorder(blackHighlight);
					}
				}
			}
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
