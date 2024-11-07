/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author ranip
 */
public class Notes implements ActionListener {
    JFrame window;
    JTextArea textArea, titleArea;
    JScrollPane scrollPane;
    JMenuBar menu;
    JMenu File, Help;
    JMenuItem JNew, JOpen, JSave, JSaveAs;
    JPanel mainPanel, fontPanel, alignPanel, timerPanel;
    JComboBox font, fontStyle, fontSize, fontColor, fontBG;
    JButton left, center, right, justify;

    NoteFunction func = new NoteFunction(this);

    // Inactivity Reminder Variables
    private static final int INACTIVITY_LIMIT_MS = 300000; // 5 minutes
    private Timer inactivityTimer;

    public Notes() {
        createWindow();
        createTextArea();
        createMenu();
        createMenuItem();
        createFormatPanel();
        createFontPanel();
        createAlignPanel();
        window.setVisible(true);
    }

    public void createWindow() {
        window = new JFrame("Study Up");
        window.setSize(1000, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);

        // Set up the inactivity reminder to monitor for inactivity
        setupInactivityReminder();
    }

    // Method to set up inactivity timer and listener
    private void setupInactivityReminder() {
        inactivityTimer = new Timer();
        resetInactivityTimer(); // Start the timer initially

        // Add a key listener to the text area to reset the timer on activity
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                resetInactivityTimer();  // Reset the timer on each key press
            }
        });
    }

    // Resets the inactivity timer
    private void resetInactivityTimer() {
        inactivityTimer.cancel();  // Cancel the current timer if running
        inactivityTimer = new Timer();  // Start a new timer

        // Schedule a task to show reminder if no activity occurs within the time limit
        inactivityTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                showReminderAlert();
            }
        }, INACTIVITY_LIMIT_MS);
    }

    // Shows a reminder alert
    private void showReminderAlert() {
        JOptionPane.showMessageDialog(window, "Reminder: Stay focused on your study session!");
    }

    public void createFormatPanel() {
        mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder("Note Formatting"));
        mainPanel.setLayout(new GridLayout(1,3));
        mainPanel.setPreferredSize(new Dimension(1000,120));
        mainPanel.setBackground(Color.getHSBColor(202, 94, 88));
        window.add(mainPanel, BorderLayout.NORTH);

        fontPanel = new JPanel();
        fontPanel.setBorder(new TitledBorder("Font"));
        fontPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        fontPanel.setBackground(Color.white);
        mainPanel.add(fontPanel);

        alignPanel = new JPanel();
        alignPanel.setBorder(new TitledBorder("Align"));
        alignPanel.setLayout(new GridLayout(2,2));
        alignPanel.setBackground(Color.white);
        mainPanel.add(alignPanel);

        timerPanel = new JPanel();
        timerPanel.setBorder(new TitledBorder("Timer"));

        timerPanel.setBackground(Color.white);
        mainPanel.add(timerPanel);
    }

    public void createFontPanel() {
        fontStyle = new JComboBox();
        fontSize = new JComboBox();
        font = new JComboBox();
        fontColor = new JComboBox();
        fontBG = new JComboBox();

        fontStyle.addItem("Arial");
        fontStyle.addItem("Times New Roman");
        fontStyle.addItem("Calibri");
        fontPanel.add(fontStyle);

        font.addItem("Bold");
        font.addItem("Italic");
        font.addItem("Underline");
        fontPanel.add(font);

        fontSize.addItem("12");
        fontSize.addItem("18");
        fontSize.addItem("24");
        fontPanel.add(fontSize);

        fontColor.addItem("Black");
        fontColor.addItem("White");
        fontColor.addItem("Gray");
        fontPanel.add(fontColor);

        fontBG.addItem("Blue Highlight");
        fontBG.addItem("Green Highlight");
        fontBG.addItem("Yellow Highlight");
        fontPanel.add(fontBG);
    }

    public void createAlignPanel() {
        left = new JButton("Left");
        right = new JButton("Right");
        center = new JButton("Center");
        justify = new JButton("Justify");
        alignPanel.add(left);
        alignPanel.add(right);
        alignPanel.add(center);
        alignPanel.add(justify);
    }

    public void createMenu() {
        menu = new JMenuBar();
        window.setJMenuBar(menu);

        File = new JMenu("File");
        menu.add(File);

        Help = new JMenu ("Help");
        menu.add(Help);
    }

    public void createMenuItem() {
        JNew = new JMenuItem("New");
        JNew.addActionListener(this);
        JNew.setActionCommand("New Note");
        File.add(JNew);

        JOpen = new JMenuItem("Open");
        JOpen.addActionListener(this);
        JOpen.setActionCommand("Open Note");   
        File.add(JOpen);

        JSave = new JMenuItem("Save");
        JSave.addActionListener(this);
        JSave.setActionCommand("Save Note");
        File.add(JSave);

        JSaveAs = new JMenuItem("Save As");
        JSaveAs.addActionListener(this);
        JSaveAs.setActionCommand("Note Save As");
        File.add(JSaveAs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch(cmd){
            case "New Note": 
                func.newNote();
                break;
            case "Open Note": 
                func.openNote();
                break;
            case "Save Note": 
                func.saveNote();
                break;
            case "Note Save As": 
                func.saveAsNote();
                break;
        }
    }

    public static void main(String[] args) {
        new Notes();
    }      
}
