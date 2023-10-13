package org.zerock.myapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebFilter("/*")
public class MyFilter
	extends HttpFilter {
//	implements Filter {
	private static final long serialVersionUID = 1L;

	
	@Override
	public void init(FilterConfig config)
		throws ServletException {
		log.trace("init({}) invoked.", config);
		
		// 초기화 파라미터를 얻어보자!!!
		String param1 = config.getInitParameter("PARAM1");
		String param2 = config.getInitParameter("PARAM2");
		
		log.info("\t+ param1: {}, param2: {}", param1, param2);
	} // init

	@Override
	public void doFilter(
			ServletRequest req, ServletResponse res, FilterChain chain
		) throws IOException, ServletException {
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

	@Override
	public void destroy() {
		log.trace("destroy() invoked.");

	} // destroy

} // end class
