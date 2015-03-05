/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

/**
 *
 * @author Kit
 */
public class ARbuttonDataContainer extends JPanel{
    JPanel inner;
    JPanel arrowBox;
    BoxLayout innerLayout;
    GridBagLayout outerLayout;
    GridBagConstraints gbc;
    BoxLayout arrowBoxLayout;
    ImageIcon hArrowHead;
    ImageIcon vArrowHead;
    JToggleButton btnArrowHead;
    public ARbuttonDataContainer()
    {
        inner = new JPanel();
        arrowBox = new JPanel();
        setBackground(Color.white);
        inner.setBackground(Color.white);
        arrowBox.setBackground(Color.white);
        outerLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.setLayout(outerLayout);
        arrowBoxLayout = new BoxLayout(arrowBox, BoxLayout.Y_AXIS);
        arrowBox.setLayout(arrowBoxLayout);
        //arrowBox.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        
        innerLayout = new BoxLayout(inner, BoxLayout.Y_AXIS);
        inner.setLayout(innerLayout);
        inner.setBackground(Color.red);
        inner.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        
        hArrowHead = new ImageIcon(getClass().getClassLoader().getResource(
                "resources/hArrow.png"));
        vArrowHead = new ImageIcon(getClass().getClassLoader().getResource(
                "resources/vArrow.png"));
        int innerH = inner.getY();
        btnArrowHead= new JToggleButton(hArrowHead);
        btnArrowHead.setSelectedIcon(vArrowHead);
        //btnArrowHead.setBackground(Color.white);
        btnArrowHead.setPreferredSize(new Dimension (45,40));
        btnArrowHead.setFocusPainted(false); //rm txt border
        btnArrowHead.setBorderPainted(false);
        btnArrowHead.setContentAreaFilled(false);//rm bg
        this.add(arrowBox, gbc);
        
        arrowBox.add(btnArrowHead);
        this.add(inner);
        

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ARbuttonContainer optionExample = new ARbuttonContainer("Example button");
        ARbuttonContainer optionExample2 = new ARbuttonContainer("Example button2");
        ARbuttonDataContainer buttonDataContainer = new ARbuttonDataContainer();
        ARo1stAvgDataGrid datagrid = new ARo1stAvgDataGrid();
        //buttonDataContainer.add(inner);
        buttonDataContainer.inner.add(optionExample);
        buttonDataContainer.inner.add(datagrid);
        //datagrid.setVisible(false);
        //buttonDataContainer.add(optionExample2, BorderLayout.SOUTH);
        //JFrameForTest jframe = new JFrameForTest();
        //jframe.add(buttonDataContainer);
    }
}
