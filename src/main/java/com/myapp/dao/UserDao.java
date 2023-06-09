package com.myapp.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.myapp.beans.Contact;
import com.myapp.beans.User;
import com.myapp.beans.Mood;

import com.myapp.dao.DbConnectDao;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



public class UserDao {
    public User checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
       
        Connection connection = DbConnectDao.getConnection();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        User user = null;

        if (result.next()) {
            user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("name"));
            user.setMail(email);
        }

        connection.close();

        return user;
    }

    public static boolean createUser(User user, HttpServletRequest request) {
        try {
            Connection conn = DbConnectDao.getConnection();
            
            // Check if the email already exists in the database
            String checkEmailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement checkEmailStmt = conn.prepareStatement(checkEmailQuery);
            checkEmailStmt.setString(1, user.getMail());
            ResultSet checkEmailResult = checkEmailStmt.executeQuery();
            
            if (checkEmailResult.next()) {
                // The email already exists in the database
                String error="Email already exists! Use another email address";
                request.setAttribute("error", error);
                return false;
            }
            
            // Insert the new user into the database
            String insertQuery = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, user.getName());
            insertStmt.setString(2, user.getMail());
            insertStmt.setString(3, user.getPassword());
            int rowsInserted = insertStmt.executeUpdate();
            
            if (rowsInserted > 0) {
                // User successfully inserted
                String created="Account created successfully! You can log in";
                request.setAttribute("created", created);
                return true;
            } else {
                // User not inserted for some reason
                String error="User not created due to some error. Please try again later";
                request.setAttribute("error", error);
                return false;
            }
        } catch (SQLException e) {
            // Handle the exception
            String error="User not created due to some error. Please try again later";
            request.setAttribute("error", error);
            return false;
        }
    }

    
    public static ArrayList<Contact> getEmergencyContacts(int userId) throws SQLException, ClassNotFoundException {
        ArrayList<Contact> contacts = new ArrayList<>();
        
        // Establish a database connection
        
        Connection connection = DbConnectDao.getConnection();

        // Create a prepared statement to retrieve the user's contacts from the database
        String sql = "SELECT * FROM contacts WHERE id_user = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet result = statement.executeQuery();

        // Add each contact to the list of contacts
        while (result.next()) {
            Contact contact = new Contact();
            contact.setId(result.getInt("id_contact"));
            contact.setName(result.getString("name"));
            contact.setPhone(result.getString("phone"));
            contact.setMail(result.getString("email"));
            contacts.add(contact);
        }

        // Close the database connection and resources
        result.close();
        statement.close();
        connection.close();

        return contacts;
    }
    public void addMood(int userId, String mood, String tip) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbConnectDao.getConnection(); // get connection to database
            stmt = conn.prepareStatement("INSERT INTO mood ( mood_name, tips,id_user) VALUES (?, ?, ?)");
            stmt.setString(1, mood);
            stmt.setString(2, tip);
            stmt.setInt(3, userId);

            stmt.executeUpdate();
        } finally {
            stmt.close();
        	conn.close();        
        	}
    }
    public  static List<Mood> getMood(int userId) throws SQLException {
        List<Mood> moods = new ArrayList<>();
        Connection connection = DbConnectDao.getConnection();

        String sql = "SELECT * FROM mood WHERE id_user= ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet result = statement.executeQuery();

        // Add each contact to the list of contacts
        while (result.next()) {
        Mood mood = new Mood();
           mood.setType(result.getString("mood_name"));
           mood.setTip(result.getString("tips"));
            mood.setDate(result.getDate("date_mood").toLocalDate());
            moods.add(mood);
        }

        // Close the database connection and resources
        result.close();
        statement.close();
        connection.close();

        return moods;
    }
    
}




