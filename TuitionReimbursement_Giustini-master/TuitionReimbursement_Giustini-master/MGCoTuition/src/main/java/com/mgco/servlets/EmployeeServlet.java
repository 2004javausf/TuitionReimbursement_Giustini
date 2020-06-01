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
import com.mgco.beans.Employee;
import com.mgco.daoimpl.EmployeeDAOImpl;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
	}
	private void setAccessControlHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Methods", "*");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		System.out.println("In doGet of EmployeeServlet");
		String filter = request.getPathInfo().substring(1);
		
		ObjectMapper mapper = new ObjectMapper();
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		PrintWriter pw = response.getWriter();
		String empJSON;
		if(filter.equals("")) {
			try {
				empJSON = mapper.writeValueAsString(edi.getEmployeeList());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				pw.print(empJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.flush();
		} else {
			try {
				empJSON = mapper.writeValueAsString(edi.getEmployeeById(filter));
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				pw.print(empJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			pw.flush();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		System.out.println("In doPost of EmployeeServlet");
		Employee em = null;
		ObjectMapper mapper = new ObjectMapper();
		//convert JSON to POJO
		em = mapper.readValue(request.getInputStream(), Employee.class);
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		Employee checkEm = edi.getEmployeeByName(em.getUsername());
		PrintWriter pw = response.getWriter();
		if(checkEm.getPassword().equals(em.getPassword())) {
			String empJSON = mapper.writeValueAsString(checkEm);
			pw.write(empJSON);
			pw.close();
			response.setStatus(201);
		}
		pw.write("Username or Password is incorrect. Try again.");
		pw.close();
	}

}
