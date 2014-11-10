package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.CloseableTabController;

/**
 * @author alec
 * This is the panel that goes inside a tab
 */
public class TabView extends JPanel{
	private final String tabTitle;
	private final JLabel tabLabel;
	private final TaskManagerTabView view;
	private int index;
	private final JButton closeButton;
	
	public TabView(TaskManagerTabView view, String tabTitle) {
		this.view = view;
		this.index = view.getTabCount();
		this.tabTitle = tabTitle;
		tabLabel = new JLabel(tabTitle);
		this.closeButton = new JButton("X");
		closeButton.addActionListener( new CloseableTabController(view, index) );
		
		this.add(tabLabel);
		this.add(closeButton);
	}

	public String getTabTitle() {
		return tabTitle;
	}

}
