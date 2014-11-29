package edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

public class DragStageController implements DropTargetListener{

	private DragStagePanel stage;
	
	public DragStageController(DragStagePanel stage){
		this.stage = stage;
	}
	
	@Override
	public void dragEnter(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
				Transferable transferable = dtde.getTransferable();
				DragTaskPanel panel;
				if (transferable.isDataFlavorSupported(TaskTransferHandler.getFlavor())) {
					try {
						panel = (DragTaskPanel) transferable.getTransferData(TaskTransferHandler.getFlavor());
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}

				} else
					return;
				panel.setVisible(false);
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		Transferable transferable = dtde.getTransferable();
		DragTaskPanel panel;
		if (transferable.isDataFlavorSupported(TaskTransferHandler.getFlavor())) {
			try {
				panel = (DragTaskPanel) transferable.getTransferData(TaskTransferHandler.getFlavor());
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

		} else
			return;
		stage.dropTask(panel, dtde.getLocation());
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
