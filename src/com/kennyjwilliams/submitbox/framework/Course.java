package com.kennyjwilliams.submitbox.framework;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Kenny
 */
public class Course implements Serializable
{
    private String name;
    private ArrayList<Assignment> assignments;
    
    /**
     * Creates a new course with the given name
     * @param name The name of the course
     */
    public Course(String name)
    {
        this.name = name;
        assignments = new ArrayList<>();
        //addAssignment(new Assignment("Default"));
    }
    
    /**
     * Gets the name of the course
     * @return The name of the course
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name of the course
     * @param name The name of the course
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Adds an assignment to the course
     * @param assignment Assignment to be added to the course
     */
    public void addAssignment(Assignment assignment)
    {
        assignments.add(assignment);
    }
    
    /**
     * Removes an assignment from the course
     * @param assignment The assignment to be removed from the course
     */
    public void removeAssignment(Assignment assignment)
    {
        removeAssignment(assignment.getName());
    }
    
    /**
     * Removes an assignment from the course
     * @param assignment The assignment name to be removed from the course
     */
    public void removeAssignment(String assignment)
    {
        assignments.remove(getAssignment(name));
    }
    
    /**
     * Gets an assignment from the course
     * @param name The name of the assignment
     * @return The Assignment object
     */
    public Assignment getAssignment(String name)
    {
        Assignment result = null;
        for(Assignment a : getAssignments())
        {
            if(a.getName().equals(name))
            {
                result = a;
            }
        }
        return result;
    }
    
    /**
     * Gets a list of all the assignments the course has
     * @return List of assignments
     */
    public ArrayList<Assignment> getAssignments()
    {
        return assignments;
    }
}
