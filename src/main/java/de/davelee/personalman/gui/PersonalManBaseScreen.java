package de.davelee.personalman.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.davelee.personalman.UserInterface;

/**
 * Base screen for all screens in the PersonalMan program. Other screens extend this base screen.
 * @author Dave Lee
 */
public class PersonalManBaseScreen extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4474714835323725521L;
	
	protected UserInterface userInterface;
	protected JPanel screenPanel;
	protected Container container;

	/**
	 * Create a new PersonalManBaseScreen based on the supplied user interface.
	 * @param userInterface a <code>UserInterface</code> object representing the current user interface.
	 */
	public PersonalManBaseScreen ( final UserInterface userInterface ) {
		//Initialise user interface variable.
        this.userInterface = userInterface;
        
        //Initialise GUI with title and close attributes.
        this.setTitle ("PersonalMan - HR Management Software");
        this.setResizable (false);
        this.setDefaultCloseOperation (DO_NOTHING_ON_CLOSE);
        this.setBackground(Color.WHITE);
        userInterface.setCurrentFrame ( this );
        
        //Set image icon.
        Image img = Toolkit.getDefaultToolkit().getImage(AbsenceScreen.class.getResource("/images/PersonalManlogo.png"));
        setIconImage(img);
        
        //Call the Exit method in the UserInterface class if the user hits exit.
        this.addWindowListener ( new WindowAdapter() {
            public void windowClosing ( WindowEvent e ) {
                userInterface.exit();
            }
        });
        
        //Get a container to add things to.
        container = this.getContentPane();
        container.setBackground(Color.WHITE);
        container.add(Box.createRigidArea(new Dimension(0,10))); //Spacer.
        
        //Create a screen panel in box layout to store everything.
        screenPanel = new JPanel();
        screenPanel.setLayout ( new BorderLayout() );
        screenPanel.setBackground(Color.WHITE);
	}
	
	/**
	 * Method to create a file chooser dialog based on the supplied title.
	 * @param title a <code>String</code> with the desired dialog title.
	 * @return a <code>JFileChooser</code> object to display to the user as a dialog.
	 */
	public JFileChooser showFileDialog ( final String title ) {
    	//Create file dialog box.
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle(title);
        //Only display files with xml extension.
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
        fileDialog.setFileFilter(filter);
        //Display file dialog.
        return fileDialog;
    }
	
}
