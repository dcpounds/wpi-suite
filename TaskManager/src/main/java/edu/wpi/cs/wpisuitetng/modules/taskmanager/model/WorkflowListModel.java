/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;

/**
 * @author dave
 *
 */
/**
 * @author Alec
 *
 */
/**
 * @author Alec
 *
 */
/**
 * @author Alec
 *
 */
public class WorkflowListModel extends AbstractListModel {
	
    private static final long serialVersionUID = -8385236112393098321L;
    
    private List<WorkflowModel> workflows;
    
    public WorkflowListModel() {
        workflows = new ArrayList<WorkflowModel>();
    }
    
    /**
     * Removes all work flows from this model
     * NOTE: One cannot simply construct a new instance of
     * the model, because other classes in this module have
     * references to it. Hence, we manually remove each work flow
     * from the model.
     */
    public void emptyModel() {
        int oldSize = getSize();
        Iterator<WorkflowModel> iterator = workflows.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
    }
    
    public void addWorkflow(WorkflowModel newWorkflow) {
        // Add the work flow
        this.workflows.add(newWorkflow);
        
        // Notify the model that it has changed so the GUI will be udpated
        this.fireIntervalAdded(this, 0, 0);
    }
    

    public void addWorkflows(WorkflowModel[] newWorkflows) {
        for (int i = 0; i < newWorkflows.length; i++) {
            this.workflows.add(newWorkflows[i]);
        }
        this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
    }
    
    /*
     * Returns the work flow at the given index. This method is called
     * internally by the JList in BoardPanel.
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public String getElementAt(int index) {
    	WorkflowModel workflow = workflows.get(index);
        return workflow.toString();
    }
    
    /**
     *
     * @param index
     * @return - the workflowmodel at the given position in the list of workflows
     */
    public WorkflowModel getWorkflowModel(int index) {
    	return workflows.get(index);
    }
    
    /*
     * Returns the number of work flows in the model. Also used internally
     * by the JList in BoardPanel.
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        return workflows.size();
    }
    
    //For testing
    
    /**
     * @return the work flows
     */
    List<WorkflowModel> getWorkflows() {
        return this.workflows;
    }
    
    /**
     * @return the serialversionuid
     */
    static long getSerialversionuid() {
        return serialVersionUID;
    }
}
