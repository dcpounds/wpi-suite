/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team What? We Thought This Was Bio!
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task.ArchiveController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

/**
 * @author Frick
 * @author Jay
 * @author Andrew
 * @author Alec
 * Manages the search bar's searching functionality.
 */
public class SearchController implements ActionListener, KeyListener {
	private static WorkflowModel workflowModel;
	private static JTextField searchBox;
	private static ToolbarView toolbarView;
	private static JCheckBox filterBox;
	
	public SearchController(ToolbarView toolbarView) {
		SearchController.toolbarView = toolbarView;
		SearchController.workflowModel = WorkflowController.getWorkflowModel();
		filterBox = toolbarView.getCatFilter();
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Intentionally left empty
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Intentionally left empty
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		search();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Intentionally left empty	
	}
	
	/**
	 * search()
	 * Searches tasks for string in search box, highlighting matches
	 */
	public static void search() {
		boolean foundResult = false;
		searchBox = toolbarView.getSearchBox();
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		String searchText = searchBox.getText();
		boolean caseSensitive = toolbarView.getCaseSensitive();
		
		if (!caseSensitive) {
			searchText = searchText.toLowerCase();
		}

		for (StageModel stage : workflowModel.getStageModelList().values()) {
			for(TaskModel task : stage.getTaskModelList().values()){
				boolean shouldShow = (ArchiveController.getIsPressed() || !task.getIsArchived() ? true: false);
				int stageID = stage.getID();
				int taskID = task.getID();
				TaskView taskView = workflowView.getStageViewList().get(stageID).getTaskViewList().get(taskID);
				if(taskView == null)
					continue;
				if (searchText.isEmpty() && shouldShow) {
					taskView.setVisible(true);
				}
				else if (((!caseSensitive && task.getTitle().toLowerCase().contains(searchText)) || (caseSensitive && task.getTitle().contains(searchText))) && shouldShow) {
					taskView.setVisible(true);
					foundResult = true;
				} else {
					taskView.setVisible(false);
				}
			}
		}
		
		if(!foundResult && !searchText.isEmpty())
			searchBox.setBackground(new Color(255,120,120));
		else
			searchBox.setBackground(Color.WHITE);
	}
	
	public static void catFilter() {
		
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		boolean filter = filterBox.isSelected();
		ArrayList<Color> selectedCategories = toolbarView.getSelectedColorArray();
		if(filter){
			for (StageModel stage : workflowModel.getStageModelList().values()) {
				for(TaskModel task : stage.getTaskModelList().values()){
					boolean shouldShow = (ArchiveController.getIsPressed() || !task.getIsArchived() ? true: false);
					int stageID = stage.getID();
					int taskID = task.getID();
					TaskView taskView = workflowView.getStageViewList().get(stageID).getTaskViewList().get(taskID);
					if(taskView == null)
						continue;
					if (!filter && shouldShow) {
						taskView.setVisible(true);
					}
					else if (selectedCategories.contains(task.getCatColor()) && shouldShow) {
						taskView.setVisible(true);
					} else {
						taskView.setVisible(false);
					}
				}
			}
		}
		
	}

}
