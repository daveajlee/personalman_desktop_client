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
    
    private static final String FONT_FAMILY = "Arial";
    
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
        ImageIcon logoImage = new ImageIcon(SplashScreen.class.getResource("/images/PersonalManlogo.png"));
        JLabel logoLabel = new JLabel("", logoImage, JLabel.CENTER);
        logoPanel.add(logoLabel);
        centrePanel.add(logoPanel);
        
        //Construct title panel to add to the centre panel.
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("PersonalMan - Management Software");
        titleLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 25));
        titlePanel.add(titleLabel);
        centrePanel.add(titlePanel);
        
        //Construct graphics panel to add to the centre panel.
        JPanel personPanel = new JPanel();
        personPanel.setBackground(Color.WHITE);
        ImageIcon personImage = new ImageIcon(SplashScreen.class.getResource(userInterface.getUserInterfaceMessages().getSplashImage()));
        JLabel personLabel = new JLabel("", personImage, JLabel.CENTER);
        personPanel.add(personLabel);
        centrePanel.add(personPanel);
        
        //Construct loading panel to add to the centre panel.
        JPanel loadingPanel = new JPanel();
        loadingPanel.setBackground(Color.WHITE);
        JLabel loadingLabel = new JLabel(userInterface.getUserInterfaceMessages().getSplashTitle());
        loadingLabel.setFont(new Font(FONT_FAMILY, Font.ITALIC, 15));
        loadingPanel.add(loadingLabel);
        centrePanel.add(loadingPanel);
        
        //Construct copyright panel to add to the centre panel.
        JPanel copyrightPanel = new JPanel();
        copyrightPanel.setBackground(Color.WHITE);
        JLabel copyrightLabel = new JLabel(userInterface.getUserInterfaceMessages().getSplashCopyright());
        copyrightLabel.setFont(new Font(FONT_FAMILY, Font.PLAIN, 10));
        copyrightPanel.add(copyrightLabel);
        centrePanel.add(copyrightPanel);
        
        c.add(centrePanel, BorderLayout.CENTER);
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = getPreferredSize();
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));
        
        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( getPreferredSize() );
        
    }
    
}
