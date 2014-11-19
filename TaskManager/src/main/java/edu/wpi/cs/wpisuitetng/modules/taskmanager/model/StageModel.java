package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;

/**
 * Model for respresenting a stage that holds individual tasks
 *
 */
public class StageModel extends AbstractModel {
	
	private static final long serialVersionUID = 7869886695945683209L;
	private String title;
	private boolean closable;
	private WorkflowModel workflowModel = WorkflowController.getWorkflowModel();

	public StageModel(StageModel updatedStage) {
		this(updatedStage.getTitle(), updatedStage.getClosable());
	}
	
	/**
	 * constructs a new stage
	 * 
	 * @param title - title of the stage
	 * @param taskList - list of tasks to be added
	 */
	public StageModel(String title, boolean closable) {
		this.title = title;
		this.closable = closable;
	}
	
	/**
	 * constructs a new stage
	 * 
	 * @param title - title of the stage
	 * @param taskList - list of tasks to be added
	 */
	public StageModel() {
	}

	/**
	 * @return the title of the card
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title - set the title of the string
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        StageModel other = (StageModel) obj;
        
        return this.title == other.getTitle();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, StageModel.class);
		return json;
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param json
	 * @return - the JSON version of the object
	 */
	public static StageModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, StageModel.class);
	}
	
	
    /**
     * @param json
     * @return - an array of JSON stageModels
     */
    public static StageModel[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, StageModel[].class);
    }

	public boolean getClosable() {
		return closable;
	}
	

}
