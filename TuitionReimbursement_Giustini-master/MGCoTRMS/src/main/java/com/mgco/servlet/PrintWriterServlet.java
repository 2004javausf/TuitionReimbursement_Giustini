package com.mgco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintWriterServlet extends HttpServlet{
	private static final long serialVersionUID = -7593075409233783787L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doGet of PrintWriterServlet");
		PrintWriter pw = response.getWriter();
		pw.write("<h1>test</h1>");
	}
}
