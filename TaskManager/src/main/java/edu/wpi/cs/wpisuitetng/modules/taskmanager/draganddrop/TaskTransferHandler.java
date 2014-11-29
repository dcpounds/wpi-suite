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
	
	public Transferable createTransferable(JComponent component) {
		if (component instanceof Transferable) {
			return (Transferable) component;
		}
		return null;
	}
	
	public int getSourceActions(JComponent component) {
		setDragImage(component);
		return TransferHandler.MOVE;
	}
	
	public void setDragImage(JComponent component){
		Image image = new BufferedImage(component.getWidth(), component.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics().create();
		component.paint(graphic);
		setDragImage(image);
	}
	
	@Override
	protected void exportDone(JComponent component, Transferable data, int action) {
		component.setVisible(true);
	}
	
	
	
	
}
