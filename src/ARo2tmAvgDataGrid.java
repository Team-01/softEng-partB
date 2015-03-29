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
public class ARo2tmAvgDataGrid extends JPanel
{
    GridBagLayout layout;
    GridBagConstraints gbc;
    JPanel tbl;
    private ArrayList<ArrayList> columnArray;
    SQLite db = new SQLite();
    ArrayList <String> teamColumn;
    ArrayList <String> yrAvgColumn;
    ArrayList <String> modAvgColumn;
    
    public ARo2tmAvgDataGrid()
    {
        buildThis();
    }
    
    public void buildThis()
    {
        this.setBackground(Color.white);
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
        gbc.insets = new Insets(10,0,0,30);
        
        teamColumn = buildUniqueStrArray(db.studentsMemberOfTeam);
        yrAvgColumn = buildAvgStrArray(
                teamColumn, 
                db.studentsMemberOfTeam,
                db.studentsAverageMark);
        modAvgColumn = buildAvgStrArray(
                teamColumn, 
                db.studentsMemberOfTeam,
                db.studentsModuleMark);
        
        columnArray.add(teamColumn);
        columnArray.add(yrAvgColumn);
        columnArray.add(modAvgColumn);
        ArrayList<String> columnsHead = new ArrayList();
        columnsHead.add("Team");
        columnsHead.add("Year average");
        columnsHead.add("Software Eng");
        tbl = new TableTemplate(columnsHead, columnArray,false, 1, 2);
        this.add(tbl,gbc);
        
        revalidate();
    }
    
    public ArrayList buildAvgStrArray(
            ArrayList<String> tmArray,
            ArrayList<String> stTmArray,
            ArrayList<String> stMarkArray
    )
    {
        ArrayList<String> arrayOut = new ArrayList();

        if (stTmArray.get(0) == null)
            {
                String noData = "0";
                arrayOut.add(noData);
            }
        else
        {
            for (int i = 0; i<tmArray.size(); i ++)
            {
                String team = tmArray.get(i);
                int teamMark = 0;
                for (int student = 0; student<stMarkArray.size(); student++)
                {
                    String stTeam = stTmArray.get(student);
                    int stMark = Integer.parseInt(stMarkArray.get(student));
                    System.out.println("/"+team);
                    if (stTeam.equals(team))
                    {
                        teamMark += stMark;
                    }
                }
            teamMark /= tmArray.size();
            arrayOut.add(Integer.toString(teamMark));
            }
        }
        return arrayOut;
    }
    
    public ArrayList buildUniqueStrArray(ArrayList<String> arrayIn)
    {
        ArrayList<String> arrayOut = new ArrayList();
        if (arrayIn.get(0) == null)
            {
                String noData = "No data found.";
                arrayOut.add(noData);
            }
        else
        {
            for (int i = 0 ; i<arrayIn.size();i++)
            {
                boolean duplicate = false;
                String item = arrayIn.get(i);
                for (int i2 = 0; i2<arrayOut.size(); i2++)
                {
                    if (arrayOut.get(i2).equals(item))
                    {
                        duplicate = true;
                    }
                }
                if (duplicate != true)
                {
                    arrayOut.add(item);
                }

            }
            for (int i = 0; i<arrayOut.size(); i++)
                {
                    System.out.println (arrayOut.get(i));
                }
        }
        return arrayOut;
        
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
        ARo2tmAvgDataGrid datagrid = new ARo2tmAvgDataGrid();
        jframe.add(datagrid);
        jframe.setVisible(true);
    }

}
