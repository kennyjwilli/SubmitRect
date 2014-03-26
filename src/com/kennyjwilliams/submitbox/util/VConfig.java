package com.kennyjwilliams.submitbox.util;

import java.io.File;
import java.util.List;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author kenny
 */
public class VConfig 
{
    private File file;
    private Yaml yaml;
    
    public VConfig(File file)
    {
        this.file = file;
        yaml = new Yaml();
        
    }
    
    public void save()
    {
        
    }
    
    public void set(String path, Object value)
    {
        
    }
    
    public String getString(String path)
    {
        
    }
    
    public int getInt(String path)
    {
        
    }
    
    public boolean getBoolean(String path)
    {
        
    }
    
    public double getDouble(String path)
    {
        
    }
    
    public long getLong(String path)
    {
        
    }
    
    public List<?> getList(String path)
    {
        
    }
    
    public List<String> getStringList(String path)
    {
        
    }
    
    public List<Integer> getIntegerList(String path)
    {
        
    }
    
    public List<Boolean> getBooleanList(String path)
    {
        
    }
    
    public List<Double> getDoubleList(String path)
    {
        
    }
    
    public List<Float> getFloatList(String path)
    {
        
    }
    
    public List<Long> getLongList(String path)
    {
        
    }
    
    public List<Byte> getByteList(String path)
    {
    
    }
    
    public List<Character> getCharacterList(String path)
    {
        
    }
    
    public List<Short> getShortList(String path)
    {
        
    }
    
    
}
