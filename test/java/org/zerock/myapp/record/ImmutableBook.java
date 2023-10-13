package org.zerock.myapp.record;

import lombok.extern.log4j.Log4j2;

//********************************************** //
//Records (JEP-395)
//********************************************** //
//Records 는 `JAVA 14`에서 처음 도입되었습니다.
//`JAVA 16`은 몇 가지 증분 변경 되었습니다.
//------------
//Record 는, `제한된 형식`의 클래스라는 점에서, `열거형(enum)` 과 유사합니다.
//Record 정의는, * 변경할 수 없는 * 데이터보유 객체(`immutable` data holding object)를 정의하는 간결한 방법입니다.
//------------
//JAVA 에서 간단한 `데이터 보유(immutable` data holding)` 클래스를 생성하려면 많은 상용구(boilerplate) 코드가 필요합니다.
//이는 번거로울 수 있으며, 개발자가 `equals` 및 `hashCode` 와 같은 필요한 모든 메서드를 제공하지 않는 버그로 이어질 수 있습니다 .
//마찬가지로 개발자가 적절한 불변(immutable) 클래스 를 만드는 데 필요한 단계를 건너뛰는 경우가 있습니다.
//때때로 각각의 다른 사용 사례에 대해 전문적인 클래스를 정의하는 대신 범용 클래스를 재사용하게 됩니다.
//------------
//대부분의 최신 IDE는 이러한 문제를 완화하고 코드를 작성하는 개발자의 오버헤드를 줄이는 데 도움이 되는 코드
//(예: setter, getter, 생성자 등)를 자동 생성하는 기능을 제공합니다.
//
//그러나 Records는 상용구(boilerplate) 코드를 줄이고 동일한 결과를 생성하는 내장된 메커니즘을 제공합니다.
//------------
//`JAVA 16`이 출시되면서, 이제 레코드를 내부 클래스의 클래스 멤버로 정의할 수 있습니다.
//이는 JEP-384 에 따라, `JAVA 15`의 증분 릴리스의 일부로 누락된 제한을 완화하기 때문입니다 .
//------------

//Note1: User declared `* non-static fields *` are NOT permitted in a record			(***)
//Note2: Constructor parameters are defined `* private final *` fields in a record 	(***)
//Note3: Create only `getter` methods *fluently* for each fields						(***)
//		  There are NO `setter` methods for each fields									(***)




//-----------------------

//record ImmutableBook {		// XX : Syntax error, insert "RecordHeader" to complete RecordHeaderPart.
//record ImmutableBook () {		// OK
//record ImmutableBook(String title, String author, String isbn) {	// OK
//
//	;;
//} // end record

//-----------------------

//record ImmutableBook (String title, String author, String isbn) {
//	;;
//} // end record

//-----------------------

@Log4j2						// OK

//@AllArgsConstructor		// XX
//@NoArgsConstructor		// XX
public record ImmutableBook(String title, String author, String isbn) {
	private static boolean staticField = false;
	
	
	void instanceMethod() {
		log.trace("instanceMethod() invoked.");
		
		log.info("\t+ title field: {}", title);
	} // instanceMethod
	
	
	static void staticMethod() {
		log.trace("staticMethod() invoked.");
		
		log.info("\t+ staticField field: {}", ImmutableBook.staticField);
	} // staticMethod
	
} // end record

//-----------------------