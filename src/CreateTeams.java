import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateTeams {
    
    JPanel p;
    JScrollPane sp; 
    Style mainStyle = new Style();
    
    private JPanel buttonPanel = new JPanel();
    private 
    
    public CreateTeams()
    {
        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        // Add content below...
    }
}
