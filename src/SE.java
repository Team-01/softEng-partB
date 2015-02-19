import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SE extends JFrame
{
    JPanel pMain;
    
    Style mainStyle = new Style();
    
    public SE()
    {
        
        // Set up frame
        setTitle("Software Engineering Project");
        setSize(1000, 700);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // How to make frame appear center of screen
                    Dimension dimMain = Toolkit.getDefaultToolkit().getScreenSize();
                    setLocation(dimMain.width/2-getSize().width/2, dimMain.height/2-getSize().height/2);
        
        
        // Make main panel, give it the border defined above, add it to it's container (the frame)
        pMain = new JPanel(new BorderLayout());
        EmptyBorder borderMain = new EmptyBorder(20, 20, 20, 20); // creates an empty border for the main panel
        pMain.setBorder(borderMain);
        getContentPane().add(pMain);
        pMain.setBackground(mainStyle.systemGrey);
        
        // Make the tabbed pain object
        JTabbedPane tabMenu = new JTabbedPane();
        
        // Make the tabs       
        QuestionnaireTest UseCase1 = new QuestionnaireTest();
        CreateTeams UseCase2 = new CreateTeams();
        ViewTeams UseCase3 = new ViewTeams();
        AnalyseResults UseCase4 = new AnalyseResults();
        
        // Add the tabs to the Tabbed Pane
        tabMenu.addTab("Questionnare/Test", UseCase1.sp);
        tabMenu.addTab("Create Teams", UseCase2.sp);
        tabMenu.addTab("View Teams", UseCase3.sp);
        tabMenu.addTab("Analyse Results", UseCase4.sp);
        
        //tabMenu.addTab("Analyse Results", sp4);
        // Add the tabbed pane to the main panel
        pMain.add(tabMenu, BorderLayout.CENTER);
        
       
    }
   
   
    
    public static void main(String[] args)
    {
        SE f = new SE();
        f.setVisible(true);
    }
    
}