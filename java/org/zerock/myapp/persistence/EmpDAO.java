package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.exception.DAOException;
import org.zerock.myapp.util.DataSourceFactories;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

// (1) 영속성 계층에 속함
// (2) 데이터를 조작(CRUD)할 수 있는 메소드 제공이 목표
//     - 영속성 계층의 성격에 맞게 메소드이름을 짖습니다.
// (3) 비지니스 계층에서 사용


@Log4j2
@NoArgsConstructor
public class EmpDAO {	// POJO	
	
	
	public List<EmpVO> selectAllEmployees() throws DAOException {
		log.trace("selectAllEmployees() invoked.");
		
		try {
			// Step.1 DataSource 를 이용해서, 결과셋을 얻어냄
			@Cleanup
			Connection conn = DataSourceFactories.createScottDataSource().getConnection();
			
			String sql = "SELECT /*+ index_asc(emp pk_emp) */ * FROM emp";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			// Step.2 Step.1에서 얻어낸 결과셋으로, 어떻게 데이터를
			//        리턴할 것인지에 대한 고민.
			List<EmpVO> list = new ArrayList<>();
			
			while(rs.next()) {	// 각 튜플 -> VO객체로 변환 -> List에 저장
				Integer empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				Integer mgr = rs.getInt("MGR");
				
				Date hireDate = rs.getDate("HIREDATE");
//				Timestamp hireDate = rs.getTimestamp("HIREDATE");
				
				Double sal = rs.getDouble("SAL");
				Double comm = rs.getDouble("COMM");
				Integer deptno = rs.getInt("DEPTNO");
				
				EmpVO vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);
				list.add(vo);
			} // while
			
			return list;
		} catch(Exception e) {
			throw new DAOException(e);
		} // try-catch
	} // selectAllEmployees
		
	public Integer deleteEmployee(EmpDTO dto) throws DAOException {
		log.trace("deleteEmployee({}) invoked.", dto);
		
		try {
			@Cleanup
			Connection conn = 
				DataSourceFactories.createScottDataSource().getConnection();
			
			String sql = "DELETE FROM emp WHERE empno = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			
			return pstmt.executeUpdate();
		} catch(Exception e) {
			throw new DAOException(e);
		} // try-catch
	} // deleteEmployee
	
	public EmpVO selectEmployee(EmpDTO dto) throws DAOException {
		log.trace("selectEmployee({}) invoked.", dto);
		
		try {
			@Cleanup
			Connection conn = 
				DataSourceFactories.createScottDataSource().getConnection();
			
			String sql = "SELECT * FROM emp WHERE empno = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {	// 각 튜플 -> VO객체로 변환 -> List에 저장
				Integer empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				Integer mgr = rs.getInt("MGR");
				Date hireDate = rs.getDate("HIREDATE");
				Double sal = rs.getDouble("SAL");
				Double comm = rs.getDouble("COMM");
				Integer deptno = rs.getInt("DEPTNO");
				
				return new EmpVO(
						empno, ename, job, mgr, 
						hireDate, sal, comm, deptno
					 );				
			} else {
				throw new IllegalArgumentException("empno = " + dto.getEmpno());
			} // if-else
		} catch(Exception e) {
			throw new DAOException(e);
		} // try-catch
	} // selectEmployee
	

} // end class
