package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This view is responsible for rendering a card that can be placed inside a workflow.
 *
 */
public class CardView extends JPanel {

	public CardView(String title) {
		JLabel cardTitle = new JLabel(title);
		this.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		this.setPreferredSize(new Dimension(300,600));
		this.add(cardTitle);
	}

}