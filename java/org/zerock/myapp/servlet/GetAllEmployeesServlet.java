package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// 기본생성자가 있어야, 최초 HTTP request 가 이 서블릿으로 왔을 때,
// 서블릿 컨테이너가 단 한번만(SingleTon) 객체를 생성하고자 할때에, 쉽게 생성할 수가 있죠.
@NoArgsConstructor
public class GetAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Resource(name = "jdbc/OracleCloudATP")
	@Resource(name = "jdbc/OracleCloudATPWithDriverSpy")
	private DataSource dataSource;
	
	private final String sql = """
			SELECT 
				/*+ INDEX_DESC(t EMPLOYEES_SALARY_IDX) */
			    *
			FROM
			    employees t
			WHERE
			    salary >= ?
			""";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init(config) invoked.");
		
		try {
			Objects.requireNonNull(this.dataSource);
			log.info("\t+ this.dataSource: {}", this.dataSource);
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch		
	} // init


	@Override
	public void destroy() {
		log.trace("destroy() invoked.");

	} // destroy


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			@Cleanup("close")
			Connection conn = this.dataSource.getConnection();	// 대여
			
			String sal = req.getParameter("salary");
			Objects.requireNonNull(sal);
			
			@Cleanup
			PreparedStatement pstmt = conn.prepareStatement(this.sql);
			pstmt.setDouble(1, Double.parseDouble(sal));
			
			@Cleanup
			ResultSet rs = pstmt.executeQuery();
			Objects.requireNonNull(rs);
			
//			============ 최종 응답문서 생성 ============== //
			
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup
			PrintWriter out = res.getWriter();
			
			out.println("<html>");
			out.println("	<head>");
			out.println("		<title>전체사원목록</title>");
			out.println("		<style>");
			out.println("			table, tr, th, td {");
			out.println("				border: 1px solid darkgray;");
			out.println("				border-collapse: collapse;");
			out.println("			}");
			out.println("			th, td {");
			out.println("				padding: 10px;");
			out.println("			}");
			out.println("		</style>");
			out.println("	</head>");
			out.println("	<body>");
			out.println("		<table>");
			out.println("			<caption><h1>전체사원목록</h1><caption>");
			out.println("			<tr>");
			out.println("				<th>EMPLOYEE_ID</th>");
			out.println("				<th>FIRST_NAME</th>");
			out.println("				<th>LAST_NAME</th>");
			out.println("				<th>EMAIL</th>");
			out.println("				<th>PHONE_NUMBER</th>");
			out.println("				<th>HIRE_DATE</th>");
			out.println("				<th>JOB_ID</th>");
			out.println("				<th>SALARY</th>");
			out.println("				<th>COMMISSION_PCT</th>");
			out.println("				<th>MANAGER_ID</th>");
			out.println("				<th>DEPARTMENT_ID</th>");
			out.println("			</tr>");

			// 순회코드를 넣어서, 테이블을 만들어 줍니다.
			
			while(rs.next()) {
				String employeeId 		= rs.getString("EMPLOYEE_ID");
				String firstName 		= rs.getString("FIRST_NAME");
				String lastName 		= rs.getString("LAST_NAME");
				String email 			= rs.getString("EMAIL");
				String phoneNumber 		= rs.getString("PHONE_NUMBER");
				String hireDate 		= rs.getString("HIRE_DATE");
				String jobId 			= rs.getString("JOB_ID");
				String salary 			= rs.getString("SALARY");
				String commissionPct 	= rs.getString("COMMISSION_PCT");
				String managerId 		= rs.getString("MANAGER_ID");
				String departmentId 	= rs.getString("DEPARTMENT_ID");
				
//				------------------------------------------ 
				
				out.println("			<tr>");
				out.println("				<td>"+ employeeId +"</td>");
				out.println("				<td>"+ firstName +"</td>");
				out.println("				<td>"+ lastName +"</td>");
				out.println("				<td>"+ email +"</td>");
				out.println("				<td>"+ phoneNumber +"</td>");
				out.println("				<td>"+ hireDate +"</td>");
				out.println("				<td>"+ jobId +"</td>");
				out.println("				<td>"+ salary +"</td>");
				out.println("				<td>"+ commissionPct +"</td>");
				out.println("				<td>"+ managerId +"</td>");
				out.println("				<td>"+ departmentId +"</td>");
				out.println("			</tr>");
			} // while

			out.println("		</table>");
			out.println("	</body>");
			out.println("</html>");
			out.flush();
		} catch(Exception e) {
			throw new IOException(e);
		} // try-catch
		
	} // service

} // end class
