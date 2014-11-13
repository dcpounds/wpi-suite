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

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

/**
 * @author dave
 *
 */
public class ToolbarView extends JPanel {
    
    private static final long serialVersionUID = 6568533963473785570L;
    
    /** A text field where the user can enter a new work flow */
    private JTextField txtNewWorkflowName;
    
    /** A button for submitting new work flows */
    private final JButton newWorkflowButton;
    private final JButton newCardButton;
    private final JButton newTaskButton;
    private final WorkflowListModel workflowListModel;
   
    public ToolbarView(WorkflowListModel workflowModel, TaskManagerTabView taskManagerTabView) {
        
        // Construct the list box model
        workflowListModel = workflowModel;
        
        // Construct the components to be displayed
		newWorkflowButton = new JButton("New Workflow");
		newCardButton = new JButton("New Card");
		newTaskButton = new JButton("New Task");
        
        // Construct the add work flow controller and add it to the submit button
        newWorkflowButton.addActionListener(new AddTabController(taskManagerTabView, TabType.WORKFLOW, workflowListModel));
        
        //adds a card to the mainPanel
        newCardButton.addActionListener( new AddTabController(taskManagerTabView, TabType.CARD, workflowListModel));
        
        //adds a tab to the mainPanel
        newTaskButton.addActionListener( new AddTabController(taskManagerTabView, TabType.TASK, workflowListModel));
        
        // Set the layout manager of this panel that controls the positions of the components
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); // components will  be arranged horizontally
        
        // Adjust sizes and alignments
        newWorkflowButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add the components to the panel
        add(Box.createHorizontalStrut(20));
        add(newWorkflowButton);
        //add(Box.createHorizontalStrut(20));
        //add(newCardButton);
        add(Box.createHorizontalStrut(20));
        add(newTaskButton);
    }

    static long getSerialversionuid() {
        return serialVersionUID;
    }
}