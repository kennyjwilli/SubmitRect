package com.kennyjwilliams.submitbox.admin;

import com.kennyjwilliams.submitbox.configuration.Serialization;
import com.kennyjwilliams.submitbox.framework.Assignment;
import com.kennyjwilliams.submitbox.framework.Course;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;
import org.jbundle.thin.base.screen.jcalendarbutton.JTimeButton;

/**
 *
 * @author Kenny
 */
public class EditCoursePanel extends JFrame
{
    private DefaultListModel listValues;
    private final JList assignments;
    private Assignment assn; //selected assignment
    private Course c;
    //Edit Fields
    private JTextField nameTxt;
    private JTextField dateTxt;
    private JTextField timeTxt;
    
    public EditCoursePanel(final Course c)
    {
        super(c.getName()+" Editor");
        this.c = c;
        setLayout(new BorderLayout());
        
        addWindowListener(new WindowListener()
        {
            public void windowOpened(WindowEvent e){}
            public void windowClosing(WindowEvent e){}
            public void windowClosed(WindowEvent e)
            {
                Serialization.serialize(c);
            }
            public void windowIconified(WindowEvent e){}
            public void windowDeiconified(WindowEvent e){}
            public void windowActivated(WindowEvent e){}
            public void windowDeactivated(WindowEvent e){}
        });
        
        JPanel top = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottom = new JPanel();
        JPanel right = new JPanel();
        JPanel left = new JPanel();
                
        /*
        Adds the list
        */
        listValues = new DefaultListModel();
        updateAssignments();
        assignments = new JList(listValues);
        assignments.setSelectedIndex(0);
        assignments.setFixedCellWidth(150);
        assignments.setFixedCellHeight(30);
        assignments.setVisibleRowCount(5);
        assignments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        assignments.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int i = assignments.getSelectedIndex();
                String selected = assignments.getSelectedValue().toString();
                assn = c.getAssignment(selected);
                updateTextFields();
                assignments.setSelectedIndex(i);
            }
        });
        JScrollPane scrollList = new JScrollPane(assignments);
        scrollList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(scrollList);
        
        /*
        Bottom buttons
        */
        //Add assignment button
        JButton add = new JButton("Add assignment");
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String newAssignment = JOptionPane.showInputDialog("Enter the name of the assignment");
                addAssignment(new Assignment(newAssignment));
            }
        });
        bottom.add(add);
        
        //Remove assignment button
        JButton remove = new JButton("Remove assignment");
        remove.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String assignment = assignments.getSelectedValue().toString();
                int newAssignment = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete "+assignment, "Delete an Assignment", JOptionPane.YES_NO_OPTION);
                if(newAssignment == JOptionPane.YES_OPTION)
                {
                    removeAssignment(c.getAssignment(assignment));
                }
            }
        });
        bottom.add(remove);
        
        /*
        Selected assignment
        */
        
        assn = c.getAssignment(assignments.getSelectedValue().toString());
        
        /*
        Text Field Panel
        */
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Dimension txtFieldDim = new Dimension(150, 25);
        
        //Assignment Name Text Box
        JLabel nameLbl = new JLabel("Name");
        gbc.gridx = 0;
        gbc.gridy = 0;
        fieldPanel.add(nameLbl, gbc);
        nameTxt = new JTextField(assn.getName());
        nameTxt.setPreferredSize(txtFieldDim);
        nameTxt.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e){}
            
            private void update()
            {
                String oldName = assn.getName();
                listValues.add(listValues.indexOf(oldName), nameTxt.getText());
                listValues.removeElement(oldName);
                assn.setName(nameTxt.getText());
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        fieldPanel.add(nameTxt, gbc);
        
        JLabel dateLbl = new JLabel("Date");
        gbc.gridx = 0;
        gbc.gridy = 2;
        fieldPanel.add(dateLbl, gbc);
        dateTxt = new JTextField(assn.getDueDate().getDateFormatted());
        dateTxt.setPreferredSize(txtFieldDim);
        gbc.gridx = 0;
        gbc.gridy = 3;
        fieldPanel.add(dateTxt, gbc);
        JCalendarButton cal = new JCalendarButton();
        cal.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                if(evt.getNewValue() instanceof Date)
                {
                    setDate(assn, (Date) evt.getNewValue());
                }
            }         
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        fieldPanel.add(cal, gbc);
        
        //Time Field Panel
        JLabel timeLbl = new JLabel("Time");
        gbc.gridx = 0;
        gbc.gridy = 4;
        fieldPanel.add(timeLbl, gbc);
        timeTxt = new JTextField(assn.getDueDate().getTimeFormatted());
        timeTxt.setPreferredSize(txtFieldDim);
        gbc.gridx = 0;
        gbc.gridy = 5;
        fieldPanel.add(timeTxt, gbc);
        JTimeButton timeBtn = new JTimeButton();
        timeBtn.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                if(evt.getNewValue() instanceof Date)
                {
                    setTime(assn, (Date) evt.getNewValue());
                }
            }            
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        fieldPanel.add(timeBtn, gbc);
        
        //Allow late check box
        final JCheckBox allowLate = new JCheckBox("Allow late assignments", assn.canSubmitLate());
        allowLate.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                assn.setSubmitLate(allowLate.isSelected());
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        fieldPanel.add(allowLate, gbc);
        
        //Adds text field panel to the right panel
        right.add(fieldPanel);
        
        /*
        Add panels
        */
        add(top, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(right, BorderLayout.LINE_END);
        add(bottom, BorderLayout.PAGE_END);
                
        setLocationRelativeTo(null);
        setSize(300, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
        
    public void addAssignment(Assignment asgm)
    {
        c.addAssignment(asgm);
        listValues.addElement(asgm.getName());
    }
    
    public void removeAssignment(Assignment asgm)
    {
        c.removeAssignment(asgm);
        listValues.removeElement(asgm.getName());
    }
    
    public void updateAssignments()
    {
        for(Assignment a : c.getAssignments())
        {
            listValues.addElement(a.getName());
        }
    }
    
    public void setDate(Assignment assn, Date d)
    {
        assn.getDueDate().setDate(d.getMonth(), d.getDate(), d.getYear());
        dateTxt.setText(assn.getDueDate().getDateFormatted());
    }
    
    public void setTime(Assignment assn, Date d)
    {
        assn.getDueDate().setTime(d.getHours(), d.getMinutes());
        timeTxt.setText(assn.getDueDate().getTimeFormatted());
    }
    
    public void updateTextFields()
    {
        Assignment assn = c.getAssignment(assignments.getSelectedValue().toString());
        nameTxt.setText(assn.getName());
        dateTxt.setText(assn.getDueDate().getDateFormatted());
        timeTxt.setText(assn.getDueDate().getTimeFormatted());
    }
}
