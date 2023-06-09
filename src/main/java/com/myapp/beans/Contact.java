package com.myapp.beans;

public class Contact {
	
	
	    private int id;
	    private String name_contact;
	    private String phone_contact;
	    private String email_contact;
	    private int id_user;
	    
	    public Contact() {};
	    public Contact(String name, String email, String phone, int idUserParam) {
	    	this.name_contact=name;
	    	this.phone_contact=phone;
	    	this.email_contact=email;
	    	this.id_user=idUserParam;
		}
	    
	    public void Contact1(String name, String email, String phone, int id) {
	    	this.name_contact=name;
	    	this.phone_contact=phone;
	    	this.email_contact=email;
	    	this.id=id;
		}
		public String getName() {
	        return name_contact;
	    }
	    public void setName(String name) {
	        this.name_contact= name;
	    }
	   
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id= id;
	    }
	    public String getMail() {
	        return email_contact;
	    }

	    public void setMail(String email) {
	        this.email_contact = email;
	    }
	    public String getPhone() {
	        return phone_contact;
	    }
	    public void setPhone(String phone) {
	      this.phone_contact=phone;
	    }
	    public int getIdUser() {
	        return this.id_user;
	    }
	    public void setIdUser(int id) {
	        this.id_user= id;
	    }
}
