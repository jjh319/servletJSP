package org.zerock.myapp.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.command.DefaultCommand;
import org.zerock.myapp.command.DeleteCommand;
import org.zerock.myapp.command.GetCommand;
import org.zerock.myapp.command.PatchCommand;
import org.zerock.myapp.command.PostCommand;
import org.zerock.myapp.command.PutCommand;
import org.zerock.myapp.exception.CommandException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// 아래의 URI로 매핑하면, 모든 요청은 이 서블릿으로 "집중"
// 되게 됩니다. 그래서, 맨 앞에서, 모든 요청을 받는 식의 패턴을
// 바로 "Front Controller 패턴"이라고 합니다.
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
//		===============================
//		RESTful Service 방식으로 개발
//		===============================
//		(1) GET 	: LIST   - 전체사원조회
//		(2) PUT 	: CREATE - 신입사원등록
//		(3) POST  	: UPDATE - 기존사원수정
//		(4) DELETE 	: DELETE - 기존사원삭제
//		(5) PATCH   : READ   - 사원상제조회
//		===============================
		
		String requestURI = req.getRequestURI();
		String command = req.getMethod();
		log.info("\t+ Request URI: {}, command: {} invoked.", requestURI, command);
		
		Map<String, String[]> allParams = req.getParameterMap();
		log.info("\t+ allParams: {}", allParams);
		
		// RESTful 서비스 요청에 맞게, 각 요청을 처리할 수 있도록
		// `Command Patter`을 적용하기로 합니다.
		// 참고로, 이 `Command Pattern`은, 각 명령(Command)을 처리하는
		// 클래스를 각 Command별로 정의하고, 대신 모든 유형의 Command 객체가
		// 동일한 메소드로 처리되도록 해야 합니다. 그래서, 아래와 같이,
		// 실제 명령(Command)을 처리하는 공통메소드 이름이 `process()` 입니다.
		
		try {
			
			switch(command) {
				case "GET" 		-> { new GetCommand().process(req, res); }
				case "PUT" 		-> { new PutCommand().process(req, res); }
				case "POST" 	-> { new PostCommand().process(req, res); }
				case "DELETE" 	-> { new DeleteCommand().process(req, res); }
				case "PATCH" 	-> { new PatchCommand().process(req, res); }
				default			-> { new DefaultCommand().process(req, res); }
			} // Switch Expression
			
		} catch(CommandException e) {
			throw new ServletException(e);
		} // try-catch
		
		return;
	} // service

} // end class
