package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

public class StatusModel extends AbstractModel {
	private List<String> statusNames;

	public StatusModel() {
		statusNames.add("New");
		statusNames.add("In Progress");
		statusNames.add("Testing");
		statusNames.add("Completed");
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getStatusNames() {
		return statusNames;
	}
	
	public List<String> addStatusName(String statusName) {
		statusNames.add(statusName);
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
