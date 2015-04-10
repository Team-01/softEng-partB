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
    SQLite db;// = new SQLite();                                                // Create an instance of SQLite class for working on database
    
    JPanel tablePanel;
    
    int teamNum;                                                                //Team Number variable
    int counter;                                                            //Counter for Number of teams
    int i = 0;                                                                  //Counter for JPanels
    int i2 = 0;
    
    Box box = Box.createVerticalBox();
    JPanel panelBox = new JPanel();
    
    String currentStudentTeam;
    
    
    String refreshButtonStr = "Refresh";
    JButton buttonRefresh = new JButton(refreshButtonStr);

    public ViewTeams(SE se)
    {
        // Make main class panel and make it a scrollpane
        p = new JPanel();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        //db = new SQLite();
        db = se.db;

        box.setVisible(true);
        
        buttonListener btnListener = new buttonListener();
        buttonRefresh.addActionListener(btnListener);
        p.add(buttonRefresh);
        
        db = se.db;
        refreshTables(db);
    }
    
    private void refreshTables(SQLite db)
    {
        db.refresh();
        panelBox.removeAll();
        box.removeAll();
        
        teamNum = (db.numberOfTeams()); 
        counter = 0;
        i = 0;
        i2 = 0;
        
        while(counter <teamNum)
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
            
            for (int count = 0; count < db.studentsID.size(); count++)
            {
                if (db.studentsMemberOfTeam.get(count).equals(currentStudentTeam))
                {
                    JPanel studentPanel = new JPanel(new GridLayout(0,3));
                    studentPanel.setBackground(Color.white);
                    studentPanel.setBorder(mainStyle.borderCustom(2, 2, 2, 2));
                    tablePanel.add(studentPanel);

                    String currentID = db.studentsStuID.get(count);
                    JTextField StuID = new JTextField(currentID);
                    StuID.setEditable(false);
                    JTextField StuName = new JTextField(db.studentsStuName.get(count));
                    StuName.setEditable(false);
                    JTextField StuEmail = new JTextField(db.studentsStuEmail.get(count));
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
        //p.add(refreshButton);

        panelBox.add(box);
        box.validate();
        box.repaint();
        p.add(panelBox);
        p.validate();
        p.repaint();
        
        
        //p.setVisible(true);
    }
    
    private class buttonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getActionCommand().compareTo(refreshButtonStr) == 0)
            {
                refreshTables(db);
                validate();
                repaint();
            }
        }
    }
}

