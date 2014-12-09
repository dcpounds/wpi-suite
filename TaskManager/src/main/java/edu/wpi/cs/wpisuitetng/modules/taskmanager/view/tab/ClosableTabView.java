/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.ClosableTabModel;

/**
 * @author Alec
 * This is the panel that goes inside a tab to make it closable
 */
public class ClosableTabView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8561782795446219647L;
	private final JLabel tabLabel;
	private ClosableTabModel tabModel;
	private final TabView view;
	private final JButton closeButton;
	
	/**
	 * construct the panel to make a tab closable
	 * 
	 * @param tabModel - the tab's model
	 * @param paneComponent - the child component that this tab is attached to
	 */
	public ClosableTabView(ClosableTabModel tabModel, IHashableTab paneComponent, TabType tabType){
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.view = TabController.getTabView();
		tabLabel = new JLabel(tabModel.getTabTitle());
		tabLabel.putClientProperty("html.disable", Boolean.TRUE);
		setBorder(BorderFactory.createEmptyBorder(3, 0, 2, 7));
		setOpaque(false);
		
		this.closeButton = new JButton("\u2716");
		closeButton.setFont(closeButton.getFont().deriveFont((float) 8));
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		closeButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().removeTab(paneComponent);
			}
		});
		
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
	public TabView getView() {
		return view;
	}

}
