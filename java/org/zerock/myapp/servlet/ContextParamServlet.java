package org.zerock.myapp.servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/ContextParam")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String contextParam1;
	private String contextParam2;
	
		
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init(config) invoked.");
		
		// ServletContext 객체가 있어야, Context Parameter의 값을 얻어낼 수가 있습니다.
		// 그런데, init Callback에서는 이 ServletContext 객체가 없는데,
		// 어떻게 Context Parameter의 값을 얻어낼 수가 있을까요!?
		
		ServletContext ctx = config.getServletContext();
		
		Objects.requireNonNull(ctx);
		log.info("\t+ ctx: {}", ctx);
		
//		---
		
		this.contextParam1 = ctx.getInitParameter("contextParam1");
		this.contextParam2 = ctx.getInitParameter("contextParam2");
		log.info("\t+ contextParam1: {}, contextParam2: {}", contextParam1, contextParam2);
	} // init

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");

		log.info("\t+ contextParam1: {}", this.contextParam1);
		log.info("\t+ contextParam2: {}", this.contextParam2);
	} // service

} // end class
