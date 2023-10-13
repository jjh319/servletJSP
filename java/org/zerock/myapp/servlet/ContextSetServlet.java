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

@WebServlet("/ContextSet")
public class ContextSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");

//		===============================
//		Application Scope 에 속성 바인딩
//		===============================
//		ServletContext sc = this.getServletContext();
//		
//		// Application Scope 공유데이터 영역에 이름과 나이 올림
//		sc.setAttribute("name", "Yoseph");
//		sc.setAttribute("age", 23);			// Auto-Boxing : int -> Integer

//		===============================
//		Session Scope 에 속성 바인딩
//		===============================
//		HttpSession sess = req.getSession();
//		
//		sess.setAttribute("name", "Yoseph");
//		sess.setAttribute("age", 23);

//		===============================
//		Request Scope 에 속성 바인딩
//		===============================
		req.setAttribute("name", "YOSEPH");
		req.setAttribute("age", 21);
		
		
//		===============================
//		응답문서 생성 및 전송
//		===============================
		res.setCharacterEncoding("utf8");
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
//		out.println("<h1>Application Scope에 새로운 속성 추가 성공</h1>");	
//		out.println("<h1>Session Scope에 새로운 속성 추가 성공</h1>");
		out.println("<h1>Request Scope에 새로운 속성 추가 성공</h1>");
		
		out.flush();
	} // service

} // end class
