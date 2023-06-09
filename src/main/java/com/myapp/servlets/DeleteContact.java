package com.myapp.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.dao.ContactDao;

/**
 * Servlet implementation class DeleteContact
 */
@WebServlet("/DeleteContact")
public class DeleteContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		        int id = Integer.parseInt(request.getParameter("id"));

		        try {
		            boolean isDeleted = ContactDao.deleteContact(id);
		            if (isDeleted) {
		                request.getSession().setAttribute("successMsg", "Contact deleted successfully!");
		            } else {
		                request.getSession().setAttribute("errorMsg", "Failed to delete contact.");
		            }
		        } catch (SQLException e) {
		            request.getSession().setAttribute("errorMsg", "An error occurred while deleting the contact.");
		            e.printStackTrace();
		        }
		        response.sendRedirect(request.getContextPath() + "/contacts");
		    }
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		// TODO Auto-generated method stub
		    protected void doPost(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));

		        ContactDao contactDao = (ContactDao) getServletContext().getAttribute("contactDao");
		        try {
					contactDao.deleteContact(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        HttpSession session = request.getSession();
		        session.setAttribute("successMsg", "Contact deleted successfully");
		        response.sendRedirect("contacts");
		    }
		
	}


