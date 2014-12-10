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

import javax.swing.JPanel;

/**
 * @author ??
 * An abstract class for tabs that forces tabs to implement has been modified
 *
 */
public abstract class AbstractTab extends JPanel{
	private static final long serialVersionUID = -7630763480349080421L;

	/**
	 * By default, the tab has been modified. Specific tabs will override this depending on the implementation
	 * @return
	 */
	public boolean hasBeenModified(){
		return true;
	}
}
