package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;

/**
 * @author alec
 * This is the panel that goes inside a tab to make it closable
 */
public class ClosableTabView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8561782795446219647L;
	private final JLabel tabLabel;
	private ClosableTabModel tabModel;
	private final TaskManagerTabView view;
	private final JButton closeButton;
	
	/**
	 * @param view - the parent pane that houses all the tabs
	 * @param tabModel - the tab's model
	 * @param paneComponent - the child component that this tab is attached to
	 */
	public ClosableTabView(TaskManagerTabView view, ClosableTabModel tabModel, Component paneComponent) {
		this.view = view;
		tabLabel = new JLabel(tabModel.getTabTitle());
		
		this.closeButton = new JButton("X");
		closeButton.addActionListener( new RemoveTabController(view, paneComponent) );
		
		this.add(tabLabel);
		this.add(closeButton);
	}

	public ClosableTabModel getTabModel() {
		return tabModel;
	}

	public TaskManagerTabView getView() {
		return view;
	}

}
