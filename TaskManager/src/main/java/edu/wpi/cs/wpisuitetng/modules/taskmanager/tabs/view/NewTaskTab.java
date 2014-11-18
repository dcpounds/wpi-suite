package edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view;

import java.awt.Color;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.DateLabelFormatter;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StatusModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.tabs.view.TaskManagerTabView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AssignUsersView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RemoveTabController;


/**
 * This is a tab for creating new tasks
 *
 */
public class NewTaskTab extends JPanel implements KeyListener, MouseListener{
	
	private static final long serialVersionUID = -8772773694939459349L;
	private JTextField taskTitleField;
	private JComboBox<String> stageBox;
	private JComboBox<String> workFlowBox;
	private JLabel taskDescriptionLabel;
	private JTextArea taskDescriptionField;
    private final WorkflowModel workflowModel;
    private JLabel dateDue;
    private UtilDateModel model;
    private JDatePanelImpl datePanel; 
    private JDatePickerImpl datePicker;  
    private JButton makeTaskButton;
	private JLabel titleEmptyError;
	private JLabel descriptionEmptyError;
	private JLabel dateNotAddedError;
	private JLabel starredFieldsRequired;
	
	/**
	 * contructs a tab for creating tasks
	 * 
	 * @param taskManagerTabView - the main view that holds tabs
	 * @param workflowModel - the main workflow model
	 */
	public NewTaskTab(TaskManagerTabView taskManagerTabView,  WorkflowModel workflowModel) {
		this.workflowModel = workflowModel;
	
		setLayout(new MigLayout("", "[][][][grow]", "[][][][][]"));
		
		JLabel taskTitleLabel = new JLabel("Task Title(*)");
		add(taskTitleLabel, "flowx,cell 1 2");
		
		titleEmptyError = new JLabel("Task Needs A Title");
		titleEmptyError.setForeground(Color.red);
		add(titleEmptyError, "flowx, cell 1 2");
		titleEmptyError.setVisible(false);
		
		taskTitleField = new JTextField();
		taskTitleField.setText("");
		add(taskTitleField, "flowx,cell 1 3,alignx left");
		taskTitleField.setColumns(35);
		taskTitleField.addKeyListener(this);
		
		JLabel stageLabel = new JLabel("Stage");
		add(stageLabel, "cell 3 2");
		
		stageBox = new JComboBox<String>();
		stageBox.setToolTipText("Select a status for this task");
		stageBox.setModel(new DefaultComboBoxModel<String>(new StatusModel().getStatusNames() ) );
		stageBox.setSelectedIndex(0);
		add(stageBox, "cell 3 3");
		
		taskDescriptionLabel = new JLabel("Task Description(*)");
		add(taskDescriptionLabel, "cell 1 5");
		
		descriptionEmptyError = new JLabel("Task Needs A Description");
		descriptionEmptyError.setForeground(Color.red);
		add(descriptionEmptyError, "cell 1 5");
		descriptionEmptyError.setVisible(false);
		
		taskDescriptionField = new JTextArea();
		taskDescriptionField.setLineWrap(true);
		taskDescriptionField.setColumns(50);
		taskDescriptionField.setRows(10);
		add(taskDescriptionField, "cell 1 6,alignx left,growy");
		taskDescriptionField.addKeyListener(this);
		
		AssignUsersView assignUsersView = new AssignUsersView(taskManagerTabView, workflowModel);
		add(assignUsersView, "cell 3 6,grow");
		
		makeTaskButton = new JButton("Create");
		makeTaskButton.addActionListener( new AddTaskController(taskManagerTabView, this, assignUsersView, 0));
		makeTaskButton.addActionListener( new RemoveTabController(taskManagerTabView, this));
		add(makeTaskButton, "cell 1 10");		
		makeTaskButton.setEnabled(false);
		starredFieldsRequired = new JLabel("Starred Fields Are Required");
		starredFieldsRequired.setForeground(Color.red);
		add(starredFieldsRequired, "cell 1 10");
		
		/*
		 * Creates a model of the calendar. properties sets the date to todays
		 * date. The datePanel then gets this date, and then the datePicker
		 * gets this info, and the DateLabelFormatter formats it to be
		 * in MM-dd-YYYY
		 */
		dateDue = new JLabel("Due Date:(*)");
		add(dateDue, "cell 1 8");
		
		dateNotAddedError = new JLabel("Task Needs A Due Date");
		dateNotAddedError.setForeground(Color.red);
		add(dateNotAddedError, "cell 1 8");
		dateNotAddedError.setVisible(false);
		
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
	    datePanel = new JDatePanelImpl(model, p);
	    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		add(datePicker, "cell 1 9");
		datePicker.addMouseListener(this);
		datePanel.addMouseListener(this);
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		boolean myFields[] = checkFields();
		if(myFields[0] && myFields[1] && myFields[2]){
			makeTaskButton.setEnabled(true);	
			starredFieldsRequired.setVisible(false);
		}
		else{
			makeTaskButton.setEnabled(false);
			starredFieldsRequired.setVisible(true);
		}
		if(!myFields[0]){
			titleEmptyError.setVisible(true);
		}
		if(!myFields[1]){
			descriptionEmptyError.setVisible(true);
		}
		if(!myFields[2]){
			dateNotAddedError.setVisible(true);
		}
		if(myFields[0]){
			titleEmptyError.setVisible(false);
		}
		if(myFields[1]){
			descriptionEmptyError.setVisible(false);
		}
		if(myFields[2]){
			dateNotAddedError.setVisible(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean[] checkFields(){
		boolean titleTextFull = false;
		boolean descriptionTextFull = false;
		boolean dateAdded = false;
		if(taskTitleField.getText().isEmpty()){		
			titleTextFull = false;
		}
		if(taskDescriptionField.getText().isEmpty()){
			descriptionTextFull=false;
		}
		if(this.getDateText().isEmpty()){
			dateAdded = false;
		}
		if(!taskTitleField.getText().isEmpty()){		
			titleTextFull = true;
		}
		if(!taskDescriptionField.getText().isEmpty()){
			descriptionTextFull=true;
		}
		if(!this.getDateText().isEmpty()){
			dateAdded = true;
		}
		// empty = false, ! = true
		boolean[] flags = {titleTextFull, descriptionTextFull, dateAdded};
		return flags;
	}
	  
	
	/**
	 * get the current string in the title field
	 * 
	 * @return
	 */
	public String getTitleLabelText(){
		return taskTitleField.getText();
	};
	
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
	/*Gets the date in a date format,
	 * and then it is formatted to become
	 * a string 
	 */
	public String getDateText(){
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(datePicker.getModel().getValue());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean myFields[] = checkFields();
		if(myFields[0] && myFields[1] && myFields[2]){
			makeTaskButton.setEnabled(true);	
			starredFieldsRequired.setVisible(false);
		}
		else{
			makeTaskButton.setEnabled(false);
			starredFieldsRequired.setVisible(true);
		}
		if(!myFields[0]){
			titleEmptyError.setVisible(true);
		}
		if(!myFields[1]){
			descriptionEmptyError.setVisible(true);
		}
		if(!myFields[2]){
			dateNotAddedError.setVisible(true);
		}
		if(myFields[0]){
			titleEmptyError.setVisible(false);
		}
		if(myFields[1]){
			descriptionEmptyError.setVisible(false);
		}
		if(myFields[2]){
			dateNotAddedError.setVisible(false);
		}	
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
		boolean myFields[] = checkFields();
		if(myFields[0] && myFields[1] && myFields[2]){
			makeTaskButton.setEnabled(true);	
			starredFieldsRequired.setVisible(false);
		}
		else{
			makeTaskButton.setEnabled(false);
			starredFieldsRequired.setVisible(true);
		}
		if(!myFields[0]){
			titleEmptyError.setVisible(true);
		}
		if(!myFields[1]){
			descriptionEmptyError.setVisible(true);
		}
		if(!myFields[2]){
			dateNotAddedError.setVisible(true);
		}
		if(myFields[0]){
			titleEmptyError.setVisible(false);
		}
		if(myFields[1]){
			descriptionEmptyError.setVisible(false);
		}
		if(myFields[2]){
			dateNotAddedError.setVisible(false);
		}	
	}
	
}