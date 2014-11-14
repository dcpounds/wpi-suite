package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
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
	 * construct the panel to make a tab closable
	 * 
	 * @param view - the parent pane that houses all the tabs
	 * @param tabModel - the tab's model
	 * @param paneComponent - the child component that this tab is attached to
	 */
	public ClosableTabView(TaskManagerTabView view, ClosableTabModel tabModel, Component paneComponent){
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.view = view;
		tabLabel = new JLabel(tabModel.getTabTitle());
		setBorder(BorderFactory.createEmptyBorder(3, 0, 2, 7));
		setOpaque(false);
		
		this.closeButton = new JButton("\u2716");
		closeButton.setFont(closeButton.getFont().deriveFont((float) 8));
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		closeButton.addActionListener( new RemoveTabController(view, paneComponent) );
		
		this.add(tabLabel);
		this.add(closeButton);
	}

	/**
	 * get the model of the tab that can be closed
	 * 
	 * @return
	 */
	public ClosableTabModel getTabModel() {
		return tabModel;
	}

	/**
	 * get the view that contains all the tabs
	 * 
	 * @return
	 */
	public TaskManagerTabView getView() {
		return view;
	}

}
