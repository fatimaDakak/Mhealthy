package com.myapp.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.List;
import java.util.regex.Matcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.beans.User;
import com.myapp.dao.UserDao;
import com.myapp.interImp.*;

/**
 * Servlet implementation class Interpreter
 */
@WebServlet("/Interpreter")
public class Interpreter extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InterpreterController controller;
    public Interpreter() {
        super();
        controller = new InterpreterController();

    }



    /**
     * @see HttpServlet#HttpServlet()
     */
   
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        UserDao userDao = new UserDao();
        String mood = request.getParameter("mood");
        List<String> results = controller.interpret(mood);
        String type = controller.extractFeeling(mood);
        try {
            for (String result : results) {
                userDao.addMood(userId, type, result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("results", results);
        request.setAttribute("type", type);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }}
