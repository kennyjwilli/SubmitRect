
package com.kennyjwilliams.submitbox.framework.task;

import com.kennyjwilliams.submitbox.framework.ftp.Downloader;
import javax.swing.SwingWorker;

/**
 *
 * @author Kenny
 */
public class FTPConnectionTestTask extends SwingWorker
{
    private String host;
    private String user;
    private String password;
    
    public FTPConnectionTestTask(String host, String user, String password)
    {
        this.host = host;
        this.user = user;
        this.password = password;
    }
    
    @Override
    public Object doInBackground()
    {
        return Downloader.canConnect(host, user, password, "t");
    }
}
