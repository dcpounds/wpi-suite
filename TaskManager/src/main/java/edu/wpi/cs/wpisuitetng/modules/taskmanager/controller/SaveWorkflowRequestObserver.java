/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * 
 * request observer for the save workflow controller
 * Note: this class should not need to be modified unless doing stuff with the database
 */
public class SaveWorkflowRequestObserver implements RequestObserver {

	 private final WorkflowController controller;
	    
	 public SaveWorkflowRequestObserver() {
		 controller = WorkflowController.getInstance();
	 }
	    
	@Override
	public void responseSuccess(IRequest iReq) {
	    // Get the response to the given request
	    System.out.println("Saved Model Successfully");
	}
	
	@Override
	public void responseError(IRequest iReq) {
	    System.err.println("The request to save workflow failed.");
	}
	
	@Override
	public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to save workflow failed.");
	}

}
