/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.TransferHandler;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;

/**
 * @author Alec
 * This class is responsible for handling drag and drop for tasks.
 * It allows tasks to be dragged into different stages
 */
public class DragTaskController extends MouseAdapter{
	private static boolean isDraggingTask;
	JPanel taskPanel;
	private Point mousePos;
	
	public DragTaskController(JPanel taskPanel){
		this.taskPanel = taskPanel;
	}
	
	@Override
	public void mousePressed(MouseEvent me){
		taskPanel.getTransferHandler().setDragImageOffset(me.getPoint());
	}
	
	@Override
	public void mouseDragged(MouseEvent me){		
		JComponent component = (JComponent) me.getSource();
		TransferHandler transferHandler = component.getTransferHandler();
		transferHandler.exportAsDrag(component, me, TransferHandler.MOVE);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		WorkflowView workflowView = TabController.getTabView().getWorkflowView();
		JScrollBar workflowScrollBar = workflowView.getScrollPane().getHorizontalScrollBar();
	}
}
