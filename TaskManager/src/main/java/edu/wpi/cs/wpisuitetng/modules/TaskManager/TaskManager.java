package edu.wpi.cs.wpisuitetng.modules.TaskManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;

public class TaskManager implements IJanewayModule {
	private List<JanewayTabModel> tabs;
	
	public TaskManager(){
		tabs = new ArrayList<JanewayTabModel>();
		
		JPanel toolbarPanel = new JPanel();
		toolbarPanel.add(new JLabel("TaskManager toolbar placeholder"));
		toolbarPanel.add(new JButton("Frick's frickin' button"));
		toolbarPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

		toolbarPanel.add(new JButton("GuillermoIsGreat"));

		toolbarPanel.add(new JButton("Nate"));

		
		JPanel mainPanel = new JPanel();
		mainPanel.add(new JLabel("TaskManager placeholder"));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.green, 2));
		
		JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolbarPanel, mainPanel);
		
		tabs.add(tab1);
	}
	
	public String getName(){
		return "TaskManager";
	}

	public List<JanewayTabModel> getTabs(){
		return tabs;
	}
}
