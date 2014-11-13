/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author dave
 * When the button to create a workflow is pressed, store the workflow in the database
 */
public class SaveWorkflowController implements ActionListener {
	private final WorkflowModel model;
		
	public SaveWorkflowController(WorkflowModel model) {
		this.model = model;
	}

		
	@Override
	public void actionPerformed(ActionEvent event) {
			
		// Send a request to the core to save this work flow
		final Request request = Network.getInstance().makeRequest("taskmanager/workflowmodel", HttpMethod.POST); // POST = update
		request.setBody(model.toJson()); // put the new work flow in the body of the request
		request.addObserver(new SaveWorkflowRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
	}

}
