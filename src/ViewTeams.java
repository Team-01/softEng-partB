import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewTeams {
    
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();
    
    public ViewTeams()
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
