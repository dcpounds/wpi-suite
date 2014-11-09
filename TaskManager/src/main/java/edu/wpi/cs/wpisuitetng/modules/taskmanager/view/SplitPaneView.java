package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;

import javafx.scene.control.SplitPane;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowListModel;


/**
 * @author alec
 *This view creates a split-pane JPanel with the workflowListView on the left
 * and a tabbed view on the right
 */
public class SplitPaneView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8741202156200825035L;

	public SplitPaneView( WorkflowListModel workflowListModel ) {
		this.setLayout( new BorderLayout() );
		
		//Initialize a new split pane
		JSplitPane splitPane = new JSplitPane();
		
		//Initialize the left and right components
		TaskManagerTabView tabView = new TaskManagerTabView();
		WorkflowListView workFlowListView = new WorkflowListView(workflowListModel);
		
		splitPane.setRightComponent(tabView);
		splitPane.setLeftComponent(workFlowListView);
		this.add(splitPane, BorderLayout.CENTER);
	}
}
