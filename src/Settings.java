import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Settings
{
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();
    
    public Settings(final SE se)
    {
        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel(new GridLayout(0, 3));
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        // Make 3 main panels to divide page into 3 even columns
        JPanel pLeft = new JPanel();
        JPanel pCenter = new JPanel(new GridBagLayout());
        JPanel pRight = new JPanel();
        p.add(pLeft); p.add(pCenter); p.add(pRight);
        pLeft.setBackground(Color.white);
        pCenter.setBackground(Color.white);
        pRight.setBackground(Color.white);
        GridBagConstraints gbcCenter = new GridBagConstraints();
        gbcCenter.anchor = GridBagConstraints.WEST;
        
        
        
        // Make Change Colour Theme panel
        JPanel pTheme = new JPanel(new GridLayout(0, 1));
        pTheme.setBackground(Color.white);
        pTheme.setBorder(mainStyle.borderCustom(20, 0, 50, 0));
        gbcCenter.gridy = 0;
        pCenter.add(pTheme, gbcCenter);
        // Sub 0 - set the section title
            JLabel lTheme = new JLabel("Change colour theme");
            lTheme.setForeground(mainStyle.systemColor);
            lTheme.setFont(mainStyle.fontL);
            pTheme.add(lTheme);
        // Sub 1 - current password panel
            JPanel pThemeSub1 = new JPanel(new GridLayout(0,4));
            pThemeSub1.setBackground(Color.white);
            ButtonGroup bgTheme = new ButtonGroup();
            JRadioButton bTheme1, bTheme2, bTheme3, bTheme4;
            bTheme1 = new JRadioButton("Red");
            bTheme2 = new JRadioButton("Blue");
            bTheme3 = new JRadioButton("Green");
            bTheme4 = new JRadioButton("Purple");
            final JRadioButton[] buttonsTheme = {bTheme1, bTheme2, bTheme3, bTheme4};
            for (JRadioButton b:buttonsTheme)
            {
                b.setFont(mainStyle.fontM);
                b.setForeground(mainStyle.systemDarkGrey);
                bgTheme.add(b);
                pThemeSub1.add(b);
            }
            pTheme.add(pThemeSub1);
        // Sub 2 - submit button
            JPanel pThemeSubmit = new JPanel();
            pThemeSubmit.setBackground(Color.white);
            JButton bThemeSubmit = new JButton("Submit");
            bThemeSubmit.setBackground(Color.white);
            pThemeSubmit.add(bThemeSubmit);
            pTheme.add(pThemeSubmit);
            bThemeSubmit.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    // loops through all 4 buttons. If the selected button is found it finds which it is by its position
                    // in the array and updates the database appropriately.
                    int themeCount = 0; // used to count each iteration of below for loop
                    int buttonselectCount = 0; // used to see if no buttons are selected, for purpose of creating pop up message
                    for (JRadioButton b:buttonsTheme)
                    {
                        if (b.isSelected())
                        {
                            switch (themeCount)
                            { 
                                case 0: se.db.modify("UPDATE settings SET colorR='176', colorG='23', colorB='31' WHERE ID=1"); break;//Red
                                case 1: se.db.modify("UPDATE settings SET colorR='61', colorG='89', colorB='171' WHERE ID=1"); break;//Blue
                                case 2: se.db.modify("UPDATE settings SET colorR='48', colorG='128', colorB='20' WHERE ID=1"); break;//Green
                                case 3: se.db.modify("UPDATE settings SET colorR='180', colorG='82', colorB='205' WHERE ID=1"); break;//Purple
                            }
                            
                            mainStyle.createPopUpFrame("Theme changes will take effect next time you start the system.", 450, 150);
                            
                            // if button is selected, increase buttonselectCount
                            buttonselectCount++;
                        }
                        else
                        {
                            // If the last button isn't selected
                            if (themeCount==buttonsTheme.length-1 && buttonselectCount==0)
                            {
                                mainStyle.createPopUpFrame("No colour selected. Please select a theme.", 350, 150);
                            }
                        }
                        themeCount++;               
                    }
                }
            });
       
        
        
        
        
        
        // Make change admin password panel 
        JPanel pPassword = new JPanel(new GridLayout(0, 1));
        pPassword.setBackground(Color.white);
        pPassword.setBorder(mainStyle.borderCustom(20, 0, 50, 0));
        gbcCenter.gridy++;
        pCenter.add(pPassword, gbcCenter);
        // Sub 0 - set the section title
            JLabel lPassword = new JLabel("Change admin password");
            lPassword.setForeground(mainStyle.systemColor);
            lPassword.setFont(mainStyle.fontL);
            pPassword.add(lPassword);
        // Sub 1 - current password panel
            JPanel pPasswordSub1 = new JPanel(new GridLayout(0,2));
            pPasswordSub1.setBackground(Color.white);
            JLabel lPasswordSub1 = new JLabel("Current password:");
            lPasswordSub1.setFont(mainStyle.fontS);
            lPasswordSub1.setForeground(mainStyle.systemDarkGrey);
            final JTextField tPasswordSub1 = new JTextField();
            tPasswordSub1.setPreferredSize(new Dimension( 170, 20 ));
            pPasswordSub1.add(lPasswordSub1);
            pPasswordSub1.add(tPasswordSub1);
            pPassword.add(pPasswordSub1);
        // Sub 2 - new password panel
            JPanel pPasswordSub2 = new JPanel(new GridLayout(0,2));
            pPasswordSub2.setBackground(Color.white);
            JLabel lPasswordSub2 = new JLabel("New password:");
            lPasswordSub2.setFont(mainStyle.fontS);
            lPasswordSub2.setForeground(mainStyle.systemDarkGrey);
            final JTextField tPasswordSub2 = new JTextField();
            tPasswordSub2.setPreferredSize(new Dimension( 170, 20 ));
            pPasswordSub2.add(lPasswordSub2);
            pPasswordSub2.add(tPasswordSub2);
            pPassword.add(pPasswordSub2);
        // Sub 3 - new password retype panel
            JPanel pPasswordSub3 = new JPanel(new GridLayout(0,2));
            pPasswordSub3.setBackground(Color.white);
            JLabel lPasswordSub3 = new JLabel("Retype new password:");
            lPasswordSub3.setFont(mainStyle.fontS);
            lPasswordSub3.setForeground(mainStyle.systemDarkGrey);
            final JTextField tPasswordSub3 = new JTextField();
            tPasswordSub3.setPreferredSize(new Dimension( 170, 20 ));
            pPasswordSub3.add(lPasswordSub3);
            pPasswordSub3.add(tPasswordSub3);
            pPassword.add(pPasswordSub3);
        // Sub 4 - submit button
            JPanel pPasswordSubmit = new JPanel();
            pPasswordSubmit.setBackground(Color.white);
            JButton bPasswordSubmit = new JButton("Submit");
            bPasswordSubmit.setBackground(Color.white);
            pPasswordSubmit.add(bPasswordSubmit);
            pPassword.add(pPasswordSubmit);
            bPasswordSubmit.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    String currentPass = tPasswordSub1.getText();
                    String newPass = tPasswordSub2.getText();
                    String newPassConfirm = tPasswordSub3.getText();

                    se.db.selectSettings(); //refresh data in arraylists
                    
                    if (currentPass.equals(se.db.settingsAdminPassword.get(0)))
                    {
                        if (newPass.equals(newPassConfirm))
                        {
                            se.db.modify("UPDATE settings SET adminPassword = '"+newPass+"' WHERE ID = 1");
                            mainStyle.createPopUpFrame("Password successfully updated.", 350, 150);
                            tPasswordSub1.setText("");
                            tPasswordSub2.setText("");
                            tPasswordSub3.setText("");
                        }
                        else
                        {
                            mainStyle.createPopUpFrame("New passwords do not match.", 350, 150);
                        }
                    }
                    else
                    {
                        mainStyle.createPopUpFrame("Current password incorrect.", 350, 150);
                    }
                }
            });
        
        
        // Make change admin reset panel 
        JPanel pReset = new JPanel(new GridLayout(0, 1));
        pReset.setBackground(Color.white);
        pReset.setBorder(mainStyle.borderCustom(20, 0, 20, 0));
        gbcCenter.gridy++;
        pCenter.add(pReset, gbcCenter);
        // Sub 0 - set the section title
            JLabel lReset = new JLabel("Reset the system");
            lReset.setForeground(mainStyle.systemColor);
            lReset.setFont(mainStyle.fontL);
            pReset.add(lReset);
        // Sub 1 - current reset panel
            JPanel pResetSub1 = new JPanel(new GridLayout(0,1));
            pResetSub1.setBackground(Color.white);
            JLabel lResetSub1a = new JLabel("Performing a system reset will delete all student data");
            JLabel lResetSub1b = new JLabel("and reset questions to default. It cannot be undone.");
            lResetSub1a.setForeground(mainStyle.systemDarkGrey);
            lResetSub1b.setForeground(mainStyle.systemDarkGrey);
            lResetSub1a.setFont(mainStyle.fontS);
            lResetSub1b.setFont(mainStyle.fontS);
            pResetSub1.add(lResetSub1a);
            pResetSub1.add(lResetSub1b);
            pReset.add(pResetSub1);
        // Sub 2 - current reset panel
            JPanel pResetSub2 = new JPanel(new GridLayout(0,2));
            pResetSub2.setBackground(Color.white);
            JLabel lResetSub2 = new JLabel("Enter admin password:");
            lResetSub2.setForeground(mainStyle.systemDarkGrey);
            final JTextField tResetSub2 = new JTextField();
            tResetSub2.setPreferredSize(new Dimension( 170, 20 ));
            pResetSub2.add(lResetSub2);
            pResetSub2.add(tResetSub2);
            pReset.add(pResetSub2);
        // Sub 4 - submit button
            JPanel pResetSubmit = new JPanel();
            pResetSubmit.setBackground(Color.white);
            JButton bResetSubmit = new JButton("Submit");
            bResetSubmit.setBackground(Color.white);
            pResetSubmit.add(bResetSubmit);
            pReset.add(pResetSubmit);
            bResetSubmit.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    se.db.selectSettings(); //refresh data in arraylists
                    
                    if (tResetSub2.getText().equals(se.db.settingsAdminPassword.get(0)))
                    {
                        // Delete all info in questions and students table
                        se.db.modify("DELETE FROM students");
                        se.db.modify("DELETE FROM questions");
                        
                        // Get arraylist of default questions
                        String q1 = "I work best under pressure and enjoy challenges.";
                        String q2 = "I sometimes provoke and offend others in the team.";
                        String q3 = "I often transform ideas into practical solutions.";
                        String q4 = "I dont like new ideas that conflict with current plans.";
                        String q5 = "I always meet deadlines.";
                        String q6 = "I would rather do tasks myself than delegate them.";
                        String q7 = "I can confidently make key decisions for the team.";
                        String q8 = "Im good at manipulating team mates into my way of thinking.";
                        String q9 = "I enjoy the social aspects working as a team and working with others.";
                        String q10 = "I can be easily influenced by other team members.";
                        String q11 = "I enjoy working with people outside of the team to help achieve team goals.";
                        String q12 = "I can lose interest in projects when they no longer excite me.";
                        String q13 = "I like to think outside the box and thrive on praise from others.";
                        String q14 = "I find that my ideas are usually better than team mates ideas.";
                        String q15 = "Im good at strategic planning, as I can objectively analyse ideas.";
                        String q16 = "I find it difficult to motivate myself and to inspire others around me.";
                        String q17 = "I like to focus exclusively on my area of expertise.";
                        String q18 = "I would rather concentrate on my own work than the big picture.";
                        String q19 = "I have previous programming knowledge and/or experience.";
                        String q20 = "Is the Java expression \"int mynum = 27.89;\" a valid one?";
                        String q21 = "Is C# the language used for querying databases?";
                        String q22 = "Does the Python expression \"count ++\" increase the value of count by 1?";
                        
                        ArrayList<String> questions = new ArrayList<String>();
                        questions.add(q1); questions.add(q2); questions.add(q3); questions.add(q4); questions.add(q5);
                        questions.add(q6); questions.add(q7); questions.add(q8); questions.add(q9); questions.add(q10);
                        questions.add(q11); questions.add(q12); questions.add(q13); questions.add(q14); questions.add(q15);
                        questions.add(q16); questions.add(q17); questions.add(q18); questions.add(q19); questions.add(q20);
                        questions.add(q21); questions.add(q22);
                        
                        // Insert all default questions
                        int count = 1;
                        for (String q:questions)
                        {
                            se.db.modify("INSERT INTO questions (number, question) VALUES ("+count+", '"+q+"')");
                            count++;
                        }
                        mainStyle.createPopUpFrame("System successfully reset.", 300, 150);
                    }
                    else
                    {
                        // Show pop up for incorrect password
                        mainStyle.createPopUpFrame("Incorrect password entered. Please try again.", 400, 150);
                    }
                   
                }
            });
    }
}