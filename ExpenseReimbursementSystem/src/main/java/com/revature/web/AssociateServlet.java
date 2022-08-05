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
import com.revature.ModelReimbursement;
import com.revature.repository.ReimbursementRepository;
import com.revature.repository.ReimbursementRepoImplement;


import ch.qos.logback.classic.Logger;




public class AssociateServlet extends HttpServlet{

	
	private static final long serialVersionUID = -8878959260454490926L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String resource = request.getRequestURI();
		
		AssociateRepository mainAssociateRepository = new AssociateRepoImplement();
		ReimbursementRepository mainReimbursementRepository = new ReimbursementRepoImplement();
		String necessaryAssociateResource = resource.replace("/ExpenseReimbursementSystem","");
		ModelAssociate successfulLogin = null;
		PrintWriter writer = response.getWriter();
		ObjectMapper mapTime = new ObjectMapper();
		
		String httpVerb = request.getMethod();
		
		switch(necessaryAssociateResource) {
		case"/associates/login":
			response.setContentType("application/json");
			String usernameTyped = request.getParameter("username");
			String passwordTyped = request.getParameter("password");
			ModelAssociate currentUser = mainAssociateRepository.locatebyUsername(usernameTyped);
				if(httpVerb.equals("GET")) {
					if(currentUser == null) {
						writer.write("Incorrect Username or No Current Account");
						response.setStatus(401);
					}else {
						successfulLogin = mainAssociateRepository.locatebyUsername(usernameTyped);
						Cookie associateGetsCookie = new Cookie("authenticated", "true");
						response.addCookie(associateGetsCookie);
						writer.write("Welcome Associate");
						response.setStatus(202);
					}
				}else if (httpVerb.equals("POST")) {
						ModelAssociate rookie = new ModelAssociate();
						System.out.println(request.getQueryString());				
						String rookieName = request.getParameter("associate_name");
						String rookieUsername = request.getParameter("associate_username");
						String rookiePassword = request.getParameter("associate_password");
						ModelAssociate usernameTaken = mainAssociateRepository.locatebyUsername(rookieUsername);
							
						if (usernameTaken != null) {
							writer.write("Username already exsists. Try again");
							response.setStatus(400);
						} else {
							rookie = new ModelAssociate(0, rookieName, rookieUsername, rookiePassword);
							mainAssociateRepository.save(rookie);
							Cookie newAssociateGetsCookie = new Cookie("authenticated","true");
							response.addCookie(newAssociateGetsCookie);
							response.setContentType("application/json");
							writer.write("Welcome New Revature Associate");
							response.setStatus(201);						
			
				}
				break;
				}
		
				
				
		case"/associates/list":
			if(httpVerb.equals("GET")) {
				response.setContentType("application/json");
				AssociateRepository associateRepository = new AssociateRepoImplement();
				List<ModelAssociate> associates = associateRepository.viewAssociateList();
				mapTime = new ObjectMapper();
				String json = mapTime.writeValueAsString(associates);
				writer.write(json);
				response.setStatus(200);
			}else {response.setStatus(404);
				writer.write("Request Not Possible");
			}break;
			
		case"/associates/ticket-submission":
			if(httpVerb.equals("POST")) {
				ModelReimbursement newSubmission = new ModelReimbursement();
				System.out.println(request.getQueryString());				
				String associateName = request.getParameter("associate_name");
				String associateUsername = request.getParameter("associate_username");
				String amount = request.getParameter("amount");
					double amountParsed = Double.parseDouble(amount);
				String description = request.getParameter("description");
				
				newSubmission = new ModelReimbursement(0, associateName, associateUsername, 1, amountParsed, description, "pending");
				mainReimbursementRepository.save(newSubmission);
				Cookie newTicketCookie = new Cookie("authenticated","true");
				response.addCookie(newTicketCookie);
				response.setContentType("application/json");
				writer.write("Ticket processed. If ticket request is still pending after 3 buisness days, please contact your manager.");
				response.setStatus(201);
				
			}
			
		}
			
	}
			
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

}
