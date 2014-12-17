/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.RepositoryIssue;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.service.IssueService;

import com.google.gson.reflect.TypeToken;

import static org.eclipse.egit.github.core.client.IGitHubConstants.SEGMENT_ISSUES;
import static org.eclipse.egit.github.core.client.IGitHubConstants.SEGMENT_REPOS;
import static org.eclipse.egit.github.core.client.PagedRequest.PAGE_FIRST;
import static org.eclipse.egit.github.core.client.PagedRequest.PAGE_SIZE;
import static org.eclipse.egit.github.core.client.IGitHubConstants.SEGMENT_COMMENTS;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityListModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.GitLinkTab;

/**
 * @author Alec
 * Integrates github with TaskManager
 */
/**
 * @author Alec
 *
 */
public class GitController extends IssueService implements ActionListener {
	private GitLinkTab gitTab;

	public GitController(GitLinkTab gitTab){
		this.gitTab = gitTab;
	}
	
	/**
	 * Get a specific issue from github
	 * @param issueNumber
	 * @return
	 * @throws IOException
	 */
	private Issue getIssue(String issueNumber) throws IOException {
		if (issueNumber == null)
			throw new IllegalArgumentException("Issue number cannot be null"); //$NON-NLS-1$
		if (issueNumber.length() == 0)
			throw new IllegalArgumentException("Issue number cannot be empty"); //$NON-NLS-1$

		StringBuilder uri = new StringBuilder(SEGMENT_REPOS);
		//We need to append the path to the repository that the user provided
		uri.append('/').append(gitTab.getRepositoryURL().getText());
		uri.append(SEGMENT_ISSUES);
		uri.append('/').append(issueNumber);
		GitHubRequest request = createRequest();
		request.setUri(uri);
		request.setType(Issue.class);
		return (Issue) client.get(request).getBody();
	}
	
	/**
	 * Retrieves all issues from the github repository
	 * @return a list of repositoryIssues
	 * @throws IOException
	 */
	public List<RepositoryIssue> getAllIssues(GitHubClient client) throws IOException {
		List<RepositoryIssue> issues = new ArrayList<RepositoryIssue>();
		try{
			issues = super.getAll(this.pageIssues(client));
			createTasks(issues);
		}catch (Exception e){
			String msg = e.getMessage();
			if(msg != null && !msg.isEmpty())
				setSuccessMessage("Failed to get issues from the repository: " + msg);
			else
				setSuccessMessage("Failed to get issues from the repository.");
			
			e.printStackTrace();
			return issues;
		}
		
		setSuccessMessage("Successfully imported issues from the repository!");
		return issues;
	}
	
	/**
	 * This method is used to iterate through all of the issues and retrieve them all
	 * @return a pageIterator for pageIssues
	 */
	public PageIterator<RepositoryIssue> pageIssues(GitHubClient client) {
		PagedRequest<RepositoryIssue> request = createPagedRequest(PAGE_FIRST, PAGE_SIZE);
		StringBuilder uri = new StringBuilder(SEGMENT_REPOS);
		//We need to append the path to the repository that the user provided
		uri.append('/').append(gitTab.getRepositoryURL().getText());
		uri.append(SEGMENT_ISSUES);
		request.setUri(uri);
		request.setType(new TypeToken<List<RepositoryIssue>>() {
		}.getType());
		return createPageIterator(request);
	}
	
	/**
	 * Create a new github client using the provided credentials
	 * @return the fully authenticated client
	 * @throws IOException
	 */
	public GitHubClient createClient() throws IOException {
		GitHubClient client = null;
			try{
				URL parsedUrl = new URL("https://api.github.com");
				client = new GitHubClient(parsedUrl.getHost(), parsedUrl.getPort(), parsedUrl.getProtocol());
			}catch(Exception e){
				setSuccessMessage("Failed to create a client: " + e.getMessage());
				e.printStackTrace();
			}
		getAllIssues(client);
		return client;
	}
	
	/**
	 * Makes an activity list from the comments
	 * @param issue
	 * @return the activitylistmodel
	 */
	public ActivityListModel makeActivities(Issue issue){
		ActivityListModel activityList = new ActivityListModel();
		List<Comment> commentList = new ArrayList<Comment>();
		
		try {
			commentList = this.getComments(gitTab.getRepositoryURL().getText(), Integer.toString(issue.getNumber()) );
			System.out.println("Successfully fetched comments");
		} catch (IOException e) {
			System.out.println("Error fetching comments");
			e.printStackTrace();
		}
		
		for(Comment comment : commentList){
			ActivityModel activity = new ActivityModel(comment.getBody());
			activity.setDate(comment.getCreatedAt());
			activity.setUser(comment.getUser().getLogin());
			activityList.addActivity(activity);
		}
		return activityList;
	}
	
	/**
	 * Get an issue's comments
	 *
	 * @param repository
	 * @param issueNumber
	 * @return list of comments
	 * @throws IOException
	 */
	private List<Comment> getComments(String repoId, String issueNumber)
			throws IOException {
		if (issueNumber == null)
			throw new IllegalArgumentException("Issue number cannot be null"); //$NON-NLS-1$
		if (issueNumber.length() == 0)
			throw new IllegalArgumentException("Issue number cannot be empty"); //$NON-NLS-1$

		StringBuilder uri = new StringBuilder(SEGMENT_REPOS);
		uri.append('/').append(repoId);
		uri.append(SEGMENT_ISSUES);
		uri.append('/').append(issueNumber);
		uri.append(SEGMENT_COMMENTS);
		PagedRequest<Comment> request = createPagedRequest();
		request.setUri(uri);
		request.setType(new TypeToken<List<Comment>>() {
		}.getType());
		return getAll(request);
	}
	
	/**
	 * Create a task from an issue and add it to the workflow
	 * @param issue
	 */
	public void createTasks(List<RepositoryIssue> issues){
		HashMap<Integer,StageModel> stageList = WorkflowController.getWorkflowModel().getStageModelList();
		HashMap<Integer,StageModel> stagesToUpdate = new HashMap<Integer, StageModel>();
		String tag = "{TM}";
		
		for(RepositoryIssue issue : issues){
			//Skip if the task is not tagged
			if(!issue.getTitle().contains(tag))
				continue;
			
			TaskModel task = new TaskModel();
			task.setTitle(issue.getTitle().replace(tag,""));
			task.setDescription(issue.getBody());
			int issueID = new BigDecimal(issue.getId()).intValueExact();
			task.setID(issueID);
			task.setCreator(issue.getUser().getName());
			task.setCatID(1);
			task.setActivities(this.makeActivities(issue));
			task.setCatColor(Color.WHITE);
			
			Format formatter = new SimpleDateFormat("MM/dd/yyyy");
			task.setDueDate(formatter.format(issue.getCreatedAt()));
			
			StageModel stage = StageController.locateTaskStage(issueID);
			stage = stage == null ? (StageModel) stageList.values().toArray()[0] : stage;
			StageView stageView = TabController.getTabView().getWorkflowView().getStageViewList().get(stage.getID());
			task.setStageID(stage.getID());
			
			TaskView taskView = new TaskView(task,stageView);
			TaskModel existing = stage.getTaskModelList().get(task.getID());
			if(existing != null){
				taskView = stageView.getTaskViewList().get(task.getID());
				task.setIsArchived(existing.getIsArchived());
				taskView.setContents(task);
			}
			
			stage.addTaskModel(task);
			stageView.addTaskView(taskView);
			stagesToUpdate.put(stage.getID(), stage);
			DataLoggerController.getDataModel().addSnapshot(task);
		}
		
		for(StageModel stage : stagesToUpdate.values()){
			StageController.sendUpdateRequest(stage);
		}
	}
	
	/**
	 * Set the success/failure messaage in the tab view
	 * @param message
	 */
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
