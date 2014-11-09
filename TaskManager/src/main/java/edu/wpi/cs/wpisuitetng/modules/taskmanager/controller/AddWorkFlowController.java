/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkFlowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkFlowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author dave
 *
 */
public class AddWorkFlowController implements ActionListener {

	
	private final WorkFlowListModel model;
	private final ToolbarView view;
		
		
	public AddWorkFlowController(WorkFlowListModel model, ToolbarView view) {
		this.model = model;
		this.view = view;
	}

		
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the text that was entered
		String workFlowName = view.getTxtNewWorkFlowName().getText();
			
		// Make sure there is text
		if (workFlowName.length() > 0) {
			// Clear the text field
			view.getTxtNewWorkFlowName().setText("");
			
			// Send a request to the core to save this work flow
			final Request request = Network.getInstance().makeRequest("taskmanager/workflowmodel", HttpMethod.PUT); // PUT == create
			request.setBody(new WorkFlowModel(workFlowName).toJson()); // put the new work flow in the body of the request
			request.addObserver(new AddWorkFlowRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}
	}

		
	public void addWorkFlowToModel(WorkFlowModel workFlow) {
		model.addWorkFlow(workFlow);
	}

}
