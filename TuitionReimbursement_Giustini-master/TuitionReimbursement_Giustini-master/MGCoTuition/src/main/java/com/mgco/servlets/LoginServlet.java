package com.mgco.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgco.beans.UserLogin;
import com.mgco.daoimpl.EmployeeDAOImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doGet of LoginServlet");
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		System.out.println("in doPost of LoginServlet");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		System.out.println(user + pass);
		//ObjectMapper mapper = new ObjectMapper();
		//UserLogin u = new UserLogin();
		//u = mapper.readValue(request.getInputStream(), UserLogin.class);
		boolean j = edi.getLogin(user, pass);
		if(j) {
			request.getRequestDispatcher("home.html").forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
		}
	}

}
