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
 * Dummy Model for unique tabs.
 */
public class DummyTabModel implements IDisplayModel {

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.model.IDisplayModel#getTitle()
	 */
	@Override
	public String getTitle() {
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.model.IDisplayModel#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {

	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.model.IDisplayModel#getID()
	 */
	@Override
	public int getID() {
		return 0;
	}

}
