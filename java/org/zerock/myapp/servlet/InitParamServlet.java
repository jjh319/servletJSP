package org.zerock.myapp.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebServlet("/InitParam")
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String dirPath;
	private String userid;


	// 초기화 담당하는 Life Cycle 메소드
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);
		
		// 서블릿의 등록된 초기화 파라미터가 필요할 때에는, 서블릿 객체가
		// 생성된 직후(즉, NEW상태)에서 한번만 얻는 것입니다.
		this.dirPath = config.getInitParameter("PARAM1");
		this.userid = config.getInitParameter("PARAM2");
		
		log.info("\t+ param1: {}, param2: {}", this.dirPath, this.userid);
	} // init
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
//		String dirPath = getInitParameter("PARAM1");
//		String userid = getInitParameter("PARAM2");
		
		log.info("\t+ 1. dirPath: {}, userid: {}", this.dirPath, this.userid);
		
		
	} // service
	

} // end class
