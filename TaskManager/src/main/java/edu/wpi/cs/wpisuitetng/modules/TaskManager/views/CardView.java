package edu.wpi.cs.wpisuitetng.modules.TaskManager.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardView extends JPanel {

	public CardView(String title) {
		JLabel cardTitle = new JLabel(title);
		this.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		this.setPreferredSize(new Dimension(600,400));
		this.add(cardTitle);
	}

}
