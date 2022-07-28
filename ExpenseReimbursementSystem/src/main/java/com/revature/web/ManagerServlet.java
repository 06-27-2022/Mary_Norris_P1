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
		
		Cookie[] allCookies = request.getCookies();
			boolean managerCookies = false;
			boolean associateCookies = false;
			if(allCookies != null) {
				for(Cookie cookies : allCookies) {
					if(cookies.getName().equals("manager")) {
						managerCookies = true;
					}
					if(cookies.getName().equals("authenticated")) {
						associateCookies = true;
					}
				}

		ManagerRepository mainManagerRepository = new ManagerRepoImplement();
		ReimbursementRepository mainTicketRepository = new ReimbursementRepoImplement();
		String necessaryTicketResource = resource.replace("/ExpenseReimbursementSystem", "");
		ModelManager successfulLogin = null;
		
		
		switch(necessaryTicketResource) {
		case "/managers":
			if(httpVerb.equals("GET")) {
				ManagerRepository managerRepository = new ManagerRepoImplement();
				List<ModelManager> managers = managerRepository.viewManagerList();
				ObjectMapper mapTime = new ObjectMapper();
				String json = mapTime.writeValueAsString(managers);
				response.setContentType("application/json");
				writer.write(json);
				response.setStatus(200);
			}else {response.setStatus(400);
			writer.write("Request Invalid");
		break;}
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
				}
		case "/managers/tickets":
			response.setContentType("application/json");
			String ticketAccessUsername = request.getParameter("username");
			String ticketAccessPassword = request.getParameter("password");
			ModelManager accesstoReimbursements = mainManagerRepository.locatebyManagerUsername(ticketAccessUsername);
			ModelManager accesstoReimbursementsPassword = mainManagerRepository.locatebyManagerPassword(ticketAccessPassword);
			ModelReimbursement findTicket = null;
				if(accesstoReimbursements !=null && accesstoReimbursementsPassword != null) {
					Cookie managerTicketCookie = new Cookie("manager", "true");
					response.addCookie(managerTicketCookie);
					if(httpVerb.equals("GET") && managerCookies ) {			
						ReimbursementRepository ticketRepository = new ReimbursementRepoImplement();
						List<ModelReimbursement> tickets = ticketRepository.viewReimbursementList();
						ObjectMapper mapTime = new ObjectMapper();
						String json = mapTime.writeValueAsString(tickets);
						response.setContentType("application/json");
						writer.write(json);
						response.setStatus(200);
					}else if (httpVerb.equals("POST") && managerCookies) {
						int ID = Integer.parseInt(request.getParameter("reimbursement_id"));
						ModelReimbursement ticketCollection = mainTicketRepository.locateReimbursementId(ID);
						ObjectMapper mapTime = new ObjectMapper();
						String approved = new String ();
						String denied = new String ();
						ticketCollection.setApprovedORdenied(approved);
						writer.write("Please enter 'approved' or 'denied'");
					}
						
				}
			}
			
		}
			
	}
			

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}

