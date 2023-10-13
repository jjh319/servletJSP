package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/UsingJNDIResource")
public class UsingJNDIResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// JNDI Lookup을 통해 CP의 주소획득
	private DataSource dataSource;




	@Override
	public void init() throws ServletException {
		log.trace("init() invoked.");
		
		try {	// JNDI Lookup
			// 1. JNDI tree 의 뿌리(root)에 접근할 수 있게 해주는 객체를 얻어야 함
			//	  방법: InitialContext 타입의 객체를 생성하시면 됩니다.
			Context ctx = new InitialContext();
			
			// 2. Context 객체를 얻고나서, Context 인터페이스에 정의된 메소드로 JNDI lookup수행
			//    특이사항: JNDI tree에 공유자원객체를 바인딩할 때에는, 이름 앞에 아래와 같은 prefix를 붙여야 함.
			//              Prefix : "java:comp/env/" + 공유자원객체의 이름 문법으로 바인딩 됨.
			Object obj = ctx.lookup("java:comp/env/jdbc/OracleCloudATP");
			
			// 3. null 인지 아닌지 검증
//			Objects.requireNonNull(obj);
			assert obj != null;
			
			// 4. 원래의 타입으로 환원시켜야 함(다형성-1에 의한 Object type -> 실제자식타입으로)
			this.dataSource = (DataSource) obj;
			
			log.debug("\t+ this.dataSource: {}", this.dataSource);
		} catch(NamingException | NullPointerException e) {
			throw new ServletException(e);
		} // try-catch
	} // init

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
				
		try {
			Connection conn = this.dataSource.getConnection();
			
			String sql = "SELECT empno, ename, sal, deptno FROM emp";
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			
			try (conn; pstmt; rs;) {
			
				while(rs.next()) {
					String empno 	= rs.getString("empno");
					String ename 	= rs.getString("ename");
					String sal 		= rs.getString("sal");
					String deptno 	= rs.getString("deptno");
					
					// 응답문서로 출력
					out.println( String.format("<p>%s\t%s\t%s\t%s</p>", empno, ename, sal, deptno) );				
				} // while
			
			} // try-with-resources
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			out.println("</body></html>");
		} // try-catch-finally

		out.flush();
	} // service
	

} // end class
