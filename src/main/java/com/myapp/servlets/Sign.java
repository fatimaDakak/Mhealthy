package com.myapp.servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.myapp.beans.User;
import com.myapp.dao.UserDao;

/**
 * Servlet implementation class Sign
 */
@WebServlet("/Sign")
public class Sign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sign() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signup.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    User user = new User();
	    String email = request.getParameter("email");
	    String name = request.getParameter("name");
	    String password = request.getParameter("password");
	    user.setMail(email);
	    user.setName(name);
	    user.setPassword(password);
	    
	    boolean addUser = UserDao.createUser(user, request);
	    if (addUser) {
	        // Alert for successful user creation
	        String created = "Account created successfully! You can now log in.";
	        request.setAttribute("alertType", "success");
	        response.sendRedirect("login?alertType=success&message=" + created);

	    } else {
	        // Alert for email already exists in the database
	        String error = "Email already exists! Use another email address.";
	        request.setAttribute("alertType", "danger");
	        request.setAttribute("message", error);
	        request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
	    }
	}}
