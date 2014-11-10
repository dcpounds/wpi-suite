/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author dave
 *
 */
public class GetWorkflowsController implements ActionListener {
    
    private final WorkflowListModel model;
    
    public GetWorkflowsController(WorkflowListModel model) {
        this.model = model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Send a request to the core to save this workflow
        final Request request = Network.getInstance().makeRequest("taskmanager/workflowmodel", HttpMethod.GET); // GET == read
        request.addObserver(new GetWorkflowsRequestObserver(this)); // add an observer to process the response
        request.send(); // send the request
    }
    
    /**
     * Add the given work flows to the local model (they were received from the
     * core).
     * This method is called by the GetWorkflowsRequestObserver
     * 
     * @param workflows an array of work flows received from the server
     */
    public void receivedWorkflows(WorkflowModel[] workflows) {
        // Empty the local model to eliminate duplications
        model.emptyModel();
        
        // Make sure the response was not null
        if (workflows != null) {
            
            // add the work flows to the local model
            model.addWorkflows(workflows);
        }
    }

}
