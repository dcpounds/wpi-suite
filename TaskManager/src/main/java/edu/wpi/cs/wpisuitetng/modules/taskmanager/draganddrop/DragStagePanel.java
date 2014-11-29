package edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop;

import java.awt.Point;
import java.awt.dnd.DropTarget;

import javax.swing.JPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
public class DragStagePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6616674170936811473L;

	/**
	 * An extendable JPanel that can accept dropped tasks
	 */
	public DragStagePanel(){
		this.setTransferHandler(new TaskTransferHandler());
		this.setDropTarget(new DropTarget(this, new DragStageController(this)));
	}
	
	/**
	 * @param taskPanel - the taskPanel that is being dropped
	 * @param dropPoint - the point withint the stage that the task was dropped
	 */
	public void dropTask(DragTaskPanel taskPanel, Point dropPoint) {
		WorkflowModel workflowModel = WorkflowController.getWorkflowModel();
		TaskView taskView = (TaskView)taskPanel;
		StageView oldStageView = taskView.getStageView();
		StageView newStageView = (StageView)this;
		int taskID = (taskView).getID();
		TaskModel taskModel = workflowModel.getTaskModelByID(taskID);
		StageModel oldStageModel = workflowModel.getStageModelByID(oldStageView.getID());
		StageModel newStageModel = workflowModel.getStageModelByID(newStageView.getID());
		
		int index = getIndexAtPoint(dropPoint);
		oldStageView.removeTaskView(taskView);
		oldStageModel.removeTask(taskModel);
		taskModel.setStageID(newStageView.getID());
		newStageModel.addTaskModelAtIndex(index, taskModel);
		StageController.sendUpdateRequest(oldStageModel);
		StageController.sendUpdateRequest(newStageModel);

	}
	
	/**
	 * @param point - the coordinate within the target stage that the user dropped the task 
	 * @return the index at which to put the stage into 
	 */
	public int getIndexAtPoint(Point point){
		TaskView closestTask = null;
		double shortestDistance = Double.MAX_VALUE;
		for(TaskView taskView : ((StageView) this).getTaskViewList()){
			Point taskLocation = taskView.getLocation();
			double dy = taskLocation.y - point.y;
			double dx = taskLocation.x - point.x;
			double distance = Math.sqrt( Math.pow(dx,2) + Math.pow(dy,2) );
			if(distance < shortestDistance){
				shortestDistance = distance;
				closestTask = taskView;
			}
		}
		int index = ((StageView)this).getTaskViewList().indexOf(closestTask);
		if(index < 0)
			return 0;
		System.out.println("Index of drop is " + index);
		return index;
	}
}
