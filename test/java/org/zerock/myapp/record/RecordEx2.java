package org.zerock.myapp.record;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


// Note: lombok ver`1.8.22` is *NOT* compatible with the JAVA 15 and below. (***)
//       If using JAVA 15 and below, please use lombok ver`1.8.20`			(***)

@Log4j2
public class RecordEx2 {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
//		---
		
		// ------------------------- //
		// 1. Without `Records`
		// ------------------------- //
		// Prior to `Records`, we would create an `immutable` data transfer object (`DTO`) as:
		
		Person1 person1 = new Person1("Yoseph", 23);
		
		log.info("Person1 name: {}, age: {}", person1.name(), person1.age());	// OK: Getters
		
//		---

		// ------------------------- //
		// 2. With `Records`
		// ------------------------- //
		// Using the new `Record` class, we can define the same `immutable` data object in a much more compact way;
		
		Person2 person2 = new Person2("Yoseph", 23);
		
		log.info("person2 toString: {}", person2.toString());
		log.info("Person2 name: {}, age: {}", person2.name(), person2.age());	// OK: Only getters created.
		
		person2.instanceMethod();
		Person2.staticMethod();
		
		
	} // main

} // end class
