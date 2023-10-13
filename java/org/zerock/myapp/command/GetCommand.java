package org.zerock.myapp.command;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.exception.CommandException;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class GetCommand implements Command {

	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res)
		throws CommandException {
		log.trace("process(req, res) invoked.");		
		
		try {
			// 참고로 DAO 객체는 매번 new 연산자로 생성해서 사용해야 합니다.
			// 만일 한번만 DAO 객체를 생성하고, 이를 필드에 저장해서, "재사용"하고자 한다면
			// DAO의 모든 메소드는 반드시 Multi-Thread 환경하에서, "Thread-Safe"하게 구현
			// 되어야 합니다.
			EmpDAO dao = new EmpDAO();
			List<EmpVO> list = dao.selectAllEmployees();
			
//			=============================
//			응답문서 생성 및 전송
//			=============================
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup
			PrintWriter out = res.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("	<ol>");
			
			// List 를 차례로 순회하면서, 각 사원정보를 출력하는 태그를 생성합니다.
			// void accept(T t);
			String format = "<li>%s, %s, %s, %s, %s, %s, %s, %s</li>";
			
			list.forEach(vo -> {
				out.println(
					String.format(format, 
						vo.getEmpno(), 
						vo.getEname(), 
						vo.getJob(), 
						vo.getMgr(), 
						vo.getHireDate(), 
						vo.getSal(), 
						vo.getComm(), 
						vo.getDeptno()
					)
				);
			});

			out.println("	</ol>");
			out.println("</body>");
			out.println("</html>");
			
			out.flush();
		} catch(Exception e) {
			throw new CommandException(e);
		} // try-catch
	} // process

} // end class
