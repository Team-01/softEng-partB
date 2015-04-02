import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kit
 */
public class TableTemplate extends JPanel implements MouseListener
{
    private ImageIcon gArrow;
    private ImageIcon rArrow;
    private ImageIcon neutral;
    
    private GridBagConstraints gbc;
    private GridBagLayout tblLayout;
    private GridBagLayout dataViewLvl1Layout;
    
    private ArrayList<ArrayList> columnArray;
    private ArrayList<ArrayList> lblcolArray;
    
    private ArrayList<JPanel> selectedPnls;
    private ArrayList<JLabel> selectedLbls;

    Color systemLightGrey2 = new Color(227, 227, 227);
    
    Style mainStyle = new Style();
   
    public TableTemplate(
            ArrayList<String> tblHeadings,
            ArrayList<ArrayList> stDataArrays,
            boolean selectable) 
    {
        buildTbl(tblHeadings, stDataArrays, selectable);
    }
    
    public TableTemplate(
            ArrayList<String> tblHeadings,
            ArrayList<ArrayList> stDataArrays,
            boolean selectable,
            int columnIlook, 
            int columnItest)
    {
        buildTblComp(tblHeadings,
                    stDataArrays, 
                    selectable, 
                    columnIlook, 
                    columnItest);
    }
    
    public void buildTblComp(
        ArrayList<String> tblHeadings,
        ArrayList<ArrayList> stDataArrays,
        boolean selectable,
        int columnIlook, 
        int columnItest)
    {
        buildTbl(tblHeadings, stDataArrays, selectable);
        gArrow = new ImageIcon(getClass().getClassLoader().getResource(
                "resources/gArrow.png"));
        rArrow = new ImageIcon(getClass().getClassLoader().getResource(
                "resources/rArrow.png"));
        neutral = new ImageIcon(getClass().getClassLoader().getResource(
                "resources/neutral.png"));
        JLabel gArrowLbl = new JLabel(gArrow);
        JLabel rArrowLbl = new JLabel(rArrow);
        JLabel neutralLbl = new JLabel(neutral);
        
        JPanel cHeaderPnl = buildTblLblPanel(gArrowLbl,selectable);
        //cHeaderPnl.add(neutralLbl);
        cHeaderPnl.add(rArrowLbl);
        
        gbc.gridx = tblHeadings.size();
        gbc.gridy = 0;
        this.add(cHeaderPnl,gbc);
        gbc.gridy = 1;
        int columnLength = stDataArrays.get(columnIlook).size(); 
        for (int row = 0; row<columnLength; row++)
        {
            gArrowLbl = new JLabel(gArrow);
            rArrowLbl = new JLabel(rArrow);
            neutralLbl = new JLabel(neutral);
            ArrayList<String>columnLookArray = stDataArrays.get(columnIlook);
            ArrayList<String>columnTestArray = stDataArrays.get(columnItest);
            int lookData = Integer.parseInt(columnLookArray.get(row));
            int testData = Integer.parseInt(columnTestArray.get(row));
            if (lookData > testData)
            {
                JPanel rArrowPnl = buildTblLblPanel(rArrowLbl, selectable);
                this.add(rArrowPnl,gbc);
            }
            else if (lookData < testData)
            {
                JPanel gArrowPnl = buildTblLblPanel(gArrowLbl, selectable);
                this.add(gArrowPnl,gbc);
            }
            else if (lookData == testData)
            {
                JPanel neutralPnl = buildTblLblPanel(neutralLbl, selectable);
                this.add(neutralPnl,gbc);
            }
            gbc.gridy++;
        }
    }
            
    
    public void buildTbl(
            ArrayList<String> tblHeadings,
            ArrayList<ArrayList> stDataArrays,
            boolean selectable)
    {
        
        selectedPnls = new ArrayList();
        selectedLbls = new ArrayList();
        columnArray = new ArrayList();
        lblcolArray = new ArrayList();
        tblLayout = new GridBagLayout();
        this.setLayout(tblLayout);
        //this.setBorder(mainStyle.borderDefault);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridx = 0;
	gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < stDataArrays.size(); i++)
        {
            ArrayList<JPanel> column = new ArrayList();
            ArrayList<JLabel> lblcolumn = new ArrayList();
            ArrayList nextArray = stDataArrays.get(i);
            JPanel columnHead =  buildTblHeadPanel(tblHeadings.get(i));
            JLabel headlbl = new JLabel(tblHeadings.get(i));
            lblcolumn.add(headlbl);
            column.add(columnHead);
            
            for(int i2 = 0; i2 < nextArray.size(); i2++)
            {
                gbc.gridy = 0;
                String cellStr = nextArray.get(i2).toString();
                JLabel cellLbl = new JLabel(cellStr);
                JPanel cellPnl = buildTblLblPanel(cellLbl, selectable);
                
                if (nextArray.get(i2) != null)
                {
                    cellLbl.setText(cellStr);
                }
                else
                {
                    cellLbl.setText("no data");
                }
                lblcolumn.add(cellLbl);
                column.add(cellPnl);
            }
            
            columnArray.add(column);
            lblcolArray.add(lblcolumn);
            ArrayList<JPanel>columnRef = columnArray.get(i);
            
            for(int row = 0; row<columnRef.size(); row++)
            {
                this.add(columnRef.get(row), gbc);
                gbc.gridy++;
                revalidate();
            }
            
            gbc.gridx++;
            revalidate();
        }
        
    }
    
    public JPanel buildTblLblPanel(JLabel lbl, Boolean selectable)
    {
        JPanel tblLblPanel = new JPanel();
        //GridBagLayout pnlLayout = new GridBagLayout();
        //tblLblPanel.setLayout(pnlLayout);
        //GridBagConstraints gbcpnl= new GridBagConstraints();
        //gbcpnl.gridheight = 1;
        //gbcpnl.gridwidth = 1;
        //gbcpnl.fill = GridBagConstraints.HORIZONTAL;

        tblLblPanel.setBackground(Color.white);
        tblLblPanel.setBorder(BorderFactory.createLineBorder
                                                (mainStyle.systemLightGrey, 1));
        if (selectable == true)
        {
            tblLblPanel.addMouseListener(this);
            lbl.addMouseListener(this);
            lbl.setFont(mainStyle.fontXS);
        }
        
        tblLblPanel.add(lbl);
        return tblLblPanel;
    }
    
        public JPanel buildTblHeadPanel(String text)
    {
        JLabel tblHeadLbl = new JLabel(text);
        JPanel tblHeadPanel = buildTblLblPanel(tblHeadLbl, false);

        tblHeadLbl.setFont(mainStyle.fontS);
        //tblHeadPanel.setBorder(mainStyle.borderDefault);

        return tblHeadPanel;
    }

    public void actionRowEvent(Object event, String type)
    {
        int r = 0;
        int rows = columnArray.get(0).size();
        JPanel cell = new JPanel();
        JLabel cellLbl = new JLabel();
        for (int column = 0; column<columnArray.size(); column++)
            for (int row = 1; row<rows; row++)
            {
                ArrayList<JPanel>col = columnArray.get(column);
                ArrayList<JLabel>lblcol = lblcolArray.get(column);
                cell = col.get(row);
                cellLbl = lblcol.get(row);
                //col.get(row).setBackground(Color.white);
                if (event==cell ||
                    event==cellLbl)
                {
                    selectedPnls = new ArrayList();
                    selectedLbls = new ArrayList();
                    for (int i = 0;i<columnArray.size(); i++)
                    {
                        col = columnArray.get(i);
                        cell=col.get(row);
                        cellLbl=lblcol.get(row);
                        selectedPnls.add(cell);
                        selectedLbls.add(cellLbl);
                        
                        if (type == "select")
                        {
                            //if(cell.getBackground() == mainStyle.systemDarkGrey ||
                            //   cell.getBackground() == systemLightGrey2)
                            //{
                            //    cell.setBackground(Color.white);
                           // }
                            
                            //{
                                cell.setBackground(mainStyle.systemDarkGrey);
                            //}
                            r = row;
                        }
                        else if (type == "hover")
                        {
                            if(cell.getBackground() == mainStyle.systemDarkGrey)
                            {
                                cell.setBackground(systemLightGrey2);
                            }
                            else
                            {
                                cell.setBackground(mainStyle.systemLightGrey);
                            }
                        }
                        else if (type == "exit")
                        {
                            if(cell.getBackground() == systemLightGrey2||
                               cell.getBackground() == mainStyle.systemDarkGrey)
                            {
                                cell.setBackground(mainStyle.systemDarkGrey);
                            }
                            else
                            {
                                cell.setBackground(Color.white);
                            }
                        }
                    }
                }
                else if (type == "select")
                {
                    if (row!=r)
                    cell.setBackground(Color.white);
                }
                
               // {
                   
               // }
            }
    }
        
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        actionRowEvent(e.getSource(), "select");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        actionRowEvent(e.getSource(), "hover");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        actionRowEvent(e.getSource(),"exit");
    }
    
    public void setTblRowBackground(int column, int row, Color colour)
    {
    }
    
    public static void main(String[] args)
    {
        
        
        SQLite db = new SQLite(); // Create an instance of SQLite class for working on database
        ArrayList<ArrayList> columns = new ArrayList();
        columns.add(db.studentsID);
        columns.add(db.studentsModuleMark);
        columns.add(db.studentsAverageMark);
        
        ArrayList<String> columnsHead = new ArrayList();
        columnsHead.add("Name");
        columnsHead.add("Year average");
        columnsHead.add("Software Eng");
        TableTemplate tbl = new TableTemplate(columnsHead, columns, true);
        ARjFrameForTest jframeAll = new ARjFrameForTest();
        jframeAll.add(tbl);
        jframeAll.setVisible(true);
    }
}