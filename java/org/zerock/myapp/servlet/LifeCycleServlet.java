package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/LifeCycle")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);

	} // init


	@Override
	public void destroy() {
		log.trace("destroy() invoked.");
		
	} // destroy


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)	
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");

		// (1) try () {
		try {					
			// ==== 응답문서 전송 ==================
			
			// 첫번째로 무조건 이 코드 넣어라!
			res.setCharacterEncoding("utf8");		
			res.setContentType("text/html; charset=utf8");
			
//			(2) @Cleanup			
			PrintWriter out = res.getWriter();
			
			try (out;) {	// (3) try-with-resources 블록의 단독사용
				out.println("<name>이름</name>");			
				out.flush();
			} // try-with-resources
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
