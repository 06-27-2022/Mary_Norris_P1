package com.revature.repository;

import java.util.List;
import com.revature.ModelReimbursement;

public interface ReimbursementRepository {

	List<ModelReimbursement> viewReimbursementList();
	
	void save(ModelReimbursement additionalReimbursement);
	
	void update(String approvedORdenied, int reimbursementID);
		
	ModelReimbursement locateReimbursementId(int reimbursementId);
	
	ModelReimbursement locateReimbursementUsername(String usernameOnTicket);
	
	ModelReimbursement filterByStatus(String currentStatus);
}
