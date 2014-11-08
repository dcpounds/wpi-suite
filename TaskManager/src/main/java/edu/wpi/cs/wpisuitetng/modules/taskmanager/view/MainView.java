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

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutListModel;

/**
 * @author dave
 *
 */
public class MainView extends JPanel {
	    
    /**
     * 
     */
    private static final long serialVersionUID = 6561533712473785570L;
    
    /** A list box to display all the message on the board */
    private final JList<LayoutListModel> layoutList;
    
    private final JScrollPane lstScrollPane;
    
    /**
     * This is a model for the lstBoard component. Basically it
     * contains the data to be displayed in the list box.
     */
    private final LayoutListModel layoutListModel;
    
    /**
     * Construct the panel, the three components, and add the
     * three components to the panel.
     * 
     * @param boardModel
     */
    public MainView(LayoutListModel layoutListModel) {
        
        // Construct the list box model
        this.layoutListModel = layoutListModel;
        
        // Construct the components to be displayed
        layoutList = new JList<LayoutListModel>(layoutListModel);
        
        // Change the font of the JList
        layoutList.setFont(layoutList.getFont().deriveFont(11));
        
        // Set the layout manager of this panel that controls the positions of the components
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // components will  be arranged vertically
        
        // Put the listbox in a scroll pane
        this.lstScrollPane = new JScrollPane(layoutList);
        lstScrollPane.setPreferredSize(new Dimension(500, 400));
        
      
        
        // Add the components to the panel
        add(Box.createVerticalStrut(20)); // leave a 20 pixel gap
        add(lstScrollPane);
    }
    
    JList<LayoutListModel> getLayoutList() {
        return this.layoutList;
    }
   
    LayoutListModel getLstBoardModel() {
        return this.layoutListModel;
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


