package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;

// (1) 조회할 테이블의 스키마 구조를 따라서 만든다.
//     - 각 필드는 테이블의 각 컬럼과 1:1 매핑되게 선언
//	   - 테이블의 각 컬럼의 이름/타입/크기에 맞게 선언
//     - 온전히 한개의 행을 저장할 수 있는 필드를 가져야 합니다.
// (2) NULL 값을 저장할 수 있는 타입으로 필드를 정의 => only 참조타입
// (3) POJO
// (4) 불변의 성질을 가져야 합니다(즉, Read-Only, Immutable)


@Value
public class EmpVO {
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hireDate;
//	private Timestamp hireDate;
	private Double sal;
	private Double comm;
	private Integer deptno;
	
	
	
} // end class
