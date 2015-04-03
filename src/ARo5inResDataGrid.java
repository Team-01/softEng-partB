/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.awt.BorderLayout.PAGE_START;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.NORTH;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Kit
 */
public class ARo5inResDataGrid extends FindStudentTemplate implements MouseListener
{   
    private boolean enterMarksAdded;
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
    private JPanel dataViewLvl1Panel;
    private GridBagLayout menuLvl1Layout;
    private GridBagLayout dataViewLvl1Layout;
    public ARo5inResDataGrid() 
    {
        createEnterMarksComponents();
    }
    
    public JLabel buildLblMenuLvl1(String lblTxt)
    {
        JLabel menuLabel = new JLabel(lblTxt);
            menuLabel.setFont(mainStyle.fontS);
            return menuLabel;
    }
    
    public JPanel buildLvl1PanelGrid(GridBagLayout layout)
    {
        JPanel panelLvl1gb = new JPanel();
        panelLvl1gb.setBackground(Color.white);
        panelLvl1gb.setLayout(layout);
        return panelLvl1gb;
    }
    
    
    
    public void createEnterMarksComponents()
    {
        setKeyActions = new SetKeyActions();
        menuLvl1Layout = new GridBagLayout();

        gbcmenu = new GridBagConstraints();
            
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
    
    public JTextField buildTxtPercentMenu(int colWidth)
    {
        JTextField txtEnterPercent = buildTxtMenu(colWidth);   
        getPercentValid = new GetPercentageValid();
        txtEnterPercent.setInputVerifier(getPercentValid);
        txtEnterPercent.addKeyListener(setKeyActions);
        return txtEnterPercent;
    }
    
        public JPanel buildPanelCompMenuLvl1()
    {
        JPanel compHolder = new JPanel();
        compHolder.setBackground(Color.white);
        FlowLayout compHolderLayout = new FlowLayout(5,5,2);
        compHolder.setLayout(compHolderLayout);
        getFocus = new ARo5inResDataGrid.GetFocus();
        compHolder.addFocusListener(getFocus);
        return compHolder;
    }
    
    public void actionEnterMarks()
    {
        if (getTxtStuRowIndex() != -1)
            if(getPercentValid.verify(enterModMarkTxt)==true)
            {
                if(getPercentValid.verify(enterYrAvgTxt)==true)
                {
                    setEnterMarksData(getTxtStuRowIndex());
                }
            }
        else if (getTxtStuRowIndex() == -1)
        {
        mainStyle.createPopUpFrame("You haven't selected a student.", 350, 150);
        }
    }
    
    public void actionSelectNextSt()
    {
        
        if (getRowSelected()<db.studentsID.size()-1)
            {
                setRowSelected(getRowSelected()+1);
                String stTxtInput = db.studentsStuEmail.get(getRowSelected());
                setEnterEmailTxt(stTxtInput);
            }
        else
            {
                setRowSelected(0);
                setEnterEmailTxt(db.studentsStuEmail.get(getRowSelected()));
                //actionClickStudent();
            }
        setEnterMarksTxt(getRowSelected());
        setRowSelected(getRowSelected());
        actionSelectStudentsBtn();
    }
   
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
        
        gbcmenu.insets = new Insets(30,25,0,0);
        gbcmenu.gridx = 0;
        gbcmenu.gridy = 2;
        this.menuLvl1Panel.add(enterMarksPanel, gbcmenu);
        
        //gbcmenu.anchor = GridBagConstraints.NORTHEAST;
        gbcmenu.insets = new Insets(15,220,15,0);
        gbcmenu.gridx = 0;
        gbcmenu.gridy = 3;
        btnEnterMarksPnl.add(btnEnterMarks);
        btnEnterMarksPnl.add(btnNextStPnl);
        menuLvl1Panel.add(btnEnterMarksPnl,gbcmenu);
        
    }
    
    public JPanel buildAbtn(String btnTxt)
    {
        JPanel btn = new ButtonTemplate(btnTxt);
        btn.addMouseListener(this);
        btn.addKeyListener(setKeyActions);
        return btn;
    }
    
    public void setEnterMarksData(int stIDindex)
    {
        int arrayPointer = getRowSelected();
            
        if (!enterModMarkTxt.getText().equals(""))
        {
            db.modify("UPDATE students SET moduleMark = '"
                    +enterModMarkTxt.getText()+"' WHERE ID = '"+db.studentsID.get(getRowSelected())+"'");
            setSelectedCellTxt(enterModMarkTxt.getText() + "%", 2);
            db.studentsModuleMark.set(arrayPointer,enterModMarkTxt.getText());
        }
        
        if (!enterYrAvgTxt.getText().equals(""))
        {
            db.modify("UPDATE students SET averageMark = '"
                    +enterYrAvgTxt.getText()+"' WHERE ID = '"+db.studentsID.get(getRowSelected())+"'");
            setSelectedCellTxt(enterYrAvgTxt.getText() + "%", 3);
            revalidate();
            db.studentsAverageMark.set(arrayPointer,enterYrAvgTxt.getText());
            
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
        
    @Override
    public void mouseReleased(MouseEvent event) {                
        if (event.getSource() == enterEmailBtnPanel)
        {
            actionSelectStudentsBtn();
           // if (enterMarksAdded = false)
          //  {
                addEnterMarksComponents();
           //     enterMarksAdded = true;
           // }
            
            setEnterMarksTxt(getRowSelected());
        }
        else if (event.getSource() == btnEnterMarks)
        {
            actionEnterMarks();
        }
        else if (event.getSource() == btnNextStPnl)
        {
            actionSelectNextSt();
        }
        else
        {
            actionClickStudent();
            //if (enterMarksAdded = false)
            //{
                addEnterMarksComponents();
            //    enterMarksAdded = true;
           // }
            setEnterMarksTxt(getRowSelected());
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
      
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
                    btnNextStPnl.requestFocus();
                }  
                else if (e.getSource() == btnNextStPnl)
                {
                    actionSelectNextSt();
                    enterModMarkTxt.requestFocus();
                }  
                else if (e.getSource() == enterModMarkTxt)
                {
                    enterYrAvgTxt.requestFocus();
                }
                else if (e.getSource() == enterYrAvgTxt)
                {
                    btnEnterMarks.requestFocus();
                }  
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
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

            if (e.getSource() == btnEnterMarks)
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

    
    public static void main(String[] args) {
        //for testing of file only
        ARjFrameForTest jframe = new ARjFrameForTest();
        ARo5inResDataGrid datagrid = new ARo5inResDataGrid();
        jframe.add(datagrid);
        jframe.setVisible(true);
    }
}
