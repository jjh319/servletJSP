package org.zerock.myapp.util;

import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;


@Log4j2

// static 필드는 여러번 초기화를 해도,
// 단 값(객체)으로 초기화한다면, 2번이상 초기화되지 않습니다.
public class DataSourceFactories {
	private static DataSource scottDataSource;
	private static DataSource hrDataSource;
	
	private static final
		String scottDataSourceName = "java:comp/env/jdbc/OracleCloudATPWithDriverSpy";
	private static final
		String hrDataSourceName = "java:comp/env/jdbc/OracleCloudATP";
	
	
	// static initializer 영역에서는 반드시 예외가 위로 던져저서는 안됩니다.
	static { // 이 영역은 JVM이 단 한번만 수행되는 것을 보장하는 블록
		log.trace("static initialzr invoked.");
		
		try {
			Context ctx = new InitialContext();
			
			scottDataSource = (DataSource) ctx.lookup(scottDataSourceName);
			hrDataSource = (DataSource) ctx.lookup(hrDataSourceName);
			
			Objects.requireNonNull(scottDataSource);
			log.info("\t+ scottDataSource: {}", DataSourceFactories.scottDataSource);
			
			Objects.requireNonNull(hrDataSource);
			log.info("\t+ hrDataSource: {}", DataSourceFactories.hrDataSource);
		} catch(NamingException e) {;;}
	} // static initializer
		
	public static DataSource createScottDataSource() {
		return DataSourceFactories.scottDataSource;
	} // getScottDataSource
		
	public static DataSource createHrDataSource() {
		return DataSourceFactories.hrDataSource;
	} // getScottDataSource
	

} // end class
