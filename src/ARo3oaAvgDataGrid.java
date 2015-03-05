/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.awt.BorderLayout.PAGE_START;
import java.awt.Color;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.NORTH;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kit
 */
public class ARo3oaAvgDataGrid extends JPanel
{
    private GridBagLayout layout;
    private ARData data;
    private JLabel[] studentNames;
    private GridBagConstraints gbc;
   
    public ARo3oaAvgDataGrid() 
    {
        //layout settings
        this.setBackground(Color.white);
        layout = new GridBagLayout();
        this.setLayout(layout);
        gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty=1;
        gbc.gridx = 0;
	gbc.gridy = 0;
        //grab student data to fill grid with
        data = new ARData();
        studentNames = data.students;
        
        //add to grid
        fillStudentNames(studentNames);
    }
    
    public void fillStudentNames(JLabel[] labelsArray) 
    {
        gbc.gridy = 0;
        for (int student=0; student<=labelsArray.length-1; student++)
        {
            this.add(labelsArray[student],gbc);
            gbc.gridy++;
            //setBackground(Color.yellow);
            
        }
    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //for testing of file only
        //JFrameForTest jframe = new JFrameForTest();
        ARo3oaAvgDataGrid datagrid = new ARo3oaAvgDataGrid();
        //jframe.add(datagrid);
    }

}
