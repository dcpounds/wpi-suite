/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddWorkFlowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkFlowModel;
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
	    
	    // Parse the message out of the response body
	    final WorkFlowModel message = WorkFlowModel.fromJson(response.getBody());
	    
	    // Pass the messages back to the controller
	    controller.addWorkFlowToModel(message);
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
