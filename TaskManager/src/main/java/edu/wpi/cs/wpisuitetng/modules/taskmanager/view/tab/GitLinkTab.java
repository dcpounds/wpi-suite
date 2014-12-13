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

public class GitLinkTab  extends JPanel implements KeyListener, IHashableTab{
	private JTextField repositoryURL;
	private JTextField usernameField;
	private JTextField passField;
	private JLabel lblVerification;
	public GitLinkTab() {
		setLayout(new MigLayout("", "[grow][][grow]", "[][][][][][][][][][]"));
		
		JLabel lblTitle = new JLabel("Link With A Github Repository");
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
		JButton linkButton = new JButton("Link");
		linkButton.addActionListener(controller);
		
		JLabel lblUsername = new JLabel("Username:");
		add(lblUsername, "cell 0 4");
		
		usernameField = new JTextField();
		add(usernameField, "cell 0 5");
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		add(lblPassword, "cell 0 6");
		
		passField = new JPasswordField();
		add(passField, "cell 0 7");
		passField.setColumns(10);
		add(linkButton, "flowx,cell 0 8");
		
		lblVerification = new JLabel("Verification");
		lblVerification.setForeground(Color.RED);
		lblVerification.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblVerification, "cell 0 9");
		
		JButton unlinkButton = new JButton("Unlink");
		add(unlinkButton, "cell 0 8");
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

	public JTextField getUsernameField() {
		return usernameField;
	}

	public void setUsernameField(JTextField usernameField) {
		this.usernameField = usernameField;
	}

	public JTextField getPassField() {
		return passField;
	}

	public void setPassField(JTextField passField) {
		this.passField = passField;
	}

	@Override
	public int getModelID() {
		return this.hashCode();
	}

	@Override
	public TabType getTabType() {
		return TabType.GIT;
	}

	@Override
	public boolean hasBeenModified() {
		return false;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
