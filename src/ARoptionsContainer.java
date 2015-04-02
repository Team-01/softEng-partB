import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

/**
 * @author Kit
 * //TODO: Add proper notes
 * //TODO: start on AR5inResDataGrid first
 * //TODO: add mouseover event to button to change shade
 */
public class ARoptionsContainer extends JPanel implements MouseListener
{
    /**
    * An instance of the Style class for accessing system styles
    */
    Style mainStyle = new Style(); 
        
    private GridBagLayout outerLayout;
    private BoxLayout innerLayout;
    
    private JPanel stAvgBtnDataContainer1, tmAvgBtnDataContainer2,
                   oaAvgBtnDataContainer3, stComBtnDataContainer4,
                   inResBtnDataContainer5;
    
    Color systemLightGrey2 = new Color(227, 227, 227);
    
    /**
     * sets the number of options for arrays to cycle
     */
    int optionInt = 5; 
    
    /**
     * array to hold JPanels to hold each option button
     */
    private JPanel[] options = new JPanel[optionInt];
    /**
     * array to hold JToggleButtons for each option.
     */
    private JLabel[] btns = new JLabel[optionInt];
    /**
     * array to hold JToggleButtons to display graphic of arrowhead to go
     * alongside each option button
     */
    private JToggleButton[] arrowBtns = new JToggleButton[optionInt];
    /**
     * 
     */
    private JPanel[] datagrids = new JPanel[optionInt];
    private JPanel[] datacontainers = new JPanel[optionInt];
    
    /**
     * these buttons will be used to track which button and arrow pair were last
     * selected so that they can close the option if the same button or arrow
     * are selected
     */
    private JLabel lastBtnSelected;
    private JToggleButton lastArrSelected;
    
    private GetFocus getFocus;
    
    public ARoptionsContainer()
    {        
        getFocus = new GetFocus();
        lastBtnSelected = new JLabel();
        lastArrSelected = new JToggleButton();
        outerLayout = new GridBagLayout();
        
        JPanel innerGrid = new JPanel();
        innerLayout = new BoxLayout(innerGrid, BoxLayout.Y_AXIS);
        innerGrid.setLayout(innerLayout);
        

        btns[0] = buildBtn
        ("Year averages and Software Engineering module mark for all students");
        btns[1] = buildBtn
        ("Team averages");
        btns[2] = buildBtn
        ("Overall module average");
        btns[3] = buildBtn
        ("Compare a student’s results against their team’s results.");
        btns[4] = buildBtn
        ("Enter results for student(s)");
        
        options[0] = buildBtnJPanel(btns[0]);
        options[1] = buildBtnJPanel(btns[1]);
        options[2] = buildBtnJPanel(btns[2]);
        options[3] = buildBtnJPanel(btns[3]);
        options[4] = buildBtnJPanel(btns[4]); 
        
        datacontainers[0] = buildInnerPanel(options[0]);
        datacontainers[1] = buildInnerPanel(options[1]);
        datacontainers[2] = buildInnerPanel(options[2]);
        datacontainers[3] = buildInnerPanel(options[3]);
        datacontainers[4] = buildInnerPanel(options[4]);
        
        arrowBtns[0] = buildBtnArrow();
        arrowBtns[0].addFocusListener(getFocus);
        arrowBtns[1] = buildBtnArrow();
        arrowBtns[1].addFocusListener(getFocus);
        arrowBtns[2] = buildBtnArrow();
        arrowBtns[2].addFocusListener(getFocus);
        arrowBtns[3] = buildBtnArrow();
        arrowBtns[3].addFocusListener(getFocus);
        arrowBtns[4] = buildBtnArrow(); 
        arrowBtns[4].addFocusListener(getFocus);
        
        stAvgBtnDataContainer1 = buildBtnDataPanel(
                datacontainers[0],
                arrowBtns[0]);
        tmAvgBtnDataContainer2 = buildBtnDataPanel(
                datacontainers[1],
                arrowBtns[1]);
        oaAvgBtnDataContainer3 = buildBtnDataPanel(
                datacontainers[2],
                arrowBtns[2]);
        stComBtnDataContainer4 = buildBtnDataPanel(
                datacontainers[3],
                arrowBtns[3]);
        inResBtnDataContainer5 = buildBtnDataPanel(
                datacontainers[4],
                arrowBtns[4]);
        
        datagrids[0] = new ARo1stAvgDataGrid();
        datagrids[1] = new ARo2tmAvgDataGrid();
        datagrids[2] = new ARo3oaAvgDataGrid();
        datagrids[3] = new ARo4stComDataGrid();
        datagrids[4] = new ARo5inResDataGrid();

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

        add(innerGrid);
    }
    
    public JPanel buildBtnDataPanel( 
            JPanel inner, 
            JToggleButton arrowBtn)
    {
        JPanel btnDataPanel = new JPanel();
        GridBagLayout btnDataPanelLayout = new GridBagLayout();
        btnDataPanel.setLayout(btnDataPanelLayout);
    
        GridBagConstraints gbcbtnDataPanelLayout = new GridBagConstraints();
        
        //gbcbtnDataPanelLayout.weightx = 0;
        gbcbtnDataPanelLayout.weighty = 1;
        gbcbtnDataPanelLayout.gridx = 0;
        gbcbtnDataPanelLayout.gridy = 0;
        gbcbtnDataPanelLayout.anchor = GridBagConstraints.NORTHWEST;
        gbcbtnDataPanelLayout.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel panelArrowBtn = buildArrowBtnPanel();
        btnDataPanel.add(panelArrowBtn, gbcbtnDataPanelLayout);
        panelArrowBtn.add(arrowBtn);
        
        //inner.add(btn);
        //gbcbtnDataPanelLayout.weightx = 0;
        gbcbtnDataPanelLayout.gridx = 1;
        
        btnDataPanel.add(inner,gbcbtnDataPanelLayout);
        btnDataPanel.setBackground(Color.white);
        
        return btnDataPanel;
    }
    
    /**
     * Builds a JPanel to contain and provide a visible border for a 
     * button option and data, which are contained in their own JPanels.
     * TODO: set colour of frame, may need to add colour to style if desired not
     * available
     */
    private JPanel buildInnerPanel(JPanel btnPanel)
    {
        JPanel innerPanel = new JPanel();
        BoxLayout innerPanelLayout = new BoxLayout(innerPanel, BoxLayout.Y_AXIS);
        //FlowLayout innerPanelLayout = new FlowLayout();
        innerPanel.setLayout(innerPanelLayout);
        innerPanel.setBackground(Color.white);
        innerPanel.setBorder(BorderFactory.createLineBorder
                                                    (mainStyle.systemColor, 3));
        innerPanel.add(btnPanel);
        return innerPanel;
    }
    
        public JPanel buildArrowBtnPanel()
    {
        JPanel arrowPanel = new JPanel();
        BoxLayout arrowPanelLayout = new BoxLayout(arrowPanel, BoxLayout.Y_AXIS);
        arrowPanel.setLayout(arrowPanelLayout);
        arrowPanel.setBackground(Color.white);
        //arrowPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        
        return arrowPanel;
    }
    
    /**
     * @param btnLabel used to label the button (uses the buildBtn function
     * to insert a button within the panel)
     * @return 
     * Used to build and return a JPanel containing a button used for one of 
     * the 'Analyse results' options.
     */
    private JPanel buildBtnJPanel(JLabel btn)
    {
        JPanel btnJPanel = new JPanel();
        
        GridBagLayout btnLayout = new GridBagLayout();
        
        GridBagConstraints gbcBtnLayout = new GridBagConstraints();
        
        gbcBtnLayout.weightx = 1.0;
        gbcBtnLayout.weighty = 1.0;
        gbcBtnLayout.gridx = 0;
        gbcBtnLayout.gridy = 0;
        //gbcBtnLayout.anchor = GridBagConstraints.WEST;
        gbcBtnLayout.fill = GridBagConstraints.HORIZONTAL;
        
        btnJPanel.setBackground(Color.white);
        btnJPanel.setLayout(btnLayout);
        btnJPanel.add(btn,gbcBtnLayout);
        
        return btnJPanel;
    }
    
    /**
     * @return 
     * Creates and returns a JToggleButton which shows an arrow designed to be
     * placed next to a button made with the buildBtn function.
     * TODO: look into component class to see if you can draw the triangle in 
     * java instead, this way it can be coloured dynamically
     */
    private JToggleButton buildBtnArrow()
    {
        ImageIcon hArrowHead;
        ImageIcon vArrowHead;
        JToggleButton btnArrowHead;
        
        hArrowHead = new ImageIcon(getClass().getClassLoader().getResource(
                "resources/hArrow.png"));
        vArrowHead = new ImageIcon(getClass().getClassLoader().getResource(
                "resources/vArrow.png"));
        
        btnArrowHead = new JToggleButton(hArrowHead);
        
        btnArrowHead.setSelectedIcon(vArrowHead);
        btnArrowHead.setBackground(Color.white);
        btnArrowHead.setPreferredSize(new Dimension (45,40));
        btnArrowHead.setFocusPainted(false); //rm txt border
        btnArrowHead.setBorderPainted(false);
        btnArrowHead.addMouseListener(this);
        //btnArrowHead.setVerticalAlignment(0);
        btnArrowHead.setContentAreaFilled(false);//rm bg
        
        return btnArrowHead;
    }
    
    /**
     * @param btnLabel desired label for button
     * @return optionBtn
     * Creates and returns a button used for 'Analyse results' options.
     * TODO: alter fonts - use the ones set in the Style class to match program
     */
    private JLabel buildBtn(String btnLabel)
    {        
        JLabel optionBtn = new JLabel(btnLabel);
        //MotionMouseListener mouseOver = new MotionMouseListener();
        optionBtn.setPreferredSize(new Dimension(570,27));
       
        //optionBtn.setEnabled(true);
        //optionBtn.setFocusPainted(false); //rm txt border
        //optionBtn.setBorderPainted(false);//rm main border
        optionBtn.setOpaque(true);
        optionBtn.setBackground(Color.white);
        
        //optionBtn.setContentAreaFilled(false); //rm bg altogether
        optionBtn.setHorizontalAlignment(SwingConstants.LEFT);
        //optionBtn.setRolloverEnabled(true);
        optionBtn.addMouseListener(this);
        //optionBtn.addMouseListener(new btnMouseListener());
        optionBtn.setFont(mainStyle.fontM);
        optionBtn.setForeground(mainStyle.systemExtraDarkGrey);
        return optionBtn;
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Object sourceButton = event.getSource();
        for (int compNum=0; compNum < options.length; compNum++)
        {
            if (btns[compNum] == sourceButton || 
                arrowBtns[compNum] == sourceButton)
            {
                if (lastBtnSelected == btns[compNum] || 
                    lastArrSelected == arrowBtns[compNum])
                {
                    //btns[compNum].setSelected(false);
                    arrowBtns[compNum].setSelected(false);
                    datacontainers[compNum].remove(datagrids[compNum]);
                    btns[compNum].setBackground(Color.white);
                    btns[compNum].setForeground(mainStyle.systemExtraDarkGrey);
                    lastBtnSelected = new JLabel();
                    lastArrSelected = new JToggleButton();
                }
                
                else //if (btns[compNum].isSelected() == false || 
                     //    arrowBtns[compNum].isSelected() == false)
                {
                    datacontainers[compNum].add(datagrids[compNum]);
                    arrowBtns[compNum].setSelected(true);
                    //btns[compNum].setSelected(false);
                    btns[compNum].setBackground(mainStyle.systemDarkGrey);
                    btns[compNum].setForeground(Color.black);
                    lastBtnSelected = btns[compNum];
                    lastArrSelected = arrowBtns[compNum];
                }
            }
            else
            {
                datacontainers[compNum].remove(datagrids[compNum]);
                //btns[compNum].setSelected(false);
                btns[compNum].setBackground(Color.white);
                btns[compNum].setForeground(mainStyle.systemExtraDarkGrey);
                arrowBtns[compNum].setSelected(false);
                
            }
        }
        revalidate();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        for (int compNum=0; compNum < options.length; compNum++)
        {
            if (event.getSource() == btns[compNum] || 
            event.getSource() == arrowBtns[compNum])
            {
                JLabel btn = btns[compNum];
                if(btn.getBackground() == mainStyle.systemDarkGrey)
                {
                    btns[compNum].setBackground(systemLightGrey2);
                }
                else
                {
                    btns[compNum].setBackground(mainStyle.systemLightGrey);
                }
            }
        }
        //revalidate();
    }

    @Override
    public void mouseExited(MouseEvent event) 
    {
        for (int compNum=0; compNum < options.length; compNum++)
        {
            if (event.getSource() == btns[compNum] || 
            event.getSource() == arrowBtns[compNum])
            {
                JLabel btn = btns[compNum];
                if(btn.getBackground() == mainStyle.systemLightGrey)
                {
                    btns[compNum].setBackground(Color.white);
                }
                else if(btn.getBackground() == systemLightGrey2)
                {
                    btns[compNum].setBackground(mainStyle.systemDarkGrey);
                }
            }
        }
        //revalidate();
    }
    
    private class GetFocus implements FocusListener{

        @Override
        public void focusGained(FocusEvent e) {
            for (int compNum=0; compNum < options.length; compNum++)
            {
                if (e.getSource() == arrowBtns[compNum])
                {
                    options[compNum].setBorder(mainStyle.borderFocused);
                    System.out.println("b");
                }
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            for (int compNum=0; compNum < options.length; compNum++)
            {
                if (e.getSource() == arrowBtns[compNum])
                {
                    options[compNum].setBorder(null);
                }
            }
        }
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //to test all components and jpanels on a frame
        ARjFrameForTest jframeAll = new ARjFrameForTest();
        ARoptionsContainer optionsContainer = new ARoptionsContainer();
        jframeAll.add(optionsContainer);
        jframeAll.setVisible(true);
    }
}