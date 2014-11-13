package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.CardModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;


/**
 * This class is used to display a workflow in the GUI. Right now the cards are hard-coded in,
 *  but in the future we will be able to dynamically add and remove them through
 *
 */
public class WorkflowView extends JPanel {
	private ArrayList<CardModel> cardModelList;
	private ArrayList<CardView> cardViewList = new ArrayList<CardView>();
	
	public WorkflowView(WorkflowModel workflowModel) {
		this.setLayout( new BorderLayout() );
		JPanel workflowPanel = new JPanel();
		JScrollPane scrollBar = new JScrollPane(workflowPanel);
		
		
		
		this.cardModelList = workflowModel.getCardList();
		for(CardModel cardModel: cardModelList){
			CardView cardView = new CardView(cardModel);
			cardViewList.add(cardView);
			workflowPanel.add(cardView);
		}
		
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
