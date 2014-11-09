/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddWorkFlowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * @author dave
 *
 */
public class AddWorkFlowRequestObserver implements RequestObserver {

	 private final AddWorkFlowController controller;
	    
	    public AddWorkFlowRequestObserver(AddWorkFlowController controller) {
	        this.controller = controller;
	    }
	    
	@Override
	public void responseSuccess(IRequest iReq) {
	    // Get the response to the given request
	    final ResponseModel response = iReq.getResponse();
	    
	    // Parse the work flow out of the response body
	    final WorkflowModel workFlow = WorkflowModel.fromJson(response.getBody());
	    
	    // Pass the work flows back to the controller
	    controller.addWorkFlowToModel(workFlow);
	}
	
	@Override
	public void responseError(IRequest iReq) {
	    System.err.println("The request to add a work flow failed.");
	}
	
	@Override
	public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to add a work flow failed.");
	}
	
	/**
	 * @return the controller
	 */
	public AddWorkFlowController getController() {
	    return this.controller;
	}

}
