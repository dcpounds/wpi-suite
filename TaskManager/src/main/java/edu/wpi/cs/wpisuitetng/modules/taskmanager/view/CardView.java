package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import java.awt.CardLayout;

import net.miginfocom.swing.MigLayout;

/**
 * This view is responsible for rendering a card that can be placed inside a workflow.
 *
 */
public class CardView extends JPanel {
	private String title;
	private JPanel cardPane;
	private JScrollPane scrollPane;
	private static int defaultHeight = 250;
	private static int defaultWidth = 600;

	/**
	 * Constructs a new Card
	 * @param title - the title of this card
	 */
	public CardView(String title) {
		this.cardPane = new JPanel();
		setPreferredSize(new Dimension( defaultHeight, defaultWidth) );
		cardPane.setPreferredSize( new Dimension( defaultHeight - 20, defaultWidth - 20) );
		scrollPane = new JScrollPane(cardPane);
		cardPane.setLayout(new BoxLayout(cardPane, BoxLayout.Y_AXIS));
		add(scrollPane);
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addTaskView(TaskContainerView taskView) {
		cardPane.add(taskView);
		revalidate();
		repaint();
	}
	
	public void removeTaskView(TaskContainerView taskView) {
		cardPane.remove(taskView);
		revalidate();
		repaint();	
	}

}