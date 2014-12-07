
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;


/**
 * Responsible for listening for db requests from the CoreUserController
 * used to fetch the list of core users from the database
 */
public class RequirementsRequestObserver implements RequestObserver {
    
	RequirementsController controller;
	
    public RequirementsRequestObserver(RequirementsController requirementsCont){
        this.controller = requirementsCont;
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    
    @Override
    public void responseSuccess(IRequest iReq) {
    	System.out.println("recieving requirements");
        Requirement[] requirements = Requirement.fromJsonArray(iReq.getResponse().getBody());
    	System.out.println(requirements[1].getName());
        WorkflowModel workflowModel = WorkflowController.getWorkflowModel();
        workflowModel.setRequirementsList(requirements);
        //controller.addRequirementsToList(requirements);
    }
    
//    @Override
//    public void responseSuccessString(IRequest iReq) {
//        Requirement[] requirements = Requirement.fromJsonArray(iReq.getResponse().getBody());
//        WorkflowModel workflowModel = WorkflowController.getWorkflowModel();
//        String[] requirementStrings = this.requirementsToStrings(requirements);
//        workflowModel.setRequirementsList(requirementStrings);
//        controller.addRequirementsToList(requirementStrings);
//    }
    
    public String[] requirementsToStrings(Requirement[] requirements) {
		// TODO Auto-generated method stub
    	System.out.println(requirements[1].getName());
    	System.out.println(requirements.length);
    	String[] reqStrings = new String[requirements.length];
    	for (int i = 0; i<=requirements.length; i++){
    		reqStrings[i] = requirements[i].getName();
    		System.out.println(reqStrings[i]);
    	}
    	System.out.println("made requirements into strings");
		return reqStrings;
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