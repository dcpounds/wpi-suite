package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Font;

/**
 * This view is responsible for rendering a card that can be placed inside a workflow.
 *
 */
public class CardView extends JPanel {
	private JScrollPane scrollPane;
	private JPanel cardPane;
	private String title;

	/**
	 * Constructs a new Card
	 * @param title - the title of this card
	 */
	public CardView(String title) {

		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		this.cardPane = new JPanel();
		this.scrollPane = new JScrollPane(cardPane);
		
		JLabel cardTitle = new JLabel(title);
		cardTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		cardPane.setBorder(new LineBorder(Color.GRAY, 2, true));
		cardPane.setPreferredSize(new Dimension(250,500));
		this.add(cardTitle, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addTaskView(TaskView taskView) {
		cardPane.add(taskView);
	}

}