package edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class DragTaskController extends MouseAdapter{
	JPanel taskPanel;
	
	public DragTaskController(JPanel taskPanel){
		this.taskPanel = taskPanel;
	}
	
	public void mousePressed(MouseEvent me){
		taskPanel.getTransferHandler().setDragImageOffset(me.getPoint());
	}
	
	public void mouseDragged(MouseEvent me){
		JComponent component = (JComponent) me.getSource();
		TransferHandler transferHandler = component.getTransferHandler();
		transferHandler.exportAsDrag(component, me, TransferHandler.MOVE);
	}

}
