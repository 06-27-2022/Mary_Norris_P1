package com.revature;


public class ModelAssociate {
 
		private int associate_id;
		private String associate_name;
		private String associate_username;
		private String associate_password;
		
	
	public ModelAssociate(){
		super();
	}
	
	public ModelAssociate(int associate_id, String associate_name, String associate_username, String associate_password) {
		super();
		this.associate_id = associate_id;
		this.associate_name = associate_name;
		this.associate_username = associate_username;
		this.associate_password = associate_password;
}

	public int getAssociate_id() {
		return associate_id;
	}

	public void setAssociate_id(int associate_id) {
		this.associate_id = associate_id;
	}

	public String getAssociate_name() {
		return associate_name;
	}

	public void setAssociate_name(String associate_name) {
		this.associate_name = associate_name;
	}

	public String getAssociate_username() {
		return associate_username;
	}

	public void setAssociate_username(String associate_username) {
		this.associate_username = associate_username;
	}

	public String getAssociate_password() {
		return associate_password;
	}

	public void setAssociate_password(String associate_password) {
		this.associate_password = associate_password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associate_id;
		result = prime * result + ((associate_name == null) ? 0 : associate_name.hashCode());
		result = prime * result + ((associate_password == null) ? 0 : associate_password.hashCode());
		result = prime * result + ((associate_username == null) ? 0 : associate_username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelAssociate other = (ModelAssociate) obj;
		if (associate_id != other.associate_id)
			return false;
		if (associate_name == null) {
			if (other.associate_name != null)
				return false;
		} else if (!associate_name.equals(other.associate_name))
			return false;
		if (associate_password == null) {
			if (other.associate_password != null)
				return false;
		} else if (!associate_password.equals(other.associate_password))
			return false;
		if (associate_username == null) {
			if (other.associate_username != null)
				return false;
		} else if (!associate_username.equals(other.associate_username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModelAssociate [associate_id=" + associate_id + ", associate_name=" + associate_name
				+ ", associate_username=" + associate_username + ", associate_password=" + associate_password + "]";
	}
	
	
	
}	
	
	 
