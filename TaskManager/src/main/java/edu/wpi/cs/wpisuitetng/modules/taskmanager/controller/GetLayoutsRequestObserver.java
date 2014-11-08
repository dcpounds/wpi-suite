/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;


import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * @author dave
 *
 */
public class GetLayoutsRequestObserver implements RequestObserver {
    
    private GetLayoutsController controller;
    
    public GetLayoutsRequestObserver(GetLayoutsController controller) {
        this.controller = controller;
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        LayoutModel[] layouts = LayoutModel.fromJsonArray(iReq.getResponse().getBody());
        controller.receivedLayouts(layouts);
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
	    System.err.println("The request to view layouts failed.");
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to view layouts failed.");
    }
    
    /**
     * @return the controller
     */
    public GetLayoutsController getController() {
        return this.controller;
    }

}
