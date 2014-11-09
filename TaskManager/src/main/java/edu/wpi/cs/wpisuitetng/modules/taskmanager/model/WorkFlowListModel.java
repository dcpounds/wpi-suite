/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkFlowModel;

/**
 * @author dave
 *
 */
public class WorkFlowListModel extends AbstractListModel {
	
    private static final long serialVersionUID = -8385236112393098321L;
    
    private List<WorkFlowModel> workFlows;
    
    public WorkFlowListModel() {
        workFlows = new ArrayList<WorkFlowModel>();
    }
    
    /**
     * Removes all messages from this model
     * NOTE: One cannot simply construct a new instance of
     * the model, because other classes in this module have
     * references to it. Hence, we manually remove each message
     * from the model.
     */
    public void emptyModel() {
        int oldSize = getSize();
        Iterator<WorkFlowModel> iterator = workFlows.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
    }
    
    public void addWorkFlow(WorkFlowModel newWorkFlow) {
        // Add the message
        this.workFlows.add(newWorkFlow);
        
        // Notify the model that it has changed so the GUI will be udpated
        this.fireIntervalAdded(this, 0, 0);
    }
    

    public void addWorkFlows(WorkFlowModel[] newWorkFlows) {
        for (int i = 0; i < newWorkFlows.length; i++) {
            this.workFlows.add(newWorkFlows[i]);
        }
        this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
    }
    
    /*
     * Returns the layout at the given index. This method is called
     * internally by the JList in BoardPanel.
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public String getElementAt(int index) {
    	WorkFlowModel WorkFlow = workFlows.get(index);
        return WorkFlow.toString();
    }
    
    /*
     * Returns the number of layouts in the model. Also used internally
     * by the JList in BoardPanel.
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        return workFlows.size();
    }
    
    //For testing
    
    /**
     * @return the messages
     */
    List<WorkFlowModel> getWorkFlows() {
        return this.workFlows;
    }
    
    /**
     * @return the serialversionuid
     */
    static long getSerialversionuid() {
        return serialVersionUID;
    }
}
