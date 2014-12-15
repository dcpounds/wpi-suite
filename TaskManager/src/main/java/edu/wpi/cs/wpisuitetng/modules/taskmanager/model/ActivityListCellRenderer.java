/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityModel;

public class ActivityListCellRenderer implements ListCellRenderer {
	
	private JPanel panel;
	private JTextArea textArea;
	private JPanel sidePanel;
	private JSeparator separator;
	private JLabel stampLabel;

	public ActivityListCellRenderer(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		sidePanel = new JPanel(new BorderLayout());
		sidePanel.setOpaque(true);
		sidePanel.setBackground(Color.white);
		stampLabel = new JLabel();
		stampLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		sidePanel.add(stampLabel, BorderLayout.NORTH);
		
		panel.add(sidePanel, BorderLayout.WEST);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel.add(textArea,  BorderLayout.CENTER);
	}

	@Override
	public Component getListCellRendererComponent(final JList list, final Object value,
			final int index, final boolean isSelected, final boolean hasFocus) {
		
		ActivityModel activity = ((ActivityModel)value);
		stampLabel.setText(activity.getStamp());
		textArea.setText(activity.getMessage());
		
		int width = list.getWidth() - sidePanel.getWidth();
		if (width > 0){
			textArea.setSize(width, Short.MAX_VALUE);
		}
		return panel;
	}

}
