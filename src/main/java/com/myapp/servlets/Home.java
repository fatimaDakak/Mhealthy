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
import java.util.*;

import com.myapp.beans.Contact;
import com.myapp.beans.User;
import com.myapp.beans.Mood;
import org.json.JSONObject;

import com.myapp.dao.UserDao;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		 HttpSession session = request.getSession();
             User user = (User) session.getAttribute("user");
             if (user == null) {
                 // If "user" is null, session has expired or user is not logged in.
                 // Set an error message and redirect to the login page.
                 session.setAttribute("errorMessage", "Session expired or user not logged in. Please log in again.");
                // response.sendRedirect("login");
         	    response.sendRedirect(request.getContextPath() + "/login");

                 return;
             }
             else {
    		List<Mood> moods;
            ArrayList<Contact> contacts = new ArrayList<>();
            try {
				contacts=UserDao.getEmergencyContacts(user.getId());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	     try {
    		     //request.setAttribute("contacts", contacts);
    			 moods = UserDao.getMood(user.getId());
    			 
    			 
    		     request.setAttribute("moods", moods);
    		     int anxiousCount = 0;
    		     int happyCount = 0;
    		     int sadCount = 0;

    		     for (Mood mood : moods) {
    		         switch (mood.getType()) {
    		             case "anxious":
    		                 anxiousCount++;
    		                 break;
    		             case "happy":
    		                 happyCount++;
    		                 break;
    		             case "sad":
    		                 sadCount++;
    		                 break;
    		         }
    		     }

    		     request.setAttribute("anxiousCount", anxiousCount);
    		     request.setAttribute("happyCount", happyCount);
    		     request.setAttribute("sadCount", sadCount);
    		     request.setAttribute("contacts", contacts);


    	     }
    		
    		 catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}}
    	     RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
    		    dispatcher.forward(request, response);
    	
    	
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

