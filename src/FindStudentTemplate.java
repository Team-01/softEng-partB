import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import static java.awt.FlowLayout.CENTER;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createDashedBorder;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.border.Border;


/**
 *
 * @author Kit
 */
public class FindStudentTemplate extends JPanel implements MouseListener
{
    private TableTemplate stTable;
    private JPanel enterEmailPnl;
    private SetKeyActions setKeyActions;
    private GetFocus getFocus;
    private JLabel enterEmailBtnLbl;
    public JPanel enterEmailLblPanel;
    private GridBagConstraints tablegbc;
    private int stRowSelected;
    private GridBagLayout layout;
    SQLite db = new SQLite(); // Create an instance of SQLite class for working on database
    private JLabel enterEmailLbl;
    public JPanel enterEmailBtnPanel;
    private JLabel enterEmailBtn;
    private JPanel enterEmailTxtPanel;
    JPanel enterModMarkLblPanel;
    JPanel enterModMarkTxtPanel;
    JPanel enterEmailPanel;
    JPanel enterYrAvgLblPanel;
    JPanel enterYrAvgTxtPanel;
    JPanel enterYrAvgPanel;
    private JTextField enterEmailTxt;
    private GridBagConstraints gbc;
    public GridBagConstraints gbcmenu; 
    private GridBagConstraints gbcdataview; 
    private JLabel stFoundLbl = new JLabel();
    public JPanel menuLvl1Panel;
    private JPanel dataViewLvl1Panel;
    private GridBagLayout menuLvl1Layout;
    private GridBagLayout dataViewLvl1Layout;
    
    ArrayList<String>stNameArray;
    ArrayList<String>stEmailArray;
    ArrayList<String>stModMarkArray;
    ArrayList<String>stYrAvgArray;
    
    ArrayList<ArrayList>stTblColumns;
    
    ArrayList<String>stTblColHeaders;

    Color systemLightGrey2 = new Color(227, 227, 227);
    
    Style mainStyle = new Style();
   
    public FindStudentTemplate() 
    {        
        
        buildThis();
        
    }
    
    /**
     * note that for now buildLvl2PanelGrid is the same as buildLvl1PanelGrid
     * this has been created for potential future differentiations.
     * @param layout
     * @return 
     */
    public JPanel buildLvl2PanelGrid(GridBagLayout layout)
    {
        JPanel panelLvl2gb = buildLvl1PanelGrid(layout);
        return panelLvl2gb;
    }
    
    public JPanel buildLvl1PanelGrid(GridBagLayout layout)
    {
        JPanel panelLvl1gb = new JPanel();
        panelLvl1gb.setBackground(Color.white);
        panelLvl1gb.setLayout(layout);
        return panelLvl1gb;
    }
    
    public JPanel buildPanelCompMenuLvl1()
    {
        JPanel compHolder = new JPanel();
        compHolder.setBackground(Color.white);
        FlowLayout compHolderLayout = new FlowLayout(5,5,2);
        compHolder.setLayout(compHolderLayout);
        getFocus = new GetFocus();
        compHolder.addFocusListener(getFocus);
        return compHolder;
    }
    
    public JPanel buildAbtn(String btnTxt)
    {
        JPanel btn = new ButtonTemplate(btnTxt);
        btn.addMouseListener(this);
        btn.addKeyListener(setKeyActions);
        return btn;
    }
    
    public JPanel buildPanelLblMenuLvl1(JLabel label)
    {
        JPanel lblHolder = buildPanelCompMenuLvl1();
        lblHolder.add(label);
        return lblHolder;
    }
    
        public JPanel buildPanelTxtMenuLvl1(JTextField txtfield)
    {
        JPanel txtHolder = buildPanelCompMenuLvl1();
        txtHolder.add(txtfield);
        return txtHolder;
    }
    
    public JTextField buildTxtMenu(int colWidth)
    {
        JTextField txtField = new JTextField(colWidth);
        txtField.setBorder(mainStyle.borderDefault);
        txtField.setFont(mainStyle.fontS);
        txtField.setHorizontalAlignment(JTextField.CENTER);
        txtField.addFocusListener(getFocus);
        txtField.setFocusable(true);
        return txtField;
    }
    
    
    /**
     * determines the message that will appear once a student is selected, either
     * by using the button or selected from the students listed.
     * @param source
     * @param found
     * @param IDindex 
     */
    public void actionClickStudent()
    {
        enterEmailTxt.setText(stTable.getSelectedRowEleStr(1));
        actionSelectStudentsBtn();
    }
    
    public void actionSelectStudentsBtn()
    {
        stRowSelected = getTxtStuRowIndex();
        //System.out.println(stRowSelected);
        stTable.setSelectSt(stRowSelected);
        actionStFoundLbl(stRowSelected);
    }
    
    public int getRowSelected()
    {
        return stRowSelected;
    }
    
    public void setRowSelected(int i)
    {
        stRowSelected = i;
    }
    public boolean actionStFoundLbl(int rowIndex)
    {
        boolean found = false;
        String lblText = "";
        stFoundLbl.setFont(mainStyle.fontS);
        if (rowIndex == -1)
        {
            stFoundLbl.setForeground(Color.red);
            String stEmail = enterEmailTxt.getText();
            lblText = "<html>Student with email <b>"
                             +stEmail+
                             "</b> could not be found.";
            found = false;
        }
        else
        {
            stFoundLbl.setForeground(mainStyle.systemColor);
            String stName = stTable.getSelectedRowEleStr(0);
            String stEmail = stTable.getSelectedRowEleStr(1);
            lblText = "<html>Student <b>"
                    +stName+"</b>, email <b>"+stEmail+"</b> selected.";
            found = true;
            
        }
        stFoundLbl.setText(lblText);
        gbcmenu.insets = new Insets(0,15,10,0);
        gbcmenu.gridx = 0;
        gbcmenu.gridy = 2;
        menuLvl1Panel.add(stFoundLbl, gbcmenu);
        revalidate();
        return found;
    }
    
    public void createMenuComponents()
    {
        setKeyActions = new SetKeyActions();
        gbc = new GridBagConstraints();
        
        menuLvl1Layout = new GridBagLayout();
        dataViewLvl1Layout = new GridBagLayout();
        
        menuLvl1Panel = buildLvl1PanelGrid(menuLvl1Layout);
        menuLvl1Panel.setFocusable(false);
        dataViewLvl1Panel = buildLvl1PanelGrid(menuLvl1Layout);
        enterEmailLbl = buildLblMenuLvl1
                       ("Enter student email or select student from list");
        enterEmailLblPanel = buildPanelLblMenuLvl1(enterEmailLbl);
        enterEmailTxt = buildTxtMenu(20);
        enterEmailTxtPanel = buildPanelTxtMenuLvl1(enterEmailTxt);
        
        enterEmailBtnPanel = buildAbtn("Select student");
        
        enterEmailPnl = buildPanelCompMenuLvl1();
        
    }
    
    public void createStTbl()
    {
        gbcdataview = new GridBagConstraints();
        stNameArray = db.studentsStuName;
        stEmailArray = db.studentsStuEmail;
        stModMarkArray = db.studentsModuleMark;
        stYrAvgArray = db.studentsAverageMark;
        stTblColumns = new ArrayList<ArrayList>();
        stTblColumns.add(stNameArray);
        stTblColumns.add(stEmailArray);
        stTblColumns.add(stModMarkArray);
        stTblColumns.add(stYrAvgArray);
        
        stTblColHeaders = new ArrayList<String>();
        String nameHeader = "Name";
        String emailHeader = "Email";
        String modMarkHeader = "Software Eng";
        String yrAvgHeader = "Year Average";
        stTblColHeaders.add(nameHeader);
        stTblColHeaders.add(emailHeader);
        stTblColHeaders.add(modMarkHeader);
        stTblColHeaders.add(yrAvgHeader);
        
        stTable = new TableTemplate(stTblColHeaders,stTblColumns, this, true);

    }
    
    /**
     * creates a jlabel button styled for the MenuLvl1 panel layout.
     * @param btnLabel is created separately in case it needs to be dynamically
     * changed
     * @return 
     */

    public JLabel buildLblMenuLvl1(String lblTxt)
    {
        JLabel menuLabel = new JLabel(lblTxt);
            menuLabel.setFont(mainStyle.fontS);
            return menuLabel;
    }

    public void buildThis()
    {
        layout = new GridBagLayout();
        
        this.setLayout(layout);
        this.setBackground(Color.white); 
        createStTbl();
        createMenuComponents();
        addLvl1Panels();
        revalidate();
        
    }
    public int getSelectedTblRow()
    {
        return stTable.getSelectedRow();
    }
    public String getRowEleStr(int i)
    {
        return stTable.getSelectedRowEleStr(i);
    }
    public String getEnteredEmailTxt()
    {
        if (actionStFoundLbl(stRowSelected) != false)
        {
            return enterEmailTxt.getText();
        }
        else
        {
            return "No student found";
        }
    }
    public int getTxtStuRowIndex()
    {
        int stRowIndex = -1;
        for (int i = 0; i < db.studentsStuEmail.size(); i++)
        {
            if(db.studentsStuEmail.get(i).equals(enterEmailTxt.getText()))
            {
               stRowIndex = i;
               break;
            }
        }
        return stRowIndex;
    }
    
    public void setSelectedCellTxt(String txt, int col)
    {
        stTable.setSelectedCellTxt(txt, col);
    }
    
    public void setEnterEmailTxt(String txt)
    {
        enterEmailTxt.setText(txt);
    }
      
    public void addMenuLvl1InitialComponents()
    {
        gbcmenu = new GridBagConstraints();
        gbcmenu.anchor = GridBagConstraints.NORTHWEST;
        gbcmenu.insets = new Insets(15,30,0,0);
        gbcmenu.weightx=1;
        gbcmenu.weighty=1;
        gbcmenu.gridx = 0;
	gbcmenu.gridy = 0;
        gbcmenu.gridwidth=1;
        menuLvl1Panel.add(enterEmailLblPanel,gbcmenu);
        gbcmenu.gridy = 1;
        enterEmailPnl.add(enterEmailTxtPanel);
        enterEmailPnl.add(enterEmailBtnPanel);
        menuLvl1Panel.add(enterEmailPnl,gbcmenu);
        revalidate();
    }
    
    /**
     * adds the panels used to build the table to select students from.
     */
    public void addDataViewLvl1Panels()
    {
        gbcdataview = new GridBagConstraints();
        gbcdataview.anchor = GridBagConstraints.NORTHWEST;
        gbcdataview.weightx=1;
        gbcdataview.weighty=1;
        gbcdataview.gridx = 0;
	gbcdataview.gridy = 1;
        gbcdataview.gridwidth=1;
        gbcdataview.fill = GridBagConstraints.HORIZONTAL;
        
        tablegbc = new GridBagConstraints();
        tablegbc.anchor = GridBagConstraints.NORTHWEST;
        tablegbc.weightx=1;
        tablegbc.weighty=1;
        tablegbc.gridx = 0;
	tablegbc.gridy = 1;
        tablegbc.fill = GridBagConstraints.HORIZONTAL;
        dataViewLvl1Panel.add(stTable, gbcdataview);     
        
    }
    
    /**
     * adds menu and dataView to main jpanel
     */
    public void addLvl1Panels()
    {

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
	gbc.gridy = 0;
        gbc.gridwidth=1;
        
        this.add(menuLvl1Panel, gbc);
        gbc.insets = new Insets(10,0,0,10);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(dataViewLvl1Panel, gbc);
        
        addMenuLvl1InitialComponents();
        addDataViewLvl1Panels();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent event) 
    {

    }

    @Override
    public void mousePressed(MouseEvent event) 
    {

    }

    @Override
    public void mouseReleased(MouseEvent event) {                
        if (event.getSource() == enterEmailBtnPanel)
        {
            actionSelectStudentsBtn();
        }
        else
        {
            actionClickStudent();
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (event.getSource() == enterEmailBtn)
        {
            actionSelectStudentsBtn();
        }
        //revalidate();
    }

    @Override
    public void mouseExited(MouseEvent event) {
       
    }
    
    private class GetFocus implements FocusListener{
//TODO: non urgent - put components in array at some point to clean up code.
        @Override
        public void focusGained(FocusEvent e) {
            if(e.getSource() == enterEmailTxt)
            {
                enterEmailTxt.setBorder(mainStyle.borderFocused);
                enterEmailTxt.setForeground(mainStyle.systemColor);
                enterEmailLbl.setForeground(mainStyle.systemColor);
            }
            else if(e.getSource() == enterEmailBtnPanel)
            {
                enterEmailBtnPanel.setBorder(mainStyle.borderFocused);
                enterEmailBtnLbl.setForeground(mainStyle.systemColor);
                enterEmailLbl.setForeground(mainStyle.systemColor);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == enterEmailTxt)
            {
                enterEmailTxt.setBorder(mainStyle.borderDefault);
                enterEmailTxt.setForeground(Color.black);
                enterEmailLbl.setForeground(Color.black);
            }
            else if(e.getSource() == enterEmailBtnPanel)
            {
                enterEmailBtnPanel.setBorder(mainStyle.borderDefault);
                enterEmailBtnLbl.setForeground(Color.black);
                enterEmailLbl.setForeground(Color.black);
            } 
        }
    }
    
    private class SetKeyActions implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e){}
        @Override
        public void keyPressed(KeyEvent e) {
            
            //if(e.getKeyCode() == KeyEvent.VK_ENTER)
            //{
            //    if (e.getSource() == btnEnterMarks)
            //    {
                    //actionEnterMarks();
            //    }  
            //}
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        
    }
            /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //for testing of file only
        ARjFrameForTest jframe = new ARjFrameForTest();
        FindStudentTemplate datagrid = new FindStudentTemplate();
        jframe.add(datagrid);
        jframe.setVisible(true);
        
       // for (int i = 0; i < datagrid.db.studentsID.size(); i++)
      //  {
     //      System.out.println(datagrid.db.studentsID.get(i) + " ");
     //   }
    }
}
