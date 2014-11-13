/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author dave
 *
 */
public class InitializeWorkflowController  {
    
    private final WorkflowModel model;
    private boolean hasReceivedResponse;
    
    public InitializeWorkflowController(WorkflowModel model) {
        this.model = model;
    }
    
    public void initializeWorkFlow() {
        // Send a request to the core to save this workflow
    	hasReceivedResponse = false;
        final Request request = Network.getInstance().makeRequest("taskmanager/workflowmodel", HttpMethod.GET); // GET == read
        request.addObserver(new InitializeWorkflowRequestObserver(this)); // add an observer to process the response
        request.send(); // send the request
    }
    
   
    public void receivedWorkflow(WorkflowModel receivedModel) {
        // Empty the local model to eliminate duplications
    	hasReceivedResponse = true;
    	if(receivedModel != null){
    		this.model.copyFrom(receivedModel);
    	}
    }

    public void waitForResponse(){
    	while(!hasReceivedResponse){
    		continue;
    	}
    	hasReceivedResponse = false;
    }
    
}
