import static com.sun.org.apache.bcel.internal.Constants.WIDE;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ViewTeams extends JFrame
{
    
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();                                              // Create an instance of the Style class for accessing system styles
    SQLite db = new SQLite();                                                   // Create an instance of SQLite class for working on database
    
    JPanel tableP;
    
    int teamNum = (db.studentsMemberOfTeam.size()-2);                                                         //Team Number variable
    int counter = 0;//Counter for Number of teams
    int i = 0;                                                                  //Counter for JPanels
    int i2 = 0;

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
        
        Box box = Box.createVerticalBox();
        box.setVisible(true);
        
        while(counter<teamNum)
        {
            currentStudentTeam = String.valueOf(i);

            // Panel for entire table
            tableP = new JPanel(new GridLayout(0,1));

            //Panel for Team Number Names
            JPanel teamHeader = new JPanel();
            JLabel teamLabel = new JLabel("Team "+counter);
            teamHeader.add(teamLabel);
            box.add(teamHeader);
            
            teamHeader.setForeground(mainStyle.systemColor);
            teamLabel.setForeground(mainStyle.systemColor);
            teamLabel.setFont(mainStyle.fontL);

            // Panel for the header row and all JLabels
            JPanel headerP = new JPanel(new GridLayout(0,3));
            headerP.setBorder(mainStyle.borderCustom(5, 5, 5, 5));
            headerP.setBackground(mainStyle.systemLightGrey);
            headerP.setForeground(mainStyle.systemColor);
            tableP.add(headerP);

            ArrayList<JLabel> headers = new ArrayList<JLabel>();

            JLabel headerL1 = new JLabel("Student Name");
            JLabel headerL2 = new JLabel("Student ID");
            JLabel headerL3 = new JLabel("Student Email");

            headers.add(headerL1);
            headers.add(headerL2);
            headers.add(headerL3);
        
            for (JLabel header:headers)
            {
                header.setForeground(mainStyle.systemColor);
                header.setFont(mainStyle.fontS);
                headerP.add(header);
            }
                    //Creation of Button
            //JButton buttonName = new JButton("Team "+counter+"");
            //box.add(buttonName);
            box.add(Box.createVerticalStrut (5));
            box.add(tableP);
            box.add(Box.createVerticalStrut (20));
            
            for (int count = 0; count < se.db.studentsID.size(); count++)
            {
                if (se.db.studentsMemberOfTeam.get(count).equals(currentStudentTeam))
                {
                    JPanel studentP = new JPanel(new GridLayout(0,3));
                    studentP.setBackground(Color.white);
                    studentP.setBorder(mainStyle.borderCustom(2, 2, 2, 2));
                    tableP.add(studentP);

                    String currentID = se.db.studentsStuID.get(count);
                    JTextField StuID = new JTextField(currentID);
                    StuID.setEditable(false);
                    JTextField StuName = new JTextField(se.db.studentsStuName.get(count));
                    StuName.setEditable(false);
                    JTextField StuEmail = new JTextField(se.db.studentsStuEmail.get(count));
                    StuEmail.setEditable(false);
                    
                    // Add all components to panel
                    studentP.add(StuName);
                    studentP.add(StuID);
                    studentP.add(StuEmail);
                }
            }
            counter ++;
            i ++;
        }
    p.add(box);
    p.setVisible(true);
    }       
}
