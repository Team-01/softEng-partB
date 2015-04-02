import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateTeams {
    
    JPanel p;
    JScrollPane sp; 
    Style mainStyle = new Style();
    
    private String strComboNumTeams = "comboNumTeams";
    private JComboBox comboNumTeams = new JComboBox();
    private String strCheckGroupPartTime = "checkGroupPartTime";
    private JCheckBox checkGroupPartTime = new JCheckBox("Group part time");
    
    private JPanel panelLists = new JPanel(new GridBagLayout());
    private JPanel panelButtons = new JPanel(new GridBagLayout());
    
    public ArrayList<Student> Students;
    public int numTeams;
    private boolean fakeStudents = true;
    private SQLite dbToUseWhenEventFired;
    private boolean groupPartTime = false;
        
    
    public CreateTeams(SE se)
    {
        dbToUseWhenEventFired = se.db;
        Random rand = new Random();
        if (fakeStudents) Students = getFakeStudents(rand.nextInt(16)+10);
        else Students = getStudentsFromDB(se.db);

        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);

        // Add content below...
        String[] strNumTeams = {"1", "2", "3", "4", "5", "6"};
        for (String opt : strNumTeams)
        {
            comboNumTeams.addItem(opt);
        }
        
        listenerButtons lButtons = new listenerButtons();
        comboNumTeams.setActionCommand(strComboNumTeams);
        comboNumTeams.addActionListener(lButtons);
        
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.anchor = GridBagConstraints.EAST;
        gbcButtons.gridy = 0;
        gbcButtons.insets = new Insets(10, 0, 0, 0);
        panelButtons.add(comboNumTeams, gbcButtons);
        
        checkGroupPartTime.setHorizontalTextPosition(SwingConstants.LEFT);
        checkGroupPartTime.setActionCommand(strCheckGroupPartTime);
        checkGroupPartTime.addActionListener(lButtons);
        
        gbcButtons.gridy = gbcButtons.gridy + 1;
        panelButtons.add(checkGroupPartTime, gbcButtons);
        
                
        GridBagConstraints gbcPanelLists = new GridBagConstraints();
        gbcPanelLists.anchor = GridBagConstraints.PAGE_START;
        gbcPanelLists.gridy = 0;
        gbcPanelLists.weighty = 0.5;
        gbcPanelLists.insets = new Insets(0, 10, 10, 0);
        
        p.add(panelLists, gbcPanelLists);
        
        
        GridBagConstraints gbcPanelButtons = new GridBagConstraints();
        gbcPanelButtons.anchor = GridBagConstraints.FIRST_LINE_END;
        gbcPanelButtons.gridy = 0;
        gbcPanelButtons.weighty = 0.5;
        gbcPanelButtons.insets = new Insets(0, 10, 10, 0);
 
        p.add(panelButtons, gbcPanelButtons);
        
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        //set a starting value for the number of teams, this 
        //also fires the event and populates the right number
        //of lists
        comboNumTeams.setSelectedIndex(3);
        
    }
    
    private ArrayList<Student> getStudentsFromDB(SQLite db)
    {
        ArrayList<Student> Students = new ArrayList();
        
        db.refresh();
        for (int i = 0; i < db.studentsID.size(); i++)
        {
            Student s = new Student();
            s.ID = db.studentsID.get(i);
            s.averageMark = Integer.parseInt(db.studentsAverageMark.get(i));
            s.name = db.studentsStuName.get(i);
            
            if (db.studentsStuStudyType.get(i).compareTo("FT") == 0) s.fullTime = true;
            else s.fullTime = false;
            
            if (db.studentsMemberOfTeam.get(i) != null) 
                s.memberOfTeam = Integer.parseInt(db.studentsMemberOfTeam.get(i));
            else s.memberOfTeam = -1;
            
            if (db.studentsModuleMark.get(i) != null)
                s.moduleMark = Integer.parseInt(db.studentsModuleMark.get(i));
            else s.moduleMark = -1;
            
            if (db.studentsTestScore.get(i) != null)
                s.testScore = Integer.parseInt(db.studentsTestScore.get(i));
            else s.testScore = -1;
            
            if (s.testScore > 0) s.prevExperience = true;
            else s.prevExperience = false;
            
            s.previousSubject = db.studentsPreviousSubject.get(i);
            s.trCF = Integer.parseInt(db.studentsTrCF.get(i));
            s.trCO = Integer.parseInt(db.studentsTrCO.get(i));
            s.trIMP = Integer.parseInt(db.studentsTrIMP.get(i));
            s.trME = Integer.parseInt(db.studentsTrME.get(i));
            s.trPL = Integer.parseInt(db.studentsTrPL.get(i));
            s.trRI = Integer.parseInt(db.studentsTrRI.get(i));
            s.trSH = Integer.parseInt(db.studentsTrSH.get(i));
            s.trSP = Integer.parseInt(db.studentsTrSP.get(i));
            s.trTW = Integer.parseInt(db.studentsTrTW.get(i));
            s.assignTeamRole();
            db.modify("update students set teamRole='" + s.teamRole + "' where ID='" + s.ID + "'");
            db.refresh();
            
            Students.add(s);
        }
        
        
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
            newStudent.name = "Student";
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

            if (! fakeStudents)
                dbToUseWhenEventFired.modify("update students set memberOfTeam='"
                        + s.memberOfTeam + "' where ID='" + s.ID + "'" );
            i++;
        }
        
        if (groupPartTime)
        {
            for (Student s : Students)
            {
                if (s.fullTime == false && s.memberOfTeam > 0)
                {
                    int originalTeam = s.memberOfTeam;
                    s.memberOfTeam = 0;
                    
                    if (! fakeStudents)
                        dbToUseWhenEventFired.modify("update students set memberOfTeam='"
                        + s.memberOfTeam + "' where ID='" + s.ID + "'" );
                    
                    boolean swapped = false;
                    i = 0;
                    while (! swapped)
                    {
                        if (Students.get(i).fullTime == true &&
                                Students.get(i).memberOfTeam == 0)
                        {
                            Students.get(i).memberOfTeam = originalTeam;
                            swapped = true;
                            if (! fakeStudents)
                                dbToUseWhenEventFired.modify("update students set memberOfTeam='"
                                + Students.get(i).memberOfTeam + "' where ID='" + Students.get(i).ID + "'" );
                        }
                        i++;
                    }
                }
            }
        }
        dbToUseWhenEventFired.refresh();
    }
    private String studentDisplayString(Student s)
    {
        String ft;
        if (s.fullTime) ft = "FT";
        else ft = "PT";
        String displayString = s.name + " " + " (" +
                ft + ", Test Score: " + s.testScore + ", Team Role: " +
                s.teamRole + ")";
        return displayString;
    }
    
    private void refreshTeamLists()
    {
        
        panelLists.removeAll();

        GroupStudents group = new GroupStudents();
        //group by test score and team role to mix the experience and
        //person type up amongst the teams
        group.group(Students, group.BY_TEST_SCORE + group.BY_TEAM_ROLE);
        assignTeams(numTeams);

        for (int i = 0; i < numTeams; i++)
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = i+1;
            gbc.insets = new Insets(10, 0, 0, 10);
            gbc.anchor = GridBagConstraints.WEST;

            JLabel teamLabel = new JLabel("Team " + i);
            teamLabel.setForeground(mainStyle.systemColor);
            teamLabel.setFont(mainStyle.fontM);

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
            panelLists.add(teamLabel, gbc);
            panelLists.add(list, gbc);
        }

        sp.validate();
        sp.repaint();
    }
    private class listenerButtons implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            if (cmd.compareTo(strComboNumTeams) == 0)
            {
                JComboBox comboBox = (JComboBox)e.getSource();
                numTeams = Integer.parseInt(comboBox.getSelectedItem().toString());
                refreshTeamLists();
            }
            else if (cmd.compareTo(strCheckGroupPartTime) == 0)
            {
                JCheckBox checkBox = (JCheckBox)e.getSource();
                if (checkBox.isSelected()) groupPartTime = true;
                else groupPartTime = false;
                refreshTeamLists();
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