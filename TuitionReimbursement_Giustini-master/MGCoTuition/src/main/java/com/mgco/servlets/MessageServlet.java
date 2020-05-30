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
import com.mgco.beans.Message;
import com.mgco.daoimpl.MessageDAOImpl;

public class MessageServlet extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		System.out.println("In doGet MessageServlet");
		String filter = request.getPathInfo().substring(1);
		
		ObjectMapper mapper = new ObjectMapper();
		MessageDAOImpl mdi = new MessageDAOImpl();
		PrintWriter pw = response.getWriter();
		String msgJSON;
		if(filter.equals("")) {
			try {
				msgJSON = mapper.writeValueAsString(mdi.getMessageList());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				pw.print(msgJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.flush();			
		} else {
			try {
				msgJSON = mapper.writeValueAsString(mdi.getMessagesById(Integer.parseInt(filter)));
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				pw.print(msgJSON);
			} catch (JsonProcessingException | NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			pw.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		System.out.println("In doPost MessageServlet");
		Message msg = null;
		ObjectMapper mapper = new ObjectMapper();
		msg = mapper.readValue(request.getInputStream(), Message.class);
		System.out.println(msg);
		MessageDAOImpl mdi = new MessageDAOImpl();
		try {
			mdi.insertMessage(msg);
			PrintWriter pw = response.getWriter();
			pw.write("<h3>Added Message</h3>");
			pw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
