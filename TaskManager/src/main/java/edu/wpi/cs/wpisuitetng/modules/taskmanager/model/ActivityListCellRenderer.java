package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

public class ActivityListCellRenderer implements ListCellRenderer {
	
	private JPanel panel;
	private JTextArea textArea;
	private JLabel icon;
	private JPanel iconPanel;

	public ActivityListCellRenderer(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		iconPanel = new JPanel(new BorderLayout());
		icon = new JLabel("Icon");
		
		iconPanel.add(icon, BorderLayout.NORTH);
		panel.add(iconPanel, BorderLayout.WEST);
		
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel.add(textArea,  BorderLayout.CENTER);
		
		
	}

	@Override
	public Component getListCellRendererComponent(final JList list, final Object value,
			final int index, final boolean isSelected, final boolean hasFocus) {
		
		final String text = (String) value.toString();
		textArea.setText(text);
		int width = list.getWidth();
		if (width > 0)
			textArea.setSize(width, Short.MAX_VALUE);
		return panel;
	}

}
