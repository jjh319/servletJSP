package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/ContextGet")
public class ContextGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");

		// ================================================
		// Application Scope에 공유된 2개의 속성값 획득
		// ================================================
//		ServletContext sc = this.getServletContext();
//		ServletConfig config = this.getServletConfig();
//		
//		log.info("\t+ sc: {}, config: {}", sc, config);
//		
//		// 현재 서블릿의 등록이름과 정보를 상속받은 메소드로 얻습니다.
//		String servletName = this.getServletName();
//		String servletInfo = this.getServletInfo();

		// ================================================
		// Session Scope에 공유된 2개의 속성값 획득
		// ================================================
//		HttpSession sess = req.getSession();
//		
//		String name = (String) sess.getAttribute("name");
//		int age 	= (int) sess.getAttribute("age");	// Auto-Unboxing: Integer -> int

		// ================================================
		// Request Scope에 공유된 2개의 속성값 획득
		// ================================================
		String name = (String) req.getAttribute("name");
		Integer age = (Integer) req.getAttribute("age");	// Auto-Unboxing: Integer -> int
		
		// ================================================
		// Request Scope에 공유된 2개의 속성 삭제
		// ================================================
		req.removeAttribute("name");
		req.removeAttribute("age");

		// ================================================
		// 응답문서 생성 및 전송
		// ================================================
		res.setCharacterEncoding("utf8");
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
//		out.println("<h3>servletName: " + servletName +"</h3>");
//		out.println("<h3>servletInfo: " + servletInfo +"</h3>");
		
		out.println("<h3>name: " + name +"</h3>");
		out.println("<h3>age: " + age +"</h3>");
		
		out.flush();
	} // service

} // end class
