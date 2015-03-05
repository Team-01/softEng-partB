/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Kit
 */
public class ARoptionsContainer extends JPanel implements ActionListener{
    private GridBagLayout outerLayout;
    private BoxLayout innerLayout;
    
    private GridBagConstraints gbc;
    
    private ARbuttonContainer btnYrAvg1, btnTmAvg2, btnOaAvg3, 
                              btnStCom4, btnInRes5;
  
    private ARo1stAvgDataGrid stAvgDataGrid1;
    private ARo2tmAvgDataGrid tmAvgDataGrid2;
    private ARo3oaAvgDataGrid oaAvgDataGrid3;
    private ARo4stComDataGrid stComDataGrid4;
    private ARo5inResDataGrid inResDataGrid5;
    
    private ARbuttonDataContainer stAvgBtnDataContainer1;
    private ARbuttonDataContainer tmAvgBtnDataContainer2;
    private ARbuttonDataContainer oaAvgBtnDataContainer3;
    private ARbuttonDataContainer stComBtnDataContainer4;
    private ARbuttonDataContainer inResBtnDataContainer5;
    
    /**
     * sets the number of options for arrays to cycle
     */
    int optionInt = 5; 
    
    private JToggleButton[] options = new JToggleButton[optionInt];
    private JToggleButton[] arrowoptions = new JToggleButton[optionInt];
    private JPanel[] datagrids = new JPanel[optionInt];
    private JPanel[] datacontainers = new JPanel[optionInt];
    
    /**
     * these buttons will be used to track which button and arrow pair were last
     * selected so that they can close the option if the same button or arrow
     * are selected
     */
    private JToggleButton lastBtnSelected;
    private JToggleButton lastArrSelected;
    
    public ARoptionsContainer()
    {
        lastBtnSelected = new JToggleButton();
        lastArrSelected = new JToggleButton();
        outerLayout = new GridBagLayout();
        
        gbc = new GridBagConstraints();
        
        JPanel innerGrid = new JPanel();
        innerLayout = new BoxLayout(innerGrid, BoxLayout.Y_AXIS);
        innerGrid.setLayout(innerLayout);
            
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        stAvgBtnDataContainer1 = new ARbuttonDataContainer();
        tmAvgBtnDataContainer2 = new ARbuttonDataContainer();
        oaAvgBtnDataContainer3 = new ARbuttonDataContainer();
        stComBtnDataContainer4 = new ARbuttonDataContainer();
        inResBtnDataContainer5 = new ARbuttonDataContainer();
        
        btnYrAvg1 = new ARbuttonContainer
        ("Year averages and Software Engineering module mark for all students");
        btnTmAvg2 = new ARbuttonContainer
        ("Team averages");
        btnOaAvg3 = new ARbuttonContainer
        ("Overall module average");
        btnStCom4 = new ARbuttonContainer
        ("Compare a student’s results against their team’s results.");
        btnInRes5 = new ARbuttonContainer
        ("Enter results for student(s)"); 
        
        options[0] = btnYrAvg1.button;
        options[1] = btnTmAvg2.button;
        options[2] = btnOaAvg3.button;
        options[3] = btnStCom4.button;
        options[4] = btnInRes5.button;
        
        arrowoptions[0] = stAvgBtnDataContainer1.btnArrowHead;
        arrowoptions[1] = tmAvgBtnDataContainer2.btnArrowHead;
        arrowoptions[2] = oaAvgBtnDataContainer3.btnArrowHead;
        arrowoptions[3] = stComBtnDataContainer4.btnArrowHead;
        arrowoptions[4] = inResBtnDataContainer5.btnArrowHead; 
        
        for (int compNum = 0; compNum<optionInt; compNum++)
        {
            options[compNum].addActionListener(this);
            arrowoptions[compNum].addActionListener(this);
        }
        
        stAvgDataGrid1 = new ARo1stAvgDataGrid();
        tmAvgDataGrid2 = new ARo2tmAvgDataGrid();
        oaAvgDataGrid3 = new ARo3oaAvgDataGrid();
        stComDataGrid4 = new ARo4stComDataGrid();
        inResDataGrid5 = new ARo5inResDataGrid();
   
        stAvgBtnDataContainer1.inner.add(btnYrAvg1);
        tmAvgBtnDataContainer2.inner.add(btnTmAvg2);
        oaAvgBtnDataContainer3.inner.add(btnOaAvg3);
        stComBtnDataContainer4.inner.add(btnStCom4);
        inResBtnDataContainer5.inner.add(btnInRes5);
        innerGrid.setBackground(Color.white);
        innerGrid.add(Box.createRigidArea(new Dimension(0,5)));
        innerGrid.add(stAvgBtnDataContainer1);
        innerGrid.add(Box.createRigidArea(new Dimension(0,3)));
        innerGrid.add(tmAvgBtnDataContainer2);
        innerGrid.add(Box.createRigidArea(new Dimension(0,3)));
        innerGrid.add(oaAvgBtnDataContainer3);
        innerGrid.add(Box.createRigidArea(new Dimension(0,3)));
        innerGrid.add(stComBtnDataContainer4);
        innerGrid.add(Box.createRigidArea(new Dimension(0,3)));
        innerGrid.add(inResBtnDataContainer5);
        innerGrid.add(Box.createRigidArea(new Dimension(0,3)));
        
        datagrids[0] = stAvgDataGrid1;
        datagrids[1] = tmAvgDataGrid2;
        datagrids[2] = oaAvgDataGrid3;
        datagrids[3] = stComDataGrid4;
        datagrids[4] = inResDataGrid5;
        
        datacontainers[0] = stAvgBtnDataContainer1.inner;
        datacontainers[1] = tmAvgBtnDataContainer2.inner;
        datacontainers[2] = oaAvgBtnDataContainer3.inner;
        datacontainers[3] = stComBtnDataContainer4.inner;
        datacontainers[4] = inResBtnDataContainer5.inner; 
        add(innerGrid);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        Object sourceButton = event.getSource();
        for (int compNum=0; compNum < options.length; compNum++)
        {
            if (options[compNum] == sourceButton || arrowoptions[compNum] == sourceButton)
            {
                if (lastBtnSelected == options[compNum] || 
                    lastArrSelected == arrowoptions[compNum])
                {
                    options[compNum].setSelected(false);
                    arrowoptions[compNum].setSelected(false);
                    datacontainers[compNum].remove(datagrids[compNum]);
                    lastBtnSelected = new JToggleButton();
                    lastArrSelected = new JToggleButton();
                }
                
                else if (options[compNum].isSelected() == false || arrowoptions[compNum].isSelected() == false)
                {
                    datacontainers[compNum].add(datagrids[compNum]);
                    arrowoptions[compNum].setSelected(true);
                    options[compNum].setSelected(true);
                    lastBtnSelected = options[compNum];
                    lastArrSelected = arrowoptions[compNum];
                }
            }
            else
            {
                datacontainers[compNum].remove(datagrids[compNum]);
                options[compNum].setSelected(false);
                arrowoptions[compNum].setSelected(false);
            }
        }
        revalidate();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //JFrameForTest jframe = new JFrameForTest();
        ARoptionsContainer optionsContainer = new ARoptionsContainer();
        //jframe.add(optionsContainer);
        //jframe.setVisible(true);
    }
}