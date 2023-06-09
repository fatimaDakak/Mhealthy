package com.myapp.beans;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myapp.dao.UserDao;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class User {

	    private int id;
	    private String name;
	    private String email;
	    private String password;
	    private ArrayList<Contact> contacts = new ArrayList<>();

	    
	    public String getName() {
	        return name;
	    }
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id=id;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public String getMail() {
	        return email;
	    }
	    public void setMail(String email) {
	        this.email = email;
	    }
	    public List<Contact> getContacts() {
	        return contacts;
	    }
	    public void setContacts(ArrayList<Contact> contacts2) {
	        this.contacts=contacts2;
	    }
	    
	    
	    
	    public String sendSMS(int id_user, String name) {
	    	
	        final String ACCOUNT_SID = "ACf567abf56d76b11bcbed32f28c25ab25";
	        final String AUTH_TOKEN = "47cea94b8837aff0610f0f25cd5fd961";
	        final String FROM_PHONE_NUMBER = "+13853762268"; // replace with your Twilio phone number
	        final String MESSAGE_TEXT = "I'm having a panic attack, please come help me as soon as you can.";

	        // Retrieve contacts for the user from the database
	        ArrayList<Contact> contacts;
	        try {
	            contacts = UserDao.getEmergencyContacts(id_user);

	            // Send message to all the contacts
	            boolean sentSuccessfully = false;
	            for (Contact contact : contacts) {
	                // Create a new Twilio instance
	                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	                // Set the recipient phone number and the message text
	                String toPhoneNumber = contact.getPhone();
	                System.out.println(toPhoneNumber);
	                String messageText = "Your friend " + name + ": " + MESSAGE_TEXT;

	                // Send the SMS message
	                Message message = Message.creator(
	                        new PhoneNumber(toPhoneNumber),
	                        new PhoneNumber(FROM_PHONE_NUMBER),
	                        messageText).create();

	                sentSuccessfully = true;
	            }

	            if (sentSuccessfully) {
	                return "Your emergency message has been sent successfully! Try to practice breathing techniques while your friend comes to help you.";
	            } else {
	                return "Failed to send emergency message to any contacts.";
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            // Handle the exception
	            return "An error occurred while sending emergency messages.";
	        }
	    }
}


