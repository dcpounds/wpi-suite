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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.*;


/**
 * @author Ashton
 * This class provides a combobox that allows task categories to be set
 */
public class ColorComboBox extends JComboBox {
	
	private static final long serialVersionUID = 7202220072831533595L;

	static Hashtable <String, Color> colors;
	
	private Color selectedColor= Color.WHITE;

	public ColorComboBox() {
	super();
	DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
	Enumeration colorNames = addColors().keys();
	while(colorNames.hasMoreElements()){
		String tString = colorNames.nextElement().toString();
		cbModel.addElement(tString);
		System.out.println("colors"+tString);
	}
	setModel(cbModel);  
	setRenderer(new ColorRenderer());  
	this.setOpaque(true);  
	this.setSelectedIndex(0);  
	}  
	
	
	@Override
	public void setSelectedItem(Object object){
		super.setSelectedItem(object);
	
	selectedColor = colors.get(object);
	setBackground(selectedColor);
	setFont(new Font(Font.SERIF, Font.BOLD,15 ));
	if(object.toString().equals("YELLOW")|object.toString().equals("WHITE")){
			setForeground(Color.black);
	}
	else setForeground(Color.white);
	
	}
	
	public Color getSelectedColor(){
	return this.getBackground();
	}
	
	private Hashtable addColors(){
		colors = new<String, Color> Hashtable();
		colors.put("GRAY", Color.LIGHT_GRAY);
		colors.put("WHITE", Color.WHITE);
		colors.put("BROWN", new Color(0xA67C52));
		colors.put("RED", new Color(0xF7977A));
		colors.put("PINK", new Color(0xF49AC2));
		colors.put("ORANGE", new Color(0xFDC68A));
		colors.put("YELLOW", new Color(0xFFF79A));
		colors.put("GREEN", new Color(0x82CA9D));
		colors.put("BLUE", new Color(0x8493CA));
		colors.put("PURPLE", new Color(0xA187BE));
		
		
		return colors;
	}

	
	class ColorRenderer extends JLabel implements javax.swing.ListCellRenderer{
		public ColorRenderer(){
			this.setOpaque(true);
		}
		public Component getListCellRendererComponent(JList list, Object key, int index,
				boolean isSelected, boolean isFocused){
			Color color = colors.get(key);
			String name = key.toString();
		
			list.setSelectionBackground(null);
			list.setSelectionForeground(null);
			
			if(isSelected){
				setBorder(BorderFactory.createEtchedBorder());
			}
			else setBorder(null);
			
			setFont(new Font(Font.SERIF, Font.BOLD,15 ));
			setBackground(color);
			setText(name);
			setForeground(Color.black);
			if(key.toString().equals("YELLOW")|key.toString().equals("WHITE")){
				setForeground(Color.black);
			}
			else setForeground(Color.white);
		
			
			
			return this;
				
				
		
		}
	}
	
	
	
}
