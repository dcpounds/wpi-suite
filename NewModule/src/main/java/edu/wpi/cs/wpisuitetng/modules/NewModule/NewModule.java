package edu.wpi.cs.wpisuitetng.modules.NewModule;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;

public class NewModule implements IJanewayModule {
	private List<JanewayTabModel> tabs;
	
	public NewModule(){
		tabs = new ArrayList<JanewayTabModel>();
		
		JPanel toolbarPanel = new JPanel();
		toolbarPanel.add(new JLabel("NewModule toolbar placeholder"));
		toolbarPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(new JLabel("NewModule placeholder"));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.green, 2));
		
		JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolbarPanel, mainPanel);
		
		tabs.add(tab1);
	}
	
	public String getName(){
		return "NewModule";
	}

	public List<JanewayTabModel> getTabs(){
		return tabs;
	}
}