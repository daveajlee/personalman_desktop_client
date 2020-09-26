package de.davelee.personalman.gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * This panel creates a form where the user can enter company information such as name etc.
 * The submit buttons are provided by the frame which adds this panel.
 */
public class RegisterCompanyPanel extends JPanel {

    /**
     * Create a new panel and add all necessary components to this panel.
     */
    public RegisterCompanyPanel ( ) {
        this.setLayout(new GridLayout(3,2,5,5));
        this.setBackground(Color.WHITE);

        //add company name
        add(new JLabel("Company Name:", JLabel.CENTER));
        JTextField firstNameField = new JTextField();
        add(firstNameField);

        //add default amount of annual leave
        add(new JLabel("Default Annual Leave", JLabel.CENTER));
        JSpinner annualLeaveSpinner = new JSpinner(new SpinnerNumberModel(25,1,365,1));
        add(annualLeaveSpinner);

        //add base country (for holidays etc.)
        add(new JLabel("Base Country", JLabel.CENTER));
        JComboBox<String> baseCountryBox = new JComboBox<>(java.util.List.of("Germany", "Scotland", "England").toArray(new String[3]));
        add(baseCountryBox);
    }

}
