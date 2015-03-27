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
    
    public ARo1stAvgDataGrid()
    {
        buildThis();
    }
    
    public void buildThis()
    {
        layout = new GridBagLayout();
        columnArray = new ArrayList();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        this.setLayout(layout);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty=1;
        gbc.insets = new Insets(10,50,0,100);
        columnArray.add(db.studentsStuName);
        columnArray.add(db.studentsModuleMark);
        columnArray.add(db.studentsAverageMark);
        ArrayList<String> columnsHead = new ArrayList();
        columnsHead.add("Name");
        columnsHead.add("Year average");
        columnsHead.add("Software Eng");
        tbl = new TableTemplate(columnsHead, columnArray,false);
        this.add(tbl,gbc);
        revalidate();
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
