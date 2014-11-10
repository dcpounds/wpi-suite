/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author dave
 * When the button to create a workflow is pressed, store the workflow in the database
 */
public class AddWorkflowController implements ActionListener {

	
	private final WorkflowListModel model;
	private final ToolbarView view;
		
		
	public AddWorkflowController(WorkflowListModel model, ToolbarView view) {
		this.model = model;
		this.view = view;
	}

		
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the text that was entered
		String workflowName = view.getTxtNewWorkflowName().getText();
			
		// Make sure there is text
		if (workflowName.length() > 0) {
			// Clear the text field
			view.getTxtNewWorkflowName().setText("");
			
			// Send a request to the core to save this work flow
			final Request request = Network.getInstance().makeRequest("taskmanager/workflowmodel", HttpMethod.PUT); // PUT == create
			request.setBody(new WorkflowModel(workflowName).toJson()); // put the new work flow in the body of the request
			request.addObserver(new AddWorkflowRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}
	}

		
	public void addWorkflowToModel(WorkflowModel workflow) {
		model.addWorkflow(workflow);
	}

}
