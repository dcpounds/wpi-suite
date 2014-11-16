/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.SaveWorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;

/**
 * Class representing the view of the toolbar at the top of the gui
 *
 */
public class ToolbarView extends JPanel {
    
    private static final long serialVersionUID = 6568533963473785570L;
    private final JButton saveWorkflowButton;
    private final JButton newStageButton;
    private final JButton newTaskButton;
    private final WorkflowModel workflowModel;
   
    /**
     * Creates a new tool bar
     * 
     * @param model - main work flow
     * @param taskManagerTabView - tab panel containing all the tabs
     */
    public ToolbarView(WorkflowModel model, TaskManagerTabView taskManagerTabView) {
        this.workflowModel = model;
        
        ImageIcon testIcon = new ImageIcon("C:/Users/Will/Pictures/testIcon.png");
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        
  		saveWorkflowButton = new JButton("Save Workflow");
		saveWorkflowButton.addActionListener(new SaveWorkflowController(workflowModel));
        add(Box.createHorizontalStrut(20));
        add(saveWorkflowButton);
		
		newStageButton = new JButton("New Stage");
        newStageButton.addActionListener( new AddTabController(taskManagerTabView, TabType.STAGE, workflowModel));
        add(Box.createHorizontalStrut(20));
        add(newStageButton);
		
		newTaskButton = new JButton("New Task",testIcon);
        newTaskButton.addActionListener( new AddTabController(taskManagerTabView, TabType.TASK, workflowModel));
        add(Box.createHorizontalStrut(20));
        add(newTaskButton);
    }

    static long getSerialversionuid() {
        return serialVersionUID;
    }
}