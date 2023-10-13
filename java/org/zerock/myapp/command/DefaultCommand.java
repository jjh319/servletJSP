package org.zerock.myapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.exception.CommandException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class DefaultCommand implements Command {

	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res)
		throws CommandException {
		log.trace("process(req, res) invoked.");
		
		try {
			
		} catch(Exception e) {
			throw new CommandException(e);
		} // try-catch
	} // process

} // end class
