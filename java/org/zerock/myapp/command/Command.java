package org.zerock.myapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.exception.CommandException;


public interface Command {
	public abstract void process(HttpServletRequest req, HttpServletResponse res)
			throws CommandException;
} // end interface
