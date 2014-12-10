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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.DateLabelFormatter;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.ActivityModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.SearchController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;



import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


/**
 * This is a tab for creating new tasks
 *
 *  11/16 Further Progress on utilizing this class for editting functionality aswell.
 * 		Authors  Guillermo, Ashton;
 */
public class NewTaskTab extends JPanel implements KeyListener, MouseListener, ActionListener, IHashableTab{
	
	private static final long serialVersionUID = -8772773694939459349L;
	private TaskModel taskModel;
	private JTextField taskTitleField;
	private JTextField estEffortField;
	private JTextField actEffortField;
	private JComboBox<String> stageBox;
	private JTextField daysUntilField;
	private JLabel taskDescriptionLabel;
	private JTextArea taskDescriptionField;
    private final WorkflowModel workflowModel;
    private JLabel dateDue;
    private UtilDateModel dateModel;
    private JDatePanelImpl datePanel; 
    private JDatePickerImpl datePicker;  
    private JButton sbmtTaskButton;
	private JLabel titleEmptyError;
	private JLabel estEffortError;
	private JLabel actEffortError;
	private JLabel daysUntilError;
	private JLabel descriptionEmptyError;
	private JLabel dateNotAddedError;
	private JScrollPane descriptionScrollPane;
	private AssignUsersView assignUsersView;
	private ActionType action;
	private boolean isEditingTask;
	private JLabel colorTitle;
	private ColorComboBox colorBox;
	
	/**
	 * contructs a tab for creating tasks
	 * 
	 * @param taskManagerTabView - the main view that holds tabs
	 */

	public NewTaskTab(TaskModel model) {
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][]"));
		JLabel taskTitleLabel = new JLabel("Task Title(*)");
		add(taskTitleLabel, "flowx,cell 0 0");
		this.workflowModel = WorkflowController.getWorkflowModel();
		
		
		//Decide what action the user is taking	
		if(model == null){
			this.taskModel = new TaskModel();
			this.action = ActionType.CREATE;
			this.isEditingTask = false;
		} else{
			this.taskModel = model;
			this.action = ActionType.EDIT;
			this.isEditingTask = true;
		}
		
		//Set an error if the task needs a title
		titleEmptyError = new JLabel("Task Needs A Title");
		titleEmptyError.setForeground(Color.red);
		add(titleEmptyError, "flowx,cell 0 0");
		titleEmptyError.setVisible(false);
		
		//Set the task title
		taskTitleField = new JTextField();
		taskTitleField.setText(this.taskModel.getTitle());
		add(taskTitleField, "flowx,cell 0 1,alignx left");
		taskTitleField.setColumns(35);
		taskTitleField.addKeyListener(this);
		
		//Let the user decide where to put the task
		JLabel stageLabel = new JLabel("Stage");
		add(stageLabel, "cell 1 0, pad 0 7 0 50");
		stageBox = new JComboBox<String>();
		stageBox.setToolTipText("Select a status for this task");
		stageBox.setModel(new DefaultComboBoxModel<String>( getStatusOptions() ));
		
		//Set the default selected value of the stage selection box
		if(model != null)
			stageBox.setSelectedItem( workflowModel.getStageModelList().get(model.getStageID()).getTitle());
		
		add(stageBox, "cell 1 1, pad 0 7 0 0");
		stageBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		        int selected = ((JComboBox) e.getSource()).getSelectedIndex();
		      }
		});
		
		//Set a deescription for the task
		taskDescriptionLabel = new JLabel("Task Description(*)");
		add(taskDescriptionLabel, "cell 0 2");
		
		//Warn if the user has not put in a description
		descriptionEmptyError = new JLabel("Task Needs A Description");
		descriptionEmptyError.setForeground(Color.red);
		add(descriptionEmptyError, "cell 0 2");
		descriptionEmptyError.setVisible(false);
		descriptionScrollPane = new JScrollPane();
		add(descriptionScrollPane, "cell 0 3,grow");
		taskDescriptionField = new JTextArea(this.taskModel.getDescription());
		descriptionScrollPane.setViewportView(taskDescriptionField);
		taskDescriptionField.setLineWrap(true);
		taskDescriptionField.setColumns(45);
		taskDescriptionField.setRows(10);
		taskDescriptionField.addKeyListener(this);
		
		//Create a view where users can assign users to the task
		assignUsersView = new AssignUsersView();
		add(assignUsersView, "cell 1 3,grow");
		
		//Warn if the users put in a bad estimated effort
		JLabel estEffortLabel = new JLabel("Estimated Effort");
		add(estEffortLabel, "flowx,cell 1 4, pad 0 7 0 100");
		estEffortError = new JLabel("Must specify a valid effort");
		estEffortError.setForeground(Color.red);
		add(estEffortError, "flowx,cell 1 4, pad 0 5 0 100");
		estEffortError.setVisible(false);
		
		//Set the estimated effort
		estEffortField = new JTextField();
		estEffortField.setHorizontalAlignment(SwingConstants.RIGHT);
		estEffortField.setText(Integer.toString(this.taskModel.getEstimatedEffort()));
		add(estEffortField, "flowx,cell 1 5,alignx left, pad 0 7 0 0");
		estEffortField.setColumns(10);
		estEffortField.addKeyListener(this);
		
		colorTitle = new JLabel("Select Category");
		add(colorTitle, "cell 0 9");
		
		//Set the actual effort
		JLabel actEffortLabel = new JLabel("Actual Effort");
		add(actEffortLabel, "flowx,pad 0 7 0 100,cell 1 11");
		actEffortField = new JTextField();
		actEffortField.setHorizontalAlignment(SwingConstants.RIGHT);
		actEffortField.setText(Integer.toString(this.taskModel.getActualEffort()));
		add(actEffortField, "flowx,pad 0 7 0 0,cell 1 12,alignx left");
		actEffortField.setColumns(10);
		actEffortField.addKeyListener(this);
		
		//Warn if users put an invalid actual effort
		actEffortError = new JLabel("Must specify a valid effort");
		actEffortError.setForeground(Color.red);
		add(actEffortError, "flowx,pad 0 5 0 100,cell 1 11");
		actEffortError.setVisible(false);
		
		//The submit button
		sbmtTaskButton = new JButton("Submit");
		NewTaskTab thisTab = this;
		sbmtTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buildTask();
				TabController.getInstance().removeTab(thisTab);
			}	
		});
		add(sbmtTaskButton, "flowx,cell 0 12");		
		sbmtTaskButton.setEnabled(false);
		
		/*
		 * Creates a model of the calendar. properties sets the date to todays
		 * date. The datePanel then gets this date, and then the datePicker
		 * gets this info, and the DateLabelFormatter formats it to be
		 * in MM-dd-YYYY
		 */
		dateDue = new JLabel("Due Date:(*)");
		add(dateDue, "flowx,cell 0 4");
		
		dateNotAddedError = new JLabel("Task Needs A Due Date");
		dateNotAddedError.setForeground(Color.red);
		add(dateNotAddedError, "cell 0 4");
		dateNotAddedError.setVisible(false);
		
		dateModel = new UtilDateModel();
		if(!this.taskModel.getDueDate().equals("")){
			try{
				Date date = new SimpleDateFormat("MM/dd/yyyy").parse(this.taskModel.getDueDate());
				dateModel.setValue(date);
			} catch(ParseException e) {
				e.printStackTrace();
			}
		}
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
	    datePanel = new JDatePanelImpl(dateModel, p);
	    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		add(datePicker, "flowx,cell 0 5");
		datePicker.addMouseListener(this);
		datePanel.addMouseListener(this);
		datePicker.addActionListener(this);
		
		colorTitle = new JLabel("                  ");
		add(colorTitle, "cell 1 0");
		
		//Colorcombobox is a custom jcombobox that allows color section visible
		colorBox = new ColorComboBox();
		add(colorBox,"cell 0 10");
		colorBox.setBounds(100,20,140,30); 
		colorBox.setToolTipText("Select a Category Color");
		
		
		daysUntilField = new JTextField();
		daysUntilField.setHorizontalAlignment(SwingConstants.RIGHT);
		daysUntilField.setText(Integer.toString(this.taskModel.getTimeThreshold()));
		daysUntilField.setToolTipText("Number of days task will display Yellow before due date");
		add(daysUntilField, "pad 0 50 0 0,cell 0 5,alignx right");
		daysUntilField.setColumns(10);
		daysUntilField.addKeyListener(this);
		
		//Let the user specify the number of critical days before due
		JLabel daysUntilLabel = new JLabel("Critical days before deadline");
		add(daysUntilLabel, "pad 0 0 0 50,cell 0 5,alignx left");
		daysUntilError = new JLabel("Must specify number of days");
		daysUntilError.setHorizontalAlignment(SwingConstants.RIGHT);
		daysUntilError.setForeground(Color.red);
		add(daysUntilError, "cell 0 4,alignx right,pad 0 47 0 70");
		daysUntilError.setVisible(false);
		
		checkForErrors();
	}
	
	/**
	 * Given the input that the user provided, construct the task
	 * @return - the task that has been built with the fields that the user entered
	 */
	public void buildTask() {
		StageModel stageModel = null;
		for(StageModel stage : workflowModel.getStageModelList().values()){
			if(stage.getTitle() == stageBox.getSelectedItem())
				stageModel = stage;
		}
		
		//If the task has moved to a different stage, make sure to remove it from the original stage before moving it
		if(taskModel.getStageID()!= 0 && taskModel.getStageID() != stageModel.getID()){
			StageModel originalStage = workflowModel.getStageModelByID(taskModel.getStageID());
			originalStage.removeTask(taskModel);
			StageController.sendUpdateRequest(originalStage);
		}
		
		TaskModel taskModel = new TaskModel();
		taskModel.setID( this.taskModel.getID() );
		String creatorName = ConfigManager.getConfig().getUserName();
		taskModel.setCreator( creatorName );
		taskModel.setTitle(this.getTitleLabelText());
		taskModel.setDescription(this.getDescriptionText());
		taskModel.setDueDate(this.getDateText());
		taskModel.setUsersAssignedTo( assignUsersView.getAssignedUsers());
		taskModel.setEstimatedEffort(this.getEstimatedEffort());
		taskModel.setActualEffort(this.getActualEffort());
		taskModel.setDueDate(this.getDateText());
		taskModel.setStageID( stageModel.getID() );
		taskModel.setTimeThreshold(getDaysUntil());
		taskModel.setActivities(this.taskModel.getActivities());
		taskModel.setCatColor(colorBox.getSelectedColor());
		ActivityModel message;
		if(isEditingTask){
			message = new ActivityModel("Updated the task");
		} else {
			message = new ActivityModel("Created the task");
		}
		taskModel.addActivity(message);
		//Adds the task to the stageModel if it is new, or updataes it if it already exists
		stageModel.addUpdateTaskModel(taskModel);
		
		SearchController.search();
	}
	
	/**
	 * Checks that all the requirements for creating a new task are met and updates the correct fields
	 * indicating what if anything still needs to be done.
	 */
	public void checkForErrors(){
		boolean isTitleTextFull = taskTitleField.getText().isEmpty()? false: true;	
		titleEmptyError.setVisible(!isTitleTextFull);
		
		boolean isDescriptionTextFull = taskDescriptionField.getText().isEmpty()? false: true;
		descriptionEmptyError.setVisible(!isDescriptionTextFull);
		
		boolean isDateAdded = this.getDateText().isEmpty()? false: true;
		dateNotAddedError.setVisible(!isDateAdded);
		
		boolean isEstEffortPosInt = this.getEstimatedEffort() < 0? false: true;
		estEffortError.setVisible(!isEstEffortPosInt);
		
		boolean isActEffortPosInt = this.getActualEffort() < 0? false: true;
		actEffortError.setVisible(!isActEffortPosInt);
		
		boolean isDaysUntilPosInt = this.getDaysUntil() < 0? false: true;
		daysUntilError.setVisible(!isDaysUntilPosInt);
		
		boolean shouldSubmitBeEnabled = 
					isTitleTextFull &&
					isDaysUntilPosInt &&
					isDescriptionTextFull && 
					isDateAdded &&
					isEstEffortPosInt &&
					isActEffortPosInt? true: false;
		sbmtTaskButton.setEnabled(shouldSubmitBeEnabled);	
	}
	
	public AssignUsersView getAssignUserView(){
		return assignUsersView;
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		checkForErrors();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		checkForErrors();
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		checkForErrors();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		checkForErrors();
	}
	  
	public int getEstimatedEffort(){
		int effort;
		try {
			effort = Integer.parseInt(estEffortField.getText());
		} catch (NumberFormatException e){
			effort = -1;
		}
		return effort;
	}
	
	public int getActualEffort(){
		int effort;
		try {
			effort = Integer.parseInt(actEffortField.getText());
		} catch (NumberFormatException e){
			effort = -1;
		}
		return effort;
	}
	
	public int getDaysUntil(){
		int days;
		try {
			days = Integer.parseInt(daysUntilField.getText());
		} catch (NumberFormatException e){
			days = -1;
		}
		return days;
	}
	
	/**
	 * get the current string in the title field
	 * 
	 * @return
	 */
	public String getTitleLabelText(){
		return taskTitleField.getText();
	};
	
	public int getStageSelectionIndex() {
		return this.stageBox.getSelectedIndex();
	}
	
	/**
	 * get the current string in the description field
	 * 
	 * @return
	 */
	public String getDescriptionText(){
		return taskDescriptionField.getText();
	}
	
	/**
	 * get the string representing the name of the current selected stage
	 * 
	 * @return
	 */
	public String getStatusText() {
		return (String)stageBox.getSelectedItem();
	}
	
	/**
	 * @return - a string list of all stage titles
	 */
	public String[] getStatusOptions(){
		ArrayList<String> statusOptions = new ArrayList<String>();
		for( StageModel stage : workflowModel.getStageModelList().values() ){
			String truncatedTitle;
			if(stage.getTitle().length() >= 21)
				truncatedTitle =  stage.getTitle().substring(0,21) + "...";
			else
				truncatedTitle = stage.getTitle();
			statusOptions.add( truncatedTitle );
		}
		return statusOptions.toArray( new String[statusOptions.size() ]);
	}
	
	
	/**Gets the date in a date format,
	 * and then it is formatted to become
	 * a string 
	 */
	public String getDateText(){
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		if(datePicker.getModel().getValue() == null){
			return "";
		} else {
			return formatter.format(datePicker.getModel().getValue());
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		checkForErrors();
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		checkForErrors();
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		checkForErrors();
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		checkForErrors();
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		checkForErrors();
	}

	@Override
	public int getModelID() {
		return taskModel.getID();
	}

	@Override
	public TabType getTabType() {
		return TabType.TASK;
	}
	
}
