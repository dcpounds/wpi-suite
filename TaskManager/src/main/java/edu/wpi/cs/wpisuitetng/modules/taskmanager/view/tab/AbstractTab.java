package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

import javax.swing.JPanel;

import com.sun.xml.internal.ws.api.Component;

public abstract class AbstractTab extends JPanel{

	public boolean hasBeenModified(){
		return true;
	}
}
