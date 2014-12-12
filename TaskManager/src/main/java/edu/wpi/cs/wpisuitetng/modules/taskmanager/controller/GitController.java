package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import org.eclipse.egit.github.core.client.GitHubClient;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.GitLinkTab;

/**
 * @author Alec
 * Integrates github with TaskManager
 */
public class GitController implements ActionListener {
	private GitLinkTab gitTab;

	public GitController(GitLinkTab gitTab){
		this.gitTab = gitTab;
	}
	
	/**
	 * Create the connection using the provided credentials
	 */
	private GitHubClient authenticate(GitHubClient client){
		try{
			client.setCredentials(gitTab.getUsernameField().getText(), gitTab.getPassField().getText());
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Successfully created client");
		return client;
	}
	
	/**
	 * Create a new github client using the provided repo url
	 * @return the fully authenticated client
	 * @throws IOException
	 */
	public GitHubClient createClient() throws IOException {
		GitHubClient client = null;
		String urlText = gitTab.getRepositoryURL().getText();
		if(!urlText.isEmpty()){
			URL parsedUrl = new URL(urlText);
			client = new GitHubClient(parsedUrl.getHost(), parsedUrl.getPort(), parsedUrl.getProtocol());
		} else
			client = new GitHubClient();
		return authenticate(client);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			createClient();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
