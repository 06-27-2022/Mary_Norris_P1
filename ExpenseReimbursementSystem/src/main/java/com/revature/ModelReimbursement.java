package com.revature;

import java.util.Objects;

public class ModelReimbursement {
	
	private int reimbursementId;
	private String associateName;
	private String associateUsername;
	private int managerId;
	private double reimbursementAmount;
	private String reimbursementDescription;
	private String approvedORdenied;
	
	
	public ModelReimbursement() {
		super();
	}
	
	public ModelReimbursement(int reimbursementId, String associateName, String associateUsername, int managerId, 
		double reimbursementAmount, String reimbursementDescription, String approvedORdenied){
		
		this.reimbursementId = reimbursementId;
		this.associateName = associateName;
		this.associateUsername = associateUsername;
		this.managerId = managerId;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementDescription = reimbursementDescription;
		this.approvedORdenied = approvedORdenied;
		
	}
	
	
	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getAssociateUsername() {
		return associateUsername;
	}

	public void setAssociateUsername(String associateUsername) {
		this.associateUsername = associateUsername;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public String getReimbursementDescription() {
		return reimbursementDescription;
	}

	public void setReimbursementDescription(String reimbursementDescription) {
		this.reimbursementDescription = reimbursementDescription;
	}

	public String getApprovedORdenied() {
		return approvedORdenied;
	}

	public void setApprovedORdenied(String approvedORdenied) {
		this.approvedORdenied = approvedORdenied;
	}


	@Override
	public int hashCode() {
		return Objects.hash(approvedORdenied, associateName, associateUsername, managerId, reimbursementAmount,
				reimbursementDescription, reimbursementId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelReimbursement other = (ModelReimbursement) obj;
		return Objects.equals(approvedORdenied, other.approvedORdenied)
				&& Objects.equals(associateName, other.associateName)
				&& Objects.equals(associateUsername, other.associateUsername) && managerId == other.managerId
				&& Double.doubleToLongBits(reimbursementAmount) == Double.doubleToLongBits(other.reimbursementAmount)
				&& Objects.equals(reimbursementDescription, other.reimbursementDescription)
				&& reimbursementId == other.reimbursementId;
	}

	@Override
	public String toString() {
		return "ModelReimbursement [reimbursementId=" + reimbursementId + ", associateName=" + associateName
				+ ", associateUsername=" + associateUsername + ", managerId=" + managerId + ", reimbursementAmount="
				+ reimbursementAmount + ", reimbursementDescription=" + reimbursementDescription + ", approvedORdenied="
				+ approvedORdenied + "]";
	}
	
	
}



