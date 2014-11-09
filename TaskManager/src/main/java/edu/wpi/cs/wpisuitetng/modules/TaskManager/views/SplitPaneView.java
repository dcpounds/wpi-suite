package edu.wpi.cs.wpisuitetng.modules.TaskManager.views;

import java.awt.BorderLayout;

import javafx.scene.control.SplitPane;

import javax.swing.JPanel;
import javax.swing.JSplitPane;


/**
 * @author alec
 *This view creates a split-pane JPanel with the workflowListView on the left
 * and a tabbed view on the right
 */
public class SplitPaneView extends JPanel{
	
	public SplitPaneView() {
		this.setLayout( new BorderLayout() );
		
		//Initialize a new split pane
		JSplitPane splitPane = new JSplitPane();
		
		//Initialize the left and right components
		TaskManagerTabView tabView = new TaskManagerTabView();
		WorkflowListView workFlowListView = new WorkflowListView();
		
		splitPane.setRightComponent(tabView);
		splitPane.setLeftComponent(workFlowListView);
		this.add(splitPane, BorderLayout.CENTER);
	}
}
