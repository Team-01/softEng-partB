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
    
    JPanel panelLists = new JPanel();
        
    
    public CreateTeams()
    {
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
        
        JPanel myPanel = new JPanel();
        
        myPanel.add(panelButtons, BorderLayout.EAST);
        myPanel.add(panelLists, BorderLayout.CENTER);
        p.add(myPanel);
        //p.add(panelButtons, BorderLayout.EAST);
        
        //p.add(panelLists, BorderLayout.WEST);
        
        
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
    }
    
    class listenerButtons implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            if (cmd.equals(strComboNumTeams))
            {
                System.out.println("Num Teams Chosen");
                JComboBox comboBox = (JComboBox)e.getSource();
                int numTeams = Integer.parseInt(comboBox.getSelectedItem().toString());
                System.out.println(numTeams);
                
                panelLists.removeAll();
                
                
                //JButton newButton = new JButton("hello!");
                //newButton.addActionListener(new listenerLists());
                //panelLists.add(newButton);
                
                ArrayList listItems = new ArrayList();
                for (int i = 0; i < 25; i++)
                {
                    String studentID = "S"+i;
                    listItems.add(studentID);
                }
                
                int sizeOfTeams = listItems.size() / numTeams;
                int extraMembers = 0;
                if (listItems.size() % numTeams != 0)
                {
                    extraMembers = listItems.size() % numTeams;
                }
                
                int listsMade = 0;
                while (listsMade < numTeams)
                {
                    DefaultListModel lModel = new DefaultListModel();
                    for (int i = 0; i < sizeOfTeams; i++)
                    {
                        String student=listItems.remove(0).toString();
                        lModel.addElement(student);
                    }
                    
                    if ((extraMembers > 0) & (listsMade == numTeams - 1))
                    {
                        for (int i = 0; i < extraMembers; i++)
                        {
                            String student=listItems.remove(0).toString();
                            lModel.addElement(student);
                        } 
                    }
                    JList newList = new JList(lModel);
                    panelLists.add(newList);
                    listsMade++;
                }

                sp.validate();
                sp.repaint();
            }
        }
    }
    
    class listenerLists implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e.getActionCommand());
        }
    }
}