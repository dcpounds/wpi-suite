package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * This class is used to display a workflow in the GUI. Right now the cards are hard-coded in,
 *  but in the future we will be able to dynamically add and remove them through
 *
 */
public class WorkflowView extends JPanel {
	private ArrayList<CardView> cardViewList;
	
	public WorkflowView(String title) {
		this.setLayout( new BorderLayout() );
		JPanel workFlowPanel = new JPanel();
		JScrollPane scrollBar = new JScrollPane(workFlowPanel);
		
		//Create the four base cards
		CardView newCard = new CardView("New");
		CardView scheduledCard  = new CardView("Scheduled");
		CardView inProgressCard = new CardView("In Progress");
		CardView completedCard = new CardView("Completed");
		
		this.cardViewList = new ArrayList<CardView>();
		cardViewList.add(newCard);
		cardViewList.add(scheduledCard);
		cardViewList.add(inProgressCard);
		cardViewList.add(completedCard);
		
		//Add them to the workflow panel
		workFlowPanel.add(newCard);
		workFlowPanel.add(scheduledCard);
		workFlowPanel.add(inProgressCard);
		workFlowPanel.add(completedCard);
		this.add(scrollBar, BorderLayout.CENTER);
		
	}
	
	
	/**
	 * @return the list of CardViews in the workflow view
	 */
	public ArrayList<CardView> getCardViewList() {
		
		if(cardViewList.size() == 0){
			return new ArrayList<CardView>();
		}
		
		return this.cardViewList;
	}
	
	/**
	 * @param cardViewList - a list of cardViews to put in the list of card views
	 */
	public void setCardViewList( ArrayList<CardView> cardViewList ) {
		this.cardViewList = cardViewList;
	}

}
