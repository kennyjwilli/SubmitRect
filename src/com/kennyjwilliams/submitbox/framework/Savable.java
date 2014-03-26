
package com.kennyjwilliams.submitbox.framework;

import java.io.FileWriter;
import java.io.IOException;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Kenny
 */
public class Savable 
{
    public void save(String file)
    {
        Yaml yaml = new Yaml();
        FileWriter writer;
        try 
        {
            writer = new FileWriter(file);
            yaml.dump(this, writer);
            writer.close();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
