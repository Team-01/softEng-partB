import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;


public class Welcome
{
    JPanel p;
    JScrollPane sp;
    Style mainStyle = new Style();
    
    public Welcome(SE se)
    {
        // Make main class panel and make it a scrollpane
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbcWelcome = new GridBagConstraints();
        p.setBackground(Color.white);
        p.setBorder(mainStyle.border20);
        sp = new JScrollPane(p);
        sp.setBorder(mainStyle.borderScroll);
        mainStyle.smoothScroll(sp);
        
        // Create main title panel + label
        JPanel pTitle = new JPanel(new BorderLayout());
        pTitle.setBackground(Color.white);
        JLabel lTitle = new JLabel("Team Generator");
        lTitle.setForeground(mainStyle.systemColor);
        lTitle.setFont(mainStyle.fontXL);
        lTitle.setBorder(mainStyle.borderCustom(20, 0, 0, 0));
        pTitle.add(lTitle);
        gbcWelcome.gridy = 0;
        p.add(pTitle, gbcWelcome);
        
        // Create subtitle panel + label
        JPanel pSubTitle = new JPanel(new BorderLayout());
        pSubTitle.setBorder(mainStyle.borderCustom(5, 0, 40, 0));
        pSubTitle.setBackground(Color.white);
        JLabel lSubTitle = new JLabel("Allocate your students into well-balanced teams.");
        lSubTitle.setForeground(mainStyle.systemExtraDarkGrey);
        lSubTitle.setFont(mainStyle.fontL);
        pSubTitle.add(lSubTitle);
        gbcWelcome.gridy = 1;
        p.add(pSubTitle, gbcWelcome);
        
        
        // Create string array of all main features
        String fM1, fM2, fM3, fM4;
        fM1 = "Questionnaire / Test"; fM2 = "Create Teams"; fM3 = "View Teams"; fM4 = "Analyse Results";
        String[] fM = {fM1, fM2, fM3, fM4};
        
        // Create string array of all main features descriptions
        String fM1D, fM2D, fM3D, fM4D;
        fM1D = "Students answer a questionnaire (and a test if they have programming experience.)"; 
        fM2D = "The system uses the questionnaire & test results to allocate well-balanced teams."; 
        fM3D = "Once team allocation is confirmed, you can view the members of each team."; 
        fM4D = "You can analyse the team allocation against students' marks.";
        String[] fMD = {fM1D, fM2D, fM3D, fM4D};
        
        // Create string array of all extra features
        String fE1, fE2, fE3;
        fE1 = "Manage Questions"; fE2 = "Manage Students"; fE3 = "Settings";
        String[] fE = {fE1, fE2, fE3};
        
        // Create string array of all extra features descriptions
        String fE1D, fE2D, fE3D;
        fE1D = "View and modify all questions included in the questionnaire."; 
        fE2D = "View details about all students and delete a student if required."; 
        fE3D = "Manage system settings, such as changing colour theme or resetting the system.";
        String[] fED = {fE1D, fE2D, fE3D};
        
        // Create all features panel
        JPanel pFeatures = new JPanel(new GridBagLayout());
        GridBagConstraints gbcFeatures = new GridBagConstraints();
        gbcFeatures.anchor = GridBagConstraints.NORTH;
        pFeatures.setBackground(Color.white);
        gbcWelcome.gridy = 2;
        p.add(pFeatures, gbcWelcome);
        
        // Create main features panel and content
        JPanel pFeaturesMain = new JPanel(new GridLayout(0, 1));
        pFeaturesMain.setBackground(Color.white);
        pFeaturesMain.setBorder(mainStyle.borderCustom(10, 30, 20, 20));
        JPanel pFeaturesMainTitle = new JPanel();
        pFeaturesMainTitle.setBackground(mainStyle.systemExtraLightGrey);
        JLabel lFeaturesMainTitle = new JLabel("Main Features");
        lFeaturesMainTitle.setBorder(mainStyle.borderCustom(10, 10, 10, 10));
        lFeaturesMainTitle.setFont(mainStyle.fontM);
        lFeaturesMainTitle.setForeground(mainStyle.systemExtraDarkGrey);
        pFeaturesMainTitle.add(lFeaturesMainTitle);
        pFeaturesMain.add(pFeaturesMainTitle);
        gbcFeatures.gridx=0;
        pFeatures.add(pFeaturesMain, gbcFeatures);
        int fMDCount = 0; //used to match feature description with feature title
        for (String f:fM)
        {
            JLabel lFeatureName = new JLabel(f);
            JLabel lFeatureDesc = new JLabel(fMD[fMDCount]);
            lFeatureName.setForeground(mainStyle.systemColor);
            lFeatureDesc.setForeground(mainStyle.systemDarkGrey);
            lFeatureName.setFont(mainStyle.fontM);
            lFeatureDesc.setFont(mainStyle.fontS);
            lFeatureName.setBorder(mainStyle.borderCustom(0, 10, 0, 10));
            lFeatureDesc.setBorder(mainStyle.borderCustom(-20, 10, 15, 10));
            pFeaturesMain.add(lFeatureName);
            pFeaturesMain.add(lFeatureDesc);
            fMDCount++;
        }
        
         // Create extra features panel and content
        JPanel pFeaturesExtra = new JPanel(new GridLayout(0, 1));
        pFeaturesExtra.setBackground(Color.white);
        pFeaturesExtra.setBorder(mainStyle.borderCustom(10, 20, 20, 30));
        JPanel pFeaturesExtraTitle = new JPanel();
        pFeaturesExtraTitle.setBackground(mainStyle.systemExtraLightGrey);
        JLabel lFeaturesExtraTitle = new JLabel("Extra Features");
        lFeaturesExtraTitle.setBorder(mainStyle.borderCustom(10, 10, 10, 10));
        lFeaturesExtraTitle.setFont(mainStyle.fontM);
        lFeaturesExtraTitle.setForeground(mainStyle.systemExtraDarkGrey);
        pFeaturesExtraTitle.add(lFeaturesExtraTitle);
        pFeaturesExtra.add(pFeaturesExtraTitle);
        gbcFeatures.gridx=1;
        pFeatures.add(pFeaturesExtra, gbcFeatures);
        int fEDCount = 0; //used to match feature description with feature title
        for (String f:fE)
        {
            JLabel lFeatureExtraName = new JLabel(f);
            JLabel lFeatureExtraDesc = new JLabel(fED[fEDCount]);
            lFeatureExtraName.setForeground(mainStyle.systemColor);
            lFeatureExtraDesc.setForeground(mainStyle.systemDarkGrey);
            lFeatureExtraName.setFont(mainStyle.fontM);
            lFeatureExtraDesc.setFont(mainStyle.fontS);
            lFeatureExtraName.setBorder(mainStyle.borderCustom(0, 10, 0, 10));
            lFeatureExtraDesc.setBorder(mainStyle.borderCustom(-20, 10, 15, 10));
            pFeaturesExtra.add(lFeatureExtraName);
            pFeaturesExtra.add(lFeatureExtraDesc);
            fEDCount++;
        }
        
        // Make page footer
        JPanel pFooter = new JPanel();
        pFooter.setBackground(Color.white);
        JLabel lFooter = new JLabel("Team Generator was developed by Team 1 on MSc Computing at Cardiff University, 2015.");
        lFooter.setForeground(mainStyle.systemColor);
        lFooter.setFont(mainStyle.fontS);
        pFooter.add(lFooter);
        gbcWelcome.gridy = 3;
        p.add(pFooter, gbcWelcome);
    }
}
