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
public class ARo3oaAvgDataGrid extends JPanel
{
    GridBagLayout layout;
    GridBagConstraints gbc;
    JPanel tbl;
    SQLite db;
    Style mainStyle = new Style();
    
    Double yrAverage;
    Double modMarkAvg;
    Double avgDifference;
    
    String indicator;
    
    JLabel modAvgLbl;
    JLabel yrAvgLbl;
    JLabel avgDiffLbl;     
    
    public ARo3oaAvgDataGrid()
    {

    }
    
    public void buildThis()
    {
        db = new SQLite();
        if(modAvgLbl!= null)
        {
            this.remove(modAvgLbl);
            this.remove(yrAvgLbl);
            this.remove(avgDiffLbl);
        }
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
        
        yrAverage = avgArray(db.studentsAverageMark);
        modMarkAvg = avgArray(db.studentsModuleMark);
        avgDifference = scoreDifference(modMarkAvg, yrAverage);
        
        indicator = differenceIndicator(modMarkAvg, yrAverage);
        
        modAvgLbl = buildLbl("<html>"
                + "The overall <b>module average</b> for the class is <b>" 
                + modMarkAvg + "%</b>.</html>");
        yrAvgLbl = buildLbl("<html>" 
                + "The overall <b>year average</b> for the class is <b>"
                + yrAverage + "%</b>.</html>");
        avgDiffLbl = buildLbl("<html>"
                + "In comparison the module average is <b>"
                + avgDifference + "% " + indicator + "</b>.</html>"); 
        
        gbc.gridy = 1;
        gbc.insets = new Insets(10,50,20,10);
        this.add(modAvgLbl,gbc);
        gbc.gridy = 2;
        this.add(yrAvgLbl,gbc);
        gbc.gridy = 3;
        this.add(avgDiffLbl,gbc);
        revalidate();
    }
    
    public JLabel buildLbl(String text)
    {
        JLabel label = new JLabel(text);
        label.setFont(mainStyle.fontM);
        label.setForeground(mainStyle.systemExtraDarkGrey);
        return label;
    }
    
    public double avgArray(ArrayList<String> arrayIn)
    {
        double total = 0;
        for (int i=0; i<arrayIn.size(); i++)
        {
            double number = 0;
            if (arrayIn.get(i) != null)
            {
            number = Double.parseDouble(arrayIn.get(i));
            }
            total+=number;
        }
        double average = total/arrayIn.size();
        return average;
    }
    
    public double scoreDifference(double n1, double n2)
    {
        if (n1>n2)
        {
            return n1-n2;
        }
        else if (n1<n2)
        {
            return n2-n1;
        }
        else
            return 0;
    }
    
    public String differenceIndicator(double n1, double n2)
    {
        if (n1>n2)
        {
            return "higher";
        }
        else if (n1<n2)
        {
            return "lower";
        }
        else
            return "the same.";
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
        ARo3oaAvgDataGrid datagrid = new ARo3oaAvgDataGrid();
        jframe.add(datagrid);
        jframe.setVisible(true);
    }

}
