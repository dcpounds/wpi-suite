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

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;

/**
 * @author Nathaniel Jefferson
 *
 */
public class ClosableTabModelTest {

	@Test
	public void ClosableTabModeltest() {
		ClosableTabModel tab = new ClosableTabModel();
		ClosableTabModel tab1 = new ClosableTabModel("tab");
		ClosableTabModel tab2 = new ClosableTabModel("tab2", 0);
		ImageIcon img = new ImageIcon();
		ClosableTabModel tab3 = new ClosableTabModel("tab3",img,1);
		
		tab.setTabTitle("tab");
		tab.setTabIndex(3);
		tab.setImageIcon("hello.png");
		
		assertEquals("tab",tab1.getTabTitle());
		assertEquals(0,tab2.getTabIndex());
		assertTrue(tab3.getImageIcon().equals(img));
	}

}
