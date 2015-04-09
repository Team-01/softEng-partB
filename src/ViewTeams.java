import static com.sun.org.apache.bcel.internal.Constants.WIDE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;


public class ViewTeams extends JFrame 
{
    
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();                                              // Create an instance of the Style class for accessing system styles
    SQLite db;// = new SQLite();                                                   // Create an instance of SQLite class for working on database
    
    JPanel tablePanel;
    
    //int 
    int teamNum;// = (db.numberOfTeams());                                                         //Team Number variable
    int counter = 0;//Counter for Number of teams
    int i = 0;                                                                  //Counter for JPanels
    int i2 = 0;
    RefreshListener rb = new RefreshListener();
    
    JButton refreshButton = new JButton("Refresh Page");
    

    Vector<String> columnName;                           //Vector in which desired column names are set
    Vector<String> studentData = new Vector<String>();                                 //Vector in which student data is set
    Vector<Vector<String>> dataColumn = new Vector<Vector<String>>();           //Vector in which data vector is set for DefaultTableModel
    
    String currentStudentTeam;


        
    public ViewTeams(SE se)
    {
        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        db = new SQLite();
        teamNum = (db.numberOfTeams()); 
        
        Box box = Box.createVerticalBox();
        box.setVisible(true);
        
        //Refresh Button Settings
        refreshButton.setForeground(mainStyle.systemColor);
        refreshButton.setFont(mainStyle.fontL);
        refreshButton.setBackground(Color.WHITE);
        refreshButton.setContentAreaFilled(false);
        refreshButton.setOpaque(true);
        
        refreshButton.addActionListener(rb);
        
        while(counter<teamNum)
        {
            currentStudentTeam = String.valueOf(i);

            // Panel for Table layout
            tablePanel = new JPanel(new GridLayout(0,1));

            //Panel for Team Number Names
            JPanel teamHeader = new JPanel();
            JLabel teamLabel = new JLabel("Team "+counter);
            teamHeader.add(teamLabel);
            box.add(teamHeader);
            
            teamHeader.setForeground(mainStyle.systemColor);
            teamLabel.setForeground(mainStyle.systemColor);
            teamLabel.setFont(mainStyle.fontL);

            // Panel for the header row and all JLabels
            JPanel headerPanel = new JPanel(new GridLayout(0,3));
            headerPanel.setBorder(mainStyle.borderCustom(5, 5, 5, 5));
            headerPanel.setBackground(mainStyle.systemLightGrey);
            headerPanel.setForeground(mainStyle.systemColor);
            tablePanel.add(headerPanel);

            ArrayList<JLabel> headerArray = new ArrayList<JLabel>();

            JLabel header1 = new JLabel("Student Name");
            JLabel header2 = new JLabel("Student ID");
            JLabel header3 = new JLabel("Student Email");

            headerArray.add(header1);
            headerArray.add(header2);
            headerArray.add(header3);
        
            for (JLabel header:headerArray)
            {
                header.setForeground(mainStyle.systemColor);
                header.setFont(mainStyle.fontS);
                headerPanel.add(header);
            }
                    //Creation of Button
            //JButton buttonName = new JButton("Team "+counter+"");
            //box.add(buttonName);
            box.add(Box.createVerticalStrut (5));
            box.add(tablePanel);
            box.add(Box.createVerticalStrut (20));
            
            for (int count = 0; count < se.db.studentsID.size(); count++)
            {
                if (se.db.studentsMemberOfTeam.get(count).equals(currentStudentTeam))
                {
                    JPanel studentPanel = new JPanel(new GridLayout(0,3));
                    studentPanel.setBackground(Color.white);
                    studentPanel.setBorder(mainStyle.borderCustom(2, 2, 2, 2));
                    tablePanel.add(studentPanel);

                    String currentID = se.db.studentsStuID.get(count);
                    JTextField StuID = new JTextField(currentID);
                    StuID.setEditable(false);
                    JTextField StuName = new JTextField(se.db.studentsStuName.get(count));
                    StuName.setEditable(false);
                    JTextField StuEmail = new JTextField(se.db.studentsStuEmail.get(count));
                    StuEmail.setEditable(false);
                    
                    // Add all components to panel
                    studentPanel.add(StuName);
                    studentPanel.add(StuID);
                    studentPanel.add(StuEmail);
                }
            }
            counter ++;
            i ++;
        }
        p.add(box);
        p.add(refreshButton);
        p.setVisible(true);   
    }
    
    private class RefreshListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == refreshButton)
            {
                sp.validate();
                sp.revalidate();
                sp.repaint();
            }
        }
    }
}

