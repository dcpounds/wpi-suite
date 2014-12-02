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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.task.TaskModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;

import javax.swing.JScrollPane;


/**
 * This is a tab for creating new tasks
 *
 *  11/16 Further Progress on utilizing this class for editting functionality aswell.
 * 		Authors  Guillermo, Ashton;
 *		
 */
public class NewTaskTab extends AbstractTab implements KeyListener, MouseListener, ActionListener{
	
	private static final long serialVersionUID = -8772773694939459349L;
	private TaskModel taskModel;
	private JTextField taskTitleField;
	private JTextField estEffortField;
	private JTextField actEffortField;
	private JComboBox<String> stageBox;
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
	private JLabel descriptionEmptyError;
	private JLabel dateNotAddedError;
	private JScrollPane descriptionScrollPane;
	private AssignUsersView assignUsersView;
	private ActionType action;
	private TaskModel oldTask;
	
	/**
	 * contructs a tab for creating tasks
	 * 
	 * @param taskManagerTabView - the main view that holds tabs
	 */

	public NewTaskTab(TaskModel model) {
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
		JLabel taskTitleLabel = new JLabel("Task Title(*)");
		add(taskTitleLabel, "flowx,cell 0 0");
		this.workflowModel = WorkflowController.getWorkflowModel();
		
		
		//Decide what action the user is taking	
		if(model == null){
			this.taskModel = new TaskModel();
			this.action = ActionType.CREATE;
		} else{
			this.taskModel = model;
			this.taskModel.setEditState(true);
			this.action = ActionType.EDIT;
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
		add(stageLabel, "cell 1 0");
		stageBox = new JComboBox<String>();
		stageBox.setToolTipText("Select a status for this task");
		stageBox.setModel(new DefaultComboBoxModel<String>( getStatusOptions() ));
		//stageBox.setSelectedIndex(this.taskModel.getStageID());
		add(stageBox, "cell 1 1");
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
		add(estEffortLabel, "flowx,cell 1 4");
		estEffortError = new JLabel("Must specify a valid effort");
		estEffortError.setForeground(Color.red);
		add(estEffortError, "flowx,cell 1 4");
		estEffortError.setVisible(false);
		
		//Set the estimated effort
		estEffortField = new JTextField();
		estEffortField.setText(Integer.toString(this.taskModel.getEstimatedEffort()));
		add(estEffortField, "flowx,cell 1 5,alignx left");
		estEffortField.setColumns(10);
		estEffortField.addKeyListener(this);
		
		//Set the actual effort
		JLabel actEffortLabel = new JLabel("Actual Effort");
		add(actEffortLabel, "flowx,cell 1 6");
		actEffortField = new JTextField();
		actEffortField.setText(Integer.toString(this.taskModel.getActualEffort()));
		add(actEffortField, "flowx,cell 1 7,alignx left");
		actEffortField.setColumns(10);
		actEffortField.addKeyListener(this);
		
		//Warn if users put an invalid actual effort
		actEffortError = new JLabel("Must specify nonnegative effort");
		actEffortError.setForeground(Color.red);
		add(actEffortError, "flowx,cell 1 6");
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
		add(sbmtTaskButton, "cell 0 7");		
		sbmtTaskButton.setEnabled(false);
		
		/*
		 * Creates a model of the calendar. properties sets the date to todays
		 * date. The datePanel then gets this date, and then the datePicker
		 * gets this info, and the DateLabelFormatter formats it to be
		 * in MM-dd-YYYY
		 */
		dateDue = new JLabel("Due Date:(*)");
		add(dateDue, "cell 0 4");
		
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
		add(datePicker, "cell 0 5");
		datePicker.addMouseListener(this);
		datePanel.addMouseListener(this);
		datePicker.addActionListener(this);
		
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
		taskModel.setEditState(false);
		//Adds the task to the stageModel if it is new, or updataes it if it already exists
		stageModel.addUpdateTaskModel(taskModel);
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
		
		boolean shouldSubmitBeEnabled = 
					isTitleTextFull && 
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
		hasBeenModified();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		checkForErrors();
		hasBeenModified();
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		checkForErrors();
		hasBeenModified();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		checkForErrors();
		hasBeenModified();
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
			statusOptions.add( stage.getTitle() );
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
	public boolean hasBeenModified() {

		if(taskModel.getTitle() == taskTitleField.getText() && 
				taskModel.getDueDate() == this.getDateText() &&
				taskModel.getDescription() == taskDescriptionField.getText() &&
				taskModel.getEstimatedEffort() == this.getEstimatedEffort()&&
				taskModel.getActualEffort() == this.getActualEffort()){
			return false;
		}
		else{
			return true;
		}
	}
	
}
