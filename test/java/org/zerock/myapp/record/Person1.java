package org.zerock.myapp.record;

import lombok.Value;
import lombok.experimental.Accessors;


//1. Without Records Example.
//	  Prior to `Records`, 
//	  We would create an `immutable` data transfer object (`DTO`)

@Accessors(fluent=true)

// @Value:
// 
// 1. 아래와 같은 lombok annotations
// 자동으로 적용시킨다!!!:
// 	(1) @AllArgsConstructor
//	(2) @Getter (@Setter는 적용안함)
//	(3) @EqualsAndHashcode
//	(4) @ToString
//
// 2. 모든 각 필드 앞에, `private final` 이라는
//    키워드를 자동으로 붙여줍니다.

@Value

public class Person1 {
//	private final 
	String name;
	
//	private final 
	int age;
	
	
} // end class












