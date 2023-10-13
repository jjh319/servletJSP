package org.zerock.myapp.exception;


public class CommandException extends Exception {
	private static final long serialVersionUID = 1L;
	

	public CommandException(String message) {
		super(message);
	} // Constructor1
	
	public CommandException(Exception e) {
		super(e);
	} // Constructor2

} // end class
