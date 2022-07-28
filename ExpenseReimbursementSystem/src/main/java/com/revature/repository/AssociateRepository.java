package com.revature.repository;

import java.util.List;
import com.revature.ModelAssociate;

public interface AssociateRepository {
	
	List<ModelAssociate> viewAssociateList();
	
	void save(ModelAssociate additonalAssociate);
	
	void update(ModelAssociate associate);
	
	ModelAssociate locatebyUsername(String associate_username);
}
