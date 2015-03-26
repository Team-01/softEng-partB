import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 *
 * @author Kit
 */
public class TableTemplate extends JPanel
{
    private JLabel stFoundLbl = new JLabel();

    private GridBagConstraints gbc;
    private GridBagLayout tblLayout;
    private GridBagLayout dataViewLvl1Layout;

    Color systemLightGrey2 = new Color(227, 227, 227);
    
    Style mainStyle = new Style();
   
    public TableTemplate(ArrayList<ArrayList> stDataArrays, boolean selectable) 
    {
        this.setLayout(tblLayout);
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridx = 0;
	gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < stDataArrays.size(); i++)
        {
            ArrayList nextArray = stDataArrays.get(i);
            for(int i2 = 0; i2 < nextArray.size(); i2++)
            {
                String cellStr = nextArray.get(i2).toString();
                JLabel cellLbl = new JLabel(cellStr);
                JPanel cellPnl = buildTblLblPanel(cellLbl, selectable);
                
                if (nextArray.get(i2) != null)
                {
                    cellLbl.setText(cellStr);
                }
                else
                {
                    cellLbl.setText("no data");
                }
                this.add(cellPnl);
                gbc.gridy++;
            }
            gbc.gridx++;
            revalidate();
        }
        
    }      
    
    public JPanel buildTblLblPanel(JLabel lbl, Boolean selectable)
    {
        JPanel tblLblPanel = new JPanel();

        tblLblPanel.setBackground(Color.white);
        tblLblPanel.setBorder(BorderFactory.createLineBorder
                                                (mainStyle.systemLightGrey, 1));
        if (selectable == true)
        {
           // tblLblPanel.addMouseListener(this);
         //   lbl.addMouseListener(this);
            
        }
        tblLblPanel.add(lbl);
        return tblLblPanel;
    }
    
 
    public static void main(String[] args)
    {
        SQLite db = new SQLite(); // Create an instance of SQLite class for working on database
    }
}