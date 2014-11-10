/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddWorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * @author dave
 *
 */
public class AddWorkflowRequestObserver implements RequestObserver {

	 private final AddWorkflowController controller;
	    
	    public AddWorkflowRequestObserver(AddWorkflowController controller) {
	        this.controller = controller;
	    }
	    
	@Override
	public void responseSuccess(IRequest iReq) {
	    // Get the response to the given request
	    final ResponseModel response = iReq.getResponse();
	    
	    // Parse the workflow out of the response body
	    final WorkflowModel workflow = WorkflowModel.fromJson(response.getBody());
	    
	    // Pass the workflows back to the controller
	    controller.addWorkflowToModel(workflow);
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
	public AddWorkflowController getController() {
	    return this.controller;
	}

}
