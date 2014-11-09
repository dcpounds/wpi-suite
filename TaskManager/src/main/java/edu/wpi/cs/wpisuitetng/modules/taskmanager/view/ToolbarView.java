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

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddWorkFlowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;

/**
 * @author dave
 *
 */
public class ToolbarView extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6568533963473785570L;
    
    /** A text field where the user can enter a new work flow */
    private JTextField txtNewWorkFlowName;
    
    /** A button for submitting new work flows */
    private final JButton btnSubmit;
    
    private final MouseAdapter mouseListener;
    
    private final WorkflowListModel workFlowListModel;
    
   
    public ToolbarView(WorkflowListModel workFlowModel) {
        
        // Construct the list box model
        workFlowListModel = workFlowModel;
        
        // Construct the components to be displayed
        txtNewWorkFlowName = new JTextField("Enter a work flow here.");
        btnSubmit = new JButton("Submit");
        
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtNewWorkFlowName.setText("");
            }
        };
        
        // Construct the add work flow controller and add it to the submit button
        btnSubmit.addActionListener(new AddWorkFlowController(workFlowListModel, this));
        
        // Set the layout manager of this panel that controls the positions of the components
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // components will  be arranged vertically
        
        // Clear the contents of the text field when the user clicks on it
        txtNewWorkFlowName.addMouseListener(mouseListener);
        
        // Adjust sizes and alignments
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add the components to the panel
        add(Box.createVerticalStrut(20));
        add(txtNewWorkFlowName);
        add(Box.createVerticalStrut(20));
        add(btnSubmit);
    }
    
    public JTextField getTxtNewWorkFlowName() {
        return txtNewWorkFlowName;
    }
    
    void setTxtNewWorkFlowName(JTextField txtNewWorkFlowName) {
        this.txtNewWorkFlowName = txtNewWorkFlowName;
    }
    
    /**
     * @return the btnSubmit
     */
    JButton getBtnSubmit() {
        return this.btnSubmit;
    }
    
    WorkflowListModel getWorkFlowListModel() {
        return this.workFlowListModel;
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