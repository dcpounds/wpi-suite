/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

/**
 * @author ??
 * This interface allows tabs to be hashed
 */
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
	
	public boolean hasBeenModified();
}
