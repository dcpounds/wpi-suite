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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.GitController;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;

public class GitLinkTab  extends JPanel implements KeyListener, IHashableTab{
	private JTextField repositoryURL;
	private JLabel lblVerification;
	public GitLinkTab() {
		setLayout(new MigLayout("", "[grow][][grow]", "[][][][][][][grow][][][][]"));
		
		JLabel lblTitle = new JLabel("Import Github Issues");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblTitle, "cell 0 0");
		
		JLabel lblRepository = new JLabel("Repository");
		lblRepository.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblRepository, "cell 0 1");
		
		JLabel lblHttpgithubcom = new JLabel("http://github.com/");
		add(lblHttpgithubcom, "flowx,cell 0 2");
		
		repositoryURL = new JTextField();
		add(repositoryURL, "cell 0 2");
		repositoryURL.setColumns(10);
		
		GitController controller = new GitController(this);
		JButton importButton = new JButton("Import");
		importButton.addActionListener(controller);
		add(importButton, "flowx,cell 0 3");
		
		lblVerification = new JLabel("Verification");
		lblVerification.setForeground(Color.RED);
		lblVerification.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblVerification, "cell 0 4");
		
		lblVerification.setVisible(false);
	}

	public JLabel getLblVerification() {
		return lblVerification;
	}

	public void setLblVerificationText(String text) {
		this.lblVerification.setText(text);
	}

	public JTextField getRepositoryURL() {
		return repositoryURL;
	}

	public void setRepositoryURL(JTextField repositoryURL) {
		this.repositoryURL = repositoryURL;
	}

	@Override
	public int getModelID() {
		return 0;
	}

	@Override
	public TabType getTabType() {
		return TabType.GIT;
	}

	@Override
	public boolean hasBeenModified() {
		if(repositoryURL.getText().trim().isEmpty()){
			return false;
		}
		else{return true;}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		hasBeenModified();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		hasBeenModified();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		hasBeenModified();
	}

}
