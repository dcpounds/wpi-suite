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

import javax.swing.ImageIcon;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * model for a tab that can be closed
 *
 */
public class ClosableTabModel extends AbstractModel {
	private String tabTitle;
	private ImageIcon icon;
	private int index;
	
	/**
	 * @param tabTitle - the title of the icon
	 * @param icon - the ImageIcon to use
	 */
	public ClosableTabModel(String tabTitle, ImageIcon icon, int index) {
		this.tabTitle = tabTitle;
		this.icon = icon;
		this.index = index;
	}
	
	public ClosableTabModel(String tabTitle, int index) {
		this.tabTitle = tabTitle;
		icon = new ImageIcon();
		this.index = index;
	}
	
	public ClosableTabModel(String tabTitle) {
		this.tabTitle = tabTitle;
		icon = new ImageIcon();
	}
	
	public ClosableTabModel() {
		tabTitle = null;
		icon = new ImageIcon();
	}
	
	/**
	 * @return the icon associated with this closable tab
	 */
	public ImageIcon getImageIcon() {
		return icon;
	}
	
	/**
	 * @return the title of this tab
	 */
	public String getTabTitle(){
		return tabTitle;
	}
	
	/** Set the title of this tab
	 * @param tabTitle
	 */
	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle; 
	}
	
	/** Get the index of this tab
	 * @return
	 */
	public int getTabIndex() {
		return index;
	}
	
	/** 
	 * Set the index of this tab
	 * @param index
	 */
	public void setTabIndex(int index) {
		this.index = index;
	}
	
	/**
	 * Set the icon for this tab
	 * @param path
	 */
	public void setImageIcon(String path){
		icon = new ImageIcon(path);
	}
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
