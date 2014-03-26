
package com.kennyjwilliams.submitbox.admin;

import com.kennyjwilliams.submitbox.framework.ftp.FTP;
import com.kennyjwilliams.submitbox.framework.ftp.Downloader;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kenny
 */
public class TestConnectionFrame extends JDialog
{
    private JLabel connection;
    private JButton ok;
    
    public TestConnectionFrame(Dialog d, String host, String user, String pass)
    {
        super(d, "Testing Connection ...", true);
        setLayout(new BorderLayout());
        
        /*
        BorderLayout main panels
        */
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();
        
        /*
        Connection label
        */
        connection = new JLabel("Connecting...");
        center.add(connection);
        
        /*
        Bottom OK button
        */
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        ok.setEnabled(false);
        bottom.add(ok);
        
        /*
        Add panels to the frame
        */
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);
        
        /*
        JFrame values
        */
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 125);
        setResizable(true);
        setVisible(true);
        testConnection(host, user, pass);
    }
    
    public void testConnection(String host, String user, String pass)
    {
        System.out.println("1");
        int status = Downloader.getFTP().canConnect();
        if(status == FTP.LOGIN_ERROR)
        {
            connection.setText("Could not login to server...");
        }else if (status == FTP.DIRECTORY_MISSING)
        {
            connection.setText("Invalid directory...");
        }else
        {
            connection.setText("Connection Successful!");
        }
        System.out.println("2");
        ok.setEnabled(true);
        System.out.println("3");
    }
}
