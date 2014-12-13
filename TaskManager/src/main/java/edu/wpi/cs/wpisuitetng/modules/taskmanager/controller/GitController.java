package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;

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
	
	public void getIssuesFromRepo(GitHubClient client, String repo){
		List<Issue> issues = new ArrayList<Issue>();
		IssueService issueService = new IssueService(client);
		try {
			issues = issueService.getIssues(client.getUser(), repo, null);
		} catch (IOException e) {
			System.out.println("Error getting issues");
			e.printStackTrace();
		}
		
		System.out.println("\n\nRepo is " + repo);
		System.out.println("Got issues:");
		for(Issue issue : issues){
			System.out.println(issue.getTitle());
		}
	}
	
	public void editIssue(GitHubClient client, String title, String body, String state) throws IOException {
		IssueService issueService = new IssueService(client);
		Issue issue = new Issue();
		issue.setNumber(1);
		issue.setTitle(title);
		issue.setBody(body);
		issue.setState(state);
		issueService.editIssue(client.getUser(), gitTab.getRepositoryName().getText(), issue);
		Map<String,String> params = new HashMap<String,String>();
		params.put(IssueService.FIELD_TITLE, title);
		params.put(IssueService.FIELD_BODY, body);
		params.put(IssueService.FILTER_STATE, state);
	}
	
	/**
	 * Create the connection using the provided credentials
	 */
	private GitHubClient authenticate(GitHubClient client){
		String repoName = gitTab.getRepositoryName().getText();
		try{
			client.setCredentials(gitTab.getUsernameField().getText(), gitTab.getPassField().getText());
		}catch(Exception e){
			e.printStackTrace();
			setSuccessMessage("Invalid username or password provided");
			return null;
		}
		setSuccessMessage("Successfully connected to repository!");
		getIssuesFromRepo(client, repoName);
		//makeIssue(client, "Title", "Body", "State");
		return client;
	}
	
	/**
	 * Create a new github client using the provided repo url
	 * @return the fully authenticated client
	 * @throws IOException
	 */
	public GitHubClient createClient() throws IOException {
		GitHubClient client = null;
		String repoName = gitTab.getRepositoryName().getText();
		if(!repoName.isEmpty()){
			try{
				URL parsedUrl = new URL("https://api.github.com");
				client = new GitHubClient(parsedUrl.getHost(), parsedUrl.getPort(), parsedUrl.getProtocol());
			}catch(Exception e){
				setSuccessMessage("Please enter a valid URL.");
				e.printStackTrace();
			}
		} else
			client = new GitHubClient();
		return authenticate(client);
	}
	
	private void setSuccessMessage(String message){
		gitTab.getLblVerification().setVisible(true);
		gitTab.getLblVerification().setText(message);
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
