package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
//import jakarta.servlet.annotation.WebListener;
//import jakarta.servlet.http.HttpSessionActivationListener;
//import jakarta.servlet.http.HttpSessionEvent;


@Log4j2
@NoArgsConstructor

@WebListener
public class SessionActivationListener implements HttpSessionActivationListener {


    // Notification that the session is about to be passivated.
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        log.trace("---------------------------------------");
        log.trace("* sessionWillPassivate(se) invoked.");
        log.trace("---------------------------------------");

        log.info("\t+ session: {}", se.getSession().getId());
    } // sessionWillPassivate

    // Notification that the session has just been activated.
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        log.trace("---------------------------------------");
        log.trace("* sessionDidActivate(se) invoked.");
        log.trace("---------------------------------------");

        log.info("\t+ session: {}", se.getSession().getId());
    } // sessionDidActivate

} // end class
