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
public class WorkFlowEntityManager implements EntityManager<WorkFlowModel> {

	private final Data db;
	
	public WorkFlowEntityManager(Data db){
		this.db = db;
	}
	
	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public WorkFlowModel makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		// Parse the work flow from JSON
        final WorkFlowModel newWorkFlow = WorkFlowModel.fromJson(content);
        
        // Save the work flow in the database if possible, otherwise throw an exception
        // We want the work flow to be associated with the project the user logged in to
        if (!db.save(newWorkFlow, s.getProject())) {
            throw new WPISuiteException();
        }
        
        // Return the newly created work flow (this gets passed back to the client)
        return newWorkFlow;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public WorkFlowModel[] getEntity(Session s, String id)
			throws NotFoundException, WPISuiteException {
		WorkFlowModel[] workFlows = null;
		try {
			workFlows = db.retrieve(WorkFlowModel.class, "name", id, s.getProject()).toArray(new WorkFlowModel[0]);
		} catch (WPISuiteException e) {
			e.printStackTrace();
		}
		if(workFlows.length < 1 || workFlows[0] == null) {
			throw new NotFoundException();
		}
		return workFlows;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	@Override
	public WorkFlowModel[] getAll(Session s) throws WPISuiteException {
        // Ask the database to retrieve all objects of the type PostBoardWorkFlow.
        // Passing a dummy PostBoardWorkFlow lets the db know what type of object to retrieve
        // Passing the project makes it only get work flows from that project
        List<Model> workFlows = db.retrieveAll(new WorkFlowModel(null), s.getProject());
        
        // Return the list of work flows as an array
        return workFlows.toArray(new WorkFlowModel[0]);
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public WorkFlowModel update(Session s, String content)
			throws WPISuiteException {
		
		WorkFlowModel updatedWorkFlow = WorkFlowModel.fromJson(content);
		/*
		 * Because of the disconnected objects problem in db4o, we can't just save Requirements.
		 * We have to get the original work flow from db4o, copy properties from updatedWorkfFlow,
		 * then save the original work flow again.
		 */
		List<Model> oldWorkFlows = db.retrieve(WorkFlowModel.class, "name", updatedWorkFlow.getName(), s.getProject());
		if(oldWorkFlows.size() < 1 || oldWorkFlows.get(0) == null) {
			throw new NotFoundException();
		}
				
		WorkFlowModel existingWorkFlow = (WorkFlowModel)oldWorkFlows.get(0);		

		// copy values to old requirement and fill in our changeset appropriately
		existingWorkFlow.copyFrom(updatedWorkFlow);
		
		if(!db.save(existingWorkFlow, s.getProject())) {
			throw new WPISuiteException();
		}
		
		return existingWorkFlow;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
	 */
	@Override
	public void save(Session s, WorkFlowModel model) throws WPISuiteException {
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
		db.deleteAll(new WorkFlowModel(null), s.getProject());
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
	 */
	@Override
	public int Count() throws WPISuiteException {
        return db.retrieveAll(new WorkFlowModel(null)).size();
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
