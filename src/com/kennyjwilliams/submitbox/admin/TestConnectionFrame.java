package com.kennyjwilliams.submitbox.admin;

import com.kennyjwilliams.submitbox.framework.ftp.FTP;
import com.kennyjwilliams.submitbox.framework.ftp.Downloader;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Kenny
 */
public class TestConnectionFrame extends JDialog
{
    private JLabel connection;
    private JButton ok;
    private JProgressBar uploadBar;
    
    public TestConnectionFrame(Dialog d, final String host, final String user, final String pass)
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
        uploadBar = new JProgressBar(0, 100);
        uploadBar.setStringPainted(false);
        uploadBar.setIndeterminate(true);
        uploadBar.setVisible(true);
        center.add(uploadBar);
        
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
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                testConnection(host, user, pass);
            }
        }).start();
        
        setVisible(true);
    }
    
    public void testConnection(String host, String user, String pass)
    {
        final int status = FTP.getInstance().canConnect();
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
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
                uploadBar.setVisible(false);
                ok.setEnabled(true);
            }
        });
    }
}
