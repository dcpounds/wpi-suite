package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.ClosableTabController;
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
	
	public ClosableTabView(TaskManagerTabView view, ClosableTabModel tabModel) {
		this.view = view;
		tabLabel = new JLabel(tabModel.getTabTitle());
		
		this.closeButton = new JButton("X");
		closeButton.addActionListener( new ClosableTabController(view, tabModel) );
		
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
