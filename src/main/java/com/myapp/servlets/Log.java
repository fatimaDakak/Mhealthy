package com.myapp.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.beans.User;
import com.myapp.dao.UserDao;


/**
 * Servlet implementation class Log
 */
@WebServlet("/Log")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String alertType = request.getParameter("alertType");
	String message = request.getParameter("message");

		if (alertType != null && message != null) {
	    request.setAttribute("alertType", alertType);
	    request.setAttribute("message", message);
	}

		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
        String password = request.getParameter("password");
         
        UserDao userDao = new UserDao();
         
        try {
            User user = userDao.checkLogin(email, password);
            String destPage = "/WEB-INF/login.jsp";
             
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("home");
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
                dispatcher.forward(request, response);
            }
        }
			 catch (SQLException | ClassNotFoundException ex) {
			            throw new ServletException();
			        }	}

}
