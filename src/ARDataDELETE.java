/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Kit
 */
public class ARDataDELETE {
    //string array for testing **TBD
    String[] StudentNames =
    {
        "Test Student1", 
        "Test Student2", 
        "Test Student3",
        "Test Student4",
        "Test Student5",
        "Test Student6", 
        "Test Student7",
        "Test Student8",
        "Test Student9",
        "Test Student10"
    };
    public JLabel[] students;
    
    public ARDataDELETE()
    {
        students = getStudentNamesLabels(StudentNames);
    }
    
    public JLabel[] getStudentNamesLabels(String[] studentNames)
    {
    JLabel[] students = new JLabel[studentNames.length];
        for (int student = 0; student < students.length; student++)
        {
             students[student]= new JLabel(studentNames[student]);
             students[student].setPreferredSize(new Dimension( 140, 24 ) );
        }
        return students;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
