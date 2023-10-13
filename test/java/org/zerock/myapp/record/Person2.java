package org.zerock.myapp.record;

import lombok.extern.log4j.Log4j2;


//********************************************** //
//Records (JEP 384)
//********************************************** //
//The `Record` is a new type of class in JAVA that makes it easy to create immutable data objects.
//`Record` 는 변경 할 수 없는(immutable) 데이터 개체를 쉽게 만들 수 있도록 하는, JAVA의 새로운 유형의 클래스입니다 .
//--------
//
//01. 실제로 상태를 보유하는 불변(immutable) 객체를 생성하기 위한 많은 코드가 있음을 주목하십시오.
//02. 모든 필드는 `final` 을 사용하여 명시적으로 정의되며, 모든 인자를 매개변수로 가지는 단일 생성자가 있으며
//03. 모든 필드에 대한 접근자(accessor) 메서드가 있습니다.
//04. 어떤 경우에는 sub-classing 을 방지하기 위해, 클래스 자체를 `final`로 선언할 수도 있습니다.
//05. 많은 경우에, 한 단계 더 나아가, 의미 있는 로깅 출력을 제공하기 위해 `toString` 메서드를 재정의합니다.
//06. 이러한 개체의 두 인스턴스를 비교할 때, 예기치 않은 결과를 피하기 위해,
//	  `equals` 및 `hashCode` 메서드를 재정의 할 수도 있습니다.
//--------
//
//07. 무엇보다도 클래스 정의에는, Records 에 특정한 새로운 구문이 있습니다.
//  이 헤더는 `Record` 내부의 필드에 대한 세부 정보를 제공하는 곳입니다.
//08. 이 헤더를 사용하여 컴파일러는 내부 필드를 유추할 수 있습니다.
//  즉, 기본적으로 제공되는 특정 멤버 변수와 접근자를 정의할 필요가 없습니다.
//09. 또한 생성자를 제공할 필요가 없습니다.
//10. 또한 컴파일러는 `toString` , `equals` 및 `hashCode` 메서드에 대한 합리적인 구현을 제공합니다.
//11. `Record` 는 많은 상용구(boilerplate) 코드를 제거 하지만, 일부 기본 동작을 재정의할 수 있습니다.
//  예를 들어, 일부 유효성 검사를 수행하는 정식 `생성자(constructor)`를 정의할 수 있습니다.
//--------
//
//`Record` 에는 몇 가지 제한 사항이 있음을 언급할 가치가 있습니다;
//	1. 무엇보다도 그것들은 항상 final 이고,
//	2. abstract 로 선언될 수 없으며 ,
//	3. 네이티브 메소드를 사용할 수 없습니다.

//Note1: `Record`에 선언된 모든 매개변수는 자동으로, `private final` 인스턴스 필드로 전환 (***)
//Note2: 전환된 모든 각 필드마다, 접근자(accessor) 메소드가 fluent api 형태로 자동생성됨(단, Getter만 생성) (***)
//		  즉, 각 필드에 대한 Setter는 생성되지 않음 (***)
//Note3: lombok `@ToString, @EqualsAndHashCode` 어노테이션과 비슷하게,
//		  `ToString`, `equals`, `hashcode` 메소드를 자동으로 재정의 합니다 (***)
//		  그러나 원한다면, 이 `ToString`, `equals`, `hashcode` 메소드를 직접 선언가능하고 호출됨 (***)
//Note4: 생성자(Constructor)를 정의할 필요가 없고, 필드를 선언할 필요가 없음 (static 필드선언만 허용) (***)
//		  그러나 원한다면, 생성자(constructors)와 static initializers를 직접 선언할 수 있음 (***)
//		  생성자(constructor)를 직접 선언하면, new 연산자 호출시, 직접 선언된 생성자가 호출됨 (***)
//Note5: 원한다면 접근자 메소드(accessors, getters)를 직접 선언할 수 있고, 호출시 직접 선언된 접근자 메소드가 호출됨 (***)


@Log4j2
// Illegal modifier for the record Person2; 
// only public, final and strictfp are permitted
public record Person2(String name, int age) {
	// XX : User declared non-static fields isMale are not permitted in a record. (***)
//	private boolean isMale;	
	
	// OK : Only static fields declaration permitted in a record. (***)
	private static boolean isMale;	
	

	// OK : If we want, we can declare static initializer.
	static {
		isMale = false;
		
		log.trace("static initializer invoked.");
	} // static initializer
	
//	---
	
	// OK : If we want, we can declare constructors.
	public Person2(String name, int age) {
		log.trace("Person2 constructor({}, {}) invoked.", name, age);
		
		this.name = name +"!!!";
		this.age = age + 100;
	} // Constructor
	
//	---
	
	// Also, We can declare instance/static methods. (***)
	;;
	
//	---
	
	// OK: User-defined fluent accessors(getters) declaration. (***)
	@Override
	public String name() {
		log.trace("name() invoked.");
		return this.name;
	} // name
	

	@Override
	public int age() {
		log.trace("age() invoked.");
		return this.age;
	} // age
	
//	---
	
	// OK: User-defined `toString` method overriding. (***)
	//     But * NOT * recommended.
//	@Override
//	public String toString() {
//		log.trace("toString() invoked.");
//		
//		return "Hello!!!";
//	} // toString
	
//	---

	// OK: User-defined `equals` and `hashCode` methods overriding. (***)
	//		But * NOT * recommended.
//	@Override
//	public boolean equals(Object obj) {
//		log.trace("equals({}) invoked.", obj);
//		
//		return false;
//	} // equals


//	@Override
//	public int hashCode() {
//		log.trace("hashCode() invoked.");
//		
//		return Objects.hash(this.name, this.age);
//	} // hashCode
	
//	---
	
	public void instanceMethod() {
		log.trace("instanceMethod() invoked.");
		log.info("\t+ name: {}, age: {}", this.name, this.age);
		
//		this.name ="Yoseph";			// XX
	} // instanceMethod
	
	
	public static void staticMethod() {
		log.trace("staticMethod() invoked.");
		log.info("\t+ isMale: {}", Person2.isMale);
		
		Person2.isMale = false;			// OK
	} // staticMethod
	
	
} // end record
