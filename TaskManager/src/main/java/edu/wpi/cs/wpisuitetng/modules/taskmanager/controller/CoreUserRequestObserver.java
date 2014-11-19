
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * Responsible for listening for db requests from the CoreUserController
 * used to fetch the list of core users from the database
 */
public class CoreUserRequestObserver implements RequestObserver {
    
	CoreUserController controller;
	
    public CoreUserRequestObserver(CoreUserController controller){
        this.controller = controller;
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        User[] coreUsers = User.fromJsonArray(iReq.getResponse().getBody());
        WorkflowModel workflowModel = WorkflowController.getWorkflowModel();
        workflowModel.setUserList(coreUsers);
        controller.addUsersToList(coreUsers);
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
	    System.err.println("The request to get coreUsers failed.");
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to get coreUsers failed:" + exception.getMessage());
    }

}