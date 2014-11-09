package edu.wpi.cs.wpisuitetng.modules.TaskManager.views;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author alec
 * This is the pane that contains a list of workflows to switch between
 */
public class WorkflowListView extends JPanel {
	
	public WorkflowListView() {
		JScrollPane workflowListPane = new JScrollPane(); //add the list of workflows as a paramter later
		JLabel title = new JLabel("Workflows");
		this.add(title);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		workflowListPane.setPreferredSize(new Dimension( 200, 600));
		this.add(workflowListPane);
	}

}
