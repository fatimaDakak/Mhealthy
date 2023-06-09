package com.myapp.beans;

import java.time.LocalDate;

public class Mood {
	private int id;
	private String mood;
	private String tips;
	private LocalDate date;
	
	public Mood() {};
	public Mood(String type, String tip,LocalDate date) {
        this.mood = type;
        this.tips = tip;
        this.date = date;

    }

    public String getType() {
        return mood;
    }

    public String getTip() {
        return tips;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setType(String type) {
        this.mood=type;
    }

    public void setTip(String tips) {
        this.tips=tips;
    }

    
    public void setDate(LocalDate date) {
        this.date=date;
    }

    
  
 
}
