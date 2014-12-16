/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
 * This class is used to display a workflow in the GUI.
 */
public class WorkflowView extends JPanel implements MouseMotionListener{
	private static final long serialVersionUID = -3276090208342185552L;
	private LinkedHashMap<Integer,StageView> stageViewList;
	private JPanel workflowPanel;
	private JScrollPane scrollBar;
	PointerInfo currentPosition;
	Point b;
	int xLocation;
	int yLocation;
	
	/**
	 * constructs a view for the main workflow based off the main workflow model
	 */
	public WorkflowView(WorkflowModel workflowModel){
		
		//Code for getting the name of the project need to work on placement of the label
		Configuration configuration = ConfigManager.getConfig();
		String projectName = configuration.getProjectName();
		workflowModel = WorkflowController.getWorkflowModel();
		
		this.workflowPanel = new JPanel();
		this.workflowPanel.addMouseMotionListener(this);
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
		try{
			workflowPanel.add(stageView, index);
			stageViewList.put(stageView.getID(),stageView);
			revalidate();
			repaint();
		}catch(Exception e){
			if(stageView == null)
				return;
						
			workflowPanel.add(stageView);
			stageViewList.put(stageView.getID(),stageView);
			revalidate();
			repaint();
		}
	}
	
	
	/**
	 * @param stageView - the view to add to the list
	 */
	public void removeStageView(StageView stageView){
		
		if( stageViewList.remove(stageView.getID()) == null){
			System.out.println("Failed to remove stageView " + stageView.getTitle() + " from stageViewList");
		} else{
			System.out.println("Successfully removed stageView " + stageView.getTitle() + " from stageViewList");
		}
		
		workflowPanel.remove(stageView);
		
		revalidate();
		repaint();
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

	@Override
	public void mouseDragged(MouseEvent me) {
		dynamicScroll();
		
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		dynamicScroll();
	}
	
	public void dynamicScroll(){
		int leftEdgeLocation = 130;
		int rightEdgeLocation = this.getWidth() - 10;
		b = super.getMousePosition();
		xLocation = (int) b.getX();
		yLocation = (int) b.getY();
		System.out.println("location:" + xLocation);
		JScrollBar horizontalScrollBar = this.getScrollPane().getHorizontalScrollBar();
		int currentBarLocation = this.getScrollPane().getHorizontalScrollBar().getValue();
		int barMinimum = this.getScrollPane().getHorizontalScrollBar().getMinimum();
		int barMaximum = this.getScrollPane().getHorizontalScrollBar().getMaximum();
		while(xLocation <= leftEdgeLocation && currentBarLocation != barMinimum){
			b = super.getMousePosition();
			xLocation = (int) b.getX();
			currentBarLocation = currentBarLocation-1;
			horizontalScrollBar.setValue(currentBarLocation);
		}
		while(xLocation >= rightEdgeLocation && currentBarLocation != barMaximum){
			currentPosition = MouseInfo.getPointerInfo();
			b = currentPosition.getLocation();
			xLocation = (int) b.getX();
			currentBarLocation = currentBarLocation+1;
			horizontalScrollBar.setValue(currentBarLocation);
		}
	}


}
