import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ManageStudents
{
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();
    JPanel tableP;
    
    public ManageStudents(final SE se)
    {
        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        // Panel for entire table
        tableP = new JPanel(new GridLayout(0,1));
        p.add(tableP);
        
        // Panel for the header row and all JLabels
        JPanel headerP = new JPanel(new GridLayout(0,7));
        headerP.setBorder(mainStyle.borderCustom(5, 5, 5, 5));
        headerP.setBackground(mainStyle.systemLightGrey);
        headerP.setForeground(mainStyle.systemDarkGrey);
        tableP.add(headerP);
        
        ArrayList<JLabel> headers = new ArrayList<JLabel>();
        
        JLabel headerL1 = new JLabel("Student ID");
        JLabel headerL2 = new JLabel("Student Name");
        JLabel headerL3 = new JLabel("Previous Subject");
        JLabel headerL4 = new JLabel("Study Type");
        JLabel headerL5 = new JLabel("Email Address");
        JLabel headerL6 = new JLabel("Delete Student");
        JLabel headerL7 = new JLabel("Update Student");
        
        headers.add(headerL1); headers.add(headerL2); headers.add(headerL3); 
        headers.add(headerL4); headers.add(headerL5); headers.add(headerL6);
        headers.add(headerL7);
        
        for (JLabel header:headers)
        {
            header.setForeground(mainStyle.systemExtraDarkGrey);
            header.setFont(mainStyle.fontS);
            headerP.add(header);
        }
        
        for (int stuCount = 0; stuCount < se.db.studentsID.size(); stuCount++)
        {
            final JPanel studentP = new JPanel(new GridLayout(0,7));
            studentP.setBackground(Color.white);
            studentP.setBorder(mainStyle.borderCustom(2, 2, 2, 2));
            tableP.add(studentP);
            
            final String currentID = se.db.studentsStuID.get(stuCount);
            
            final JTextField ID = new JTextField(currentID);
            final JTextField Name = new JTextField(se.db.studentsStuName.get(stuCount));
            final JComboBox PreviousSubject = new JComboBox();
            PreviousSubject.addItem("Business"); PreviousSubject.addItem("Engineering"); PreviousSubject.addItem("Science");
            PreviousSubject.addItem("Art"); PreviousSubject.addItem("Technology"); PreviousSubject.addItem("Other");
            PreviousSubject.setSelectedItem(se.db.studentsPreviousSubject.get(stuCount));
            final JComboBox StudyType = new JComboBox();
            StudyType.addItem("FT"); StudyType.addItem("PT");
            StudyType.setSelectedItem(se.db.studentsStuStudyType.get(stuCount));
            final JTextField Email = new JTextField(se.db.studentsStuEmail.get(stuCount));
            
            // Add buttons
            JButton Delete = new JButton("Delete");
            Delete.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    // Remove student from DB
                    se.db.modify("DELETE FROM students WHERE stuID='"+currentID+"';");
                    
                    // Remove from panel and repaint
                    tableP.remove(studentP);
                    tableP.validate();
                    tableP.repaint();
                }
                
            });
            
            JButton Update = new JButton("Update");
            Update.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    // Update DB record for current student
                    se.db.modify("UPDATE students SET stuID='"+ID.getText()+"', stuName='"+Name.getText()+"',"
                            + "previousSubject='"+PreviousSubject.getSelectedItem().toString()+"', stuStudyType='"+StudyType.getSelectedItem().toString()+"', "
                            + "stuEmail='"+Email.getText()+"'"
                            + "WHERE stuID='"+currentID+"';");
                }
                
            });
            
            // Add all components to panel
            studentP.add(ID);
            studentP.add(Name);
            studentP.add(PreviousSubject);
            studentP.add(StudyType);
            studentP.add(Email);
            studentP.add(Delete);
            studentP.add(Update);   
        }
    }
}
