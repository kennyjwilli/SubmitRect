package com.kennyjwilliams.submitbox.framework.ftp;

/**
 *
 * @author Kenny
 */
public class Downloader 
{  
    private static FTP ftp;
    private static String dlDir;
    
    public Downloader(FTP ftp, String dlDir)
    {
        Downloader.ftp = ftp;
        Downloader.dlDir = dlDir;
    }
    
    public static FTP getFTP()
    {
        return ftp;
    }
    
    public static String getDownloadDirectory()
    {
        return dlDir;
    }
    
    public static void setDownloadDirectory(String dir)
    {
        Downloader.dlDir = dir;
    }
}
