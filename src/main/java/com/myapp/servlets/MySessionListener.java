package com.myapp.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener, HttpSessionIdListener {

    public MySessionListener() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getSession().getAttribute("request");
        HttpServletResponse response = (HttpServletResponse) event.getSession().getAttribute("response");

        if (request != null && response != null) {
            HttpSession session = event.getSession();

            // check if the user is still logged in
            if (session.getAttribute("user.getId") != null) {
                session.invalidate();
                try {
                    response.sendRedirect(request.getContextPath() + "/WEB-INF/login.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // TODO Auto-generated method stub
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // TODO Auto-generated method stub
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        // TODO Auto-generated method stub
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        // TODO Auto-generated method stub
    }

    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        // TODO Auto-generated method stub
    }

}
