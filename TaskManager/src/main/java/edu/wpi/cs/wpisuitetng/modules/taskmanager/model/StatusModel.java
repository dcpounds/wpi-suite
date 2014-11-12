package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * @author Alec
 * Used to store the various statuses that a task can have
 * We use a static array so that statuses are kept constant wherever they are used
 */
public class StatusModel extends AbstractModel {
	private static String[] statusNames;

	public StatusModel() {
		StatusModel.statusNames = new String[]{"New","In Progress","Testing","Complete"};
	}
	
	public String[] getStatusNames() {
		return statusNames;
	}
	
	public String[] addStatusName(String statusName) {
		statusNames[statusNames.length] = statusName;
		return statusNames;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
