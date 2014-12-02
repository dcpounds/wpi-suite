package edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

/**
 * This is a model for activities for a task. It contains all of the activities
 * to be displayed on the activities board. It extends AbstractListModel so that
 * it can provide the model data to the JList component in the activities board.
 * 
 * Code was based on the postboard model
 */
public class ActivityListModel extends AbstractListModel<ActivityModel> {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8385236147593098321L;
    
    /** The list of activities on the board */
    private List<ActivityModel> activities;
    
    /**
     * Constructs a new board with no activities.
     */
    public ActivityListModel() {
        activities = new ArrayList<ActivityModel>();
    }
    
    /**
     * Adds the given activity to the board
     * 
     * @param newActivity the new activity to add
     */
    public void addActivity(ActivityModel newActivity) {
        // Add the activity
        this.activities.add(newActivity);
        
        // Notify the model that it has changed so the GUI will be udpated
        this.fireIntervalAdded(this, 0, 0);
    }
    
    /**
     * Adds the given array of activities to the board
     * 
     * @param activities the array of activities to add
     */
    public void addActivities(ActivityModel[] activities) {
        for (int i = 0; i < activities.length; i++) {
            this.activities.add(activities[i]);
        }
        this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
    }
    
    /**
     * Removes all activities from this model
     * NOTE: One cannot simply construct a new instance of
     * the model, because other classes in this module have
     * references to it. Hence, we manually remove each activity
     * from the model.
     */
    public void emptyModel() {
        int oldSize = getSize();
        Iterator<ActivityModel> iterator = activities.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
    }
    
    /*
     * Returns the activity at the given index. This method is called
     * internally by the JList in BoardPanel. Note this method returns
     * elements in reverse order, so newest activities are returned first.
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public ActivityModel getElementAt(int index) {
        return activities.get(activities.size() - 1 - index);
    }
    
    /*
     * Returns the number of activities in the model. Also used internally
     * by the JList in BoardPanel.
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        return activities.size();
    }
    
    //For testing
    
    /**
     * @return the activities
     */
    List<ActivityModel> getactivities() {
        return this.activities;
    }
    
    /**
     * @return the serialversionuid
     */
    static long getSerialversionuid() {
        return serialVersionUID;
    }

}
