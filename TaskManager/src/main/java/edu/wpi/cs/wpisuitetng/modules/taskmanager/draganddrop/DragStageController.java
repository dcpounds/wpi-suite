package edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.WorkflowView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabView;

public class DragStageController implements DropTargetListener, MouseListener, MouseMotionListener{

	private DragStagePanel stage;
	private int initialXPos;
	private int targetXPos;
	private int mouseX;
	private int mouseY;
	private int stageID;
	private StageModel stageModel;
	private WorkflowView workflowView;
	
	public DragStageController(DragStagePanel stage){
		this.stage = stage;
		stageID = ((StageView)stage).getID();
		this.workflowView = TabController.getTabView().getWorkflowView();
		
		
	}
	
	@Override
	public void dragEnter(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
	 */
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

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
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
		stage.placeTask(dtde.getLocation(), panel);
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
	 */
	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		e.translatePoint(e.getComponent().getX() - mouseX, 0);
		stage.setLocation(e.getX(), stage.getY());
		int overlap = stage.getX() + (stage.getWidth()/3);
		
		for(StageView view : TabController.getTabView().getWorkflowView().getStageViewList().values() ){
			if(view == stage)
				continue;
			
			if(view.getX() < overlap && (view.getX() + stage.getWidth() > overlap)){
				//The position we will put our stage in if the user lets go of the mouse
				this.targetXPos = view.getX();
				//Put the stage we're displacing at the old initialXPos
				view.setLocation(initialXPos, stage.getY() );
				this.initialXPos = targetXPos;
				
			}
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		this.initialXPos = targetXPos = stage.getLocation().x;
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.stageModel = WorkflowController.getWorkflowModel().getStageModelByID(stageID);
		stage.setLocation(targetXPos, stage.getLocation().y);
		initialXPos = stage.getX();
		StageController.sendUpdateRequest(stageModel);
		
		// TODO Auto-generated method stub
		
	}
	
	
	

}
