package com.kennyjwilliams.submitbox.admin;

import com.kennyjwilliams.submitbox.framework.Course;
import com.kennyjwilliams.submitbox.CourseAPI;
import com.kennyjwilliams.submitbox.configuration.Serialization;
import com.kennyjwilliams.submitbox.framework.ftp.FTP;
import com.kennyjwilliams.submitbox.util.SLAPI;
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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Kenny
 */
public class AdminPanel extends JFrame
{
    private JList list;
    private DefaultListModel listValues;
    
    public AdminPanel()
    {
        super("Admin Panel");
        //Sets the look and feel to the default for the OS of the user
        try
        {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e){}
        
        //Save all courses on frame close
        addWindowListener(new WindowListener()
        {
            public void windowOpened(WindowEvent e){}
            public void windowClosing(WindowEvent e)
            {
                SLAPI.saveCourses();
            }
            public void windowClosed(WindowEvent e)
            {
                SLAPI.saveCourses();
            }
            public void windowIconified(WindowEvent e){}
            public void windowDeiconified(WindowEvent e){}
            public void windowActivated(WindowEvent e){}
            public void windowDeactivated(WindowEvent e){}
        });
        
        //Loads all courses
        SLAPI.loadCourses();
        
        JPanel main = new JPanel(new BorderLayout());
        
        JPanel top = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottom = new JPanel();
        JPanel right = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        /*
        Setup the FTP Settings
        */
        if(new File("settings"+File.separator+"settings.sbt").exists())
        {
            FTP.init(Serialization.deserializeFTP("settings"+File.separator+"settings.sbt"));
        }else
        {
            FTP.init("", "", "", "", "");
        }
        
        /*
        Menu Bar
        */
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem settings = new JMenuItem("Settings");
        settings.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new SettingsPanel(AdminPanel.this);
            }
        });
        file.add(settings);
        menu.add(file);
        setJMenuBar(menu);
        
        //Label the list
        JLabel listTitle = new JLabel("Class Editor");
        top.add(listTitle);
        
        //Adds the Table for the classes
        //setupClassesTemp();
        listValues = new DefaultListModel();
        updateClassList(listValues);
        list = new JList(listValues);
        list.setFixedCellWidth(150);
        list.setFixedCellHeight(30);
        list.setVisibleRowCount(5);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollList = new JScrollPane(list);
        scrollList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scrollList, gbc);

        /*
        Right panel buttons
        */
        Dimension btnDim = new Dimension(130, 30);
        double btnWeighty = 0.00;
        //Add class button
        JButton add = new JButton("Add class");
        add.setPreferredSize(btnDim);
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String newClass = JOptionPane.showInputDialog("Enter a new class name", "Add a class");
                if(newClass != null)
                {
                    addClass(new Course(newClass));
                }
            }
        });
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.weighty = btnWeighty;
        gbc.gridx = 1;
        gbc.gridy = 0;
        right.add(add, gbc);
        
        //Remove class button
        JButton remove = new JButton("Remove class");
        remove.setPreferredSize(btnDim);
        remove.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
                if(list.getSelectedValue() != null)
                {
                    String className = list.getSelectedValue().toString();
                    int delete = JOptionPane.showConfirmDialog(null, "Aren you sure you want to delete "+className, "Delete Class?", JOptionPane.YES_NO_OPTION);
                    if(delete == JOptionPane.OK_OPTION)
                    {
                        removeClass(list.getSelectedValue().toString());
                    }
                }
            }
        });
        gbc.weighty = btnWeighty;
        gbc.gridx = 1;
        gbc.gridy = 1;
        right.add(remove, gbc);
        
        //Edit class button
        JButton edit = new JButton("Edit class");
        edit.setPreferredSize(btnDim);
        edit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(list.getSelectedValue() != null)
                {
                    new EditCoursePanel(CourseAPI.getCourse(list.getSelectedValue().toString()));
                }
            }
        });
        gbc.weighty = btnWeighty;
        gbc.gridx = 1;
        gbc.gridy = 2;
        right.add(edit, gbc);
        
        /*
        Add panels
        */
        main.add(top, BorderLayout.PAGE_START);
        main.add(centerPanel, BorderLayout.CENTER);
        main.add(right, BorderLayout.LINE_END);
        main.add(bottom, BorderLayout.PAGE_END);
        
        main.setBorder(new EmptyBorder(0, 5, 0, 5));
        
        add(main);
        
        setLocationRelativeTo(null);
        setSize(300, 320);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
    
    public void updateClassList(DefaultListModel dl)
    {
        for(Course c : CourseAPI.getCourses())
        {
            dl.addElement(c.getName());
        }
    }
    
    public void addClass(Course c)
    {
        CourseAPI.addCourse(c);
        listValues.addElement(c.getName());
    }
    
    public void removeClass(String className)
    {
        listValues.removeElement(className);
        CourseAPI.removeCourse(CourseAPI.getCourse(className));
    }
    
    private void setupClassesTemp()
    {
        Course c1 = new Course("AP_CS");
        Course c2 = new Course("SQL");
        Course c3 = new Course("HTML");
        Course c4 = new Course("CSS");
        CourseAPI.addCourse(c1);
        CourseAPI.addCourse(c2);
        CourseAPI.addCourse(c3);
        CourseAPI.addCourse(c4);
    }
}
