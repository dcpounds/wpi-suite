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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

public class DragTaskPanel extends JPanel implements Transferable{

	private static final long serialVersionUID = -1882518314940259111L;

	public DragTaskPanel(){
		this.setTransferHandler(new TaskTransferHandler());
		
		DragTaskController taskListener = new DragTaskController(this);
		this.addMouseMotionListener(taskListener);
		this.addMouseListener(taskListener);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.datatransfer.Transferable#getTransferData(java.awt.datatransfer.DataFlavor)
	 */
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (flavor.equals(TaskTransferHandler.getFlavor())) {
			return this;
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.datatransfer.Transferable#getTransferDataFlavors()
	 */
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] flavors = { TaskTransferHandler.getFlavor() };
		return flavors;
	}

	/* (non-Javadoc)
	 * @see java.awt.datatransfer.Transferable#isDataFlavorSupported(java.awt.datatransfer.DataFlavor)
	 */
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(TaskTransferHandler.getFlavor());
	}
}
