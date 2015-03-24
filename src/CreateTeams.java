import java.util.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateTeams {
    
    JPanel p;
    JScrollPane sp; 
    Style mainStyle = new Style();
    
    private JPanel panelButtons = new JPanel();
    private String strComboNumTeams = "comboNumTeams";
    private JComboBox comboNumTeams = new JComboBox();
    
    JPanel panelLists = new JPanel(new GridBagLayout());
    
    public ArrayList<Student> Students;
        
    
    public CreateTeams()
    {
        Random rand = new Random();
        Students = getFakeStudents(rand.nextInt(16)+10);

        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);

        // Add content below...
        String[] strNumTeams = {"1", "2", "3", "4", "5", "6"};
        for (String opt : strNumTeams)
        {
            comboNumTeams.addItem(opt);
        }
        
        comboNumTeams.setActionCommand(strComboNumTeams);
        comboNumTeams.addActionListener(new listenerButtons());
        panelButtons.add(comboNumTeams, BorderLayout.EAST);
        
        JPanel myPanel = new JPanel(new BorderLayout());
       
        
        myPanel.add(panelButtons, BorderLayout.EAST);
        myPanel.add(panelLists, BorderLayout.CENTER);
        p.add(myPanel);        
        
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        //set a starting value for the number of teams, this 
        //also fires the event and populates the right number
        //of lists
        comboNumTeams.setSelectedIndex(3);
    }
    
    private ArrayList<Student> getStudents()
    {
        ArrayList<Student> Students = new ArrayList();
        return Students;
    }
    
    private ArrayList<Student> getFakeStudents(int num)
    {
        ArrayList<Student> Students = new ArrayList();
        Random rnd = new Random();
        
        for (int i = 0; i < num; i++)
        {
            Student newStudent = new Student();
            newStudent.ID = String.valueOf(i);
            newStudent.firstName = "Student";
            newStudent.lastName = String.valueOf(i);
            if(i % 6 == 0) newStudent.fullTime = false;
            else newStudent.fullTime = true;
            if (i % 3 == 0) newStudent.prevExperience = false;
            else newStudent.prevExperience = true;
            newStudent.previousSubject = String.valueOf(rnd.nextInt(8));
            newStudent.trSH = rnd.nextInt(6);
            newStudent.trIMP = rnd.nextInt(6);
            newStudent.trCF = rnd.nextInt(6);
            newStudent.trCO = rnd.nextInt(6);
            newStudent.trTW = rnd.nextInt(6);
            newStudent.trRI = rnd.nextInt(6);
            newStudent.trPL = rnd.nextInt(6);
            newStudent.trME = rnd.nextInt(6);
            newStudent.trSP = rnd.nextInt(6);
            newStudent.assignTeamRole();
            if (newStudent.prevExperience == true) 
                newStudent.testScore = rnd.nextInt(4);
            
            //newStudent.print();
            Students.add(newStudent);
        }
        return Students;
    }
    
    private void assignTeams(int numTeams)
    {
        int i = 0;
        for (Student s : Students)
        {
            s.memberOfTeam = (i % numTeams);
            i++;
        }
    }
    private String studentDisplayString(Student s)
    {
        String ft;
        if (s.fullTime) ft = "FT";
        else ft = "PT";
        String displayString = s.firstName + " " + s.lastName + " (" +
                ft + ", Test Score: " + s.testScore + ", Team Role: " +
                s.teamRole + ")";
        return displayString;
    }
    
    private class listenerButtons implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            if (cmd.equals(strComboNumTeams))
            {
                panelLists.removeAll();
                JComboBox comboBox = (JComboBox)e.getSource();
                int numTeams = Integer.parseInt(comboBox.getSelectedItem().toString());
                
                GroupStudents group = new GroupStudents();
                //group by test score and team role to mix the experience and
                //person type up amongst the teams
                group.group(Students, group.BY_TEST_SCORE + group.BY_TEAM_ROLE);
                assignTeams(numTeams);
                
                for (int i = 0; i < numTeams; i++)
                {
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridy = i+1;
                    gbc.insets = new Insets(10, 0, 0, 0);
                    DefaultListModel listModel = new DefaultListModel();
                    for (Student s : Students)
                    {
                        if (s.memberOfTeam == i)
                        {
                            String str = studentDisplayString(s);
                            listModel.addElement(str);
                        }
                            
                    }
                    JList list = new JList(listModel);
                    panelLists.add(list, gbc);
                }
                
                sp.validate();
                sp.repaint();
            }
        }
    }
    
    private class listenerLists implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e.getActionCommand());
        }
    }
}