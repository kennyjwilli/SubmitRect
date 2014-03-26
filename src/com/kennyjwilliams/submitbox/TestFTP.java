
package com.kennyjwilliams.submitbox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Kenny
 */
public class TestFTP 
{
    public static void main(String[] args)
    {
        FTPClient client = new FTPClient();
        FileOutputStream fos = null;
        try
        {
            client.connect("vectorgaming.net");
            boolean login = client.login("u70048125", "baseball88");
            if(login)
            {
                System.out.println("Connection established...");
                File f = new File("files"+File.separator+"downloadedFile12.txt");
                if(!f.exists())
                {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                    System.out.println(f.getAbsolutePath());
                }
                fos = new FileOutputStream(f);
                boolean download = client.retrieveFile("config.txt", fos);
                if(download)
                {
                    System.out.println("Downloaded file successfully!");
                }else
                {
                    System.out.println("Error downloading file!");
                }
                
                boolean logout = client.logout();
                if(logout)
                {
                    System.out.println("Connection closed...");
                }
            }else
            {
                System.out.println("Connection failed...");
            }
        }catch(SocketException ex)
        {
            Logger.getLogger(TestFTP.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex)
        {
            Logger.getLogger(TestFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
