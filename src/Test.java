import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.Math.*;

public class Test extends JFrame
{
    
    SQLite db = new SQLite();
    Style mainStyle = new Style();
    String currentStudent = "";
    ArrayList<JRadioButton> allTestButtons = new ArrayList<JRadioButton>();
    JPanel pTestMain;
    GridBagConstraints gbcTestMain;
        
    public Test(String currentStu)
    {
        
        // Pass through currentStudent variable from questionnaire
        currentStudent = currentStu;
        
        // Create pop up frame for test
        setSize(670,550);
        setTitle("Programming Test");
        mainStyle.centerFrame(this);
        setResizable(true);
        setUndecorated(true);
        setBackground(mainStyle.systemColor);
        

        // Create main panel
        pTestMain = new JPanel(new GridBagLayout());
        gbcTestMain = new GridBagConstraints();
        pTestMain.setBackground(mainStyle.systemColor);
        pTestMain.setBorder(mainStyle.border20);
        gbcTestMain.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(pTestMain);

        // Create welcome panel and labels
        gbcTestMain.gridy = 0;
        JPanel introPanel = new JPanel(new GridLayout(0,1));
        JLabel introLabel1 = new JLabel("Thank you for completing the questionnaire.", SwingConstants.CENTER);
        JLabel introLabel2 = new JLabel("As you indicated that you have prior programming experience,", SwingConstants.CENTER);
        JLabel introLabel3 = new JLabel("please complete the short test below.", SwingConstants.CENTER);
        JLabel[] introLabels = {introLabel1, introLabel2, introLabel3};
        for (JLabel l:introLabels)
        {
            l.setFont(mainStyle.fontMb);
            l.setBackground(mainStyle.systemColor);
            l.setForeground(Color.white);
            introPanel.add(l);
        }
        introPanel.setBackground(mainStyle.systemColor);
        introPanel.setBorder(mainStyle.border10);
        pTestMain.add(introPanel, gbcTestMain);

            
        // Create panel for all questions and set as gridlayout for same width
        JPanel pAllQuestions = new JPanel(new GridLayout(0, 1));
        pAllQuestions.setBorder(mainStyle.borderCustom(40, 5, 40, 5));
        pAllQuestions.setBackground(mainStyle.systemColor);
        gbcTestMain.gridy++;
        pTestMain.add(pAllQuestions, gbcTestMain);

        // Refresh questions array
        db.selectQuestions();
        
        // Create button groups and add to array
        ButtonGroup bg1, bg2, bg3;
        bg1 = new ButtonGroup(); bg2 = new ButtonGroup(); bg3 = new ButtonGroup();
        ButtonGroup[] buttonGroupArray = {bg1, bg2, bg3};
        
        // Go through all questions in DB
        int testCount = 0;
        int buttonGroupCount = 0;
        for (String tq:db.questionsQuestion)
        {
            if (testCount>18) // finds if a question is a test question
            {
                // Create a test question block panel
                JPanel pTestQuestionBlock = new JPanel(new GridLayout(0, 1));
                EmptyBorder borderTestQuestionBlock = new EmptyBorder(10, 10, 10, 10);
                pTestQuestionBlock.setBackground(mainStyle.systemColor);
                pTestQuestionBlock.setBorder(borderTestQuestionBlock);
                pAllQuestions.add(pTestQuestionBlock, gbcTestMain);

                // Create the question as a label and add panel
                JPanel testQuestion = new JPanel();
                testQuestion.setBackground(mainStyle.systemColor);
                JLabel testQ = new JLabel(tq);
                testQ.setFont(mainStyle.fontM);
                testQ.setForeground(Color.white);
                testQuestion.add(testQ);
                pTestQuestionBlock.add(testQuestion);

                // Create answer panel with a new gridbagcontraint
                JPanel testAnswerBlock = new JPanel(new GridBagLayout());
                testAnswerBlock.setBackground(mainStyle.systemColor);
                GridBagConstraints gbcAnswer = new GridBagConstraints();
                pTestQuestionBlock.add(testAnswerBlock);

                // Add 2 radio buttons for Yes/No answers and add to the panel
                JRadioButton testAnswer1 = new JRadioButton("Yes");
                JRadioButton testAnswer2 = new JRadioButton("No");
                testAnswer1.setFont(mainStyle.fontS);
                testAnswer2.setFont(mainStyle.fontS);
                testAnswer1.setForeground(Color.white);
                testAnswer2.setForeground(Color.white);
                gbcAnswer.gridx = 0;
                testAnswerBlock.add(testAnswer1, gbcAnswer);
                gbcAnswer.gridx = 1;
                testAnswerBlock.add(testAnswer2, gbcAnswer);
                // Add radio buttons to button groups so each question has its own bg
                buttonGroupArray[buttonGroupCount].add(testAnswer1);
                buttonGroupArray[buttonGroupCount].add(testAnswer2);
                buttonGroupCount++;
                // Add radio buttons to 'all test buttons' array list, for use in action listener
                allTestButtons.add(testAnswer1);
                allTestButtons.add(testAnswer2);
            }

            testCount++;
        }
        
        
        // Make panel for submit button
        JPanel pSubmitTest = new JPanel(new BorderLayout());
        gbcTestMain.gridy++;
        pTestMain.add(pSubmitTest, gbcTestMain);
        // Make submit button and add to panel
        JButton bSubmitTest = new JButton("Submit Test");
        pSubmitTest.setBackground(mainStyle.systemColor);
        pSubmitTest.add(bSubmitTest, BorderLayout.CENTER);
        // Add action listener to button
        bSubmitTest.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                // Close test frame
                dispose();
                
                // Calculate score
                int score = 0;
                int allTestButtonsCount = 0;
                for (JRadioButton b: allTestButtons)
                {
                    // looks to see if correct button is selected (No for q1 or No for q2 or Yes for q3)
                    if (b.isSelected()==true && (allTestButtonsCount == 1 || allTestButtonsCount == 3 || allTestButtonsCount == 4))
                    {
                        score++;
                    }
                    allTestButtonsCount+=1;
                }
                
                
                // Update record for currentStudent with score of test
                db.modify("UPDATE students SET testScore = "+score+" WHERE stuID = '"+currentStudent+"';");
   
                // Make test results pop up frame, panel, labels and button
                mainStyle.createPopUpFrame("Thanks for completing the questionnaire & test. You scored "+score+"/3.", 450, 150);
                
                
            } 
        });
    }
   
   
}