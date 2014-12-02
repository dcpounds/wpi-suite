/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.TabType;

import java.awt.Component;

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
        
        ImageIcon stageIcon = new ImageIcon("../TaskManager/src/main/resources/new_req.png");
        ImageIcon taskIcon = new ImageIcon("../TaskManager/src/main/resources/new_itt.png");
        
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        newStageButton = new JButton("New Stage", stageIcon);
        newStageButton.addActionListener( new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		TabController.getInstance().addTab(TabType.STAGE ,null);
        	}
        });
        newStageButton.setMargin(new Insets(0,0,0,0));
        add(Box.createHorizontalStrut(20));
        add(newStageButton);
		
		newTaskButton = new JButton("New Task", taskIcon);
        newTaskButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().addTab(TabType.TASK, null);
			}
		});
        newTaskButton.setMargin(new Insets(0,0,0,0));
        
        add(Box.createHorizontalStrut(20));
        add(newTaskButton);
        
//        Component horizontalStrut = Box.createHorizontalStrut(20);
//        add(horizontalStrut);
//        
//        JButton toggleColor = new JButton("Toggle Color", null);
//        toggleColor.setMargin(new Insets(0, 0, 0, 0));
//        toggleColor.addActionListener(new ActionListener(){
//        	 @Override
//     		public void actionPerformed(ActionEvent e) {
//     			TaskModel.toggleColor();
//     			System.out.println("button pressed");
//     		}
//        });
//        add(toggleColor);
       
    }

    static long getSerialversionuid() {
        return serialVersionUID;
    }
}