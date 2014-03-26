package com.kennyjwilliams.submitbox;

import com.kennyjwilliams.submitbox.framework.Course;
import java.util.ArrayList;

/**
 *
 * @author Kenny
 */
public class CourseAPI 
{
    private static ArrayList<Course> classes = new ArrayList<>();
    
    /**
     * Gets a list of courses avaliable
     * @return List of courses
     */
    public static ArrayList<Course> getCourses()
    {
        return classes;
    }
    
    /**
     * Gets a list of course names avaliable
     * @return List of course names
     */
    public static ArrayList<String> getCourseNames()
    {
        ArrayList<String> result = new ArrayList<>();
        for(Course c : classes)
        {
            result.add(c.getName());
        }
        return result;
    }
    
    /**
     * Adds a course to the application
     * @param c The course to be added
     */
    public static void addCourse(Course c)
    {
        classes.add(c);
    }
    
    /**
     * Removes a course from the application
     * @param c Course to be removed 
     */
    public static void removeCourse(Course c)
    {
        classes.remove(c);
    }
    
    /**
     * Get the course with the given name
     * @param name The name of the course
     * @return The course object with the provided name. Null if course does not
     * exist
     */
    public static Course getCourse(String name)
    {
        Course result = null;
        for(Course c : classes)
        {
            if(c.getName().equals(name))
            {
                result = c;
            }
        }
        return result;
    }
    
    /**
     * Saves all the courses
     */
    public static void saveAll()
    {
        for(Course c : classes)
        {
            c.save(c.getName());
        }
    }
}
