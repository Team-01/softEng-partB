import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;

public class ViewTeams extends JFrame
{
    
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();                                              // Create an instance of the Style class for accessing system styles
    SQLite db = new SQLite();                                                   // Create an instance of SQLite class for working on database
    
    int teamNumber = 6;                                                         //Team Number variable
    int counter = 0;                                                            //Counter for Number of teams
    
    public ViewTeams()
    {
        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        Box box1 = Box.createVerticalBox();
        
        box1.setVisible(true);
        
        while (counter < teamNumber)                                            //Button creation loop
        {
            counter ++;
            JButton buttonName = new JButton("                                Team "+counter+"                                ");
            
            box1.add(buttonName); //Creates space in between elements
            box1.add(Box.createVerticalStrut (20));

            //Button Settings
            buttonName.setForeground(mainStyle.systemColor);
            buttonName.setFont(mainStyle.fontL);
            buttonName.setBackground(Color.WHITE);
            buttonName.setToolTipText("Click to Show Team members");
            buttonName.setContentAreaFilled(false);
            buttonName.setOpaque(true);      
        }
        p.add(box1);
        p.setVisible(true);

    }
}