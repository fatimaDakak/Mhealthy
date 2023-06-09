package com.myapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.dao.UserDao;
import com.myapp.beans.Contact;
import com.myapp.beans.User;


/**
 * Servlet implementation class SMSservlet
 */
@WebServlet("/SMSservlet")
public class SMSservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ACCOUNT_SID = "ACf567abf56d76b11bcbed32f28c25ab25";
    private static final String AUTH_TOKEN = "47cea94b8837aff0610f0f25cd5fd961";
    private static final String FROM_PHONE_NUMBER = "+12706151659"; // replace with your Twilio phone number
    private static final String MESSAGE_TEXT = "I'm having a panic attack, please come help me as soon as you can.";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SMSservlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirect to home page if accessed directly
		response.sendRedirect(request.getContextPath() + "/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Get the user from session
	    User user = (User) request.getSession().getAttribute("user");
	    // Retrieve contacts for the user from the database
        String sent =user.sendSMS(user.getId(),user.getName());	    
         request.setAttribute("sent", sent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
	    dispatcher.forward(request, response);
	}
	
	

	}
