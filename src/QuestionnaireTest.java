import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuestionnaireTest {
    
    JPanel p;
    JScrollPane sp;
    
    EmptyBorder borderScroll = new EmptyBorder(0,0,0,0);
    Style mainStyle = new Style();
    
    public QuestionnaireTest()
    {
        test();
    }
    
    public void test()
    {

    
    
     // Create first tab content
        
        // Make the panel and GBC
        p = new JPanel(new GridBagLayout());
        p.setBackground(Color.white);
        sp = new JScrollPane(p);
        sp.setBorder(borderScroll);
        sp.getVerticalScrollBar().setUnitIncrement(20); // This is required for smooth scrolling
        
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(5, 5, 5, 5);
        
        // Create the stuID panel (including label + text field) and add to p
        gbc1.gridy = 0;
        JPanel pStuID = new JPanel();
        pStuID.setBackground(Color.white); 
        EmptyBorder borderStuID = new EmptyBorder(30, 0, 30, 0);
        pStuID.setBorder(borderStuID);
        JLabel labelStuID = new JLabel("Student ID:");
        JTextField textStuID = new JTextField("e.g. C0815038");
        textStuID.setPreferredSize( new Dimension( 200, 24 ) );
        pStuID.add(labelStuID); pStuID.add(textStuID); // add to stu id panel
        p.add(pStuID, gbc1); // add stu id panel to p
        
        
        // Make the questionnaire (using questionnaire class)
        Questionnaire q = new Questionnaire(); // an object of questionnaire class
        int qCount = 0; // a count for the while loop
        int gridCountQuestion = 0; // a count to ensure each question is printed beneath the previous
        int qLength = q.questions.length;
        
        // Create the button groups
        ButtonGroup bg1, bg2, bg3, bg4, bg5, bg6, bg7;
        bg1 = new ButtonGroup(); bg2 = new ButtonGroup(); bg3 = new ButtonGroup(); 
        bg4 = new ButtonGroup(); bg5 = new ButtonGroup(); bg6 = new ButtonGroup(); bg7 = new ButtonGroup();
        // Create a Button Group array
        ButtonGroup[] bgArray = {bg1, bg2, bg3, bg4, bg5, bg6, bg7};
        
        // Make panel for whole block of questions
        gbc1.gridy = 1;
        JPanel pAllQs = new JPanel(new GridLayout(0, 1));
        p.add(pAllQs, gbc1);
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);
        
        
        
        while (qCount < qLength)
        {
            // Create a panel for each Q box
            JPanel pQBox = new JPanel(new GridLayout(0,1));
            EmptyBorder borderQBox = new EmptyBorder(20, 20, 20, 20);
            pQBox.setBorder(borderQBox);
            JPanel pQuestion = new JPanel();
            JPanel pAnswers = new JPanel(new GridBagLayout());
            
            //Set colour for panels
            pQBox.setBackground(Color.white);
            pQuestion.setBackground(mainStyle.systemRed);
            pAnswers.setBackground(mainStyle.systemGrey);
            
            // Create question (set it to bold) and answer objects
            JLabel question = new JLabel("Q"+q.questions[qCount].getID()+". "+q.questions[qCount].getQuestion());
            Font font = new Font("Arial", Font.BOLD,18);
            question.setFont(font);
            question.setForeground(Color.white);
            JRadioButton radio1 = new JRadioButton("A) "+q.questions[qCount].getAnswer(1));
            JRadioButton radio2 = new JRadioButton("B) "+q.questions[qCount].getAnswer(2));
            JRadioButton radio3 = new JRadioButton("C) "+q.questions[qCount].getAnswer(3));
            JRadioButton radio4 = new JRadioButton("D) "+q.questions[qCount].getAnswer(4));
            
            //Add buttons to the different button group objects in the ButtonGroup array, so each question has a separate group
            bgArray[qCount].add(radio1);
            bgArray[qCount].add(radio2);
            bgArray[qCount].add(radio3);
            bgArray[qCount].add(radio4);
           
            
            //Add the question to the panel
            pQuestion.add(question, gbc2);
            
            //Add the answers to the panel
            gbc2.gridy = 0; 
            gbc2.gridx = 0;
            pAnswers.add(radio1, gbc2);
            gbc2.gridx = 1;
            pAnswers.add(radio2, gbc2);
            gbc2.gridx = 2;
            pAnswers.add(radio3, gbc2);
            gbc2.gridx = 3;
            pAnswers.add(radio4, gbc2);
            
            //Add panels to their containers
            pQBox.add(pQuestion);
            pQBox.add(pAnswers);
            pAllQs.add(pQBox);
            
            // Increment
            qCount++;
            gridCountQuestion+=5;
        }
        
        // Creating a panel for the submit button, creating submitting button, adding to panel, adding panel to p
        gbc1.gridy = 2;
        JPanel pSubmitQ = new JPanel(new BorderLayout());
        pSubmitQ.setBackground(Color.white);
        EmptyBorder borderSubmitQ = new EmptyBorder(10, 20, 40, 20);
        pSubmitQ.setBorder(borderSubmitQ);
        JButton bSubmitQ = new JButton("Submit");
        bSubmitQ.addActionListener(new questionnaireToTest());
        pSubmitQ.add(bSubmitQ, BorderLayout.EAST);
        p.add(pSubmitQ, gbc1);
    }
    
    static class questionnaireToTest implements ActionListener
        {
            public void actionPerformed (ActionEvent e)
            {
                
                
                
                // Create pop up frame for test
                JFrame frameTest = new JFrame();
                frameTest.setSize(400,600);
                    // How to make frame appear center of screen
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    frameTest.setLocation(dim.width/2-frameTest.getSize().width/2, dim.height/2-frameTest.getSize().height/2);
                frameTest.setResizable(false);
                //frameTest.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Prevents user from closing pop up test frame
                frameTest.setVisible(true);
                
                
                // Create main panel
                JPanel pTestMain = new JPanel(new GridBagLayout());
                GridBagConstraints gbcTest = new GridBagConstraints();
                gbcTest.insets = new Insets(5, 5, 5, 5);
                frameTest.add(pTestMain);
                
                
                // Create welcome label
                gbcTest.gridy = 0;
                JLabel tempLabel = new JLabel("Welcome to the test");
                pTestMain.add(tempLabel);
                
                // Create test questions (will code later)
            }
            
            public void actionPerformed2 (ActionEvent e)
            {
                
            }
        }
        
}
