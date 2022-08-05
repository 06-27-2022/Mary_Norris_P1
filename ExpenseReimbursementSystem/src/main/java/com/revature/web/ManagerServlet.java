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
import com.revature.ModelManager;
import com.revature.ModelReimbursement;
import com.revature.repository.AssociateRepoImplement;
import com.revature.repository.AssociateRepository;
import com.revature.repository.ManagerRepoImplement;
import com.revature.repository.ManagerRepository;
import com.revature.repository.ReimbursementRepoImplement;
import com.revature.repository.ReimbursementRepository;

public class ManagerServlet extends HttpServlet{ 
	
	private static final long serialVersionUID = -2523059497272923808L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		String httpVerb = request.getMethod();
		PrintWriter writer = response.getWriter();
		String resource = request.getRequestURI();
		ManagerRepository mainManagerRepository = new ManagerRepoImplement();
		ReimbursementRepository mainTicketRepository = new ReimbursementRepoImplement();
		String necessaryTicketResource = resource.replace("/ExpenseReimbursementSystem", "");
		ModelManager successfulLogin = null;
		ObjectMapper mapTime = new ObjectMapper();		
		
		switch(necessaryTicketResource) {
		
		case "/managers/list":
			if(httpVerb.equals("GET")) {
				ManagerRepository managerRepository = new ManagerRepoImplement();
				List<ModelManager> managers = managerRepository.viewManagerList();
				String json = mapTime.writeValueAsString(managers);
				response.setContentType("application/json");
				writer.write(json);
				response.setStatus(200);
			}else {response.setStatus(400);
			writer.write("Request Invalid");
			}break;
			
			
		case"/managers/login":
			response.setContentType("application/json");
			String usernameTyped = request.getParameter("username");
			String passwordTyped = request.getParameter("password");
			ModelManager currentManager = mainManagerRepository.locatebyManagerUsername(usernameTyped);
				if(httpVerb.equals("GET")) {
					if(currentManager == null) {
						writer.write("Incorrect Username/Not a Manager");
						response.setStatus(401);
					}else {
						successfulLogin = mainManagerRepository.locatebyManagerUsername(usernameTyped);
						Cookie managerCookie = new Cookie("authenticated", "true");
						response.addCookie(managerCookie);
						writer.write("Welcome Manager");
						response.setStatus(202);
					}
				}break;
				
				
		case "/managers/tickets":
			response.setContentType("application/json");
			String ticketAccessUsername = request.getParameter("username");
			String ticketAccessPassword = request.getParameter("password");
			ModelManager accesstoReimbursements = mainManagerRepository.locatebyManagerUsername(ticketAccessUsername);
			ModelManager accesstoReimbursementsPassword = mainManagerRepository.locatebyManagerPassword(ticketAccessPassword);
				if(accesstoReimbursements !=null && accesstoReimbursementsPassword != null) {
					Cookie managerTicketCookie = new Cookie("manager", "true");
					response.addCookie(managerTicketCookie);
					if(httpVerb.equals("GET")) {			
						ReimbursementRepository ticketRepository = new ReimbursementRepoImplement();
						List<ModelReimbursement> tickets = ticketRepository.viewReimbursementList();
						String json = mapTime.writeValueAsString(tickets);
						response.setContentType("application/json");
						writer.write(json);
						response.setStatus(200);
					}else if (httpVerb.equals("POST")) {
						int ID = Integer.parseInt(request.getParameter("reimbursementID"));
						ModelReimbursement ticketCollection = mainTicketRepository.locateReimbursementId(ID);
						String json = mapTime.writeValueAsString(ticketCollection);
						response.setContentType("application/json");
						writer.write("Please enter 'approved' or 'denied'");
						writer.write(json);
						String approvedORdenied = request.getParameter("status");
							if (approvedORdenied.equals("approved")){
								final String approved = "approved";
								mainTicketRepository.update(approved, ID);
							}else if (approvedORdenied.equals("denied")) {
								final String denied = "denied";
								mainTicketRepository.update(denied, ID);
							}
							
					}break;
					
				}else {response.setStatus(404);
					writer.write("Request not available.");
				}
						
		}
	}
				


			

			

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}


//create a method in ManagerRepo to authenticate Manager credentials/ to clean up code
