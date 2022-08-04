package com.revature.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackUtility {
	
	public static final Logger logger = LoggerFactory.getLogger(LogbackUtility.class);
	//cannot use protected because it is being implemented in an interface
	
	public static void main(String[] args) {
		
		try {
			Class.forName("org.slf4j.Logger");
		}catch(Exception e) {
			logger.trace(null);
			e.printStackTrace();
		}
	}

}
