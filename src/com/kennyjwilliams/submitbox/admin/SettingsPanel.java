package com.kennyjwilliams.submitbox.admin;

import com.kennyjwilliams.submitbox.configuration.Serialization;
import com.kennyjwilliams.submitbox.framework.ftp.Downloader;
import com.kennyjwilliams.submitbox.framework.ftp.FTP;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Kenny
 */
public class SettingsPanel extends JDialog
{
    private final JTextField hostField;
    private final JTextField usrField;
    private final JPasswordField passField;
    private final JTextField remDirField;
    private final JTextField localDirField;
    
    public SettingsPanel(JFrame frame)
    {
        super(frame, "Settings", true);
        
        addWindowListener(new WindowListener()
        {
            public void windowOpened(WindowEvent e){}
            public void windowClosing(WindowEvent e){}
            public void windowClosed(WindowEvent e)
            {
                Serialization.serialize(FTP.getInstance());
            }
            public void windowIconified(WindowEvent e){}
            public void windowDeiconified(WindowEvent e){}
            public void windowActivated(WindowEvent e){}
            public void windowDeactivated(WindowEvent e){}
        });
        
        JPanel main = new JPanel(new BorderLayout());
        
        //BorderLayout individual panels
        JPanel center = new JPanel(new GridBagLayout());
        JPanel right = new JPanel();
        JPanel bottom = new JPanel();
        
        //GridBagLayout Setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        Dimension txtFieldDim = new Dimension(150, 25);
        JLabel lbl;
        
        //Host Text Field
        lbl = new JLabel("Host");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(3, 5, 1, 5);
        center.add(lbl, gbc);
        hostField = new JTextField(FTP.getInstance().getHost());
        hostField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                FTP.getInstance().setHost(hostField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                FTP.getInstance().setHost(hostField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e){}
        });
        hostField.setPreferredSize(txtFieldDim);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        center.add(hostField, gbc);
        
        //Username text field
        lbl = new JLabel("User");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        center.add(lbl, gbc);
        usrField = new JTextField(FTP.getInstance().getUser());
        usrField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                FTP.getInstance().setUser(usrField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                FTP.getInstance().setUser(usrField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e){}
        });
        usrField.setPreferredSize(txtFieldDim);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        center.add(usrField, gbc);
        
        //Password text field
        lbl = new JLabel("Password");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        center.add(lbl, gbc);
        passField = new JPasswordField(FTP.getInstance().getPassword());
        passField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                FTP.getInstance().setPassword(passField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                FTP.getInstance().setPassword(passField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e){}
        });
        passField.setPreferredSize(txtFieldDim);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        center.add(passField, gbc);
        
        //Remote directory text field
        lbl = new JLabel("Remote Directory");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        center.add(lbl, gbc);
        remDirField = new JTextField(FTP.getInstance().getRemoteDirectory());
        remDirField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                FTP.getInstance().setRemoteDirectory(remDirField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                FTP.getInstance().setRemoteDirectory(remDirField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e){}
        });
        remDirField.setPreferredSize(txtFieldDim);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        center.add(remDirField, gbc);
        
        //Test Connection Button
        JButton testConnection = new JButton("Test Connection");
        testConnection.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!hostField.getText().isEmpty() && !usrField.getText().isEmpty() && !passField.getText().isEmpty() && !remDirField.getText().isEmpty())
                {
                    new TestConnectionFrame(SettingsPanel.this, hostField.getText(), usrField.getText(), passField.getText());
                    
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.gridy = 4;
        center.add(testConnection, gbc);
        
        //Local directory text field
        lbl = new JLabel("Local Directory");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        center.add(lbl, gbc);
        localDirField = new JTextField(FTP.getInstance().getDownloadDirectory());
        localDirField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                
            }

            @Override
            public void changedUpdate(DocumentEvent e){}
        });
        localDirField.setPreferredSize(txtFieldDim);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        center.add(localDirField, gbc);
        
        //Local directory select button
        JButton dotBtn = new JButton("...");
        dotBtn.setPreferredSize(new Dimension(40, 30));
        dotBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showDialog(SettingsPanel.this, "Select Folder");
                if(returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File f = fc.getSelectedFile();
                    localDirField.setText(f.getAbsolutePath());
                }
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 5;
        center.add(dotBtn, gbc);
        
        //Button Dimension
        Dimension btnDim = new Dimension(100, 30);
        
        //Apply Button
        JButton apply = new JButton("Apply");
        apply.setPreferredSize(btnDim);
        apply.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
            }
        });
        bottom.add(apply);
        
        //Save Button
        JButton ok = new JButton("OK");
        ok.setPreferredSize(btnDim);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        bottom.add(ok);
        
        //Add bottom panel border
        bottom.add(new JSeparator());
        
        /*
        Add panels
        */
        main.add(center, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.PAGE_END);
        
        //Adds the main panel to the frame
        add(main);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(300, 320);
        pack();
        setVisible(true);
    }
    
    private void applyChanges()
    {
        
    }
}
