package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import com.db4o.User;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;


public class UserModel extends AbstractModel {
	private char iconText;
	private String userName;
	private User user;
	
	public UserModel() {
		this.user = new User("Default User", "NoPass");
		this.userName = this.user.name;
		this.iconText = userName.charAt(0);
	}
	
	public UserModel(User user) {
		this.user = user;
		this.userName = user.name;
		this.iconText = userName.charAt(0);
	}
	
	public char getIconText() {
		return iconText;
	}
	
	public void setIconText(char iconText) {
		this.iconText = iconText;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public void setUsername(String userName) {
		this.userName = userName;
		this.user = new User(userName, "");
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
