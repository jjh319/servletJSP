package org.zerock.myapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

@WebServlet("/UsingDataSource")
public class UsingDataSourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/OracleCloudATP")
//	@Resource(name="jdbc/OracleCloudATPWithDriverSpy")
	private DataSource dataSource;
	


	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init(config) invoked.");
		
		Objects.requireNonNull(dataSource);
		log.info("\t+ dataSource: " + dataSource);
	} // init

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		try {
			Connection conn = this.dataSource.getConnection();
						
			try(conn) {
				
				Objects.requireNonNull(conn);
				log.info("\t+ conn: " + conn);
				
			} // try-with-resource
			
		} catch(SQLException e) {
			throw new IOException(e);
		} // try-catch
		
	} // service

} // end class
