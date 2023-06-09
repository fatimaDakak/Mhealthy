package com.myapp.dao;



	import java.sql.Connection;


	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.myapp.beans.User;
	import com.myapp.beans.Contact;


	public class ContactDao {
		
		 public static boolean addContact(Contact contact,int id_user) throws SQLException {
			 
		        Connection conn = DbConnectDao.getConnection();

			 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO contacts (id_user, name, email, phone) VALUES (?, ?, ?, ?)");
			    pstmt.setInt(1, id_user); // replace iduser with the ID of the user who is adding the contact
			    pstmt.setString(2, contact.getName());
			    pstmt.setString(3, contact.getMail());
			    pstmt.setString(4, contact.getPhone());
			    pstmt.executeUpdate();
			    conn.close();
				return false;
	}
		 
	
	

			    public static boolean updateContact(Contact contact) throws SQLException {
			        Connection conn = DbConnectDao.getConnection();

			        PreparedStatement pstmt = conn.prepareStatement("UPDATE contacts SET name = ?, email = ?, phone = ? WHERE id_contact = ?");
			        pstmt.setString(1, contact.getName());
			        pstmt.setString(2, contact.getMail());
			        pstmt.setString(3, contact.getPhone());
			        pstmt.setInt(4, contact.getId());

			        int rowsAffected = pstmt.executeUpdate();
			        conn.close();
			        return rowsAffected > 0;
			    }

			    public static boolean deleteContact(int contactId) throws SQLException {
			        Connection conn = DbConnectDao.getConnection();

			        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM contacts WHERE id_contact = ?");
			        pstmt.setInt(1, contactId);

			        int rowsAffected = pstmt.executeUpdate();
			        conn.close();
			        return rowsAffected > 0;
			    }




			    public static Contact getContactById(int contactId) {
			        PreparedStatement stmt = null;
			        ResultSet rs = null;
			        Contact contact = null;

			        try {
				        Connection conn = DbConnectDao.getConnection();

			            // prepare SQL statement with parameterized query
			            stmt = conn.prepareStatement("SELECT * FROM contacts WHERE id = ?");
			            stmt.setInt(1, contactId);

			            // execute query and retrieve results
			            rs = stmt.executeQuery();

			            // if a row is returned, create a new Contact object and set its properties
			            if (rs.next()) {
			                contact = new Contact();
			                contact.setId(rs.getInt("id_contact"));
			                contact.setName(rs.getString("name_contact"));
			                contact.setMail(rs.getString("email_contact"));
			                contact.setPhone(rs.getString("phone_contact"));
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {
			            // close resources
			            try {
			                if (rs != null) {
			                    rs.close();
			                }
			                if (stmt != null) {
			                    stmt.close();
			                }
			                
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }

			        return contact; // return the retrieved Contact object, or null if no contact was found
			    }

			}

	
	
	
	
	


