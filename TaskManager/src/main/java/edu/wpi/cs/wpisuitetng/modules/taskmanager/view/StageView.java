/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop.DragStageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.draganddrop.DragStagePanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.ActionType;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;

/**
 * This view is responsible for rendering a stage that can be placed inside a workflow.
 */
public class StageView extends DragStagePanel {
	private static final long serialVersionUID = 7765491802045400161L;
	private String title;
	private JPanel stagePane;
	private JScrollPane scrollPane;
	private WorkflowView workflowView;
	private HashMap<Integer,TaskView> taskViewList;
	private JLabel lblStageTitle;
	private JButton btnClose;
	private JButton collapseAll;
	private JButton expandAll;
	private boolean closable;
	private StageModel stageModel;
	private int id;
	private JSeparator separator;
	
	/**
	 * Constructs a new Stage based off the given model
	 * @param stageModel - the model the view is based off of
	 */
	public StageView(StageModel stageModel, WorkflowView workflowView) {
		this.stageModel = stageModel;
		id = stageModel.getID();
		taskViewList = new HashMap<Integer,TaskView>();
		title = stageModel.getTitle();
		stagePane = new JPanel();
		this.workflowView = workflowView;
		setLayout(new MigLayout("insets 0", "[][grow][][]", "[][grow]"));
		this.closable = stageModel.canBeClosed();
		
		StageView thisStage = this;
		collapseAll = new JButton(" - ");
		collapseAll.setFont(new Font("Tahoma", Font.BOLD, 12));
		collapseAll.setMargin(new Insets(0, 0, 0, 0));
		collapseAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (TaskView taskView : thisStage.getTaskViewList().values()) {
					taskView.hideDetails();
				}
			}
		});
		add(collapseAll, "pad 5 2 0 2,cell 0 0,alignx left,aligny center");
		
		expandAll = new JButton("+");
		expandAll.setFont(new Font("Tahoma", Font.BOLD, 12));
		expandAll.setMargin(new Insets(0, 0, 0, 0));
		expandAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (TaskView taskView : thisStage.getTaskViewList().values()) {
					taskView.showDetails();
				}
			}
		});
		add(expandAll, "pad 5 2 0 2,cell 0 0,alignx left,aligny center");
		
		

		lblStageTitle = new JLabel();
		lblStageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblStageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblStageTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStageTitle.putClientProperty("html.disable", Boolean.TRUE);
		add(lblStageTitle, "cell 1 0,alignx center,aligny center");
		
		
		btnClose = new JButton("\u2716");
		btnClose.setFont(btnClose.getFont().deriveFont((float) 8));
		btnClose.setMargin(new Insets(0, 0, 0, 0));
		btnClose.addActionListener(new StageController(stageModel, ActionType.DELETE));
		btnClose.setEnabled(closable);
		
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(60,5));
		separator.setBackground(new Color(135,206,250));
		separator.setForeground(new Color(135,206,250));
		add(separator, "cell 2 0");
		add(btnClose, "pad 5 -5 0 -5,cell 3 0,aligny center");
		
		scrollPane = new JScrollPane(stagePane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		stagePane.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		add(scrollPane, "cell 0 1 4 1,grow");
		setBackground(new Color(135, 206, 250));
		stagePane.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		updateStageHeight();
		
		DragStageController dragController = new DragStageController(this);
		this.addMouseListener(dragController);
		this.addMouseMotionListener(dragController);
		
		
	}
	
	
	/**
	 * Overrides the getPreferredSize method to make task boxes scale dynamically
	 */
	@Override
	public Dimension getPreferredSize() {
		Component parent = this.getParent();
		final Dimension parentSize = new Dimension( workflowView.getScrollPane().getViewport().getWidth() - 30,
				workflowView.getScrollPane().getViewport().getHeight() - 30 );
		
		if( parent == null ){
			return super.getPreferredSize();
		} else {
			int stagePreferredHeight = stagePane.getPreferredSize().height;
			int stageCount = workflowView.getStageViewList().size();
			int stageWidth = parentSize.width/( stageCount < 4 ? stageCount : 4);
			truncateTitle(stageWidth);
			stagePane.setPreferredSize(new Dimension(stageWidth, stagePreferredHeight));
			return new Dimension( stageWidth, parentSize.height );
		}
	}
	
	
	/**
	 * Updates the preferred dimensions of the panel that houses the task views
	 */
	public void updateStageHeight() {
		int heightNeeded = 0;
		
		//Go through each component in the stageView
		for( TaskView taskView : this.getTaskViewList().values() )
				heightNeeded += taskView.getHeight();
		stagePane.setPreferredSize(new Dimension(this.getWidth(), heightNeeded));
	}

	/**
	 * gets the title of the stage
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the title of the stage
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * adds a task to the stage
	 * @param taskView
	 */
	public void addTaskView(TaskView taskView) {
		if(taskViewList.get(taskView.getID()) == null)
			stagePane.add(taskView);
		taskViewList.put(taskView.getID(),taskView);
		updateStageHeight();
		redrawStage();
	}
	
	/**
	 * @return the stagePane that tasks are added to
	 */
	public JPanel getStagePane() {
		return stagePane;
	}
	
	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void redrawStage(){
		stagePane.revalidate();
		stagePane.repaint();
	}
	
	/**
	 * removes a task from the stage and from the stageView list
	 * @param taskView
	 */
	public void removeTaskView(TaskView taskView) {
		stagePane.remove(taskView);
		this.getTaskViewList().remove(taskView.getID());
		updateStageHeight();	
		redrawStage();
	}
	
	
	/**
	 * gets the ID of this stage
	 */
	public int getID(){
		return  id;
	}
	
	/**
	 * Gets the list of taskviews within the stage
	 * @return the list of task views 
	 */
	public HashMap<Integer,TaskView> getTaskViewList(){
		return taskViewList;
	}
	
	/**
	 * Updates the current stage by passing in new stages
	 * @param newStageModel - the stageModel to replace the current stage with
	 */
	public void updateContents(StageModel newStageModel){		
		stageModel = newStageModel;
		btnClose.setEnabled(newStageModel.canBeClosed());
		this.redrawStage();
	}
	
	/**
	 * Updates the close button
	 * @return void
	 */
	public void updateCloseButton(){
		btnClose.setEnabled(stageModel.canBeClosed());
	}
	
	/**
	 * Clears the contents of the stage
	 * @return void
	 */
	public void clearStage(){
		stagePane.removeAll();
		this.getTaskViewList().clear();
	}
	
	
	/**
	 * Truncates the stage title if it is too long
	 * @param title
	 * @return
	 */
	public void truncateTitle(int width){
		int numChars = width/10;
		String truncatedTitle;
		if(stageModel.getTitle().length() >= numChars)
			truncatedTitle =  title.substring(0,numChars) + "...";
		else
			truncatedTitle = title;
		
		lblStageTitle.setText(truncatedTitle);
	}
}