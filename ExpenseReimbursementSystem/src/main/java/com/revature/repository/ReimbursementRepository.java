package com.revature.repository;

import java.util.List;
import com.revature.ModelReimbursement;

public interface ReimbursementRepository {

	List<ModelReimbursement> viewReimbursementList();
	
	void save(ModelReimbursement additionalReimbursement);
		
	void update(ModelReimbursement reimbursement);
		
	ModelReimbursement locateReimbursementId(int reimbursementId);
}
