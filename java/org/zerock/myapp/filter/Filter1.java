package org.zerock.myapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebFilter("/*")
public class Filter1 extends HttpFilter {
	private static final long serialVersionUID = 1L;

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
		throws IOException, ServletException {
		log.trace("doFilter(req, res, {}) invoked.", chain);
				
		// =============================
		// 전처리 영역
		// =============================
		log.info("Pre-processing ...");
		
		chain.doFilter(req, res);
		
		// =============================
		// 후처리 영역
		// ============================
		log.info("Post-processing ...");
				
	} // doFilter

} // end class
