package com.revature;

import java.util.Objects;

public class ModelManager {

	private int managerId;
	private String managerName;
	private String managerUsername;
	private String managerPassword;
	
	
	public ModelManager() {
		super();
	}
	
	public ModelManager(int managerId, String managerName, String managerUsername, String managerPassword) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.managerUsername = managerUsername;
		this.managerPassword = managerPassword;
	}
	
	
		public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}



		@Override
		public int hashCode() {
			return Objects.hash(managerId, managerName, managerPassword, managerUsername);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ModelManager other = (ModelManager) obj;
			return managerId == other.managerId && Objects.equals(managerName, other.managerName)
					&& Objects.equals(managerPassword, other.managerPassword)
					&& Objects.equals(managerUsername, other.managerUsername);
		}

		@Override
		public String toString() {
			return "ModelManager [managerId=" + managerId + ", managerName=" + managerName + ", managerUsername="
					+ managerUsername + ", managerPassword=" + managerPassword + "]";
		}
		
		
}
