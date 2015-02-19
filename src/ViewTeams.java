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
        buildContent();
    }
    
    public void buildContent()
    {
    // Create second tab content
        JPanel p = new JPanel();
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        sp.getVerticalScrollBar().setUnitIncrement(20); // This is required for smooth scrolling
    }
}
