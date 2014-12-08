package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

public interface IHashableTab {
	
	/**
	 * get the id of the model for the current tab
	 * 
	 * @return
	 */
	public int getModelID();
	
	/**
	 * get the tab type for the current tab
	 * 
	 * @return
	 */
	public TabType getTabType();
}
