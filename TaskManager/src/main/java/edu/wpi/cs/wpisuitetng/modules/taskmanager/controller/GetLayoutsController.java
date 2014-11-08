/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author dave
 *
 */
public class GetLayoutsController implements ActionListener {
    
    private final LayoutListModel model;
    
    public GetLayoutsController(LayoutListModel model) {
        this.model = model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Send a request to the core to save this message
        final Request request = Network.getInstance().makeRequest("taskmanager/layoutmodel", HttpMethod.GET); // GET == read
        request.addObserver(new GetLayoutsRequestObserver(this)); // add an observer to process the response
        request.send(); // send the request
    }
    
    /**
     * Add the given messages to the local model (they were received from the
     * core).
     * This method is called by the GetMessagesRequestObserver
     * 
     * @param layouts an array of messages received from the server
     */
    public void receivedLayouts(LayoutModel[] layouts) {
        // Empty the local model to eliminate duplications
        model.emptyModel();
        
        // Make sure the response was not null
        if (layouts != null) {
            
            // add the messages to the local model
            model.addLayouts(layouts);
        }
    }

}
