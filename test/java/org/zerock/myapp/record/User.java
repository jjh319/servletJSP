package org.zerock.myapp.record;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

//----------------
//ID와 비밀번호가 있는 사용자의 데이터 모델은, 다음과 같이 간단하게 정의할 수 있습니다.
//
//여기서 볼 수 있듯이 새로운 키워드인 `record`를 사용하고 있습니다.
//이 간단한 선언은 자동으로,
//		(1) Constructor having all arguments
//		(2) getters
//		(3) equals
//		(4) hashCode
//		(5) toString
//메서드를 추가합니다.
//----------------

@Log4j2		// OK

// @ToString is only supported on a class or enum.
//@ToString	// XX

//@AllArgsConstructor is only supported on a `class` or an `enum`.
//@AllArgsConstructor	// XX

//@NoArgsConstructor is only supported on a `class` or an `enum`.
//@NoArgsConstructor	// XX
public record User(
	// A record component id cannot have modifiers
//	public	// XX 
//	final	// XX
	int id, 			// private final field
	String password		// private final field
) {
	;;
} // end record




















