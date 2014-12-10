/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
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
