/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;


import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * @author dave
 *
 */
public class GetWorkFlowsRequestObserver implements RequestObserver {
    
    private GetWorkFlowsController controller;
    
    public GetWorkFlowsRequestObserver(GetWorkFlowsController controller) {
        this.controller = controller;
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        WorkflowModel[] workFlows = WorkflowModel.fromJsonArray(iReq.getResponse().getBody());
        controller.receivedWorkFlows(workFlows);
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
	    System.err.println("The request to view work flows failed.");
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to view work flows failed.");
    }
    
    /**
     * @return the controller
     */
    public GetWorkFlowsController getController() {
        return this.controller;
    }

}
