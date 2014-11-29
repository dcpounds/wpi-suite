package edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop;

import java.awt.Point;
import java.awt.dnd.DropTarget;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
public class DragStagePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6616674170936811473L;

	public DragStagePanel(){
		this.setTransferHandler(new TaskTransferHandler());
		this.setDropTarget(new DropTarget(this, new DragStageController(this)));
	}
	
	public void dropTask(DragTaskPanel taskPanel, Point dropPoint) {
		System.out.println("Dropped!");

	}
}
