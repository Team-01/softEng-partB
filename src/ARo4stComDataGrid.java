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
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kit
 */
public class ARo4stComDataGrid extends FindStudentTemplate implements MouseListener
{   
    private double tmModMark;
    private double tmYrAvgMark;
    private String stModMark;
    private String stAvgMark;
    private double stAvgMarks;
    private double stModMarks;
    private JLabel tbl1header;
    private JLabel tbl2header;
           
    private SQLite db;
    private ArrayList<ArrayList> tmVsStuTblArray;
    private ArrayList<ArrayList> stVsStAvgTblArray;
    private String tmSelected;
    private GridBagConstraints gbc;
    private ArrayList<String> headArray;
    private ArrayList<String> stAvgVsStuHeadArray;
    private ArrayList<String> markColArray;
    private ArrayList<String> stMarksArray;
    private ArrayList<String> stAvgMarksArray;
    private ArrayList<String> tmMarksArray;
    private FindStudentTemplate findStudent;
    private ARcommonMethods extendedMethods = new ARcommonMethods();
    private TableTemplate tbl1;
    private TableTemplate tbl2;
    private String stTeam;
    private FindStudentTemplate blank = new FindStudentTemplate();
    public ARo4stComDataGrid() 
    {
        enterEmailBtnPanel.addMouseListener(this);
        tbl1header = buildLbl(getRowEleStr(0) + " vs their team");
        tbl2header = buildLbl(getRowEleStr(0) + " vs student average");
    }
    
    public void addArrayData()
    {
        db = new SQLite();
        //Fill column headings
        headArray.add("");
        headArray.add(getRowEleStr(0));
        if(getTxtStuRowIndex()>-1)
        {    
            if(db.studentsMemberOfTeam.get(getTxtStuRowIndex()) == null)
            {
                stTeam = "No team data found";
            }
            else
            {
                stTeam = db.studentsMemberOfTeam.get(getTxtStuRowIndex());
            }
            headArray.add("Team " + stTeam);

            //Fill array for first column to indicate mark types
            markColArray.add("Year average");
            markColArray.add("Software eng");

            //Fill array for 2nd column with students yr avg and module mark
            stMarksArray.add(db.studentsAverageMark.get(getTxtStuRowIndex()));
            stMarksArray.add(db.studentsModuleMark.get(getTxtStuRowIndex()));

            //create data and fill final column for tm vs student with tm yr avg and module mark
            tmYrAvgMark = stTeamAvg(db.studentsMemberOfTeam, db.studentsAverageMark);
            tmModMark = stTeamAvg(db.studentsMemberOfTeam, db.studentsModuleMark);
            
            tmMarksArray.add(Double.toString(tmYrAvgMark));
            tmMarksArray.add(Double.toString(tmModMark));

            //fill array for first tbl team vs student
            tmVsStuTblArray.add(markColArray);
            tmVsStuTblArray.add(stMarksArray);
            tmVsStuTblArray.add(tmMarksArray);

            //fill array for 2nd tbl st vs st avg
            stAvgMarksArray.add(Double.toString(stMarkAvg(db.studentsAverageMark)));
            stAvgMarksArray.add(Double.toString(stMarkAvg(db.studentsModuleMark)));
        }
        else
        {
            System.out.println("Data not added because student not found");
        }
    }
    
    
    public void addTbls()
    {
        headArray = new ArrayList();
        tmVsStuTblArray = new ArrayList();
        tmMarksArray = new ArrayList();
        markColArray = new ArrayList();
        stMarksArray = new ArrayList();
        stAvgMarksArray = new ArrayList();
        stVsStAvgTblArray = new ArrayList();
        addArrayData();
        
        if (getTxtStuRowIndex() == -1)
        {
            System.out.println("Data not added because student not found");
        }
        else
        {
            tbl1header.setText(getRowEleStr(0) + " vs their team");
            tbl2header.setText(getRowEleStr(0) + " vs student average");
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(15,50,0,50);
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 3;
            if (tbl1 != null && tbl2 !=null)
            {
                this.menuLvl1Panel.remove(tbl1);
                this.menuLvl1Panel.remove(tbl2);
                this.menuLvl1Panel.revalidate();
                this.menuLvl1Panel.repaint();
            }
                  
            tbl1 = new TableTemplate(headArray, tmVsStuTblArray,false,blank, 2, 1);
            
            this.menuLvl1Panel.add(tbl1header,gbc);
            gbc.gridy = 4;
            this.menuLvl1Panel.add(tbl1, gbc);
            
            
            headArray = new ArrayList();
            headArray.add("");
            headArray.add(getRowEleStr(0));
            headArray.add("Average student");
            
            gbc.gridy = 5;
            this.menuLvl1Panel.add(tbl2header,gbc);
            
            stVsStAvgTblArray.add(markColArray);
            stVsStAvgTblArray.add(stMarksArray);
            stVsStAvgTblArray.add(stAvgMarksArray);
            gbc.gridy = 6;
            tbl2 = new TableTemplate(headArray, stVsStAvgTblArray,false,blank, 2, 1);
            this.menuLvl1Panel.add(tbl2, gbc);
        }
    }
    
    public JLabel buildLbl(String text)
    {
        JLabel label = new JLabel(text);
        label.setFont(mainStyle.fontM);
        label.setForeground(mainStyle.systemExtraDarkGrey);
        return label;
    }
    
    public double stMarkAvg(ArrayList<String> arraymarks)
    {
        double stmarks = 0;
        for (int i = 0; i<arraymarks.size(); i++)
        {
            stmarks += Double.parseDouble(arraymarks.get(i));
        }
        return stmarks/arraymarks.size();
    }
    
    public double stTeamAvg(ArrayList<String> stTeams, ArrayList<String> stMark)
    {
        double avg = 0;
        double tmCount = 0;
        tmSelected = db.studentsMemberOfTeam.get(getTxtStuRowIndex());
        for (int i = 0; i<stTeams.size(); i++)
        {
            try
            {
                if (stTeams.get(i).equals(tmSelected))
                {
                    avg += Double.parseDouble(stMark.get(i));
                    tmCount++;
                }
            }
            catch(NullPointerException npe)
            {
                return 0;
            }
            
        }
        return avg/tmCount;
    }
        /**
     * @param args the command line arguments
     */


 
    
    @Override
    public void mouseReleased(MouseEvent event) {                
        if (event.getSource() == enterEmailBtnPanel)
        {
            actionSelectStudentsBtn();
            addTbls();
        }
        else
        {
            actionClickStudent();
            addTbls();
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
      
    }
    public static void main(String[] args) {
        //for testing of file only
        ARjFrameForTest jframe = new ARjFrameForTest();
        ARo4stComDataGrid datagrid = new ARo4stComDataGrid();
        jframe.add(datagrid);
        jframe.setVisible(true);
    }
}