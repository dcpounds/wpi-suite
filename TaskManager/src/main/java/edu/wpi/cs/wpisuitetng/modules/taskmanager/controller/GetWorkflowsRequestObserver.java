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
public class GetWorkflowsRequestObserver implements RequestObserver {
    
    private GetWorkflowsController controller;
    
    public GetWorkflowsRequestObserver(GetWorkflowsController controller) {
        this.controller = controller;
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        WorkflowModel[] workflows = WorkflowModel.fromJsonArray(iReq.getResponse().getBody());
        controller.receivedWorkflows(workflows);
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
	    System.err.println("The request to view workflows failed.");
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to view workflows failed.");
    }
    
    /**
     * @return the controller
     */
    public GetWorkflowsController getController() {
        return this.controller;
    }

}
