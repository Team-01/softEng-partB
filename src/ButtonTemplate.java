
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kit
 */
public class ButtonTemplate extends JPanel implements MouseListener
{
    Color systemLightGrey2 = new Color(227, 227, 227);
    
    Style mainStyle = new Style();
   
    GetFocus getFocus;
    
    JLabel btnLabel;
    
    JPanel btn;
    
    FlowLayout btnLayout;
    
    public ButtonTemplate(String btnText) 
    {        
        buildThis(btnText);
    }
    
    public JLabel buildLbl(String lblTxt)
    {
        JLabel menuLabel = new JLabel(lblTxt);
            menuLabel.setFont(mainStyle.fontS);
            return menuLabel;
    }
    
    public void buildThis(String btnText)
    {
        btnLabel = buildLbl(btnText);
        this.setBackground(Color.white);
        btnLayout = new FlowLayout(5,5,2);
        this.setLayout(btnLayout);
        this.setBorder(mainStyle.borderDefault);
        getFocus = new GetFocus();
        this.addFocusListener(getFocus);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.add(btnLabel);
        
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.setBackground(mainStyle.systemLightGrey);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.setBackground(mainStyle.systemDarkGrey);
        this.requestFocus();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        this.setBackground(mainStyle.systemLightGrey);
    }

    @Override
    public void mouseExited(MouseEvent event) {
        this.setBackground(Color.white);
    }

    private class GetFocus implements FocusListener{
//TODO: non urgent - put components in array at some point to clean up code.
        @Override
        public void focusGained(FocusEvent e) {
            {
                setBorder(mainStyle.borderFocused);
                btnLabel.setForeground(mainStyle.systemColor);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            {
                setBorder(mainStyle.borderDefault);
                btnLabel.setForeground(Color.black);
            }
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JPanel btnPanel = new JPanel();
        ButtonTemplate btn = new ButtonTemplate("test button");
        ButtonTemplate btn2 = new ButtonTemplate("test button");
        //for testing of file only
        ARjFrameForTest jframe = new ARjFrameForTest();
        btnPanel.add(btn);
        btnPanel.add(btn2);
        jframe.add(btnPanel);
        jframe.setVisible(true);
        
    }
}
