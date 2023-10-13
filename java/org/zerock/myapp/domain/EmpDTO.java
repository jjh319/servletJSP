package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Data;


// DTO - Data Transfer Object
// DTO 가 웹3계층을 따라, 단방향으로 영속성 계층까지 전달하려면,
// 어차피 DTO란 것은, 현재의 요청에 대한 입력데이터 이기 때문에,
// 현재의 요청이 살아있을 동안에만 공유영역인 Request Scope에 넣어서
// 웹3계층 어디에서든 사용할 수 있도록 하고, 응답이 나가면, Request Scope은
// 자동 파괴되기 때문에, 다시 Request Scope에서 DTO를 삭제하는 것은
// 고려하지 않아도 됩니다:


@Data
public class EmpDTO {
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hireDate;
	private Double sal;
	private Double comm;
	private Integer deptno;

	
} // end class
