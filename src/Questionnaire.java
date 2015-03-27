import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Questionnaire {
    
    JPanel p;
    JScrollPane sp;
    
    Style mainStyle = new Style();  // Create an instance of the Style class for accessing system styles
    // Create an array list of all buttons (so 95 in total: 5 buttons * 19 questons) to feed answer to database
    ArrayList<JRadioButton> allButtons = new ArrayList<JRadioButton>();
    // Create an array list of all button groups, so can be used in action listener for resetting questionnaire
    ArrayList<ButtonGroup> allButtonGroups = new ArrayList<ButtonGroup>();
    JTextField textStuID;
    JTextField textStuName;
    JComboBox cbStuSubject;
    JComboBox cbStuStudyType;
    JTextField textStuEmail;
    String currentStudentID;
    String currentStudentName;
    String currentStudentEmail;
    // Create array list of all previous subjects to be shown in combobox
    ArrayList<String> prevSubjects = new ArrayList<String>();
    
    public Questionnaire(final SE se)
    {
   
        // Make the panel and GBC
        p = new JPanel(new GridBagLayout());
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        // Make it a scroll pane and smooth the scrolling
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(5, 5, 5, 5);
        
        // Fill previous subjects array list
        prevSubjects.add("Business");
        prevSubjects.add("Engineering");
        prevSubjects.add("Science");
        prevSubjects.add("Art");
        prevSubjects.add("Technology");
        prevSubjects.add("Other");
        
        
        // Create the pStuInfo panel
        gbc1.gridy = 0;
        JPanel pStuInfo = new JPanel();
        pStuInfo.setBackground(mainStyle.systemExtraLightGrey); 
        EmptyBorder borderStuID = new EmptyBorder(20, 30, 20, 30);
        pStuInfo.setBorder(borderStuID);
        
        // Create stu ID label + text field
        JLabel labelStuID = new JLabel("Stu ID:");
        labelStuID.setForeground(mainStyle.systemExtraDarkGrey);
        textStuID = new JTextField("e.g. C0815038");
        textStuID.setPreferredSize( new Dimension( 110, 25 ) );
        //Empty text field when clicked on
        textStuID.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){textStuID.setText("");}});
        
        // Create stu Name label + text field
        JLabel labelStuName = new JLabel("  Name:");
        labelStuName.setForeground(mainStyle.systemExtraDarkGrey);
        textStuName = new JTextField("e.g. Joe Bloggs");
        textStuName.setPreferredSize( new Dimension( 120, 25 ) );
        //Empty text field when clicked on
        textStuName.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){textStuName.setText("");}});
        
        // Create background subject label + combo box fields
        JLabel labelStuPrevSubject = new JLabel("   Previous Subject:");
        labelStuPrevSubject.setForeground(mainStyle.systemExtraDarkGrey);
        cbStuSubject = new JComboBox();
        for (String subject:prevSubjects)
        {
            cbStuSubject.addItem(subject);
        }
        
        // Create stu study type label + text field
        JLabel labelStuStudyType = new JLabel("  Study type:");
        labelStuStudyType.setForeground(mainStyle.systemExtraDarkGrey);
        cbStuStudyType = new JComboBox();
        cbStuStudyType.setPreferredSize( new Dimension( 140, 25 ) );
        cbStuStudyType.addItem("FT");
        cbStuStudyType.addItem("PT");
        
        // Create stu Name label + text field
        JLabel labelStuEmail = new JLabel("  Email:");
        labelStuEmail.setForeground(mainStyle.systemExtraDarkGrey);
        textStuEmail = new JTextField("e.g. joebloggs@email.com");
        textStuEmail.setPreferredSize( new Dimension( 190, 25 ) );
        //Empty text field when clicked on
        textStuEmail.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){textStuEmail.setText("");}});
        
        
        
        // Add text fields and labels to pStuInfo panel
        pStuInfo.add(labelStuID); pStuInfo.add(textStuID); 
        pStuInfo.add(labelStuName); pStuInfo.add(textStuName);
        pStuInfo.add(labelStuPrevSubject); pStuInfo.add(cbStuSubject);
        pStuInfo.add(labelStuStudyType); pStuInfo.add(cbStuStudyType);
        pStuInfo.add(labelStuEmail); pStuInfo.add(textStuEmail);
        p.add(pStuInfo, gbc1); // add stu id panel to p
        
        
        // Declare all button groups for all questions
        ButtonGroup bg1, bg2, bg3, bg4, bg5, bg6, bg7, bg8, bg9, bg10, bg11, bg12, bg13, bg14, bg15, bg16, bg17, bg18, bg19;
        // Initiate the button groups for all 19 questions
        bg1 = new ButtonGroup(); bg2 = new ButtonGroup(); bg3 = new ButtonGroup(); bg4 = new ButtonGroup();
        bg5 = new ButtonGroup(); bg6 = new ButtonGroup(); bg7 = new ButtonGroup(); bg8 = new ButtonGroup();
        bg9 = new ButtonGroup(); bg10 = new ButtonGroup(); bg11 = new ButtonGroup(); bg12 = new ButtonGroup();
        bg13 = new ButtonGroup(); bg14 = new ButtonGroup(); bg15 = new ButtonGroup(); bg16 = new ButtonGroup();
        bg17 = new ButtonGroup(); bg18 = new ButtonGroup(); bg19 = new ButtonGroup();
        // Create a Button Group array
        ButtonGroup[] bgArray = {bg1, bg2, bg3, bg4, bg5, bg6, bg7, bg8, bg9, bg10, bg11, bg12, bg13, bg14, bg15, bg16, bg17, bg18, bg19};        
        // Add each button group to the array list allButtonGroups to allow action listener to access them
        for (ButtonGroup bg:bgArray)
        {
            allButtonGroups.add(bg);
        }
        
        // Make panel for whole block of questions
        gbc1.gridy = 1;
        JPanel pAllQs = new JPanel(new GridLayout(0, 1));
        p.add(pAllQs, gbc1);
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);
        
        // Define useful variables for the while loop
        int qCount = 0; // a count for the while loop
        int gridCountQuestion = 0; // a count to ensure each question is printed beneath the previous
        
        // Use selectQuestions method on database to fill the questions arrayLists with data
        se.db.selectQuestions();
        
        while (qCount < se.db.questionsID.size()-3)
        {
            // Create a panel for each Q box
            JPanel pQBox = new JPanel(new GridLayout(0,1));
            EmptyBorder borderQBox = new EmptyBorder(30, 20, 30, 20);
            pQBox.setBorder(borderQBox);
            JPanel pQuestion = new JPanel();
            JPanel pAnswers = new JPanel(new GridBagLayout());
            
            // Set colour for panels
            pQBox.setBackground(Color.white);
            pQuestion.setBackground(Color.white);
            pAnswers.setBackground(Color.white);
            
            // Create question (set it to bold) and add to panel
            JLabel question = new JLabel("Q"+se.db.questionsNumber.get(qCount)+". "+se.db.questionsQuestion.get(qCount));
            question.setFont(mainStyle.fontM);
            question.setForeground(mainStyle.systemColor);
            pQuestion.add(question, gbc2);
            gbc2.gridx = 0;
            
            // Create radio buttons for multiple choice answers and put in an array for looping
            JRadioButton answerButton1, answerButton2, answerButton3, answerButton4, answerButton5;
            answerButton1 = new JRadioButton("Strongly disagree"); answerButton2 = new JRadioButton("Disagree");
            answerButton3 = new JRadioButton("Neither agree or disagree"); 
            answerButton4 = new JRadioButton("Agree"); answerButton5 = new JRadioButton("Strongly agree");
            JRadioButton[] answerButtons = {answerButton1, answerButton2, answerButton3, answerButton4, answerButton5};
            
            // Loop through each answer button to set background, add to panel and add to main allButtons array list
            for (JRadioButton answerButton:answerButtons) 
            {
                answerButton.setFont(mainStyle.fontS);
                answerButton.setForeground(mainStyle.systemDarkGrey);
                answerButton.setBackground(Color.white);
                bgArray[qCount].add(answerButton); // adds each button to a buttongroup (increases for each iteration of main loop)
                gbc2.gridx ++; // increase gbc grid x so each question appears to the right side of the previous
                pAnswers.add(answerButton, gbc2); // add answer to panel
                allButtons.add(answerButton); // adds each button to the overall array list, used for submitting data to db
            }
            
            
            //Add panels to their containers
            pQBox.add(pQuestion);
            pQBox.add(pAnswers);
            pAllQs.add(pQBox);
            
            // Increment main loop counts
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
        pSubmitQ.add(bSubmitQ, BorderLayout.EAST);
        p.add(pSubmitQ, gbc1);
        bSubmitQ.addActionListener(new ActionListener()
        {
            /* This action listener:
                
                1) sets currentStudent to the value in the text field at top
                2) creates variables for holding scores of each TeamRole
                3) based on answers selected, calculates scores for each TeamRole and updates the variables
                4) inserts a new record with student number given at top and all TeamRoles scores
                5) if user has programming experience it launches the Test, otherwise it launches confirmation box
                6) refreshes the questionnaire: text field, button groups, scrolls to top
            */
        
            public void actionPerformed (ActionEvent e)
            {  
                // counts all selected buttons to ensure user has answered every question
                int selectedButtons = 0;
                for (JRadioButton b:allButtons) 
                {
                    if (b.isSelected()){selectedButtons++;}
                }
                if (selectedButtons < 19)
                {
                    // Custom method in style class for creating consistently styled pop-up boxes
                    mainStyle.createPopUpFrame("Please answer all questions.", 350, 150);
                }
                else
                {
                    
                    // finds if stu ID is unique
                    se.db.selectStudents();
                    if (se.db.studentsStuID.contains(textStuID.getText()) == true)
                    {
                        // Custom method in style class for creating consistently styled pop-up boxes
                        mainStyle.createPopUpFrame("The Student ID you entered already exists.", 350, 150);
                    }
                    else
                    {
                        // variables for getting the text from student info text fields
                        currentStudentID = textStuID.getText();
                        currentStudentName = textStuName.getText();
                        currentStudentEmail = textStuEmail.getText();

                        // Create variables to hold scores for each TeamRole
                        int SHscore = 0; int IMPscore = 0; int CFscore = 0;
                        int COscore = 0; int TWscore = 0; int RIscore = 0;
                        int PLscore = 0; int MEscore = 0; int SPscore = 0;

                        // used to find currentQuestion in below loop
                        int currentQuestion = 1;
                        // used to count through buttons in below loop
                        int bCount = 0;

                        // Loop through all buttons to calculate TeamRole score
                        for (JRadioButton b:allButtons)
                        {
                            // Get a value of 1, 2, 3, 4, or 5 (1 for strongly disagree, 5 for strongly agree) to store in database
                            int answerValue = (bCount+1)%5;
                            if (answerValue == 0){answerValue = 5;}

                            // finds if current button is selected and adds its value out of 5 to TeamRole variable
                            if (b.isSelected())
                            {
                                // e.g. Ques 1 or 2 adds to CO as they're both CO questions, etc
                                if (currentQuestion == 1 || currentQuestion == 2) {SHscore += answerValue;}
                                else if (currentQuestion == 3 || currentQuestion == 4) {IMPscore += answerValue;}
                                else if (currentQuestion == 5 || currentQuestion == 6) {CFscore += answerValue;}
                                else if (currentQuestion == 7 || currentQuestion == 8) {COscore += answerValue;}
                                else if (currentQuestion == 9 || currentQuestion == 10) {TWscore += answerValue;}
                                else if (currentQuestion == 11 || currentQuestion == 12) {RIscore += answerValue;}
                                else if (currentQuestion == 13 || currentQuestion == 14) {PLscore += answerValue;}
                                else if (currentQuestion == 15 || currentQuestion == 16) {MEscore += answerValue;}
                                else if (currentQuestion == 17 || currentQuestion == 18) {SPscore += answerValue;}
                            }

                            // Increase current question for every 5 buttons (as 1 question has 5 buttons)
                            if ((bCount+1)%5==0)
                            {
                                currentQuestion++;
                            }

                            bCount++;
                        }


                        // Create a record for currentStudent in students table within DB with number and TeamRole scores
                        se.db.modify("INSERT INTO students (stuID, stuName, stuStudyType, stuEmail, previousSubject, trSH, trIMP, trCF, trCO, trTW, trRI, trPL, trME, trSP)"
                                + "VALUES ('"+currentStudentID+"', '"+currentStudentName+"', '"+cbStuStudyType.getSelectedItem()+"', '"+currentStudentEmail+"','"+cbStuSubject.getSelectedItem()+"', "
                                +SHscore+", "+IMPscore+", "+CFscore+", "+COscore+", "+TWscore
                                +","+RIscore+", "+PLscore+", "+MEscore+", "+SPscore+")");


                        // run test if student answered agree or strongly agree to programming test
                        if (allButtons.get(allButtons.size()-1).isSelected() || allButtons.get(allButtons.size()-2).isSelected())
                        {
                            Test test = new Test(currentStudentID);
                            test.setVisible(true);
                        }
                        // else show 'questionnaire complete' confirmation box
                        else
                        {
                            mainStyle.createPopUpFrame("Thank you for completing the questionnaire.", 350, 150);
                        }
                        
                       

                        // Reset questionnaire

                        // Reset top text field content
                            textStuID.setText("e.g. C0815038");
                            textStuName.setText("e.g. Joe Bloggs");
                            cbStuSubject.setSelectedIndex(0);
                            cbStuStudyType.setSelectedIndex(0);
                            textStuEmail.setText("e.g. joebloggs@email.com");

                        // Clear selection of all button groups
                        for (ButtonGroup bg:allButtonGroups)
                        {
                            bg.clearSelection();
                        }

                        // Set scroll bar to top of screen
                        JScrollBar verticalScrollBar = sp.getVerticalScrollBar();
                        verticalScrollBar.setValue(verticalScrollBar.getMinimum());
                    }
                
                }
                
            }
            
         
        });
    }

}
