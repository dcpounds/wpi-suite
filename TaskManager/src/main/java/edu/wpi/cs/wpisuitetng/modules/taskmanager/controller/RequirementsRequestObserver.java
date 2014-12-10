
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;


/**
 * Responsible for listening for db requests from the RequirementsController
 * used to fetch the list of requirements from the database
 */
public class RequirementsRequestObserver implements RequestObserver {
    
	RequirementsController controller;
	
    public RequirementsRequestObserver(RequirementsController requirementsCont){
        this.controller = requirementsCont;
    }
    
    /**
	 * Recieves the requirements from the database and adds them to the workflow model
	 * @param iReq - request
	 */
    @Override
    public void responseSuccess(IRequest iReq) {
        Requirement[] requirements = Requirement.fromJsonArray(iReq.getResponse().getBody());
        WorkflowModel workflowModel = WorkflowController.getWorkflowModel();
        controller.addRequirementsToList(requirements);
        //controller.addRequirementsToList(requirements);
    }

    
	/*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
	    System.err.println("The request to get requirements failed.");
    }
    
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
	    System.err.println("The request to get requirements failed:" + exception.getMessage());
    }

}