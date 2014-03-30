package com.kennyjwilliams.submitbox.framework;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kenny
 */
public class DueDate implements Serializable
{
    private Date date;
    
    public DueDate(Date date)
    {
        this.date = date;
    }
    
    /**
     * Gets the date 
     * @return The date
     */
    public Date getDate()
    {
        return date;
    }
    
    /**
     * Sets the date
     * @param date Date to be set
     */
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    /**
     * Sets the date for the given month, day and year
     * @param month Month number (0 - 11)
     * @param day Day number (1 - 31)
     * @param year Year number
     */
    public void setDate(int month, int day, int year)
    {
        date.setMonth(month);
        date.setDate(day);
        date.setYear(year);
    }
    
    /**
     * Sets the date from a formatted date following this syntax: mm/dd/yyy
     * @param dateFormatted Formatted date text
     */
    public void setDate(String dateFormatted)
    {
        String[] split = dateFormatted.split("/");
        date.setMonth(Integer.parseInt(split[0]));
        date.setDate(Integer.parseInt(split[1]));
        date.setYear(Integer.parseInt(split[2]));
    }
    
    /**
     * Gets the formatted version of the date in the following syntax: mm/dd/yyy
     * @return A formatted date
     */
    public String getDateFormatted()
    {
        return date.getMonth()+"/"+date.getDate()+"/"+date.getYear();
    }
    
    /**
     * Gets a formatted time text: hh:mm
     * @return Formatted time
     */
    public String getTimeFormatted()
    {
        String hours = date.getHours()+"";
        String mins = date.getMinutes()+"";
        if(date.getHours() < 10)
        {
            hours = "0"+date.getHours();
            
        }
        
        if(date.getMinutes() < 10)
        {
            mins = "0"+date.getMinutes();
        }
        return hours+":"+mins;
    }
    
    /**
     * Sets the time based on a formatted time string: hh:mm
     * @param timeFormatted Formated time text
     */
    public void setTime(String timeFormatted)
    {
        String[] split = timeFormatted.split(":");
        date.setHours(Integer.parseInt(split[0]));
        date.setMinutes(Integer.parseInt(split[1]));
    }
    
    /**
     * Sets the time according to the given hours and minutes (24 hours clock)
     * @param hours The hours (0 - 23)
     * @param minutes The minutes (0 - 59)
     */
    public void setTime(int hours, int minutes)
    {
        date.setHours(hours);
        date.setMinutes(minutes);
    }
}
