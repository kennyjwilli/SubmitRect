package com.kennyjwilliams.submitbox.framework.ftp;

import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Kenny
 */
public class FTP implements Serializable
{
    /*
    Class enums
    */
    public static int SUCCESS = 0;
    public static int LOGIN_ERROR = 1;
    public static int DIRECTORY_MISSING = 2;
    
    /*
    Instance variables
    */
    private String host;
    private String user;
    private String pass;
    private String directory;
    private String dlDir;
    private static FTP ftp;
    private static FTPClient client = new FTPClient();
        
    public FTP(String host, String user, String pass, String directory, String dlDir)
    {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.directory = directory;
        this.dlDir = dlDir;
    }
    
    public static void init(String host, String user, String pass, String directory, String dlDir)
    {
        FTP.ftp = new FTP(host, user, pass, directory, dlDir);
    }
    
    public static void init(FTP ftp)
    {
        FTP.ftp = ftp;
    }
    
    public static FTP getInstance()
    {
        return ftp;
    }
    
    public String getHost()
    {
        return host;
    }
    
    public void setHost(String host)
    {
        this.host = host;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public String getPassword()
    {
        return pass;
    }
    
    public void setPassword(String pass)
    {
        this.pass = pass;
    }
    
    public String getRemoteDirectory()
    {
        return directory;
    }
    
    public void setRemoteDirectory(String directory)
    {
        this.directory = directory;
    }
    
    public String getDownloadDirectory()
    {
        return dlDir;
    }
    
    public void setDownloadDirectory(String dlDir)
    {
        this.dlDir = dlDir;
    }
    
    public int canConnect()
    {
        int out = SUCCESS;
        try
        {
            client.connect(getHost());
            boolean login = client.login(getUser(), getPassword());
            if(!login)
            {
                out = LOGIN_ERROR;
            }
            if(!fileExists(getRemoteDirectory()))
            {
                out = DIRECTORY_MISSING;
            }
            client.logout();
        } catch (IOException ex){}
        return out;
    }
    
    public boolean fileExists(String directory) throws IOException
    {
        if(directory.isEmpty())
        {
            return true;
        }
        //Get the parent directory
        String[] split = directory.split("/");
        String dir = split[split.length - 1];
        StringBuilder parentDir = new StringBuilder();
        for(int i = 0; i <= split.length - 2; i++)
        {
            parentDir.append(split[i]).append("/");
        }
        
        for(FTPFile f : client.listFiles(parentDir.toString()))
        {
            if(f.isDirectory() && f.getName().equals(dir))
            {
               return true; 
            }
        }
        return false;
    }
}