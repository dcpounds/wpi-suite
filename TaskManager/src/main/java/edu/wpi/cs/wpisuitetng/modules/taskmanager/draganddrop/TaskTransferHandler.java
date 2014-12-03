/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team What? We Thought This Was Bio!
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class TaskTransferHandler extends TransferHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7864499109197001974L;
	private static DataFlavor flavor;
	
	/**
	 * @return the dataFlavor that encapsulates the draggableTaskPanel 
	 */
	public static DataFlavor getFlavor() {
		if(flavor == null){
			try {
				flavor = new DataFlavor(
					DataFlavor.javaJVMLocalObjectMimeType 
						+ ";class=\"" + DragTaskPanel.class.getName() + "\"");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return flavor;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#createTransferable(javax.swing.JComponent)
	 */
	public Transferable createTransferable(JComponent component) {
		if (component instanceof Transferable) {
			return (Transferable) component;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#getSourceActions(javax.swing.JComponent)
	 */
	public int getSourceActions(JComponent component) {
		setDragImage(component);
		return TransferHandler.MOVE;
	}
	
	/**
	 * @param component - the component to make the graphic from
	 * Generates a drag image so that the user can see what they're dragging
	 */
	public void setDragImage(JComponent component){
		Image image = new BufferedImage(component.getWidth(), component.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics().create();
		component.paint(graphic);
		setDragImage(image);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#exportDone(javax.swing.JComponent, java.awt.datatransfer.Transferable, int)
	 */
	@Override
	protected void exportDone(JComponent component, Transferable data, int action) {
		component.setVisible(true);
	}
	
	
	
	
}
