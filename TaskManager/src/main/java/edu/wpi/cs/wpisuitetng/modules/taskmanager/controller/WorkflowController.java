package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;

public class WorkflowController implements InternalFrameListener{
	private static WorkflowController instance = new WorkflowController();
	private static WorkflowModel model;
	boolean hasReceivedGetResponse;

	private WorkflowController () {
		model = new WorkflowModel("main");
		hasReceivedGetResponse = false;
	}

	public static WorkflowController getInstance(){
		return instance;
	}

	public static WorkflowModel getWorkflowModel(){
		return model;
	}

	public void receivedWorkflow(WorkflowModel receivedModel) {
        // Empty the local model to eliminate duplications
    	hasReceivedGetResponse = true;
    	if(receivedModel != null){
    		this.model.copyFrom(receivedModel);
    	}
    }

    public void waitForResponse(){
    	while(!hasReceivedGetResponse){
    		continue;
    	}
    	hasReceivedGetResponse = false;
    }

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
        // Send a request to the core to get workflow
		System.out.println("Retrieving workflow model");
    	hasReceivedGetResponse = false;
        //final Request request = Network.getInstance().makeRequest("taskmanager/workflowmodel/main", HttpMethod.GET); // GET == read
        //request.addObserver(new AddWorkflowRequestObserver()); // add an observer to process the response
        //request.send(); // send the request
        //waitForResponse();
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		System.out.println("Saving workflow model");
		/*// Send a request to the core to save this work flow
		final Request request = Network.getInstance().makeRequest("taskmanager/workflowmodel", HttpMethod.POST); // POST = update
		request.setBody(model.toJson()); // put the new work flow in the body of the request
		request.addObserver(new SaveWorkflowRequestObserver()); // add an observer to process the response
		request.send(); // send the request*/
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
}
