package edu.wpi.cs.wpisuitetng.modules.taskmanager.reports;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.AddStageRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.DeleteStageRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.GetStageRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.StageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.stage.UpdateStageRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author Joe
 * This class is used to accumulate and report data on the use of the program over time, which
 * cannot be derived from simply reading the current list of taskModels
 */

public class DataLoggerController {
	private static DataLoggerController instance = new DataLoggerController();
	private static DataLoggerModel dataLoggerModel;
	
	private static AddDataLoggerRequestObserver addObserver;
	private static GetDataLoggerRequestObserver getObserver;
	private static UpdateDataLoggerRequestObserver updateObserver;
	private static DeleteDataLoggerRequestObserver deleteObserver;
	
	

	
	public DataLoggerController() {
		dataLoggerModel = new DataLoggerModel();
		StageController.addObserver = new AddStageRequestObserver(this);
		StageController.updateObserver = new UpdateStageRequestObserver(this);
		StageController.deleteObserver = new DeleteStageRequestObserver(this);
		StageController.getObserver = new GetStageRequestObserver(this);
	}
	
	public static DataLoggerModel getDataModel() {
		return dataLoggerModel;
	}
	
	/**
	 * @param stageModel - the stage to add to the database
	 */
	public static void sendAddRequest(DataLoggerModel dataLoggerModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/datalogger", HttpMethod.PUT); // PUT == create
		request.setBody(dataLoggerModel.toJson()); // put the new stage in the body of the request
		request.addObserver(addObserver); // add an observer to process the response
		request.send();
	}
	
	/**
	 * get a list of all stages from the database
	 */
	public static void sendGetRequest (DataLoggerModel dataLoggerModel) {
		final Request request = Network.getInstance().makeRequest("taskmanager/datalogger", HttpMethod.GET); // PUT == create
		request.addObserver(getObserver); // add an observer to process the response
		request.send();
	}
	
	

}
