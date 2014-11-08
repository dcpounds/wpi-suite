/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author dave
 *
 */
public class AddLayoutController implements ActionListener {

	
	private final LayoutListModel model;
	private final ToolbarView view;
		
		
	public AddLayoutController(LayoutListModel model, ToolbarView view) {
		this.model = model;
		this.view = view;
	}

		
	@Override
	public void actionPerformed(ActionEvent event) {
		// Get the text that was entered
		String layoutName = view.getTxtNewLayoutName().getText();
			
		// Make sure there is text
		if (layoutName.length() > 0) {
			// Clear the text field
			view.getTxtNewLayoutName().setText("");
			
			// Send a request to the core to save this message
			final Request request = Network.getInstance().makeRequest("taskmanager/layoutmodel", HttpMethod.PUT); // PUT == create
			request.setBody(new LayoutModel(layoutName).toJson()); // put the new message in the body of the request
			request.addObserver(new AddLayoutRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}
	}

		
	public void addLayoutToModel(LayoutModel message) {
		model.addLayout(message);
	}

}
