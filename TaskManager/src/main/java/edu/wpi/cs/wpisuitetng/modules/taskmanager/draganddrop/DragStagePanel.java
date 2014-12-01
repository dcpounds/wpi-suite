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
		
		oldStageView.removeTaskView(taskView);
		oldStageModel.removeTask(taskModel);
		taskModel.setStageID(newStageView.getID());
		newStageModel.addTaskModelAtIndex(taskModel);
		StageController.sendUpdateRequest(oldStageModel);
		StageController.sendUpdateRequest(newStageModel);

	}
}
