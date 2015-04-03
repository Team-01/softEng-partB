import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewTeams extends JFrame
{
    
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();                                              // Create an instance of the Style class for accessing system styles
    SQLite db = new SQLite();                                                   // Create an instance of SQLite class for working on database
    
    int teamNumber = 7;                                                         //Team Number variable
    int counter = 0;                                                            //Counter for Number of teams
    int i = 0;
    int i2 = 0;                                                       //Counter for Number of teams
    
    public ViewTeams(SE se) throws SQLException
    {
        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        
        
        
        
        Box box1 = Box.createVerticalBox();
        ArrayList<JPanel> JPanels = new ArrayList<>();
        
        box1.setVisible(true);
        
        while(counter< teamNumber)                                              //Button creation loop
            {
                counter ++;
                JButton buttonName = new JButton("Team "+counter+"");
                JPanel tablePanel = new JPanel();
                
                JTable table = new JTable(buildTableModel(db.selectStart("SELECT StuName,StuID,StuEmail FROM students WHERE memberOfTeam = '"+counter+"';")));
            
                tablePanel.add(table);
                JPanels.add(tablePanel);
                
                box1.add(buttonName);
                box1.add(Box.createVerticalStrut (5));                             //Creates space in between elements
                box1.add(JPanels.get(i));
                box1.add(Box.createVerticalStrut (20));                             //Creates space in between elements
                i++;
            
                //Button Settings
                buttonName.setForeground(mainStyle.systemColor);
                buttonName.setFont(mainStyle.fontL);
                buttonName.setBackground(Color.WHITE);
                buttonName.setToolTipText("Click to Show Team members");
                buttonName.setContentAreaFilled(false);
                buttonName.setOpaque(true);      
            }
            i2++;
        p.add(box1);
        p.setVisible(true);

    }
    private DefaultTableModel buildTableModel(ResultSet selectStart) throws SQLException 
    {        
        ResultSetMetaData metaData = selectStart.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) 
        {
            columnNames.add(metaData.getColumnName(column));
        }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (selectStart.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(selectStart.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

    }
}