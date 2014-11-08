package edu.wpi.cs.wpisuitetng.modules.taskmanager.model;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

public class LayoutModel extends AbstractModel {
	final private String name;

	public LayoutModel(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public void save() {
	}

	@Override
	public void delete() {
	}

	@Override
	public String toJson() {
        return new Gson().toJson(this, LayoutModel.class);
	}
	

    public static LayoutModel fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, LayoutModel.class);
    }

    public static LayoutModel[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, LayoutModel[].class);
    }
    
	@Override
	public Boolean identify(Object o) {
        return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        LayoutModel other = (LayoutModel) obj;
        
        return this.name == other.getName();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.name;
	}
	

}
