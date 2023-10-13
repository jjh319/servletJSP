package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/EmpInsert")
public class EmpInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/OracleCloudATPWithDriverSpy")
	private DataSource dataSource;
	
	private final String sql = "INSERT INTO emp (empno, ename, sal, deptno) VALUES (?, ?, ?, ?)";

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invked.", config);
		
		Objects.requireNonNull(this.dataSource);
		log.info("\t+ this.dataSource: {}", this.dataSource);
	} // init


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		// 1. 전송파라미터 획득
		String empId = req.getParameter("emp_id");
		String ename = req.getParameter("ename");
		String salary = req.getParameter("salary");
		String depart = req.getParameter("depart");
		
		try {
			// 2. DataSource 에서 JDBC Connection 획득 및 신규사원등록 처리
			Connection conn = this.dataSource.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(this.sql);
			pstmt.setInt(1, Integer.parseInt(empId));
			pstmt.setString(2, ename);
			pstmt.setDouble(3, Double.parseDouble(salary));
			pstmt.setInt(4, Integer.parseInt(depart));
			
			try (conn; pstmt;) {
				int affectedRows = pstmt.executeUpdate();
				
				// ------------------------------ //
				// 응답문서 생성 및 전송          //
				// ------------------------------ //
				res.setCharacterEncoding("utf8");
				res.setContentType("text/html; charset=utf8");
				
				PrintWriter out = res.getWriter();
				out.println("<h2>입력된 행의 개수: " + affectedRows + "</h2>");				
				out.flush();
			} // try-with-resources
		} catch(Exception e) {
			throw new IOException(e);
		} // try-catch
	} // service

} // end class
