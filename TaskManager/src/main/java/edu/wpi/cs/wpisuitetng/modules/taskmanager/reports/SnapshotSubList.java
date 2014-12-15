package edu.wpi.cs.wpisuitetng.modules.taskmanager.reports;


public class SnapshotSubList extends DataLoggerModel {
	
	
	
	public SnapshotSubList() {

	}	

	
	public boolean listContainsID(int taskID)
	{
		for (int i=taskSnapList.size(); i>=0; i--)
		{
			if (taskSnapList.get(i-1).getTaskID() == taskID)
			{
				return true;
			}
		}
		return false;
	}
	
	
	

}
