package org.zerock.myapp.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebFilter("/*")
public class Filter2 extends HttpFilter {
	private static final long serialVersionUID = 1L;

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
		throws IOException, ServletException {
		log.trace("doFilter({}, {}, {}) invoked.", req, res, chain);
				
		// =============================
		// 전처리 영역
		// =============================
		log.info("Pre-processing ...");
		
		chain.doFilter(req, res);
		
		// =============================
		// 후처리 영역
		// ============================
		log.info("Post-processing ...");
		
		// --------------------------------- //
		// 응답문서 생성 및 전송
		// --------------------------------- //
		res.setCharacterEncoding("utf8");
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<h2>보내신 요청은 처리할 수 없습니다.</h2>");
				
		out.flush();
	} // doFilter

} // end class
