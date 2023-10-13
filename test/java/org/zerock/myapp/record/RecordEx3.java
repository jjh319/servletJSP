package org.zerock.myapp.record;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class RecordEx3 {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
//		----------
		
		// 1. Using Read-only class to create a `immutable data holding` object.
		
		String title = "TITLE";
		String author = "AUTHOR";
		String isbn = "ISBN";
		
		
		ReadOnlyBook book1 = new ReadOnlyBook(title, author, isbn);		// Read-only object.
		log.info("1. book1 : {}", book1);
		
//		book1.setTitle("NEWTITLE")						// XX : cannot modify.
//		book1.title = "NEWTITLE";						// XX : cannot modify.
		
//		----------

		// 2. `Record` is a class to create a `immutable data holding` object.
		//    and to create only getter methods *fluently* for each fields.
		
		ImmutableBook book2 = new ImmutableBook("TITLE", "AUTHOR", "ISBN");
		
		log.info("2. book2 : {}, type: {}", book2, book2.getClass().getName());
		log.info("\t+ title: {}, author: {}, isbn: {}", book2.title(), book2.author(), book2.isbn());
		
		book2.instanceMethod();
		ImmutableBook.staticMethod();

		// cannot modify fields in the record. (***)
		
//		----------
		
		// 3. With the release of `JAVA 16`, we can now define records as `class members` of `inner classes`.
		//	  This is due to relaxing restrictions that were missed
		//	  as part of the incremental release of `JAVA 15` under JEP-384.

		class OuterClass {
			
			class InnerClass {
				
				ImmutableBook book = new ImmutableBook("TITLE", "AUTHOR", "ISBN");	// OK
				
				
			} // end class
			
		} // end class
		
		
		OuterClass outer = new OuterClass();
		OuterClass.InnerClass inner = outer.new InnerClass();
		
		log.info("inner title: {}, author: {}, isbn: {}", inner.book.title(), inner.book.author(), inner.book.isbn());
		
		
		
	} // main

} // end class

