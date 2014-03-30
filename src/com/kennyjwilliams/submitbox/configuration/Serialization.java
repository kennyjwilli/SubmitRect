package com.kennyjwilliams.submitbox.configuration;

import com.kennyjwilliams.submitbox.framework.Course;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenny
 */
public class Serialization 
{

    /**
     * Serializes a course to the default course save directory
     * @param c The course to be serialized 
     */
    public static void serialize(Course c)
    {
        try
        {
            File f = new File("courses"+File.separator+c.getName()+".course");
            if(!f.exists())
            {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
            fos.close();
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * 
     * @param path
     * @return
     */
    public static Course deserialize(String path)
    {
        Course c = null;
        try
        {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            c = (Course) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex)
        {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
