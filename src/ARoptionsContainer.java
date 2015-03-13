import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

/**
 * @author Kit
 */
public class ARoptionsContainer extends JPanel implements ActionListener{
    private GridBagLayout outerLayout;
    private BoxLayout innerLayout;
 
    private ARo1stAvgDataGrid stAvgDataGrid1;
    private ARo2tmAvgDataGrid tmAvgDataGrid2;
    private ARo3oaAvgDataGrid oaAvgDataGrid3;
    private ARo4stComDataGrid stComDataGrid4;
    private ARo5inResDataGrid inResDataGrid5;
    
    private JPanel stAvgBtnDataContainer1;
    private JPanel tmAvgBtnDataContainer2;
    private JPanel oaAvgBtnDataContainer3;
    private JPanel stComBtnDataContainer4;
    private JPanel inResBtnDataContainer5;
    
    /**
     * sets the number of options for arrays to cycle
     */
    int optionInt = 5; 
    
    private JPanel[] options = new JPanel[optionInt];
    private JToggleButton[] btns = new JToggleButton[optionInt];
    private JToggleButton[] arrowBtns = new JToggleButton[optionInt];
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
        arrowBtns[1] = buildBtnArrow();
        arrowBtns[2] = buildBtnArrow();
        arrowBtns[3] = buildBtnArrow();
        arrowBtns[4] = buildBtnArrow(); 
        
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
        
        stAvgDataGrid1 = new ARo1stAvgDataGrid();
        tmAvgDataGrid2 = new ARo2tmAvgDataGrid();
        oaAvgDataGrid3 = new ARo3oaAvgDataGrid();
        stComDataGrid4 = new ARo4stComDataGrid();
        inResDataGrid5 = new ARo5inResDataGrid();

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

        add(innerGrid);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        Object sourceButton = event.getSource();
        for (int compNum=0; compNum < options.length; compNum++)
        {
            if (btns[compNum] == sourceButton || 
                arrowBtns[compNum] == sourceButton)
            {
                if (lastBtnSelected == btns[compNum] || 
                    lastArrSelected == arrowBtns[compNum])
                {
                    btns[compNum].setSelected(false);
                    arrowBtns[compNum].setSelected(false);
                    datacontainers[compNum].remove(datagrids[compNum]);
                    lastBtnSelected = new JToggleButton();
                    lastArrSelected = new JToggleButton();
                }
                
                else if (btns[compNum].isSelected() == false || 
                         arrowBtns[compNum].isSelected() == false)
                {
                    datacontainers[compNum].add(datagrids[compNum]);
                    arrowBtns[compNum].setSelected(true);
                    btns[compNum].setSelected(true);
                    lastBtnSelected = btns[compNum];
                    lastArrSelected = arrowBtns[compNum];
                }
            }
            else
            {
                datacontainers[compNum].remove(datagrids[compNum]);
                btns[compNum].setSelected(false);
                arrowBtns[compNum].setSelected(false);
            }
        }
        revalidate();
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
     */
    public JPanel buildInnerPanel(JPanel btnPanel)
    {
        JPanel innerPanel = new JPanel();
        BoxLayout innerPanelLayout = new BoxLayout(innerPanel, BoxLayout.Y_AXIS);
        //FlowLayout innerPanelLayout = new FlowLayout();
        innerPanel.setLayout(innerPanelLayout);
        innerPanel.setBackground(Color.white);
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
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
    public JPanel buildBtnJPanel(JToggleButton btn)
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
        btnArrowHead.addActionListener(this);
        //btnArrowHead.setVerticalAlignment(0);
        btnArrowHead.setContentAreaFilled(false);//rm bg
        
        return btnArrowHead;
    }
    
    /**
     * @param btnLabel desired label for button
     * @return optionBtn
     * Creates and returns a button used for 'Analyse results' options.
     */
    private JToggleButton buildBtn(String btnLabel)
    {        
        JToggleButton optionBtn = new JToggleButton(btnLabel);
        
        optionBtn.setPreferredSize(new Dimension(500,27));
        
        optionBtn.setEnabled(true);
        optionBtn.setFocusPainted(false); //rm txt border
        optionBtn.setBorderPainted(false);//rm main border
        optionBtn.setBackground(Color.white);
        //optionBtn.setContentAreaFilled(false); //rm bg altogether
        optionBtn.setHorizontalAlignment(SwingConstants.LEFT);
        //optionBtn.setRolloverEnabled(true);
        optionBtn.addActionListener(this);
        return optionBtn;
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
        /**
        //to test buildBtn method
        ARjFrameForTest jframeBtn = new ARjFrameForTest();
        JPanel btn = optionsContainer.buildBtnJPanel(optionsContainer.buildBtn("whatever"));
        jframeBtn.add(btn);
        //jframeBtn.setVisible(true);
        
        //to test buildInnerPanel
        ARjFrameForTest jframeInnerBtn = new ARjFrameForTest();
        JPanel innerPanel = optionsContainer.buildInnerPanel(optionsContainer.buildBtnJPanel(optionsContainer.buildBtn("whatever")));
        innerPanel.add(btn);
        ARo1stAvgDataGrid stAvgDataGrid1 = new ARo1stAvgDataGrid();
        innerPanel.add(stAvgDataGrid1);
        jframeInnerBtn.add(innerPanel);
        jframeInnerBtn.setVisible(true);
        
        //to test buildBtnJPanel
        ARjFrameForTest jframeBtnDataContainer = new ARjFrameForTest();
        JToggleButton button1 = optionsContainer.buildBtn("whatever");
        JToggleButton arrowBtn = optionsContainer.buildBtnArrow();
        JPanel btnDataPanel = optionsContainer.buildBtnDataPanel(innerPanel, arrowBtn);
        jframeBtnDataContainer.add(btnDataPanel);
        jframeBtnDataContainer.setVisible(true);
        **/
    }
}