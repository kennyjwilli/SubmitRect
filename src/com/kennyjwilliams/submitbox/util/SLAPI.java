
package com.kennyjwilliams.submitbox.util;

import com.kennyjwilliams.submitbox.CourseAPI;
import com.kennyjwilliams.submitbox.configuration.Serialization;
import com.kennyjwilliams.submitbox.framework.Course;
import java.io.File;

/**
 *
 * @author Kenny
 */
public class SLAPI 
{
    public static void saveCourses()
    {
        for(Course c : CourseAPI.getCourses())
        {
            Serialization.serialize(c);
        }
    }
    
    public static void loadCourses()
    {
        for(File f : new File("courses").listFiles())
        {
            CourseAPI.addCourse(Serialization.deserialize(f.getAbsolutePath()));
        }
    }
}
