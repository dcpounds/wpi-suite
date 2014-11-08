/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddLayoutController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.LayoutListModel;

/**
 * @author dave
 *
 */
public class ToolbarView extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6568533963473785570L;
    
    /** A text field where the user can enter a new message */
    private JTextField txtNewLayoutName;
    
    /** A button for submitting new messages */
    private final JButton btnSubmit;
    
    private final MouseAdapter mouseListener;
    
    private final LayoutListModel layoutListModel;
    
   
    public ToolbarView(LayoutListModel layoutModel) {
        
        // Construct the list box model
        layoutListModel = layoutModel;
        
        // Construct the components to be displayed
        txtNewLayoutName = new JTextField("Enter a message here.");
        btnSubmit = new JButton("Submit");
        
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtNewLayoutName.setText("");
            }
        };
        
        // Construct the add message controller and add it to the submit button
        btnSubmit.addActionListener(new AddLayoutController(layoutListModel, this));
        
        // Set the layout manager of this panel that controls the positions of the components
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // components will  be arranged vertically
        
        // Clear the contents of the text field when the user clicks on it
        txtNewLayoutName.addMouseListener(mouseListener);
        
        // Adjust sizes and alignments
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add the components to the panel
        add(Box.createVerticalStrut(20));
        add(txtNewLayoutName);
        add(Box.createVerticalStrut(20));
        add(btnSubmit);
    }
    
    public JTextField getTxtNewLayoutName() {
        return txtNewLayoutName;
    }
    
    void setTxtNewLayoutName(JTextField txtNewLayoutName) {
        this.txtNewLayoutName = txtNewLayoutName;
    }
    
    /**
     * @return the btnSubmit
     */
    JButton getBtnSubmit() {
        return this.btnSubmit;
    }
    
    LayoutListModel getLayoutListModel() {
        return this.layoutListModel;
    }

    static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    /**
     * @return the mouseListener
     */
    MouseAdapter getMouseListener() {
        return this.mouseListener;
    }
}