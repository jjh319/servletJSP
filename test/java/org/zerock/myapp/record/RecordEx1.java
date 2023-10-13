package org.zerock.myapp.record;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


// ********************************************** //
// Records (JEP 359)
// ********************************************** //
// The `Record` is a new type of class in JAVA that makes it easy to create *immutable* data objects.
// `Record` 는 변경 할 수 없는(immutable) 데이터 개체를 쉽게 만들 수 있도록 하는, JAVA의 새로운 유형의 클래스입니다.
//
// 데이터 모델 POJO에서 반복적인 상용구 코드를 줄이기 위해 `Record`가 도입되었습니다.
// 일상적인 개발을 단순화하고 효율성을 개선하며 인적 오류의 위험을 크게 최소화합니다.

@Log4j2
public class RecordEx1 {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
//		-------
		
		// Example1
		
		User user1 = new User(100, "PASS1");
		User user2 = new User(200, "PASS2");
		
		log.info("user1: {}, user2: {}", user1, user2);
		
		log.info("user1 id: {}, password: {}", user1.id(), user1.password());
		log.info("user2 id: {}, password: {}", user2.id(), user2.password());
		
//		-------
		
		assertThat(100).isEqualTo(user1.id());
		assertThat(user1).isEqualTo(user2);
	} // main

} // end class


