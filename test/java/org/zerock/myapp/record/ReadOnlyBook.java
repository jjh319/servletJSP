package org.zerock.myapp.record;

import lombok.AllArgsConstructor;
import lombok.Data;


//create ONLY getters for *final* fields.
@Data

@AllArgsConstructor
public final class ReadOnlyBook { // Read-Only => `Immutable`
	private final String title;
	private final String author;
	private final String isbn;
	
	
	
} // end class
