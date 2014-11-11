package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.db4o.User;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.UserModel;

public class UserIconView extends JPanel {
	private Color backGroundColor;
	private Color bgColor;
	private JLabel charLabel;
	

	public UserIconView(UserModel userModel) {
		JPanel iconPanel = new JPanel();
		iconPanel.setLayout(new BorderLayout());
		charLabel = new JLabel( userModel.getIconText() + "" );
		
		
		//16777215 is pure white, this will generate a color inbetween based on the
		//given character
		this.bgColor = new Color( 16777215 * (userModel.getIconText()/90) );
		
		iconPanel.setOpaque(true);
		iconPanel.setBackground(bgColor);
		iconPanel.setPreferredSize(new Dimension(32,32));
		iconPanel.add(charLabel);
		this.add(iconPanel);
		
	}
}
