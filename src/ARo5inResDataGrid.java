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
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.*;
import static java.awt.FlowLayout.CENTER;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author Kit
 */
public class ARo5inResDataGrid extends JPanel implements MouseListener
{
    
    private GridBagLayout layout;
    private ARData data;
    SQLite db = new SQLite();; // Create an instance of SQLite class for working on database
    private JLabel enterNameLbl;
    private JPanel enterNameBtnPanel;
    private JLabel enterNameBtn;
    private JPanel enterNameTxtPanel;
    private JTextField enterNameTxt;
    private GridBagConstraints gbc;
    private JLabel stFoundLbl;
    Style mainStyle = new Style();
    ArrayList<String> stuID;
   
    public ARo5inResDataGrid() 
    {
        stuID = db.studentsID;
        
         
        layout = new GridBagLayout();
        this.setLayout(layout);
        
        this.setBackground(Color.white);
        gbc = new GridBagConstraints();
        
        /**
         * create label to instruct tutor to enter student name
         */
        data = new ARData();
        enterNameLbl = new JLabel("Enter student name:");
        enterNameLbl.setFont(mainStyle.fontS);
        enterNameLbl.setHorizontalAlignment(SwingConstants.LEFT);
        
        /**
        * create JPanel to add enterNameTxt to
        */
        enterNameTxtPanel = new JPanel();
        FlowLayout txtLayout = new FlowLayout(CENTER,10,2);
        enterNameTxtPanel.setLayout(txtLayout);
        enterNameTxtPanel.setBackground(Color.white);
        
        /**
         * Create text box to enter student name
         */
        enterNameTxt = new JTextField(20);
        enterNameTxt.setFont(mainStyle.fontS);
        enterNameTxt.setHorizontalAlignment(SwingConstants.LEFT);
        
        /**
         * create JPanel to add enterNameBtn to
         */
        enterNameBtnPanel = new JPanel();
        FlowLayout buttonLayout = new FlowLayout(CENTER,10,2);
        enterNameBtnPanel.setLayout(buttonLayout);
        //enterNameBtnPanel.addMouseListener(this);
        enterNameBtnPanel.setBackground(Color.white);
        enterNameBtnPanel.setBorder(BorderFactory.createLineBorder
                                                    (mainStyle.systemColor, 2));
        
        /**
         * create button to JPanel(uses JLabel) to submit student name to database
         */        
        enterNameBtn = new JLabel("Find");
        enterNameBtn.setFont(mainStyle.fontS);
        enterNameBtn.setHorizontalAlignment(SwingConstants.LEFT);
        enterNameBtn.addMouseListener(this);
        
        /**
         * adds label instructing user to enter student name to grid
         */
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(15,70,0,0);
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridx = 1;
	gbc.gridy = 1;
        gbc.gridwidth=1;
        this.add(enterNameLbl,gbc);
        
        enterNameTxtPanel.add(enterNameTxt);
        /**
         * adds text field for user to enter student name to grid
         */
        gbc.insets = new Insets(15,130,15,0);
        gbc.gridx = 1;
	gbc.gridy = 2;
        this.add(enterNameTxtPanel,gbc);
        
        /**
         * adds button (to submit student name) to JPanel
         */
        enterNameBtnPanel.add(enterNameBtn);
        
        /**
         * adds panel for button to grid
         */
        gbc.insets = new Insets(15,0,15,90);
        gbc.gridx = 3;
        this.add(enterNameBtnPanel,gbc);
    }
    
    public void addStFoundLbl(boolean found)
    {
        stFoundLbl = new JLabel();
        stFoundLbl.setFont(mainStyle.fontS);
        String lblText;
        if(found == true)
        {
            lblText = "Student found";
            stFoundLbl.setForeground(mainStyle.systemColor);
        }
        else
        {
            lblText = "Student could not be found. Try again.";
            stFoundLbl.setForeground(Color.red);
        }
        
        stFoundLbl.setText(lblText);
        gbc.insets = new Insets(15,70,0,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(stFoundLbl,gbc);
        revalidate();
    }
       // public void addEnterMarksComponents()
       // {
        //EnterModMarkLbl = new JLabel();
        //EnterModMarkTxt = new JLabel();
       // EnterYrAvgLbl = new JLabel();
       // EnterYrAvgTxt
        //SubmitMarksBtn
       // stFoundLbl.setFont(mainStyle.fontS);
       // String lblText;
        //if(found == true)
       // {
      //      lblText = "Student found";
      //      stFoundLbl.setForeground(mainStyle.systemColor);
       // }
      //  else
      //  {
     //      lblText = "Student could not be found. Try again.";
      //      stFoundLbl.setForeground(Color.red);
       // }
        
     //   stFoundLbl.setText(lblText);
     //   gbc.insets = new Insets(15,70,0,0);
     //   gbc.gridx = 1;
    //   gbc.gridy = 3;
     //   this.add(stFoundLbl,gbc);
     //   revalidate();
      //  }
    
    @Override
    public void mouseClicked(MouseEvent event) 
    {
        if (event.getSource() == enterNameBtn)
        {
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getSource() == enterNameBtn)
        {
            /** TODO: remove studentfound/notfound label object;
            /**
             * TODO: for loop goes here to cycle through array list of student 
             * names and see if the name is included. If it is, then student
             * found label is added (TODO: method to add st found label). If it
             * is not found student not found returned
             * (TODO: method to add st not found label);
             */
            boolean found = true;
            addStFoundLbl(found);
            //stFoundBool = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (event.getSource() == enterNameBtn)
        {
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (event.getSource() == enterNameBtn)
        {
            
        }
    }

    @Override
    public void mouseExited(MouseEvent event) {
        if (event.getSource() == enterNameBtn)
        {
            
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
        
        //for (int i = 0; i < datagrid.stuID.size(); i++)
        //{
        //    System.out.println(datagrid.stuID.get(i) + " ");
        //}
    }


}
