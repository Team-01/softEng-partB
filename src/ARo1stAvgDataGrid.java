/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.awt.BorderLayout.PAGE_START;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.NORTH;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kit
 */
public class ARo1stAvgDataGrid extends JPanel
{
    GridBagLayout layout;
    GridBagConstraints gbc;
    JPanel tbl;
    private ArrayList<ArrayList> columnArray;
    SQLite db = new SQLite();
    FindStudentTemplate blank = new FindStudentTemplate();
    
    public ARo1stAvgDataGrid()
    {
        
    }
    
    public void buildThis()
    {
        if (tbl!=null)
        {
            this.remove(tbl);
        }
        db = new SQLite();
        columnArray = new ArrayList();
        this.setBackground(Color.white);
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        this.setLayout(layout);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty=1;
        gbc.insets = new Insets(10,0,0,30);
        columnArray.add(db.studentsStuName);
        columnArray.add(db.studentsStuEmail);
        columnArray.add(db.studentsModuleMark);
        columnArray.add(db.studentsAverageMark);
        ArrayList<String> columnsHead = new ArrayList();
        columnsHead.add("Name");
        columnsHead.add("Email");
        columnsHead.add("Software Eng");
        columnsHead.add("Year average");
        tbl = new TableTemplate(columnsHead, columnArray,false, blank, 3, 2);
        this.add(tbl,gbc);
        revalidate();
    }
    
    public void setData()
    {

    }
    
    public JPanel buildLvl1Panel()
    {
        JPanel panelLvl1gb = new JPanel();
        panelLvl1gb.setBackground(Color.white);
        panelLvl1gb.setLayout(layout);
        return panelLvl1gb;
    }

    
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //for testing of file only
        ARjFrameForTest jframe = new ARjFrameForTest();
        ARo1stAvgDataGrid datagrid = new ARo1stAvgDataGrid();
        jframe.add(datagrid);
        jframe.setVisible(true);
    }

}
