package de.davelee.personalman.gui;

//Import the Java GUI packages.
import java.awt.*;

import javax.swing.*;

import de.davelee.personalman.UserInterface;

/**
 * Splash screen for the PersonalMan program.
 * @author Dave Lee.
 */
public class SplashScreen extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    /**
     * Create a new splash screen.
     * @param userInterface a <code>UserInterface</code> object with the current user interface.
     */
    public SplashScreen ( final UserInterface userInterface ) {
        
        //Initialise GUI with resizable, title and decorate methods.
        this.setTitle ("PersonalMan - Management Software");
        this.setResizable (true);
        this.setUndecorated(true);
        
        //Set image icon.
        Image img = Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/images/PersonalManlogo.png"));
        setIconImage(img);
        
        //Get a container to add things to.
        Container c = this.getContentPane();
        
        //Construct centre panel with box layout to display all components.
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout ( new BoxLayout ( centrePanel, BoxLayout.PAGE_AXIS ) );
        centrePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,1), BorderFactory.createEmptyBorder(5,5,5,5)));
        centrePanel.add(Box.createRigidArea(new Dimension(0,10))); //Spacer.
        centrePanel.setBackground(Color.WHITE);
        
        //Construct logo panel to add to the centre panel.
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        ImageDisplay logoDisplay = new ImageDisplay("images/personalmanlogo.png", 0, 0);
        logoDisplay.setSize(984,493);
        logoDisplay.setBackground(Color.WHITE);
        logoPanel.add(logoDisplay);
        centrePanel.add(logoPanel);

        //Construct loading panel to add to the centre panel.
        JPanel loadingPanel = new JPanel();
        loadingPanel.setBackground(Color.WHITE);
        JLabel loadingLabel = new JLabel(userInterface.getUserInterfaceMessages().getSplashTitle());
        loadingLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        loadingPanel.add(loadingLabel);
        centrePanel.add(loadingPanel);

        //Construct copyright panel to add to the centre panel.
        JPanel copyrightPanel = new JPanel();
        copyrightPanel.setBackground(Color.WHITE);
        JLabel copyrightLabel = new JLabel(userInterface.getUserInterfaceMessages().getSplashCopyright());
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        copyrightPanel.add(copyrightLabel);
        centrePanel.add(copyrightPanel);

        c.add(centrePanel, BorderLayout.CENTER);
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = getPreferredSize();
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));
        
        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( getPreferredSize() );
        
    }
    
}
