import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ManageQuestions
{
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();
    
    // Get list of teamroles for cycling through
    ArrayList<String> teamroles = new ArrayList<String>();
    // Add arraylist of textfields (for accessing later to update DB)
    ArrayList<JTextField> questionsTF = new ArrayList<JTextField>();
    
    public ManageQuestions(final SE se)
    {
        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        // Add content below...
        
        // Panel for questions
        JPanel pQs = new JPanel(new GridLayout(0, 1));
        pQs.setSize(900, 500);
        p.add(pQs);
        
        // Add content to teamroles
        teamroles.add("Shaper");
        teamroles.add("Implementer");
        teamroles.add("Completer");
        teamroles.add("Co-ordinator");
        teamroles.add("Team Worker");
        teamroles.add("Resource Investigator");
        teamroles.add("Plant (Innovator)");
        teamroles.add("Monitor Evaluator");
        teamroles.add("Specialist");
        
        // Fill questionsTF array list with 18 blank text fields
        for (int count = 0; count<18; count++) 
        {
            JTextField tf = new JTextField();
            questionsTF.add(tf);
        }
        
        // Count variable for for-loop
        int count = 0;
        
        // loop through team roles
        for (String tr: teamroles)
        {
            // Panel for team role (broken into 2 sub panels: 1 for positive Q, 1 for negative Q)
            JPanel trP = new JPanel(new GridLayout(0,1));
            trP.setBackground(mainStyle.systemExtraLightGrey);
            trP.setBorder(mainStyle.border10);
            pQs.add(trP);
            
            // Panel and content for positive question
            JPanel posP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            posP.setBackground(mainStyle.systemExtraLightGrey);
            trP.add(posP);
            
            // Panel for title(to make it left aligned)
            JPanel trTitleP = new JPanel(new GridLayout(0,1));
            trTitleP.setPreferredSize(new Dimension(200, 25));
            posP.add(trTitleP);
            
            JLabel trL = new JLabel(tr);
            trL.setFont(mainStyle.fontMb);
            trL.setForeground(mainStyle.systemColor);
            trTitleP.add(trL);
            
            JLabel trLPos = new JLabel("Positive: ");
            trLPos.setFont(mainStyle.fontS);
            trLPos.setForeground(mainStyle.systemDarkGrey);
            posP.add(trLPos);

            // Uses the text fields from the arraylist questionsTF
            questionsTF.get(count).setText(se.db.questionsQuestion.get(count));
            questionsTF.get(count).setPreferredSize(new Dimension(700, 25));
            posP.add(questionsTF.get(count));
      
            count++;
            
            // Panel and content for negative question
            JPanel negP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            negP.setBackground(mainStyle.systemExtraLightGrey);
            trP.add(negP);
            
            JLabel trLNeg = new JLabel("Negative: ");
            trLNeg.setFont(mainStyle.fontS);
            trLNeg.setForeground(mainStyle.systemDarkGrey);
            negP.add(trLNeg);
            
            questionsTF.get(count).setText(se.db.questionsQuestion.get(count));
            questionsTF.get(count).setPreferredSize(new Dimension(700, 25));
            negP.add(questionsTF.get(count));
      
            count++;
        }
        
        // Submit button panel and button
        JPanel submitP = new JPanel();
        pQs.add(submitP);
        JButton submitB = new JButton("Submit");
        submitP.add(submitB);
        submitB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {      
                // Loop through each question and update DB
                for (int updateCount = 0; updateCount < questionsTF.size(); updateCount++)
                {
                    String newQuestion = questionsTF.get(updateCount).getText().replaceAll("'", "");
                    int quesNum = updateCount + 1;
                    
                    se.db.modify("UPDATE questions SET question='"+newQuestion+"' WHERE number="+quesNum+";");

                    System.out.println(newQuestion);
                }

                mainStyle.createPopUpFrame("Questions updated. Please restart the system for changes to take effect.", 500, 150);
            }
        });
    }
}
