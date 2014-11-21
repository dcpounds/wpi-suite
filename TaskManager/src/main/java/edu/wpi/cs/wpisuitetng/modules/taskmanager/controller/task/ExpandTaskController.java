package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;

public class ExpandTaskController implements MouseListener{
	TaskModel taskModel;
	TaskView taskContainerView;
	StageView stageView;
	
	public ExpandTaskController(TaskView taskContainerView, TaskModel taskModel){
		this.taskModel = taskModel;
		this.taskContainerView = taskContainerView;	
		this.stageView = taskContainerView.getStageView();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean isExpanded = taskModel.getIsExpanded();
		if(isExpanded == true)
			taskContainerView.hideDetails();
		else
			taskContainerView.showDetails();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
