/*
 Dinesh Sainath
 1025287
 */
package eStoreSearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 * Setting up GUI using Swing and awt packages for eStoreSearch
 * MainEStoreSearch class contains all the necessary layout and formatting grids
 * @author Dinesh Sainath
 */
public class MainEStoreSearch extends javax.swing.JFrame {    //declaring frames, panels and text fields in class to have larger scope

    JPanel PublisherLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JPanel makerLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JPanel currentPanel = new JPanel();

    JFormattedTextField IDField = new JFormattedTextField();

    JTextField DescriptionField = new JTextField();

    JTextField PriceField = new JTextField();

    JFormattedTextField YearField = new JFormattedTextField();

    JTextField AuthorsField = new JTextField();

    JTextField PublisherField = new JTextField();

    JTextField makerField = new JTextField();

    JTextArea MessagesTa = new JTextArea(3, 30);

    JFormattedTextField StartYearField = new JFormattedTextField();

    JFormattedTextField EndYearField = new JFormattedTextField();

    JFrame current;

    JPanel Table = new JPanel(new GridLayout(7, 1, 10, 10));

    JPanel ProductIDLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JPanel DescriptionLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JPanel PriceLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JPanel AuthorsLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    String[] Types = {"book", "electronic"};
    JComboBox TypeCombo = new JComboBox(Types);

    /**
     * For loading data from the text file
     * @throws HeadlessException
     */

    public MainEStoreSearch() throws HeadlessException {
        eStoreSearch.loadFromFile("DATA.txt");                 // to load the data from a text file
        initComponents();
        pack();
    }

    /**
     * initializes components for the layout
     */

    public void initComponents() {
        this.setLayout(new BorderLayout());
        this.setTitle("eStoreSearch");                          // setting up frame
        this.setPreferredSize(new Dimension(500, 700));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                eStoreSearch.saveToFile("DATA.txt");
            }
        });
        String[] commands = {"Commands", "add", "search", "quit"};           // drop down menu
        JComboBox comboBox = new JComboBox(commands);
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch ((String) e.getItem()) {                           // switch case when user selects the drop down option
                    case "Commands":
                        Commands();
                        break;
                    case "add":
                        add();
                        break;
                    case "search":
                        search();
                        break;
                    case "quit":
                        eStoreSearch.saveToFile("DATA.txt");
                        current.dispose();
                }
            }
        });
        this.add(comboBox, BorderLayout.NORTH);
        this.add(this.currentPanel, BorderLayout.CENTER);
        Commands();
    }
 
    /**
     * Drop down menu 1 - Add records(book/electronic)
     */
    public void add() {                                         // removes exisitng layout and sets up new layout to add records
        this.currentPanel.removeAll();
        this.currentPanel.setLayout(new BoxLayout(this.currentPanel, BoxLayout.Y_AXIS));
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        centerPanel.add(new JLabel("Adding a product"), BorderLayout.NORTH);
        centerPanel.add(Table);
        JPanel TypeLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        TypeLabel.add(new JLabel("Type:"));
        TypeLabel.add(TypeCombo);
        Table.add(TypeLabel);                                   // set up text fields for all user input

        ProductIDLabel.add(new JLabel("ProductID:"));
        try {
            IDField = new JFormattedTextField(new MaskFormatter("######"));
            IDField.setColumns(6);

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Wrong Format entered in ID must be 6 digits", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ProductIDLabel.add(IDField);
        Table.add(ProductIDLabel);

        DescriptionLabel.add(new JLabel("Description:"));
        DescriptionField.setColumns(12);
        DescriptionLabel.add(DescriptionField);
        Table.add(DescriptionLabel);

        PriceLabel.add(new JLabel("Price:"));
        PriceField.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent keyEvent) {
                try {
                    Integer.parseInt(PriceField.getText());
                } catch (Exception e) {
                    PriceField.setText("");
                }
            }

            public void keyReleased(KeyEvent keyEvent) {

                try {
                    Integer.parseInt(PriceField.getText());
                } catch (Exception e) {
                    PriceField.setText("");
                }
            }

            public void keyTyped(KeyEvent keyEvent) {
                try {
                    Integer.parseInt(PriceField.getText());
                } catch (Exception e) {
                    PriceField.setText("");
                }
            }
        });
        PriceField.setColumns(6);
        PriceLabel.add(PriceField);
        Table.add(PriceLabel);

        JPanel YearLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        YearLabel.add(new JLabel("Year:"));
        try {
            YearField = new JFormattedTextField(new MaskFormatter("####"));
            YearField.setColumns(4);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Year must be of 4 digits", "Error", JOptionPane.ERROR_MESSAGE);
        }
        YearLabel.add(YearField);
        Table.add(YearLabel);

        AuthorsLabel.add(new JLabel("Authors:"));
        AuthorsField.setColumns(12);
        AuthorsLabel.add(AuthorsField);
        Table.add(AuthorsLabel);

        PublisherLabel.add(new JLabel("Publisher:"));
        PublisherField.setColumns(12);
        PublisherLabel.add(PublisherField);
        Table.add(PublisherLabel);

        makerLabel.add(new JLabel("Maker:"));
        makerField.setColumns(12);
        makerLabel.add(makerField);

        this.current = this;

        TypeCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch ((String) e.getItem()) {
                    case "book":                            // for product type - book and electronic
                        Table.setLayout(new GridLayout(7, 1, 10, 10));
                        Table.remove(makerLabel);
                        Table.add(PublisherLabel);
                        Table.add(AuthorsLabel);
                        Table.repaint();
                        current.validate();
                        break;
                    case "electronic":
                        Table.setLayout(new GridLayout(6, 1, 10, 10));
                        Table.add(makerLabel);
                        Table.remove(PublisherLabel);
                        Table.remove(AuthorsLabel);
                        Table.repaint();
                        current.validate();
                        break;
                }
            }
        });

        JPanel MessagesLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        MessagesLabelPanel.add(new JLabel("Messages"));
        southPanel.add(MessagesLabelPanel, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(MessagesTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        MessagesTa.setEditable(false);
        southPanel.add(scroll);

        JButton resetButton = new JButton();
        resetButton.setText("Reset");

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TypeCombo.setSelectedIndex(0);
                IDField.setValue(null);
                DescriptionField.setText("");
                PriceField.setText("");
                YearField.setValue(null);
                AuthorsField.setText("");
                PublisherField.setText("");
                makerField.setText("");
            }
        });

        JButton addButton = new JButton();
        addButton.setText("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!IDField.isEditValid() || DescriptionField.getText().equals("") || !YearField.isEditValid()) {
                    JOptionPane.showMessageDialog(null, "ID and Description and Year Fields are mandatory", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String[] commands = {};
                if (TypeCombo.getSelectedItem().equals("book")) {
                    StringTokenizer strtok = new StringTokenizer(AuthorsField.getText(), ",");
                    commands = new String[7 + strtok.countTokens()];
                    commands[0] = "add";
                    commands[1] = "book";
                    commands[2] = IDField.getText();
                    commands[3] = DescriptionField.getText();
                    commands[4] = YearField.getText();
                    commands[5] = PriceField.getText();
                    commands[6] = PublisherField.getText();
                    int i = 7;
                    while (strtok.hasMoreTokens()) {
                        commands[i] = strtok.nextToken();
                        i++;
                    }
                } else {
                    commands = new String[7];
                    commands[0] = "add";
                    commands[1] = "electronic";
                    commands[2] = IDField.getText();
                    commands[3] = DescriptionField.getText();
                    commands[4] = YearField.getText();
                    commands[5] = PriceField.getText();
                    commands[6] = makerField.getText();
                }
                MessagesTa.setText(MessagesTa.getText() + '\n' + eStoreSearch.runOnCommand(commands));
            }
        });

        rightPanel.setLayout(new GridLayout(2, 1));

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(resetButton);
        rightPanel.add(p);

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(addButton);
        rightPanel.add(p1);

        JPanel containerX = new JPanel();

        containerX.setLayout(new BoxLayout(containerX, BoxLayout.X_AXIS));
        containerX.add(centerPanel);
        containerX.add(rightPanel);
        this.currentPanel.add(containerX);
        this.currentPanel.add(southPanel);
        this.repaint();
        this.validate();
    }


    /**
     * Drop down menu 2 - search through the existing records
     */
    public void search() {
        this.currentPanel.removeAll();
        this.currentPanel.setLayout(new BoxLayout(this.currentPanel, BoxLayout.Y_AXIS));
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        centerPanel.add(new JLabel("Searching products"), BorderLayout.NORTH);
        JPanel Table = new JPanel(new GridLayout(7, 1, 10, 10));
        centerPanel.add(Table);

        JPanel ProductIDLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ProductIDLabel.add(new JLabel("ProductID:"));
        try {
            IDField = new JFormattedTextField(new MaskFormatter("######"));
            IDField.setColumns(6);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Wrong Format entered in ID must be 6 digits", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ProductIDLabel.add(IDField);
        Table.add(ProductIDLabel);

        JPanel DescriptionLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DescriptionLabel.add(new JLabel("Description\nKeywords:"));
        DescriptionField.setColumns(12);
        DescriptionLabel.add(DescriptionField);
        Table.add(DescriptionLabel);

        JPanel StartYearLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        StartYearLabel.add(new JLabel("Start Year:"));
        try {
            StartYearField = new JFormattedTextField(new MaskFormatter("####"));
            StartYearField.setColumns(4);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Year must be of 4 digits", "Error", JOptionPane.ERROR_MESSAGE);
        }
        StartYearLabel.add(StartYearField);
        Table.add(StartYearLabel);

        JPanel EndYearLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        EndYearLabel.add(new JLabel("Start Year:"));
        try {
            EndYearField = new JFormattedTextField(new MaskFormatter("####"));
            EndYearField.setColumns(4);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Year must be of 4 digits", "Error", JOptionPane.ERROR_MESSAGE);
        }
        EndYearLabel.add(EndYearField);
        Table.add(EndYearLabel);

        JPanel MessagesLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        MessagesLabelPanel.add(new JLabel("Search Results"));
        southPanel.add(MessagesLabelPanel, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(MessagesTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        MessagesTa.setEditable(false);
        southPanel.add(scroll);

        JButton resetButton = new JButton();
        resetButton.setText("Reset");

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDField.setValue(null);
                DescriptionField.setText("");
                StartYearField.setValue(null);
                EndYearField.setValue(null);
            }
        });

        JButton addButton = new JButton();
        addButton.setText("Search");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] commands = new String[5];
                commands[0] = "search";
                commands[1] = (IDField.isEditValid()) ? IDField.getText() : "";
                commands[2] = DescriptionField.getText();
                commands[4] = (StartYearField.isEditValid()) ? StartYearField.getText() : "";
                commands[3] = (EndYearField.isEditValid()) ? EndYearField.getText() : "";
                MessagesTa.setText(eStoreSearch.runOnCommand(commands));
            }
        });

        rightPanel.setLayout(new GridLayout(2, 1));

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(resetButton);
        rightPanel.add(p);

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(addButton);
        rightPanel.add(p1);

        JPanel containerX = new JPanel();

        containerX.setLayout(new BoxLayout(containerX, BoxLayout.X_AXIS));
        containerX.add(centerPanel);
        containerX.add(rightPanel);
        this.currentPanel.add(containerX);
        this.currentPanel.add(southPanel);
        this.repaint();
        this.validate();
    }

    /**
     * Opening message when window opens
     */

    public void Commands() {
        this.currentPanel.removeAll();
        this.currentPanel.setLayout(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Welcome to eStoreSearch");
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        welcomePanel.add(welcomeLabel);
        JLabel commandLabel = new JLabel("Choose a command from the \"Commands\" menu above for adding a product, searching products, or quitting the program.");
        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        commandPanel.add(commandLabel);
        JPanel container = new JPanel(new GridLayout(2, 1, 2, 5));
        container.add(welcomePanel);
        container.add(commandPanel);
        this.currentPanel.add(container);
        this.repaint();
        this.validate();
    }


    /**
     * Main function to call the class methods
     * @param args
     */
    public static void main(String[] args) {
        try {
            /* Create and display the form */
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainEStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainEStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainEStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainEStoreSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainEStoreSearch().setVisible(true);
            }
        });
    }
}
