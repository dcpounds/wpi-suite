/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;

/**
 * @author dave
 *
 */
public class WorkflowEntityManager implements EntityManager<WorkflowModel> {

	private final Data db;
	
	public WorkflowEntityManager(Data db){
		this.db = db;
	}
	
	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public WorkflowModel makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		// Parse the workflow from JSON
        final WorkflowModel newWorkflow = WorkflowModel.fromJson(content);
        
        // Save the workflow in the database if possible, otherwise throw an exception
        // We want the workflow to be associated with the project the user logged in to
        if (!db.save(newWorkflow, s.getProject())) {
            throw new WPISuiteException();
        }
        
        // Return the newly created workflow (this gets passed back to the client)
        return newWorkflow;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public WorkflowModel[] getEntity(Session s, String id)
			throws NotFoundException, WPISuiteException {
		WorkflowModel[] workflows = null;
		try {
			workflows = db.retrieve(WorkflowModel.class, "name", id, s.getProject()).toArray(new WorkflowModel[0]);
		} catch (WPISuiteException e) {
			e.printStackTrace();
		}
		if(workflows.length < 1 || workflows[0] == null) {
			throw new NotFoundException();
		}
		return workflows;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	@Override
	public WorkflowModel[] getAll(Session s) throws WPISuiteException {
        // Ask the database to retrieve all objects of the type WorkflowMode.
        // Passing a dummy WorkflowModel lets the db know what type of object to retrieve
        // Passing the project makes it only get workflows from that project
        List<Model> workflows = db.retrieveAll(new WorkflowModel(null), s.getProject());
        
        // Return the list of work flows as an array
        return workflows.toArray(new WorkflowModel[0]);
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public WorkflowModel update(Session s, String content)
			throws WPISuiteException {
		
		WorkflowModel updatedWorkflow = WorkflowModel.fromJson(content);
		/*
		 * Because of the disconnected objects problem in db4o, we can't just save Requirements.
		 * We have to get the original work flow from db4o, copy properties from updatedWorkfflow,
		 * then save the original work flow again.
		 */
		List<Model> oldWorkflows = db.retrieve(WorkflowModel.class, "name", updatedWorkflow.getName(), s.getProject());
		if(oldWorkflows.size() < 1 || oldWorkflows.get(0) == null) {
			throw new NotFoundException();
		}
				
		WorkflowModel existingWorkflow = (WorkflowModel)oldWorkflows.get(0);		

		// copy values to old requirement and fill in our changeset appropriately
		existingWorkflow.copyFrom(updatedWorkflow);
		
		if(!db.save(existingWorkflow, s.getProject())) {
			throw new WPISuiteException();
		}
		
		return existingWorkflow;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
	 */
	@Override
	public void save(Session s, WorkflowModel model) throws WPISuiteException {
		db.save(model);
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		return (db.delete(getEntity(s, id)[0]) != null) ? true : false;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
	 */
	@Override
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		db.deleteAll(new WorkflowModel(null), s.getProject());
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
	 */
	@Override
	public int Count() throws WPISuiteException {
        return db.retrieveAll(new WorkflowModel(null)).size();
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
	 */
	@Override
	public String advancedPut(Session s, String[] args, String content)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
	 */
	@Override
	public String advancedPost(Session s, String string, String content)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

}
