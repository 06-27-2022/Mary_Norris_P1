package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ModelAssociate;
import com.revature.repository.AssociateRepoImplement;
import com.revature.repository.AssociateRepository;




public class AssociateServlet extends HttpServlet{

	
	private static final long serialVersionUID = -8878959260454490926L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String resource = request.getRequestURI();
		
		AssociateRepository mainAssociateRepository = new AssociateRepoImplement();
		String necessaryAssociateResource = resource.replace("/ExpenseReimbursementSystem", "");
		ModelAssociate successfulLogin = null;
		PrintWriter writer = response.getWriter();
		
		String httpVerb = request.getMethod();
		
		switch(necessaryAssociateResource) {
		case"/associates/login":
			response.setContentType("application/json");
			String usernameTyped = request.getParameter("username");
			String passwordTyped = request.getParameter("password");
			ModelAssociate currentUser = mainAssociateRepository.locatebyUsername(usernameTyped);
				if(httpVerb.equals("GET")) {
					if(currentUser == null) {
						writer.write("Incorrect Username/No Current Account");
						response.setStatus(401);
					}else {
						successfulLogin = mainAssociateRepository.locatebyUsername(usernameTyped);
						Cookie associateGetsCookie = new Cookie("authenticated", "true");
						response.addCookie(associateGetsCookie);
						writer.write("Welcome Associate");
						response.setStatus(202);
					}
				}else if (httpVerb.equals("POST")) {
					ModelAssociate additionalAssociate = new ModelAssociate();
					if(currentUser == null) {
						additionalAssociate = new ModelAssociate(0, "name", "username", "password");
						mainAssociateRepository.save(additionalAssociate);
						successfulLogin = mainAssociateRepository.locatebyUsername(usernameTyped);
						Cookie newAssociateGetsCookie = new Cookie("authenticated","true");
						response.addCookie(newAssociateGetsCookie);
						writer.write("Welcome New Revature Associate");
						response.setStatus(201);						
					}else {response.setStatus(400);
					writer.write("Username already exsists");
					}
					break;
				}
		case"associates/list":
			if(httpVerb.equals("GET")) {
				response.setContentType("application/json");
				AssociateRepository associateRepository = new AssociateRepoImplement();
				List<ModelAssociate> associates = associateRepository.viewAssociateList();
				ObjectMapper mapTime = new ObjectMapper();
				String json = mapTime.writeValueAsString(associates);
				writer.write(json);
				response.setStatus(200);
			}else {response.setStatus(404);
				writer.write("Request Not Possible");
			}
		}
			
}	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

}
