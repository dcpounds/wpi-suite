/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutModel;

/**
 * @author dave
 *
 */
public class LayoutListModel extends AbstractListModel {
	
    private static final long serialVersionUID = -8385236112393098321L;
    
    private List<LayoutModel> layouts;
    
    public LayoutListModel() {
        layouts = new ArrayList<LayoutModel>();
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
        Iterator<LayoutModel> iterator = layouts.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
    }
    
    public void addLayout(LayoutModel newLayout) {
        // Add the message
        this.layouts.add(newLayout);
        
        // Notify the model that it has changed so the GUI will be udpated
        this.fireIntervalAdded(this, 0, 0);
    }
    

    public void addLayouts(LayoutModel[] newLayouts) {
        for (int i = 0; i < newLayouts.length; i++) {
            this.layouts.add(newLayouts[i]);
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
    	LayoutModel layout = layouts.get(index);
        return layout.toString();
    }
    
    /*
     * Returns the number of layouts in the model. Also used internally
     * by the JList in BoardPanel.
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        return layouts.size();
    }
    
    //For testing
    
    /**
     * @return the messages
     */
    List<LayoutModel> getLayouts() {
        return this.layouts;
    }
    
    /**
     * @return the serialversionuid
     */
    static long getSerialversionuid() {
        return serialVersionUID;
    }
}
