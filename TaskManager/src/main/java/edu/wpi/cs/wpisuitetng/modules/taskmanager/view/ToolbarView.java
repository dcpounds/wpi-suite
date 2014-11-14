/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.SaveWorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
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
    private final JButton saveWorkflowButton;
    private final JButton newStageButton;
    private final JButton newTaskButton;
    private final WorkflowModel workflowModel;
   
    public ToolbarView(WorkflowModel model, TaskManagerTabView taskManagerTabView) {
        
        // Construct the list box model
        this.workflowModel = model;
        
        // Construct the components to be displayed
		saveWorkflowButton = new JButton("Save Workflow");
		newStageButton = new JButton("New Stage");
		newTaskButton = new JButton("New Task");
        
        // Construct the add work flow controller and add it to the submit button
        saveWorkflowButton.addActionListener(new SaveWorkflowController(workflowModel));
        
        //adds a stage to the mainPanel
        newStageButton.addActionListener( new AddTabController(taskManagerTabView, TabType.STAGE, workflowModel));
        
        //adds a tab to the mainPanel
        newTaskButton.addActionListener( new AddTabController(taskManagerTabView, TabType.TASK, workflowModel));
        
        // Set the layout manager of this panel that controls the positions of the components
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); // components will  be arranged horizontally
        
        // Adjust sizes and alignments
        saveWorkflowButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add the components to the panel
        add(Box.createHorizontalStrut(20));
        add(saveWorkflowButton);
        add(Box.createHorizontalStrut(20));
        add(newStageButton);
        add(Box.createHorizontalStrut(20));
        add(newTaskButton);
    }

    static long getSerialversionuid() {
        return serialVersionUID;
    }
}