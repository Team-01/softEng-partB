import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class SE extends JFrame
{
    JPanel pMain;
    Style mainStyle = new Style(); // Create an instance of the Style class for accessing system styles
    SQLite db = new SQLite(); // Create an instance of SQLite class for working on SE.db sqlite database
    
    
    
    public SE()
    {
                
        // Set up frame
        setTitle("System Title");
        setSize(1200, 750);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainStyle.centerFrame(this);
                
        // Make main panel, give it the border defined above, add it to it's container (the frame)
        pMain = new JPanel(new BorderLayout());
        pMain.setBorder(mainStyle.border20);
        getContentPane().add(pMain);
        pMain.setBackground(mainStyle.systemLightGrey);
        
        // Make the tabbed pain object
        JTabbedPane tabMenu = new JTabbedPane();
        
        
        // Make the tabs (objects of the different use case classes)
        Welcome WelcomePane = new Welcome();
        Questionnaire UseCase1 = new Questionnaire();
        CreateTeams UseCase2 = new CreateTeams();
        ViewTeams UseCase3 = new ViewTeams();
        AnalyseResults UseCase4 = new AnalyseResults();
        ManageQuestions ExtraFeature1 = new ManageQuestions();
        ManageStudents ExtraFeature2 = new ManageStudents();
        Settings ExtraFeature3 = new Settings();
        
        
        // Add the tabs to the Tabbed Pane
        tabMenu.addTab("Welcome", WelcomePane.sp);
        tabMenu.addTab("Questionnare/Test", UseCase1.sp);
        tabMenu.addTab("Create Teams", UseCase2.sp);
        tabMenu.addTab("View Teams", UseCase3.sp);
        tabMenu.addTab("Analyse Results", UseCase4.sp);
        tabMenu.addTab("Manage Questions", ExtraFeature1.sp);
        tabMenu.addTab("Manage Students", ExtraFeature2.sp);
        tabMenu.addTab("Settings", ExtraFeature3.sp);
        
        
        // Add the tabbed pane to the main panel
        pMain.add(tabMenu, BorderLayout.CENTER);
    }
   
    public static void main(String[] args)
    {
        SE f = new SE();
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); //makes full screen, but staggers at the moment
        f.setIconImage(Toolkit.getDefaultToolkit().getImage("res/SEicon.png"));
        f.setVisible(true);
    }
    
}