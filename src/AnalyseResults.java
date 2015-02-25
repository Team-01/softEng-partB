import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnalyseResults {
    
    JPanel p;
    JScrollPane sp;
    
    Style mainStyle = new Style();
    
    public AnalyseResults()
    {
        buildContent();
    }
    
    public void buildContent()
    {
    // Create second tab content
        GBTest gbt1 = new GBTest();
        p = gbt1.gridDisplay;
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        sp.getVerticalScrollBar().setUnitIncrement(20); // This is required for smooth scrolling
        
        
    }
}
