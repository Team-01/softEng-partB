/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

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
    JToggleButton optionBtn;
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
        
        revalidate();
    }
    
    //public void addOption(Str optionBtn)
    
    public JPanel createBtnJPanel(String btnLabel)
    {
        //TODO: create a new button using the function
        //TODO: 
        optionBtn = createARbtn(btnLabel);
        GridBagLayout btnLayout;
        GridBagConstraints gbcBtnLayout;
        
        JPanel btnJPanel = new JPanel();
        btnLayout = new GridBagLayout();
        
        gbcBtnLayout = new GridBagConstraints();
        gbcBtnLayout.weightx = 1.0;
        gbcBtnLayout.weighty = 1.0;
        gbcBtnLayout.gridx = 0;
        gbcBtnLayout.gridy = 0;
        //gbcBtnLayout.fill = GridBagConstraints.HORIZONTAL;
        btnJPanel.setBackground(Color.white);
        btnJPanel.setLayout(btnLayout);
        btnJPanel.add(optionBtn,gbc);
        
        return btnJPanel;
    }
    
    private JToggleButton createARbtn(String btnLabel)
    {        
        optionBtn = new JToggleButton(btnLabel);
        
        optionBtn.setPreferredSize(new Dimension(500,27));
        optionBtn.setEnabled(true);
        optionBtn.setFocusPainted(false); //rm txt border
        optionBtn.setBorderPainted(false);//rm main border
        optionBtn.setBackground(Color.white);//rm main border
        //optionBtn.setContentAreaFilled(false); //rm bg
        optionBtn.setHorizontalAlignment(SwingConstants.LEFT);
        
        return optionBtn;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ARbuttonDataContainer buttonDataContainer = new ARbuttonDataContainer();
        buttonDataContainer.createBtnJPanel("Example button");
        buttonDataContainer.inner.add(buttonDataContainer.createBtnJPanel("Example button"));
        
        //ARo1stAvgDataGrid datagrid = new ARo1stAvgDataGrid();
        //buttonDataContainer.inner.add(optionExample);
        ARjFrameForTest jframe = new ARjFrameForTest();
        jframe.add(buttonDataContainer);

    }
}
