
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
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
    
    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    
    
    /**
	 * Recieves the requirements from the database and adds them to the workflow model
	 * @param iReq - request
	 */
    @Override
    public void responseSuccess(IRequest iReq) {
    	System.out.println("recieving requirements");
        Requirement[] requirements = Requirement.fromJsonArray(iReq.getResponse().getBody());
    	System.out.println(requirements[1].getName());
    	System.out.println(requirements.length);
        WorkflowModel workflowModel = WorkflowController.getWorkflowModel();
        workflowModel.setRequirementsList(requirements);
        //controller.addRequirementsToList(requirements);
    }
    
//    /**
//     * Converts the list of requirements into a list of the names of the requirements
//	 * @param requirements - list of requirements
//	 */
//    public String[] requirementsToStrings(Requirement[] requirements) {
//		// TODO Auto-generated method stub
//    	System.out.println(requirements.length);
//    	String[] reqStrings = new String[requirements.length];
//    	for (int i = 0; i<=requirements.length; i++){
//    		reqStrings[i] = requirements[i].getName();
//    		System.out.println(reqStrings[i]);
//    	}
//    	System.out.println("made requirements into strings");
//		return reqStrings;
//	}

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