
//import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.*;
import static java.awt.GridBagConstraints.PAGE_START;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GBTest implements ActionListener{
    
    //create jpanel which will act as grid for analysis gui
    JPanel gridDisplay = new JPanel(new GridBagLayout());
    
    //create option buttons
    JButton yrAvgBtn1 = new JButton("Year averages and Software Engineering"
        + " module mark for all students");
    JButton tmAvgBtn2 = new JButton("Team averages");
    JButton oaAvgBtn3 = new JButton("Overall module average");
    JButton stComBtn4 = new JButton("Compare a student’s results against their"
            + " team’s results.");
    JButton inResBtn5 = new JButton("Enter results for student(s)");
    
    JLabel labeltest = new JLabel("whatever");
    
    //declare button on off switch
    private boolean yrAvgBtn1on;
    private boolean tmAvgBtn2on;
    
    //student name data for testing
    private String[] StudentsStr = {"Student1", "Student2", "Student3",
        "Student4", "Student5", "Student6", "Student7", "Student8", "Student9",
        "Student10"};
    
    //
    GridBagConstraints gbc = new GridBagConstraints();
    
    public GBTest()
    {
        //setTitle("GBTest Window");
        
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setVisible(true);

        //add(gridDisplay);
        //setSize(1000,700);
        //pack();
        //set gbc and add button to grid locations in JPanel
        buildDefault();
        
        //used to set buttons in off mode to start with.
        yrAvgBtn1on = false;
        tmAvgBtn2on = false;
    }
    public void buildDefault()
    {
        gbc.weightx=0.1;
        gbc.weighty=0.1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
	gbc.gridy = 0;
        //gbc.insets = new Insets(0,0,10,0);
        gridDisplay.add(yrAvgBtn1,gbc);
	gbc.gridy++;
        gridDisplay.add(tmAvgBtn2,gbc);
        gbc.gridy++;
        gridDisplay.add(oaAvgBtn3,gbc);
        gbc.gridy++;
        gridDisplay.add(stComBtn4,gbc);
        gbc.gridy++;
        gridDisplay.add(inResBtn5,gbc);

        
        //set action listeners for buttons
        yrAvgBtn1.addActionListener(this);
        tmAvgBtn2.addActionListener(this);
        oaAvgBtn3.addActionListener(this);
        stComBtn4.addActionListener(this);
        inResBtn5.addActionListener(this);
        gridDisplay.validate();
        gridDisplay.repaint();
        
    }
    

    
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource()==yrAvgBtn1)
        {
            if (yrAvgBtn1on == false)
            { 
                
                gridDisplay.removeAll();
                buildDefault();
                studentCol(StudentsStr,2);
                yrAvgBtn1on = !yrAvgBtn1on;
            }
            else
            {
                gridDisplay.removeAll();
                buildDefault();
                
                gridDisplay.validate();
                gridDisplay.repaint();
                
              }
        
        }
        if(event.getSource()==tmAvgBtn2)
        {
            studentCol(StudentsStr,3);
        }
        if(event.getSource()==oaAvgBtn3)
        {
            studentCol(StudentsStr,4);
        }
        if(event.getSource()==stComBtn4)
        {
            studentCol(StudentsStr,5);
        }
        if(event.getSource()==inResBtn5)
        {
            studentCol(StudentsStr,6);
        }
    
    }
    
    private void studentCol(String[] strArray, int rowStart)
    {
        JLabel[] students = new JLabel[strArray.length];
        for (int i=0; i<strArray.length; i++)
        {
            students[i] = new JLabel(strArray[i]);
            gbc.gridy=i+rowStart;
            //gbc.gridx=1;
            gridDisplay.add(students[i],gbc);
        }
        if (rowStart == 2)
        {
            gbc.gridy++;
            gridDisplay.add(tmAvgBtn2,gbc);
            rowStart++;
        }
        if (rowStart == 3)
        {
            gbc.gridy++;
            gridDisplay.add(oaAvgBtn3,gbc);
            rowStart++;
        }
        if (rowStart == 4)
        {
            gbc.gridy++;
            gridDisplay.add(stComBtn4,gbc);
            rowStart++;
        }
            
        if (rowStart == 5)
        {
            gbc.gridy++;
            gridDisplay.add(inResBtn5,gbc);
        }
        gridDisplay.validate();
        gridDisplay.repaint();

    }
}