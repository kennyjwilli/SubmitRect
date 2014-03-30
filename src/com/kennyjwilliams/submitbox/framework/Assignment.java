package com.kennyjwilliams.submitbox.framework;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Kenny
 */
public class Assignment implements Serializable
{
    private String name;
    private DueDate dueDate;
    private boolean allowLate;
    
    /**
     * Creates a new assignment with the date set to the current date and allow late 
     * assignments to false
     * @param name The name of the assignment
     */
    public Assignment(String name)
    {
        this.name = name;
        this.dueDate = new DueDate(new Date());
        allowLate = false;
    }
    
    /**
     * Creates a new assignment
     * @param name The name of the assignment
     * @param dueDate The DueDate of the assignment
     * @param allowLate If the assignment allows late submissions
     */
    public Assignment(String name, DueDate dueDate, boolean allowLate)
    {
        this.name = name;
        this.dueDate = dueDate;
        this.allowLate = allowLate;
    }
    
    /**
     * Gets the name of the assignment
     * @return The name of the assignment
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name of the assignment
     * @param name The name of the assignment
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Gets the DueDate of the assignment
     * @return The DueDate of the assignment
     */
    public DueDate getDueDate()
    {
        return dueDate;
    }
    
    /**
     * Sets the DueDate of the assignment
     * @param dueDate The DueDate of the assignment
     */
    public void setDueDate(DueDate dueDate)
    {
        this.dueDate = dueDate;
    }
    
    /**
     * Gets if the assignment can be submitted late
     * @return True if the assignment can be submitted late
     */
    public boolean canSubmitLate()
    {
        return allowLate;
    }
    
    /**
     * Sets if the assignment can be submitted late
     * @param value True if the assignment can be submitted late
     */
    public void setSubmitLate(boolean value)
    {
        allowLate = value;
    }
}
