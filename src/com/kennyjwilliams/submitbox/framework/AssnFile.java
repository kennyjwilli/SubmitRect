
package com.kennyjwilliams.submitbox.framework;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Kenny
 */
public class AssnFile implements Serializable
{
    private File file;
    
    /**
     * Creates a new Assignment file
     * @param file File location of the submitted file
     */
    public AssnFile(File file)
    {
        this.file = file;
    }
    
    
}
