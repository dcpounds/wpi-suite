/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.SaveWorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * @author dave
 *
 */
public class SaveWorkflowRequestObserver implements RequestObserver {

	 private final SaveWorkflowController controller;
	    
	 public SaveWorkflowRequestObserver(SaveWorkflowController controller) {
		 this.controller = controller;
	 }
	    
	@Override
	public void responseSuccess(IRequest iReq) {
	    // Get the response to the given request
	    final ResponseModel response = iReq.getResponse(); 
	    System.out.println("Saved Model Successfully");
	}
	
	@Override
	public void responseError(IRequest iReq) {
	    System.err.println("The request to add a workflow failed.");
	}
	
	@Override
	public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to add a workflow failed.");
	}
	
	/**
	 * @return the controller
	 */
	public SaveWorkflowController getController() {
	    return this.controller;
	}

}
