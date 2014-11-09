/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkFlowListModel;

/**
 * @author dave
 *
 */
public class MainView extends JPanel {
	    
    /**
     * 
     */
    private static final long serialVersionUID = 6561533712473785570L;
    
    /** A list box to display all the work flow on the board */
    private final JList<WorkFlowListModel> workFlowList;
    
    private final JScrollPane lstScrollPane;
    
    /**
     * This is a model for the lstBoard component. Basically it
     * contains the data to be displayed in the list box.
     */
    private final WorkFlowListModel workFlowListModel;
    
    /**
     * Construct the panel, the three components, and add the
     * three components to the panel.
     * 
     * @param boardModel
     */
    public MainView(WorkFlowListModel workFlowListModel) {
        
        // Construct the list box model
        this.workFlowListModel = workFlowListModel;
        
        // Construct the components to be displayed
        workFlowList = new JList<WorkFlowListModel>(workFlowListModel);
        
        // Change the font of the JList
        workFlowList.setFont(workFlowList.getFont().deriveFont(11));
        
        // Set the workflow manager of this panel that controls the positions of the components
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // components will  be arranged vertically
        
        // Put the listbox in a scroll pane
        this.lstScrollPane = new JScrollPane(workFlowList);
        lstScrollPane.setPreferredSize(new Dimension(500, 400));
        
      
        
        // Add the components to the panel
        add(Box.createVerticalStrut(20)); // leave a 20 pixel gap
        add(lstScrollPane);
    }
    
    JList<WorkFlowListModel> getWorkFlowList() {
        return this.workFlowList;
    }
   
    WorkFlowListModel getWorkFlowListModel() {
        return this.workFlowListModel;
    }
    
    /**
     * @return the serialversionuid
     */
    static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    /**
     * @return the lstScrollPane
     */
    JScrollPane getLstScrollPane() {
        return this.lstScrollPane;
    }
    
    
}


