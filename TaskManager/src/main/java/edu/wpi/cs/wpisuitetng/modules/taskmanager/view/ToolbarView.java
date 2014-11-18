/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabType;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;

/**
 * Class representing the view of the toolbar at the top of the gui
 *
 */
public class ToolbarView extends JPanel {
    
    private static final long serialVersionUID = 6568533963473785570L;
    private final JButton newStageButton;
    private final JButton newTaskButton;
    private final WorkflowModel workflowModel;
   
    /**
     * Creates a new tool bar based off the main workflow model
     * 
     * @param taskManagerTabView - tab panel containing all the tabs
     */
    public ToolbarView() {
        this.workflowModel = WorkflowController.getWorkflowModel();
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		newStageButton = new JButton("New Stage");
		newStageButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addTab(TabType.STAGE, new StageModel(""));
			}
		});
		
        add(Box.createHorizontalStrut(20));
        add(newStageButton);
		
		newTaskButton = new JButton("New Task");
        newTaskButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addTab(TabType.TASK, new StageModel(""));
			}
		});
        add(Box.createHorizontalStrut(20));
        
        add(newTaskButton);
    }

    static long getSerialversionuid() {
        return serialVersionUID;
    }
}