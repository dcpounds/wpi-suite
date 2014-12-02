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
	public void placeTask(Point dropPoint, DragTaskPanel taskPanel) {
		WorkflowModel workflowModel = WorkflowController.getWorkflowModel();
		TaskView taskView = (TaskView)taskPanel;
		
		TaskModel taskModel = workflowModel.getTaskModelByID(taskView.getID());
		
		//The original stage the task was in
		StageView originalStageView = taskView.getStageView();
		StageModel originalStageModel = workflowModel.getStageModelByID(originalStageView.getID());
		originalStageModel.removeTask(taskModel);
		originalStageView.removeTaskView(taskView);
		StageController.sendUpdateRequest(originalStageModel);
		
		//Update the task that
		StageView newStageView = (StageView)this;
		StageModel newStageModel = workflowModel.getStageModelByID(newStageView.getID());
		newStageModel.addTaskModel(taskModel);
		newStageView.addTaskView(taskView);
		
		taskView.setStageView(newStageView);
		taskModel.setStageID(newStageView.getID());
		System.out.println("Adding a taskModel to stage " + newStageModel.getTitle());
		
	
		StageController.sendUpdateRequest(newStageModel);

	}
}