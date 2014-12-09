package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.NotImplementedException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.reports.DataLoggerModel;

public class DataLoggerEntityManager implements EntityManager<DataLoggerModel> {
	
	private Data db;

	public DataLoggerEntityManager(Data db){
		this.db = db;
	}

	/** Creates a new entity for saving in the database
	 * 
	 */
	@Override
	public DataLoggerModel makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		final DataLoggerModel newDataLoggerModel = DataLoggerModel.fromJson( content );
		
		if( !db.save(newDataLoggerModel, s.getProject())){
			throw new WPISuiteException();
		}
		// TODO Auto-generated method stub
		return newDataLoggerModel;
	}

	/**
	 * Gets a list of DataLoggermodels with the given ID
	 */
	@Override
	public DataLoggerModel[] getEntity(Session s, String id)
			throws NotFoundException, WPISuiteException {
		DataLoggerModel[] DataLoggerModels = null;
		try{
			DataLoggerModels = db.retrieve(DataLoggerModel.class, "db_id", s.getProject()).toArray(new DataLoggerModel[0]);
		} catch (WPISuiteException e) {
			e.printStackTrace();
		}
		if( DataLoggerModels.length < 1 || DataLoggerModels[0] == null){
			throw new NotFoundException();
		}
		// TODO Auto-generated method stub
		return DataLoggerModels;
	}

	@Override
	/**
	 * Gets all of the database entitys that are DataLoggerModels
	 */
	public DataLoggerModel[] getAll(Session s) throws WPISuiteException {
		List<Model> DataLoggers = db.retrieveAll(new DataLoggerModel(), s.getProject());
		return DataLoggers.toArray(new DataLoggerModel[0]);
	}

	/**
	 * Updates the given DataLoggermodel in the database
	 */
	@Override
	public DataLoggerModel update(Session s, String content) throws WPISuiteException {
		DataLoggerModel updatedDataLogger = DataLoggerModel.fromJson(content);
		List<Model> oldDataLoggers = db.retrieve(DataLoggerModel.class, "db_id", updatedDataLogger.getDb_id(), s.getProject());
		if( oldDataLoggers.size() < 1 || oldDataLoggers.get(0) == null) {
			throw new BadRequestException("DataLogger with ID does not exist.");
		}
		DataLoggerModel existingDataLogger = (DataLoggerModel)oldDataLoggers.get(0);
		existingDataLogger.copyFrom(updatedDataLogger);
		
		if(!db.save(existingDataLogger, s.getProject() )){
			throw new WPISuiteException();
		}
		return existingDataLogger;
	}

	@Override
	public void save(Session s, DataLoggerModel model) throws WPISuiteException {
		db.save(model);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// TODO Auto-generated method stub
		ensureRole(s, Role.ADMIN);
		return (db.delete(getEntity(s, id)[0]) != null) ? true : false;
	}

	@Override
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int Count() throws WPISuiteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String advancedPut(Session s, String[] args, String content) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public String advancedPost(Session s, String string, String content) throws NotImplementedException {
		throw new NotImplementedException();
	}
	
	/**
	 * Ensures that a user is of the specified role
	 * @param session the session
	 * @param role the role being verified
	
	 * @throws WPISuiteException user isn't authorized for the given role */
	private void ensureRole(Session session, Role role) throws WPISuiteException {
		User user = (User) db.retrieve(User.class, "username", session.getUsername()).get(0);
		if(!user.getRole().equals(role)) {
			throw new UnauthorizedException();
		}
	}




}
