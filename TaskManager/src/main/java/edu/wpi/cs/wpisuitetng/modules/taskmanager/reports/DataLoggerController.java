package edu.wpi.cs.wpisuitetng.modules.taskmanager.reports;

/**
 * @author Joe
 * This class is used to accumulate and report data on the use of the program over time, which
 * cannot be derived from simply reading the current list of taskModels
 */

public class DataLoggerController {
	private static DataLoggerController instance = new DataLoggerController();
	private static DataLoggerModel dataLoggerModel;
	

	
	public DataLoggerController() {
		dataLoggerModel = new DataLoggerModel();
	}
	
	public static DataLoggerModel getDataModel() {
		return dataLoggerModel;
	}

}
