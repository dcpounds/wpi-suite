package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

/**
 * @author Nathaniel Jefferson
 * This Interface is used when dynamically setting the title of the tab.
 * Classes that implement it must have a getTitle() field. 
 */
public interface IDisplayModel {
	
	/**
	 * @return Title of model
	 */
	public String getTitle();
	
	/**
	 * Set the title of model
	 */
	public void setTitle(String title);
	
	/**
	 * Get the ID of the model
	 */
	public int getID();
	
}
