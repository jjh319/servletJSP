package org.zerock.myapp.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet(
		urlPatterns = { "/InitParamAnno" }, 
		initParams = { 
			@WebInitParam(name = "PARAM1", value = "VALUE1", description = "설명1"), 
			@WebInitParam(name = "PARAM2", value = "VALUE2", description = "설명2")
		})
public class InitParamAnnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	// 초기화 파라미터 필드
	private String dirPath;
	private String userid;
	
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);
		
		// init 라이프사이클 메소드 내에서, 물려받은 getInitParameter() 메소드로
		// 이 서블릿의 초기화 파라미터를 얻어내고자 한다면, 반드시 아래와 같이,
		// 부모의 init(config) 메소드를 명시적으로 호출하고 난 이후에라야 정상적으로
		// 초기화 파라미터를 얻을 수가 있다.!!!
		// 때문에, 이렇게 어렵게 하지 말고, 어차피 매개변수로 ServletConfig 객체가
		// 넘어오니, 이 ServletConfig 객체의 getInitParameter() 메소드로
		// 서블릿과 관련된 모든 설정을 얻어낼 수 있다!! (***** 가장 안정적이고 확실하다!!!)
//		super.init(config);
		
		this.dirPath = config.getInitParameter("PARAM1");
		this.userid = config.getInitParameter("PARAM2");
	} // init


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		log.info("\t+ dirPath: " + dirPath);
		log.info("\t+ userid: " + userid);
		
	} // service

} // end class
