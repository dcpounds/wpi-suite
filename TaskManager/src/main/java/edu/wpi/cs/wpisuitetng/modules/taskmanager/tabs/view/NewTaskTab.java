package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.TaskModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.DateLabelFormatter;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.StageView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TaskView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTaskController;
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
public class NewTaskTab extends JPanel implements KeyListener, MouseListener, ActionListener{
	
	private static final long serialVersionUID = -8772773694939459349L;
	private TabView tabView;
	private JTextField taskTitleField;
	private JTextField estEffortField;
	private JTextField actEffortField;
	private JComboBox<String> stageBox;
	private JComboBox<String> workFlowBox;
	private JLabel taskDescriptionLabel;
	private JTextArea taskDescriptionField;
    private final WorkflowModel workflowModel;
    private JLabel dateDue;
    private UtilDateModel model;
    private JDatePanelImpl datePanel; 
    private JDatePickerImpl datePicker;  
    private JButton sbmtTaskButton;
	private JLabel titleEmptyError;
	private JLabel estEffortError;
	private JLabel actEffortError;
	private JLabel descriptionEmptyError;
	private JLabel dateNotAddedError;
	private JLabel starredFieldsRequired;
	private JScrollPane descriptionScrollPane;
	
	/**
	 * contructs a tab for creating tasks
	 * 
	 * @param taskManagerTabView - the main view that holds tabs
	 */

	public NewTaskTab(TaskModel taskModel) {
		boolean shouldRemove = false;
		String oldStage = null;
		int oldId = -1;
		
		if(taskModel != null){
			shouldRemove = true;
			oldStage = taskModel.getStatus();
			oldId = taskModel.getId();
		} else {
			taskModel = new TaskModel();
		}
		
		this.workflowModel = WorkflowController.getWorkflowModel();
		this.tabView = TabController.getTabView();
		
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
		JLabel taskTitleLabel = new JLabel("Task Title(*)");
		add(taskTitleLabel, "flowx,cell 0 0");
		
		titleEmptyError = new JLabel("Task Needs A Title");
		titleEmptyError.setForeground(Color.red);
		add(titleEmptyError, "flowx,cell 0 0");
		titleEmptyError.setVisible(false);
		
		taskTitleField = new JTextField();
		taskTitleField.setText(taskModel.getTitle());
		add(taskTitleField, "flowx,cell 0 1,alignx left");
		taskTitleField.setColumns(35);
		taskTitleField.addKeyListener(this);
		
		
		JLabel stageLabel = new JLabel("Stage");
		add(stageLabel, "cell 1 0");

		stageBox = new JComboBox<String>();
		stageBox.setToolTipText("Select a status for this task");
		stageBox.setModel(new DefaultComboBoxModel<String>( getStatusOptions() ));
		add(stageBox, "cell 1 1");
		stageBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		        int selected = ((JComboBox) e.getSource()).getSelectedIndex();
		      }
		});
		
		taskDescriptionLabel = new JLabel("Task Description(*)");
		add(taskDescriptionLabel, "cell 0 2");
		
		descriptionEmptyError = new JLabel("Task Needs A Description");
		descriptionEmptyError.setForeground(Color.red);
		add(descriptionEmptyError, "cell 0 2");
		descriptionEmptyError.setVisible(false);
		
		descriptionScrollPane = new JScrollPane();
		add(descriptionScrollPane, "cell 0 3,grow");
		

		taskDescriptionField = new JTextArea(taskModel.getDescription());
		descriptionScrollPane.setViewportView(taskDescriptionField);
		taskDescriptionField.setLineWrap(true);
		taskDescriptionField.setColumns(50);
		taskDescriptionField.setRows(10);
		taskDescriptionField.addKeyListener(this);
		
		AssignUsersView assignUsersView = new AssignUsersView();
		add(assignUsersView, "cell 1 3,grow");
		
		JLabel estEffortLabel = new JLabel("Estimated Effort");
		add(estEffortLabel, "flowx,cell 1 4");
		
		estEffortError = new JLabel("Must specify nonnegative effort");
		estEffortError.setForeground(Color.red);
		add(estEffortError, "flowx,cell 1 4");
		estEffortError.setVisible(false);
		
		estEffortField = new JTextField();
		estEffortField.setText(Integer.toString(taskModel.getEstimatedEffort()));
		add(estEffortField, "flowx,cell 1 5,alignx left");
		estEffortField.setColumns(10);
		estEffortField.addKeyListener(this);
		
		JLabel actEffortLabel = new JLabel("Actual Effort");
		add(actEffortLabel, "flowx,cell 1 6");
		
		actEffortError = new JLabel("Must specify nonnegative effort");
		actEffortError.setForeground(Color.red);
		add(actEffortError, "flowx,cell 1 6");
		actEffortError.setVisible(false);
		
		actEffortField = new JTextField();
		actEffortField.setText(Integer.toString(taskModel.getActualEffort()));
		add(actEffortField, "flowx,cell 1 7,alignx left");
		actEffortField.setColumns(10);
		actEffortField.addKeyListener(this);
		
		sbmtTaskButton = new JButton("Submit");
		sbmtTaskButton.addActionListener( new AddTaskController(this, assignUsersView, 0));
		NewTaskTab thisTab = this;
		sbmtTaskButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TabController.getInstance().removeTab(thisTab);
			}
		});
		if(shouldRemove){
			StageView sView = tabView.getWorkflowView().getStageViewByName(oldStage);
			TaskView tView = sView.getTaskViewById(oldId);
			sbmtTaskButton.addActionListener( new RemoveTaskController(taskModel, sView, tView));
		}
		
		
		add(sbmtTaskButton, "cell 0 7");		
		sbmtTaskButton.setEnabled(false);
		starredFieldsRequired = new JLabel("Starred Fields Are Required");
		starredFieldsRequired.setForeground(Color.red);
		add(starredFieldsRequired, "cell 0 7");
		
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
		
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
	    datePanel = new JDatePanelImpl(model, p);
	    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		add(datePicker, "cell 0 5");
		datePicker.addMouseListener(this);
		datePanel.addMouseListener(this);
		datePicker.addActionListener(this);
		
		checkAndUpdateFields();
		
	}
	
	/**
	 * Checks that all the requirements for creating a new task are met and updates the correct fields
	 * indicating what if anything still needs to be done.
	 */
	public void checkAndUpdateFields(){
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
		starredFieldsRequired.setVisible(!shouldSubmitBeEnabled);
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		checkAndUpdateFields();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		checkAndUpdateFields();
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		checkAndUpdateFields();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		checkAndUpdateFields();
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
	
	public String[] getStatusOptions(){
		ArrayList<String> statusOptions = new ArrayList<String>();
		for( StageModel stage : workflowModel.getStageList() ){
			statusOptions.add( stage.getTitle() );
		}
		return statusOptions.toArray( new String[statusOptions.size() ]);
	}
	/*Gets the date in a date format,
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
		checkAndUpdateFields();
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		checkAndUpdateFields();
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		checkAndUpdateFields();
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		checkAndUpdateFields();
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		checkAndUpdateFields();
	}
	
}
