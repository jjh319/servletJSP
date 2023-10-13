package org.zerock.myapp.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.exception.CommandException;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class PatchCommand implements Command {

	
	// 지정된 사번에 해당하는 사원의 상세정보조회
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res)
		throws CommandException {
		log.trace("process(req, res) invoked.");
		
		try {
			// Step.1 전송파라미터 획득 및 DTO 객체 생성
			String empno = req.getParameter("empno");
			
			EmpDTO dto = new EmpDTO();
			dto.setEmpno(Integer.valueOf(empno));
			
			// Step.2 DAO와 DAO에 전달된 DTO를 통해서 지정된 사번정보조회
			EmpDAO dao = new EmpDAO();
			EmpVO vo = dao.selectEmployee(dto);
			
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
			
			out.println("<h3>사원: " + vo + "</h3>");
			
			out.println("</body>");
			out.println("</html>");
			
			out.flush();
		} catch(Exception e) {
			throw new CommandException(e);
		} // try-catch
	} // process

} // end class
