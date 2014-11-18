
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * 
 * request observer for the initialize workflow controller
 * Note: this class should not need to be modified unless doing stuff with the database
 */
public class AddWorkflowRequestObserver implements RequestObserver {
    
	WorkflowController controller;
	
    public AddWorkflowRequestObserver(){
        controller = WorkflowController.getInstance();
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        WorkflowModel[] workflows = WorkflowModel.fromJsonArray(iReq.getResponse().getBody());
        controller.receivedWorkflow(workflows[0]);
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
	    System.err.println("The request to view workflows failed.");
        controller.receivedWorkflow(null);
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to view workflows failed:" + exception.getMessage());
        controller.receivedWorkflow(null);
    }

}
