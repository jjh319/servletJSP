package org.zerock.myapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(urlPatterns= { "/xxx", "/yyy" })
public class Hello2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("doPost(req, res) invoked.");
		
		String name = req.getParameter("name");
		String age 	= req.getParameter("age");
		
		log.info("\t+ name: {}, age: {}", name, age);
	} // doPost


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("doGet(req, res) invoked.");
		
		String name = req.getParameter("name");
		String age 	= req.getParameter("age");
		
		log.info("\t+ name: {}, age: {}", name, age);
	} // doGet

} // end class
