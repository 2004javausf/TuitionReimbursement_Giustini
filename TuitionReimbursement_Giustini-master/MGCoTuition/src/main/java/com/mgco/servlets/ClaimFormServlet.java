package com.mgco.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgco.beans.ClaimForm;
import com.mgco.daoimpl.ClaimFormDAOImpl;

public class ClaimFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
	}
	private void setAccessControlHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Methods", "*");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		System.out.println("In doGet ReinbursementFormServlet");
		String filter = request.getPathInfo().substring(1);
		
		ObjectMapper mapper = new ObjectMapper();
		ClaimFormDAOImpl cdi = new ClaimFormDAOImpl();
		PrintWriter pw = response.getWriter();
		String claimJSON;
		if(filter.equals("")) {
			try {
				claimJSON = mapper.writeValueAsString(cdi.getClaimList());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				pw.print(claimJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.flush();			
		} else {
			try {
				claimJSON = mapper.writeValueAsString(cdi.getClaimById(Integer.parseInt(filter)));
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				pw.print(claimJSON);
			} catch (NumberFormatException | JsonProcessingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		System.out.println("In doPost ReinbursementFormServlet");
				ClaimForm rf = null;
				ObjectMapper mapper = new ObjectMapper();
				//convert JSON to java object
				rf = mapper.readValue(request.getInputStream(), ClaimForm.class);
				System.out.println(rf);
				ClaimFormDAOImpl cdi = new ClaimFormDAOImpl();
				try {
					cdi.insertClaim(rf);
					PrintWriter pw = response.getWriter();
					pw.write("submitted");
					pw.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
}
