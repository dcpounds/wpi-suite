package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class StageEntityManager implements EntityManager<StageModel> {
	
	private Data db;

	public StageEntityManager(Data db){
		this.db = db;
	}

	/** Creates a new entity for saving in the database
	 * 
	 */
	@Override
	public StageModel makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		final StageModel newStageModel = StageModel.fromJson( content );
		
		if( !db.save(newStageModel, s.getProject())){
			throw new WPISuiteException();
		}
		// TODO Auto-generated method stub
		return newStageModel;
	}

	/**
	 * Gets a list of stagemodels with the given ID
	 */
	@Override
	public StageModel[] getEntity(Session s, String id)
			throws NotFoundException, WPISuiteException {
		StageModel[] stageModels = null;
		try{
			stageModels = db.retrieve(StageModel.class, "id", s.getProject()).toArray(new StageModel[0]);
		} catch (WPISuiteException e) {
			e.printStackTrace();
		}
		if( stageModels.length < 1 || stageModels[0] == null){
			throw new NotFoundException();
		}
		// TODO Auto-generated method stub
		return stageModels;
	}

	@Override
	/**
	 * Gets all of the database entitys that are stageModels
	 */
	public StageModel[] getAll(Session s) throws WPISuiteException {
		List<Model> stages = db.retrieveAll(new StageModel(), s.getProject());
		return stages.toArray(new StageModel[0]);
	}

	/**
	 * Updates the given stagemodel in the database
	 */
	@Override
	public StageModel update(Session s, String content)
			throws WPISuiteException {
		StageModel updatedStage = StageModel.fromJson(content);
		List<Model> oldStages = db.retrieve(StageModel.class, "name", updatedStage.getTitle(), s.getProject());
		if( oldStages.size() < 1 || oldStages.get(0) == null) {
			throw new NotFoundException();
		}
		StageModel existingStage = (StageModel)oldStages.get(0);
		existingStage = new StageModel(updatedStage);
		
		if(!db.save(existingStage, s.getProject() )){
			throw new WPISuiteException();
		}
		// TODO Auto-generated method stub
		return existingStage;
	}

	@Override
	public void save(Session s, StageModel model) throws WPISuiteException {
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
	public String advancedPut(Session s, String[] args, String content)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String advancedPost(Session s, String string, String content)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
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
