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
import java.awt.Dimension;
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

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RequirementsController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicSplitPaneDivider;

/**
 * This is a tab for creating new tasks
 *
 * 11/16 Further Progress on utilizing this class for editting functionality as
 * well. Authors Guillermo, Ashton;
 */
public class NewTaskTab extends JPanel implements KeyListener, MouseListener,
		ActionListener, IHashableTab {

	private static final long serialVersionUID = -8772773694939459349L;
	private TaskModel taskModel;
	private JTextField taskTitleField;
	private JTextField estEffortField;
	private JTextField actEffortField;
	private JComboBox<String> stageBox;
	private JComboBox<String> requirementsBox;
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
	private JLabel lblRequirement;
	private JLabel daysUntilLabel2;
	private DefaultComboBoxModel<String> requirementsComboModel;
	private ActivitiesView activitiesView;
	private JPanel editPane;
	private JPanel activitiesPane;
	private JSplitPane splitPane;
	
	/**
	 * contructs a tab for creating tasks
	 * 
	 * @param taskManagerTabView
	 *            - the main view that holds tabs
	 */
	public NewTaskTab(TaskModel model) {
		
		editPane = new JPanel();
		
		// get the requirements from the database
		editPane.setLayout(new MigLayout("", "[][][][grow]",
				"[][][][][][][][][][][][][][][][][][]"));
		JLabel taskTitleLabel = new JLabel("Task Title(*)");
		editPane.add(taskTitleLabel, "flowx,cell 1 0");
		this.workflowModel = WorkflowController.getWorkflowModel();

		// Decide what action the user is taking
		if (model == null) {
			this.taskModel = new TaskModel();
			this.action = ActionType.CREATE;
			this.isEditingTask = false;
		} else {
			this.taskModel = model;
			this.action = ActionType.EDIT;
			this.isEditingTask = true;
		}

		activitiesPane = new ActivitiesView(taskModel);

		// Set an error if the task needs a title
		titleEmptyError = new JLabel("Task Needs A Title");
		titleEmptyError.setForeground(Color.red);
		editPane.add(titleEmptyError, "flowx,cell 1 0");
		titleEmptyError.setVisible(false);

		// Set the task title
		taskTitleField = new JTextField();
		taskTitleField.setText(this.taskModel.getTitle());
		editPane.add(taskTitleField, "flowx,cell 1 1,alignx left");
		taskTitleField.setColumns(45);
		taskTitleField.addKeyListener(this);

		// Let the user decide where to put the task
		JLabel stageLabel = new JLabel("Stage");
		editPane.add(stageLabel, "pad 0 10 0 50,cell 3 0");
		stageBox = new JComboBox<String>();
		stageBox.setPreferredSize(new Dimension(125,10));
		stageBox.setToolTipText("Select a status for this task");
		stageBox.setModel(new DefaultComboBoxModel<String>(getStatusOptions()));
		editPane.add(stageBox, "pad 0 10 0 0,cell 3 1");
		stageBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = ((JComboBox) e.getSource()).getSelectedIndex();
			}
		});
		this.setStageBox();

		// Set a deescription for the task
		taskDescriptionLabel = new JLabel("Task Description(*)");
		editPane.add(taskDescriptionLabel, "cell 1 2");

		// Warn if the user has not put in a description
		descriptionEmptyError = new JLabel("Task Needs A Description");
		descriptionEmptyError.setForeground(Color.red);
		editPane.add(descriptionEmptyError, "cell 1 2");
		descriptionEmptyError.setVisible(false);
		descriptionScrollPane = new JScrollPane();
		editPane.add(descriptionScrollPane, "cell 1 3,grow");
		taskDescriptionField = new JTextArea(this.taskModel.getDescription());
		descriptionScrollPane.setViewportView(taskDescriptionField);
		taskDescriptionField.setLineWrap(true);
		taskDescriptionField.setColumns(45);
		taskDescriptionField.setRows(10);
		taskDescriptionField.addKeyListener(this);

		// Create a view where users can assign users to the task
		assignUsersView = new AssignUsersView(taskModel);
		editPane.add(assignUsersView, "pad 0 10 0 0,cell 3 3,grow");

		// Warn if the users put in a bad estimated effort
		JLabel estEffortLabel = new JLabel("Estimated Effort");
		editPane.add(estEffortLabel, "flowx,pad 0 10 0 100,cell 3 6");
		estEffortError = new JLabel("Must specify a valid effort");
		estEffortError.setForeground(Color.red);
		editPane.add(estEffortError, "flowx,pad 0 5 0 100,cell 3 6");
		estEffortError.setVisible(false);

		// Set the estimated effort
		estEffortField = new JTextField();
		estEffortField.setHorizontalAlignment(SwingConstants.RIGHT);
		estEffortField.setText(Integer.toString(this.taskModel
				.getEstimatedEffort()));
		editPane.add(estEffortField, "flowx,pad 0 10 0 0,cell 3 7,alignx left");
		estEffortField.setColumns(10);
		estEffortField.addKeyListener(this);

		// Set the actual effort
		JLabel actEffortLabel = new JLabel("Actual Effort");
		editPane.add(actEffortLabel, "flowx,pad 0 10 0 100,cell 3 8");

		// Let the user specify the number of critical days before due
		JLabel daysUntilLabel = new JLabel("Task will become urgent ");
		editPane.add(daysUntilLabel, "flowx,pad 0 0 0 0,cell 1 9,alignx left");
		actEffortField = new JTextField();
		actEffortField.setHorizontalAlignment(SwingConstants.RIGHT);
		actEffortField.setText(Integer.toString(this.taskModel
				.getActualEffort()));
		editPane.add(actEffortField, "flowx,pad 0 10 0 0,cell 3 9,alignx left");
		actEffortField.setColumns(10);
		actEffortField.addKeyListener(this);

		// The submit button
		sbmtTaskButton = new JButton("Submit");
		NewTaskTab thisTab = this;
		sbmtTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buildTask();
				TabController.getInstance().removeTab(thisTab);
				TabController.getTabView().setSelectedIndex(0);
			}
		});

		colorTitle = new JLabel("Select Category");
		editPane.add(colorTitle, "cell 1 12,alignx left");

		// Colorcombobox is a custom jcombobox that allows color section visible
		colorBox = new ColorComboBox();
		editPane.add(colorBox, "cell 1 13");
		colorBox.setBounds(100, 20, 140, 30);
		colorBox.setToolTipText("Select a Category Color");
		colorTitle = new JLabel("                  ");
		if(model!=null)
		setCategoryColorBox();
		else colorBox.setSelectedIndex(1);
		
		editPane.add(colorTitle, "cell 3 0");

		// Make a label for the requirements combo box
		lblRequirement = new JLabel("Requirement:");
		editPane.add(lblRequirement, "pad 0 10 0 0,cell 3 12, pad 0 9 0 50");

		// Make the requirements combo box.
		this.requirementsComboModel = new DefaultComboBoxModel<String>();
		requirementsComboModel.addElement("None");
		new RequirementsController(this).requestRequirementsList();
		requirementsBox = new JComboBox<String>();
		requirementsBox.setPreferredSize(new Dimension(125,10));
		requirementsBox.setModel(requirementsComboModel);
		requirementsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = ((JComboBox) e.getSource()).getSelectedIndex();
			}
		});

		editPane.add(requirementsBox, "pad 0 10 0 0,cell 3 13");

		editPane.add(sbmtTaskButton, "flowx,cell 1 17");
		sbmtTaskButton.setEnabled(false);

		/*
		 * Creates a model of the calendar. properties sets the date to todays
		 * date. The datePanel then gets this date, and then the datePicker gets
		 * this info, and the DateLabelFormatter formats it to be in MM-dd-YYYY
		 */
		dateDue = new JLabel("Due Date:(*)");
		editPane.add(dateDue, "flowx,cell 1 6");
		dateNotAddedError = new JLabel("Task Needs A Due Date");
		dateNotAddedError.setForeground(Color.red);
		editPane.add(dateNotAddedError, "cell 1 6");
		dateNotAddedError.setVisible(false);

		dateModel = new UtilDateModel();
		if (!this.taskModel.getDueDate().equals("")) {
			try {
				Date date = new SimpleDateFormat("MM/dd/yyyy")
						.parse(this.taskModel.getDueDate());
				dateModel.setValue(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(dateModel, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.SOUTH,
				datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH,
				datePicker);
		editPane.add(datePicker, "flowx,cell 1 7");
		datePicker.addMouseListener(this);
		datePanel.addMouseListener(this);
		datePicker.addActionListener(this);

		// Warn if users put an invalid actual effort
		actEffortError = new JLabel("Must specify a valid effort");
		actEffortError.setForeground(Color.red);
		editPane.add(actEffortError, "pad 0 5 0 100,cell 3 8");
		actEffortError.setVisible(false);

		daysUntilError = new JLabel("Must specify valid number of days");
		daysUntilError.setHorizontalAlignment(SwingConstants.LEFT);
		daysUntilError.setForeground(Color.red);
		editPane.add(daysUntilError, "pad 0 0 0 70,cell 1 8,alignx left");
		daysUntilError.setVisible(false);
		daysUntilField = new JTextField();
		daysUntilField.setHorizontalAlignment(SwingConstants.CENTER);
		daysUntilField.setText(Integer.toString(this.taskModel
				.getTimeThreshold()));
		daysUntilField
				.setToolTipText("The task will become urgent this many days before the due date");
		editPane.add(daysUntilField, "pad 0 0 0 0,cell 1 9,alignx left");
		daysUntilField.setColumns(5);
		setDaysUntilField();
		daysUntilField.addKeyListener(this);

		daysUntilLabel2 = new JLabel(" day(s) before it is due");
		editPane.add(daysUntilLabel2, "cell 1 9");

		checkForErrors();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                           editPane, activitiesPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(700);
		add(splitPane);
	}

	/**
	 * @return the comboBoxModel used for the requirements box
	 */
	public DefaultComboBoxModel<String> getRequirementsComboModel() {
		return requirementsComboModel;
	}

	/**
	 * Given the input that the user provided, construct the task
	 * 
	 * @return - the task that has been built with the fields that the user
	 *         entered
	 */
	public void buildTask() {
		StageModel stageModel = null;
		for (StageModel stage : workflowModel.getStageModelList().values()) {
			if (stage.getTitle().equals(stageBox.getSelectedItem()))
				stageModel = stage;
		}

		// If the task has moved to a different stage, make sure to remove it
		// from the original stage before moving it
		if (taskModel.getStageID() != 0
				&& taskModel.getStageID() != stageModel.getID()) {
			StageModel originalStage = workflowModel
					.getStageModelByID(taskModel.getStageID());
			originalStage.removeTask(taskModel);
			StageController.sendUpdateRequest(originalStage);
		}

		if (!isEditingTask)
		{
			TaskModel taskModel = new TaskModel();
			taskModel.setID( this.taskModel.getID() );
		}


		String creatorName = ConfigManager.getConfig().getUserName();
		ArrayList<String> assignedUsers = assignUsersView.getAssignedUsers();
		taskModel.setCreator(creatorName);
		taskModel.setTitle(this.getTitleLabelText());
		taskModel.setDescription(this.getDescriptionText());
		taskModel.setDueDate(this.getDateText());
		taskModel.setUsersAssignedTo(assignedUsers);
		taskModel.setEstimatedEffort(this.getEstimatedEffort());
		taskModel.setActualEffort(this.getActualEffort());
		taskModel.setDueDate(this.getDateText());
		taskModel.setStageID(stageModel.getID());
		taskModel.setTimeThreshold(getDaysUntil());
		taskModel.setActivities(this.taskModel.getActivities());
		taskModel.setCatColor(colorBox.getSelectedColor());
		taskModel.setCatID(colorBox.getSelectedIndex());
		taskModel.setAssociatedRequirement((String) requirementsBox
				.getSelectedItem() );
		DataLoggerController.getDataModel().addSnapshot(taskModel);
		
		ActivityModel message;
		String messageString;
		if (isEditingTask) {
			for (int i=0; i<DataLoggerController.getDataModel().trackChanges(taskModel.getPreviousSnapshot(), 
					 taskModel.getCurrentSnapshot()).size(); i++)
			{
				messageString = DataLoggerController.getDataModel().trackChanges(taskModel.getPreviousSnapshot(), 
															 taskModel.getCurrentSnapshot()).get(i);
				message = new ActivityModel(messageString);
				taskModel.addActivity(message);
			}
			
		} else {
			message = new ActivityModel("Created the task");
			taskModel.addActivity(message);
		}

		stageModel.addUpdateTaskModel(taskModel);
	}
	
	
	/**
	 * Sets the requirement box based on the value of the given taskModel
	 */
	public void setTaskRequirementBox() {
		// Set the requirement box
		for (int index = 0; index < requirementsBox.getItemCount(); index++) {
			String requirement = requirementsBox.getItemAt(index);
			if (taskModel.getAssociatedRequirement().equals(requirement)) {
				requirementsBox.setSelectedItem(requirement);
			}
		}
	}
	
	
	/**
	 * Set the default value for the stage box
	 */
	public void setStageBox() {
		// Set the default selected value of the stage selection box
		if (taskModel.getStageID() != 0){
			String stageText = workflowModel.getStageModelList()
					.get(taskModel.getStageID()).getTitle();
			
			stageBox.setSelectedItem(stageText);
		}
	}
	
	/**
	 * Sets the category color box based on the taskModel's value
	 */
	public void setCategoryColorBox() {
		
		colorBox.setSelectedIndex(taskModel.getCatID());
		
	}

	/**
	 * Set the days until urgent field based on the taskModel
	 */
	public void setDaysUntilField() {
		daysUntilField.setText( "" + taskModel.getTimeThreshold() );
	}
	
	/**
	 * Checks that all the requirements for creating a new task are met and
	 * updates the correct fields indicating what if anything still needs to be
	 * done.
	 */
	public void checkForErrors() {
		boolean isTitleTextFull = taskTitleField.getText().trim().isEmpty() ? false
				: true;
		titleEmptyError.setVisible(!isTitleTextFull);

		boolean isDescriptionTextFull = taskDescriptionField.getText().trim()
				.isEmpty() ? false : true;
		descriptionEmptyError.setVisible(!isDescriptionTextFull);

		boolean isDateAdded = this.getDateText().isEmpty() ? false : true;
		dateNotAddedError.setVisible(!isDateAdded);

		boolean isEstEffortPosInt = this.getEstimatedEffort() < 0 ? false
				: true;
		estEffortError.setVisible(!isEstEffortPosInt);

		boolean isActEffortPosInt = this.getActualEffort() < 0 ? false : true;
		actEffortError.setVisible(!isActEffortPosInt);

		boolean isDaysUntilPosInt = this.getDaysUntil() < 0 ? false : true;
		daysUntilError.setVisible(!isDaysUntilPosInt);

		boolean shouldSubmitBeEnabled = isTitleTextFull && isDaysUntilPosInt
				&& isDescriptionTextFull && isDateAdded && isEstEffortPosInt
				&& isActEffortPosInt ? true : false;
		sbmtTaskButton.setEnabled(shouldSubmitBeEnabled);
	}

	/**
	 * @return the view that is responsible for adding/removing users
	 */
	public AssignUsersView getAssignUserView() {
		return assignUsersView;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		checkForErrors();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		checkForErrors();
		hasBeenModified();
	}

	/**
	 * @return the info entered in estimated effort
	 */
	public int getEstimatedEffort() {
		int effort;
		try {
			effort = Integer.parseInt(estEffortField.getText());
		} catch (NumberFormatException e) {
			effort = -1;
		}
		return effort;
	}

	/**
	 * @return the info entered in actual effort
	 */
	public int getActualEffort() {
		int effort;
		try {
			effort = Integer.parseInt(actEffortField.getText());
		} catch (NumberFormatException e) {
			effort = -1;
		}
		return effort;
	}

	/**
	 * @return the number of days before the due date that the task should become urgent
	 */
	public int getDaysUntil() {
		int days;
		try {
			days = Integer.parseInt(daysUntilField.getText());
		} catch (NumberFormatException e) {
			days = -1;
		}
		return days;
	}

	/**
	 * get the current string in the title field
	 * @return
	 */
	public String getTitleLabelText() {
		return taskTitleField.getText().trim();
	};

	/**
	 * @return the selected index in the stage selection box
	 */
	public int getStageSelectionIndex() {
		return this.stageBox.getSelectedIndex();
	}

	/**
	 * @return the selected index for category color
	 */
	public int getCatSelectionIndex() {
		return this.colorBox.getSelectedIndex();
	}
	
	/**
	 * get the current string in the description field
	 * 
	 * @return
	 */
	public String getDescriptionText() {
		return taskDescriptionField.getText().trim();
	}

	/**
	 * get the string representing the name of the current selected stage
	 * 
	 * @return
	 */
	public String getStatusText() {
		return (String) stageBox.getSelectedItem();
	}

	/**
	 * @return - a string list of all stage titles
	 */
	public String[] getStatusOptions() {
		ArrayList<String> statusOptions = new ArrayList<String>();
		for (StageModel stage : workflowModel.getStageModelList().values()) {
			String truncatedTitle;
			if (stage.getTitle().length() >= 21)
				truncatedTitle = stage.getTitle().substring(0, 21) + "...";
			else
				truncatedTitle = stage.getTitle();
			statusOptions.add(truncatedTitle);
		}
		return statusOptions.toArray(new String[statusOptions.size()]);
	}

	/**
	 * Gets the date in a date format, and then it is formatted to become a
	 * string
	 */
	public String getDateText() {
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		if (datePicker.getModel().getValue() == null) {
			return "";
		} else {
			return formatter.format(datePicker.getModel().getValue());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		checkForErrors();
		hasBeenModified();
	}
/**
 * Determines if any field has been modified. If any part of the field in newtask has
 * been modified, then it will return true. 
 * @return
 */
	public boolean hasBeenModified() {
		if(!taskTitleField.getText().equals(this.taskModel.getTitle()))
			return true;
	
		if(!taskDescriptionField.getText().equals(this.taskModel.getDescription()))
			return true;
		
		if(!this.getDateText().equals(this.taskModel.getDueDate()))
			return true;

		if(!(this.getEstimatedEffort() == this.taskModel.getEstimatedEffort()))
			return true;

		if(!(this.getActualEffort() == this.taskModel.getActualEffort()))
			return true;

		if(!(this.getCatSelectionIndex() == this.taskModel.getCatID()))
			return true;
		
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.IHashableTab#getModelID()
	 */
	@Override
	public int getModelID() {
		return taskModel.getID();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.IHashableTab#getTabType()
	 */
	@Override
	public TabType getTabType() {
		return TabType.TASK;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//do nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//do nothing
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//do nothing
	}


}
