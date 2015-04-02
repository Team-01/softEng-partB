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
public class ARo5inResDataGridDELETE extends JPanel implements MouseListener
{
    private JPanel btnEnterMarksPnl;
    private JPanel enterNamePnl;
    private JLabel btnNextStLbl;
    private JPanel btnNextStPnl;
    private SetKeyActions setKeyActions;
    private GetPercentageValid getPercentValid;
    private GetFocus getFocus;
    private JLabel btnEnterMarksLbl;
    private JLabel enterNameBtnLbl;
    private JPanel enterNameLblPanel;
    private GridBagConstraints tablegbc;
    private int stRowSelected;
    private GridBagLayout layout;
    SQLite db = new SQLite(); // Create an instance of SQLite class for working on database
    private JLabel enterNameLbl;
    private JPanel enterNameBtnPanel;
    private JLabel enterNameBtn;
    private JPanel enterNameTxtPanel;
    private JPanel btnEnterMarks;
    JPanel enterModMarkLblPanel;
    JPanel enterModMarkTxtPanel;
    JPanel enterMarksPanel;
    JPanel enterYrAvgLblPanel;
    JPanel enterYrAvgTxtPanel;
    JPanel enterYrAvgPanel;
    private JTextField enterNameTxt;
    private GridBagConstraints gbc;
    private GridBagConstraints gbcmenu; 
    private GridBagConstraints gbcdataview; 
    private JLabel stFoundLbl = new JLabel();
    private JLabel enterModMarkLbl;
    private JTextField enterModMarkTxt;
    private JLabel enterYrAvgLbl;
    private JTextField enterYrAvgTxt;
    private JPanel stuIDcolPanel; 
    private JPanel stuModMarkPanel;
    private JPanel stuYrAvgPanel;
    private JPanel stuIDrowPanel; 
    private JPanel tableHolder;
    private JPanel menuLvl1Panel;
    private JPanel dataViewLvl1Panel;
    private GridBagLayout menuLvl1Layout;
    private GridBagLayout dataViewLvl1Layout;

    ArrayList<JPanel> stuIDpanelArray = new ArrayList<JPanel>(); 
    ArrayList<JPanel> stuModMarkPanelArray = new ArrayList<JPanel>(); 
    ArrayList<JPanel> stuYrAvgPanelArray = new ArrayList<JPanel>(); 
    
    ArrayList<JLabel> stuIDlblArray = new ArrayList<JLabel>(); 
    ArrayList<JLabel> stuModMarkLblArray = new ArrayList<JLabel>(); 
    ArrayList<JLabel> stuYrAvgLblArray = new ArrayList<JLabel>();
    //ArrayList<JPanel> stuSelectrows = new ArrayList<JPanel>(); 
    Color systemLightGrey2 = new Color(227, 227, 227);
    
    Style mainStyle = new Style();
   
    public ARo5inResDataGridDELETE() 
    {        
        
        buildThis();
        fillStDataLblArrays();
        
    }
    
    public void fillStDataLblArrays()
    {
        for (int i = 0; i < db.studentsID.size(); i++)
        {
                
        /**
         * create JPanel to add labels to
         */
        
        JLabel stuIDx = new JLabel(db.studentsID.get(i));
        JLabel stuModMarkx = new JLabel();
        JLabel stuYrAvgx = new JLabel();
        
        JPanel stuIDxPanel = createTblLblPanels(stuIDx);
        JPanel stuModMarkxPanel = createTblLblPanels(stuModMarkx);
        JPanel stuYrAvgxPanel = createTblLblPanels(stuYrAvgx);
                
        if (db.studentsModuleMark.get(i) != null)
        {
            stuModMarkx.setText(db.studentsModuleMark.get(i));
        }
        else
        {
            stuModMarkx.setText("no data");
        }
        
        if (db.studentsAverageMark.get(i) != null)
        {
            stuYrAvgx.setText(db.studentsAverageMark.get(i));
        }
        else
        {
            stuYrAvgx.setText("no data");
        }
                    
        /**
        * create panel ..
        */
        stuIDxPanel.add(stuIDx);
        stuModMarkxPanel.add(stuModMarkx);
        stuYrAvgxPanel.add(stuYrAvgx);
        
        JPanel rowHolder = new JPanel();
        GridBagLayout rowHolderlayout = new GridBagLayout();
        rowHolder.setLayout(rowHolderlayout);
        GridBagConstraints gbcRows = new GridBagConstraints();
        gbcRows.weightx = gbcRows.weighty = 1;
        gbcRows.gridx = 0;
        
        stuIDpanelArray.add(stuIDxPanel);
        stuModMarkPanelArray.add(stuModMarkxPanel);
        stuYrAvgPanelArray.add(stuYrAvgxPanel);
        stuIDlblArray.add(stuIDx);
        stuModMarkLblArray.add(stuModMarkx);
        stuYrAvgLblArray.add(stuYrAvgx);
        
        
        }
        
        JLabel stuIDxHeader = new JLabel("Student I.D.");
        JLabel stuModMarkxHeader = new JLabel("Module Mark");
        JLabel stuYrAvgxHeader = new JLabel("Year Average");
        
        JPanel stuIDxHeaderPanel = createTblLblPanels(stuIDxHeader);
        JPanel stuModMarkxHeaderPanel = createTblLblPanels(stuModMarkxHeader);
        JPanel stuYrAvgxHeaderPanel = createTblLblPanels(stuYrAvgxHeader);

        stuIDxHeaderPanel.add(stuIDxHeader);
        stuModMarkxHeaderPanel.add(stuModMarkxHeader);
        stuYrAvgxHeaderPanel.add(stuYrAvgxHeader);
        
        tableHolder.add(stuIDxHeaderPanel, tablegbc);
        tablegbc.gridx = 1;
        tableHolder.add(stuModMarkxHeaderPanel, tablegbc);
        tablegbc.gridx = 2;
        tableHolder.add(stuYrAvgxHeaderPanel, tablegbc);
        
        /**
         * JPanel array stuRows is used to put data into the column
         * JPanel stuIDcolLayout which is added to the data grid.
         */
        for (int row = 0; row < stuIDpanelArray.size(); row++)
        {
            tablegbc.gridy++;//+1;
            tablegbc.gridx = 0;
            tableHolder.add(stuIDpanelArray.get(row),tablegbc);
            tablegbc.gridx = 1;
            tableHolder.add(stuModMarkPanelArray.get(row),tablegbc);
            tablegbc.gridx = 2;
            tableHolder.add(stuYrAvgPanelArray.get(row),tablegbc);            
        }
            revalidate();
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
    
    public JTextField buildTxtPercentMenu(int colWidth)
    {
        JTextField txtEnterPercent = buildTxtMenu(colWidth);   
        getPercentValid = new GetPercentageValid();
        txtEnterPercent.setInputVerifier(getPercentValid);
        return txtEnterPercent;
    }
    
    public JPanel createTblLblPanels(JLabel lbl)
    {
        JPanel tblLblPanel = new JPanel();

        tblLblPanel.setBackground(Color.white);
        tblLblPanel.setBorder(BorderFactory.createLineBorder
                                                (mainStyle.systemLightGrey, 1));
        tblLblPanel.addMouseListener(this);
        tblLblPanel.add(lbl);
        lbl.addMouseListener(this);
        return tblLblPanel;
    }
    
    
    /**
     * determines the message that will appear once a student is selected, either
     * by using the button or selected from the students listed.
     * @param found
     * @param IDindex 
     */
    public void actionSelectStudentsTbl(Object source)
    {
        if(getPercentValid.verify(enterModMarkTxt)==true &&
           getPercentValid.verify(enterYrAvgTxt)==true)
        {
            for (int row = 0; row < stuIDpanelArray.size(); row++)
            {

                if (source == stuIDpanelArray.get(row) ||
                    source == stuModMarkPanelArray.get(row) ||
                    source == stuYrAvgPanelArray.get(row) ||

                    source == stuIDlblArray.get(row) ||
                    source == stuModMarkLblArray.get(row) ||
                    source == stuYrAvgLblArray.get(row))   
                {

                        stRowSelected = row;
                        setTblRowBackground(stRowSelected, mainStyle.systemDarkGrey);
                        enterNameTxt.setText(stuIDlblArray.get(stRowSelected).getText());

                        actionStFoundLbl(stRowSelected);
                        addEnterMarksComponents();
                        setEnterMarksTxt(stRowSelected);
                        enterModMarkTxt.requestFocus();
                }

                else if(source != enterNameBtnPanel &&
                        source != btnEnterMarksLbl &&
                        source != btnEnterMarks)
                {
                    {
                        setTblRowBackground(row, Color.white);
                    }

                }
            }
        }
    }
    public void actionSelectStudentsBtn()
    {
            if (getTxtStRow()!=-1)
            {
                stRowSelected = getTxtStRow();
                addEnterMarksComponents();
                setEnterMarksTxt(stRowSelected);
                enterModMarkTxt.requestFocus();
                actionStFoundLbl(stRowSelected);
            }
            else
            {
                stRowSelected = getTxtStRow();
                actionStFoundLbl(stRowSelected);
            }
            
            String IDentered = enterNameTxt.getText();

            for (int row = 0; row < stuIDpanelArray.size(); row++)
            {
                JLabel stIDlbl = stuIDlblArray.get(row);
                if (stIDlbl.getText().equals(IDentered))
                {
                    setTblRowBackground(row, mainStyle.systemDarkGrey);
                }
                else
                {
                    setTblRowBackground(row, Color.white);
                }
                revalidate();
            }
    }
    
    public void actionSelectNextSt()
    {
        
        if (stRowSelected<db.studentsID.size()-1)
            {
                stRowSelected++;
                String stTxtInput = db.studentsID.get(stRowSelected);
                enterNameTxt.setText(stTxtInput);
                actionSelectStudentsBtn();
            }
        else
            {
                stRowSelected=0;
                enterNameTxt.setText(db.studentsID.get(stRowSelected));
                actionSelectStudentsBtn();
            }
    }
    public void actionEnterMarks()
    {
        if (getTxtStRow() != -1)
            if(getPercentValid.verify(enterModMarkTxt)==true)
            {
                if(getPercentValid.verify(enterYrAvgTxt)==true)
                {
                    setEnterMarksData(getTxtStRow());
                }
            }
        else if (getTxtStRow() == -1)
        {
        mainStyle.createPopUpFrame("You haven't selected a student.", 350, 150);
        }
    }
    
    public void actionStFoundLbl(int IDindex)
    {

        stFoundLbl.setFont(mainStyle.fontS);
        String lblText;
        if(IDindex != -1)
        {
            if (stuModMarkLblArray.get(IDindex).getText().equals("no data") &&
                stuYrAvgLblArray.get(IDindex).getText().equals("no data"))
            {
                lblText = "Add data for this student.";
            }
            else
            {
                lblText = "Edit data for this student.";
            }
            stFoundLbl.setForeground(mainStyle.systemColor);
        }
        else
        {
            lblText = "Student ID not found. Try again.";
            stFoundLbl.setForeground(Color.red);
        }
        
        stFoundLbl.setText(lblText);
        gbcmenu.insets = new Insets(15,150,0,0);
        gbcmenu.gridx = 0;
        gbcmenu.gridy = 1;
        menuLvl1Panel.add(stFoundLbl, gbcmenu);
    }
    
    public void createInitialComponents()
    {
        setKeyActions = new SetKeyActions();
        gbc = new GridBagConstraints();
        
        menuLvl1Layout = new GridBagLayout();
        dataViewLvl1Layout = new GridBagLayout();
        
        menuLvl1Panel = buildLvl1PanelGrid(menuLvl1Layout);
        menuLvl1Panel.setFocusable(false);
        dataViewLvl1Panel = buildLvl1PanelGrid(menuLvl1Layout);
        enterNameLbl = buildLblMenuLvl1
                       ("Enter student ID or select student from list:");
        enterNameLblPanel = buildPanelLblMenuLvl1(enterNameLbl);
        enterNameTxt = buildTxtMenu(4);
        enterNameTxtPanel = buildPanelTxtMenuLvl1(enterNameTxt);
        
        enterNameBtnPanel = buildAbtn("Select student");
        
        enterNamePnl = buildPanelCompMenuLvl1();
        
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
    
    public void createEnterMarksComponents()
    {
            gbcdataview = new GridBagConstraints();
            GridBagLayout tableHolderLayout = new GridBagLayout();
            tableHolder = buildLvl2PanelGrid(tableHolderLayout);
            tablegbc = new GridBagConstraints();
            enterModMarkLbl = buildLblMenuLvl1("Enter module mark %");
            enterYrAvgLbl   = buildLblMenuLvl1("Enter year average %");
            
            enterYrAvgTxt = buildTxtPercentMenu(4);
            enterModMarkTxt = buildTxtPercentMenu(4);
            
            enterModMarkLblPanel = buildPanelLblMenuLvl1(enterModMarkLbl);
            enterModMarkTxtPanel = buildPanelTxtMenuLvl1(enterModMarkTxt);
            enterMarksPanel = buildPanelCompMenuLvl1();
            
            enterYrAvgLblPanel = buildPanelLblMenuLvl1(enterYrAvgLbl);
            enterYrAvgTxtPanel = buildPanelTxtMenuLvl1(enterYrAvgTxt);
            enterYrAvgPanel = buildPanelCompMenuLvl1();
            
            btnEnterMarks = buildAbtn("Update marks");
            
            btnNextStPnl = buildAbtn("Select next student");
            
            btnEnterMarksPnl = buildPanelCompMenuLvl1();
        }
    
    public void buildThis()
    {
        layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.white); 
        createInitialComponents();
        createEnterMarksComponents();
        addLvl1Panels();
        revalidate();
    }
    
    public int getTxtStRow()
    {
        int stIDindex = -1;
        for (int i = 0; i < db.studentsID.size(); i++)
        {
            if(db.studentsID.get(i).equals(enterNameTxt.getText()))
            {
               stIDindex = i;
               break;
            }
        }
        return stIDindex;
    }
    
    public void setTblRowBackground(int row, Color colour)
    {
        stuIDpanelArray.get(row).setBackground(colour);
        stuModMarkPanelArray.get(row).setBackground(colour);
        stuYrAvgPanelArray.get(row).setBackground(colour);
        revalidate();
    }
    

    
    public void setEnterMarksData(int stIDindex)
    {
        if (!enterModMarkTxt.getText().equals(""))
        {
            db.modify("UPDATE students SET moduleMark = '"
                    +enterModMarkTxt.getText()+"' WHERE ID = '"+db.studentsID.get(stRowSelected)+"'");
            stuModMarkLblArray.get(stIDindex).setText(enterModMarkTxt.getText() + "%");
        }
        
        if (!enterYrAvgTxt.getText().equals(""))
        {
            db.modify("UPDATE students SET averageMark = '"
                    +enterYrAvgTxt.getText()+"' WHERE ID = '"+db.studentsID.get(stRowSelected)+"'");
            stuYrAvgLblArray.get(stIDindex).setText(enterYrAvgTxt.getText() + "%");
            revalidate();
        }
        /**
         * TODO: update marks in database (SQL)
         */
    }
    /**
     * sets the text box text for entering marks (module and year average to the
     * selected students marks, or null if no marks or student not found.
     */
    public void setEnterMarksTxt(int stIDindex)
    {
        if (db.studentsModuleMark.get(stIDindex) == null)
        {
            enterModMarkTxt.setText("");
        }
        else
        {
            enterModMarkTxt.setText(db.studentsModuleMark.get(stIDindex));
        }
        
        if (db.studentsAverageMark.get(stIDindex) == null)
            enterYrAvgTxt.setText("");
        else
        {
            enterYrAvgTxt.setText(db.studentsAverageMark.get(stIDindex));
        }
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
        enterNamePnl.add(enterNameLbl);
        enterNamePnl.add(enterNameTxtPanel);
        enterNamePnl.add(enterNameBtnPanel);
        menuLvl1Panel.add(enterNamePnl,gbcmenu);
        
        
        revalidate();
        
        //add to tab index
    }
    
    /**
     * adds the panels used to build the table to select students from.
     */
    public void addDataViewLvl1Panels()
    {
        gbcdataview.anchor = GridBagConstraints.NORTHWEST;
        gbcdataview.weightx=1;
        gbcdataview.weighty=1;
        gbcdataview.gridx = 0;
	gbcdataview.gridy = 1;
        gbcdataview.gridwidth=1;
        gbcdataview.fill = GridBagConstraints.HORIZONTAL;
        
        tablegbc.anchor = GridBagConstraints.NORTHWEST;
        tablegbc.weightx=1;
        tablegbc.weighty=1;
        tablegbc.gridx = 0;
	tablegbc.gridy = 1;
        tablegbc.fill = GridBagConstraints.HORIZONTAL;
        dataViewLvl1Panel.add(tableHolder, gbcdataview);      
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
        gbc.insets = new Insets(10,50,0,100);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(dataViewLvl1Panel, gbc);
        
        addMenuLvl1InitialComponents();
        addDataViewLvl1Panels();
    }
    
    /**
     * adds components to the menuLvl1 jpanel to enter marks
     */
    public void addEnterMarksComponents()
    {   
        enterModMarkLblPanel.add(enterModMarkLbl);
        enterModMarkTxtPanel.add(enterModMarkTxt);
        enterMarksPanel.add(enterModMarkLblPanel);
        enterMarksPanel.add(enterModMarkTxtPanel);
        
        enterYrAvgLblPanel.add(enterYrAvgLbl);
        enterYrAvgTxtPanel.add(enterYrAvgTxt);
        
        enterMarksPanel.add(enterYrAvgLblPanel);
        enterMarksPanel.add(enterYrAvgTxtPanel);
        
        gbcmenu.insets = new Insets(15,25,0,0);
        gbcmenu.gridx = 0;
        gbcmenu.gridy = 2;
        menuLvl1Panel.add(enterMarksPanel, gbcmenu);
        
        //gbcmenu.anchor = GridBagConstraints.NORTHEAST;
        gbcmenu.insets = new Insets(15,220,15,0);
        gbcmenu.gridx = 0;
        gbcmenu.gridy = 3;
        btnEnterMarksPnl.add(btnEnterMarks);
        btnEnterMarksPnl.add(btnNextStPnl);
        menuLvl1Panel.add(btnEnterMarksPnl,gbcmenu);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent event) 
    {
        if (event.getSource() == enterNameBtnPanel)
        {
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        
        actionSelectStudentsTbl(event.getSource());
                
        if (event.getSource() == enterNameBtnPanel)
        {
            actionSelectStudentsBtn();
        }
        
        if (event.getSource() == btnEnterMarks)
        {
            actionEnterMarks();
        }
        
        if (event.getSource() == btnNextStPnl)
        {
            actionSelectNextSt();
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (event.getSource() == enterNameBtn)
        {
            
        }
        for (int row = 0; row < stuIDpanelArray.size(); row++)
        {
            
            if (event.getSource() == stuIDpanelArray.get(row) ||
                event.getSource() == stuModMarkPanelArray.get(row) ||
                event.getSource() == stuYrAvgPanelArray.get(row) ||
                    
                event.getSource() == stuIDlblArray.get(row) ||
                event.getSource() == stuModMarkLblArray.get(row) ||
                event.getSource() == stuYrAvgLblArray.get(row))    
            {
                if(stuIDpanelArray.get(row).getBackground() == 
                        mainStyle.systemDarkGrey)
                {
                    setTblRowBackground(row, systemLightGrey2);
                }
                else
                {
                    setTblRowBackground(row, mainStyle.systemLightGrey);
                }
            }
        }
        revalidate();
    }

    @Override
    public void mouseExited(MouseEvent event) {
        if (event.getSource() == enterNameBtn)
        {
            
        }
        for (int row = 0; row < stuIDpanelArray.size(); row++)
        {

            if (event.getSource() == stuIDpanelArray.get(row) ||
                event.getSource() == stuModMarkPanelArray.get(row) ||
                event.getSource() == stuYrAvgPanelArray.get(row) ||
                    
                event.getSource() == stuIDlblArray.get(row) ||
                event.getSource() == stuModMarkLblArray.get(row) ||
                event.getSource() == stuYrAvgLblArray.get(row))    
            {
                if (stuIDpanelArray.get(row).getBackground() == systemLightGrey2)
                {
                    setTblRowBackground(row, mainStyle.systemDarkGrey);
                }
                else if (stuIDpanelArray.get(row).getBackground() == mainStyle.systemLightGrey)
                {
                    System.out.print("Set to white");
                    setTblRowBackground(row, Color.white);
                }
            }
        }
    }


    
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //for testing of file only
        ARjFrameForTest jframe = new ARjFrameForTest();
        ARo5inResDataGrid datagrid = new ARo5inResDataGrid();
        jframe.add(datagrid);
        jframe.setVisible(true);
        
        for (int i = 0; i < datagrid.db.studentsID.size(); i++)
        {
           System.out.println(datagrid.db.studentsID.get(i) + " ");
        }
    }

    private class GetPercentageValid extends InputVerifier {
        @Override
        public boolean verify(JComponent input) 
        {
            String textInput = ((JTextField) input).getText();
            try 
            {
                int intInput = Integer.parseInt(textInput); 
                if (intInput < 0 || intInput > 100)
                {
                    input.setBorder(mainStyle.redBorder);
                     mainStyle.createPopUpFrame(
                    "Please enter a number between 1 and 100", 350, 150);
                    return false;
                }
                else
                {
                    input.setBorder(mainStyle.borderDefault);
                    return true;
                }      
            } 
            catch (NumberFormatException e) {
                if (textInput.length() == 0)
                {
                    input.setBorder(mainStyle.borderDefault);
                    return true;
                }
                else
                {
                    input.setBorder(mainStyle.redBorder);
                    mainStyle.createPopUpFrame(
                    "Please enter numerical characters only.", 350, 150);
                    return false;
                }
            }
        }
    }
    private class GetFocus implements FocusListener{
//TODO: non urgent - put components in array at some point to clean up code.
        @Override
        public void focusGained(FocusEvent e) {
            if(e.getSource() == enterNameTxt)
            {
                enterNameTxt.setBorder(mainStyle.borderFocused);
                enterNameTxt.setForeground(mainStyle.systemColor);
                enterNameLbl.setForeground(mainStyle.systemColor);
            }
            else if(e.getSource() == enterNameBtnPanel)
            {
                enterNameBtnPanel.setBorder(mainStyle.borderFocused);
                enterNameBtnLbl.setForeground(mainStyle.systemColor);
                enterNameLbl.setForeground(mainStyle.systemColor);
            }
            else if (e.getSource() == btnEnterMarks)
            {
                btnEnterMarks.setBorder(mainStyle.borderFocused);
                btnEnterMarksLbl.setForeground(mainStyle.systemColor);
            }
            else if(e.getSource() == enterModMarkTxt)
            {
                enterModMarkTxt.setBorder(mainStyle.borderFocused);
                enterModMarkLbl.setForeground(mainStyle.systemColor);
            }
            else if(e.getSource() == btnNextStPnl)
            {
                btnNextStPnl.setBorder(mainStyle.borderFocused);
                btnNextStPnl.setForeground(mainStyle.systemColor);
            }
            else if(e.getSource() == enterYrAvgTxt)
            {
                enterYrAvgTxt.setBorder(mainStyle.borderFocused);
                enterYrAvgLbl.setForeground(mainStyle.systemColor);
            }
            else if(e.getSource() == enterYrAvgTxt)
            {
                enterYrAvgTxt.setBorder(mainStyle.borderFocused);
                enterYrAvgLbl.setForeground(mainStyle.systemColor);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == enterNameTxt)
            {
                enterNameTxt.setBorder(mainStyle.borderDefault);
                enterNameTxt.setForeground(Color.black);
                enterNameLbl.setForeground(Color.black);
            }
            else if(e.getSource() == enterNameBtnPanel)
            {
                enterNameBtnPanel.setBorder(mainStyle.borderDefault);
                enterNameBtnLbl.setForeground(Color.black);
                enterNameLbl.setForeground(Color.black);
            }
            else if (e.getSource() == btnEnterMarks)
            {
                btnEnterMarks.setBorder(mainStyle.borderDefault);
                btnEnterMarksLbl.setForeground(Color.black);
            }
            else if(e.getSource() == btnNextStPnl)
            {
                btnNextStPnl.setBorder(mainStyle.borderDefault);
                btnNextStPnl.setForeground(Color.black);
            }
            else if(e.getSource() == enterModMarkTxt)
            {
                enterModMarkTxt.setBorder(mainStyle.borderDefault);
                enterModMarkLbl.setForeground(Color.black);
            }
            else if(e.getSource() == enterYrAvgTxt)
            {
                enterYrAvgTxt.setBorder(mainStyle.borderDefault);
                enterYrAvgLbl.setForeground(Color.black);
            }
        }
    }
    private class SetKeyActions implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e){}
        @Override
        public void keyPressed(KeyEvent e) {
            
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                if (e.getSource() == btnEnterMarks)
                {
                    actionEnterMarks();
                }  
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        
    }
}
