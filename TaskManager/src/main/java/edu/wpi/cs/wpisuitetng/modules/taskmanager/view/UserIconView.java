package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.db4o.User;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;

public class UserIconView extends JPanel {
	
	private static final long serialVersionUID = -3320166719759544745L;
	private JLabel charLabel;
	
	public UserIconView(UserModel userModel) {
		setBorder(new LineBorder(Color.BLUE, 1, true));
		
		JPanel iconPanel = new JPanel();
		charLabel = new JLabel(userModel.getUsername());
		
		iconPanel.setLayout(new BorderLayout());;
		iconPanel.add(charLabel, BorderLayout.CENTER);
		this.add(iconPanel);
		
	}
}
