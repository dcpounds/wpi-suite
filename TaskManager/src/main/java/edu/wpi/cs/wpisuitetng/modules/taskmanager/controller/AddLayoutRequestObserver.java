/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddLayoutController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * @author dave
 *
 */
public class AddLayoutRequestObserver implements RequestObserver {

	 private final AddLayoutController controller;
	    
	    public AddLayoutRequestObserver(AddLayoutController controller) {
	        this.controller = controller;
	    }
	    
	@Override
	public void responseSuccess(IRequest iReq) {
	    // Get the response to the given request
	    final ResponseModel response = iReq.getResponse();
	    
	    // Parse the message out of the response body
	    final LayoutModel message = LayoutModel.fromJson(response.getBody());
	    
	    // Pass the messages back to the controller
	    controller.addLayoutToModel(message);
	}
	
	@Override
	public void responseError(IRequest iReq) {
	    System.err.println("The request to add a layout failed.");
	}
	
	@Override
	public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to add a layout failed.");
	}
	
	/**
	 * @return the controller
	 */
	public AddLayoutController getController() {
	    return this.controller;
	}

}
