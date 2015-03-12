/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

/**
 *
 * @author Kit
 */
public class ARbuttonContainer extends JPanel{
    JToggleButton button;
    GridBagLayout layout;
    GridBagConstraints gbc;
    
    public ARbuttonContainer(String buttonLabel)
    {
        setBackground(Color.white);
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        setLayout(layout);
        button = new JToggleButton(buttonLabel);
        button.setPreferredSize(new Dimension(500,27));
        add(button,gbc);
        button.setEnabled(true);
        button.setFocusPainted(false); //rm txt border
        button.setBorderPainted(false);//rm main border
        button.setContentAreaFilled(false); //rm bg
        button.setHorizontalAlignment(SwingConstants.LEFT);

    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        ARbuttonContainer optionExample = new ARbuttonContainer("Option example");
        //JFrameForTest jframe = new JFrameForTest();
        //jframe.add(optionExample);
    }
}
