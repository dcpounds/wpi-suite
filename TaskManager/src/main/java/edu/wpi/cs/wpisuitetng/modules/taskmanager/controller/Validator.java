package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Validator implements KeyListener, MouseListener{
	Component[] requiredComponents;
	JButton submit;
	
	public Validator(JButton submit, Component... requiredComponents){
		this.submit = submit;
		this.requiredComponents = requiredComponents;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		submit.setEnabled( validateFields() );
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean validateFields(){
		System.out.println("Run");
		for( Component component : requiredComponents ){
			
			//If component is a JLabel
			if( component instanceof JLabel ){
				if(((JLabel) component).getText().isEmpty())
					return false;
			}
			
			//If component is a JTextArea
			if( component instanceof JTextArea){
				if( ((JTextArea) component).getText().isEmpty() )
					return false;
			}
			
			if( component instanceof JTextField ){
				if( ((JTextField) component).getText().isEmpty() )
					return false;
			}
		}
		System.out.println("Fields passed validation");
		return true;
	}

}
