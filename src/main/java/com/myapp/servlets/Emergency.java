package com.myapp.servlets;

import java.io.IOException;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.myapp.dao.ContactDao;
import com.myapp.dao.UserDao;
import com.myapp.beans.Contact;
import com.myapp.beans.User;

import com.mysql.cj.Session;
import java.util.*;

/**
 * Servlet implementation class Emergency
 */
@WebServlet("/Emergency")
public class Emergency extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Emergency() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		// TODO Auto-generated method stub
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve the contacts for the logged in user from the database
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
        	
                 // If "user" is null, session has expired or user is not logged in.
                 // Set an error message and redirect to the login page.
                 session.setAttribute("errorMessage", "Session expired or user not logged in. Please log in again.");
                 response.sendRedirect("login");
                 return;
             }        

        int id_user = user.getId();

        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myapp", "root", "");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM contacts WHERE id_user = ?");
            pstmt.setInt(1, id_user);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id_contact"));
                contact.setName(rs.getString("name"));
                contact.setMail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contacts.add(contact);
                user.setContacts(contacts);
            }
            conn.close();
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

        // set the contacts attribute and forward to the JSP
        request.setAttribute("contacts", contacts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/emergency.jsp");
        dispatcher.forward(request, response);
        
    }
    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        Contact contact = new Contact(name, email, phone, user.getId());

        try {
            boolean isAdded = ContactDao.addContact(contact, user.getId());
            if (isAdded) {
                session.setAttribute("successMsg", "Contact added successfully!");
                response.sendRedirect(request.getContextPath() + "/contacts");
            } else {
                session.setAttribute("errorMsg", "Failed to add contact.");
                response.sendRedirect(request.getContextPath() + "/contacts");
            }
        } catch (SQLException e) {
            session.setAttribute("errorMsg", "An error occurred while adding the contact.");
            response.sendRedirect(request.getContextPath() + "/contacts");
            e.printStackTrace();
        }
    }
    }

