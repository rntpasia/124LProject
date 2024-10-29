/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ranip
 */
public class Notes implements ActionListener{
        JFrame window;
        JTextArea textArea, titleArea;
        JScrollPane scrollPane;
        JMenuBar menu;
        JMenu File, Help;
        JMenuItem JNew, JOpen, JSave, JSaveAs;
        JPanel mainPanel, fontPanel, alignPanel, timerPanel;
        JComboBox fontStyle, fontSize, fontColor, fontBG;
        JButton bold, italic, underline;
        
        NoteFunction func = new NoteFunction(this);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout lo = new GridBagLayout();
    
    public Notes(){
        createWindow();
        createTextArea();
        createMenu();
        createFormatPanel();
        createFontStyle();
        
        
        window.setVisible(true);
    }
    
    public void createWindow(){
        window = new JFrame("Study Up");
        window.setSize(1000, 900);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createTextArea(){
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
            window.add(scrollPane);
    }
    
    public void createFormatPanel(){
        mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder("Note Formatting"));
        mainPanel.setLayout(new GridLayout(1,3));
        mainPanel.setBackground(Color.getHSBColor(202, 94, 88));
        mainPanel.setBounds(10,10,1000,600);
            window.add(mainPanel, BorderLayout.NORTH);
            
        fontPanel = new JPanel();
        fontPanel.setBorder(new TitledBorder("Font"));
        fontPanel.setLayout(lo);
        fontPanel.setBounds(10,10,1000,40);
        fontPanel.setBackground(Color.lightGray);
            mainPanel.add(fontPanel);
            
        alignPanel = new JPanel();
        alignPanel.setBorder(new TitledBorder("Align"));
        alignPanel.setBounds(10,10,1000,40);
        alignPanel.setBackground(Color.white);
            mainPanel.add(alignPanel);
        
        timerPanel = new JPanel();
        timerPanel.setBorder(new TitledBorder("Timer"));
        timerPanel.setBounds(10,10,1000,40);
        timerPanel.setBackground(Color.lightGray);
            mainPanel.add(timerPanel);
    }
    
    public void createFontStyle(){
        fontStyle = new JComboBox();
        fontSize = new JComboBox();
        bold = new JButton("B");
        italic = new JButton("I");
        underline = new JButton("U");
        
            fontStyle.addItem("Arial");
            fontStyle.addItem("Times New Roman");
            fontStyle.addItem("Calibri");
            gbc.gridx = 0;
            gbc.gridy = 0;
        fontPanel.add(fontStyle, gbc);
        
            fontSize.addItem("12");
            fontSize.addItem("18");
            fontSize.addItem("24");
            gbc.gridx = 1;
            gbc.gridy = 0;
        fontPanel.add(fontSize, gbc);
                    
            gbc.gridx = 0;
            gbc.gridy = 1;
        fontPanel.add(bold, gbc);
       
            gbc.gridx = 1;
            gbc.gridy = 1;
        fontPanel.add(italic, gbc);
        
          gbc.gridx = 2;
           gbc.gridy = 1;
        fontPanel.add(underline, gbc);
    }
    
     public void createMenu(){
        menu = new JMenuBar();
        window.setJMenuBar(menu);
        
        File = new JMenu("File");
            menu.add(File);
            
        Help = new JMenu ("Help");
            menu.add(Help);
            
        JNew = new JMenuItem("New");
            JNew.addActionListener(this);
            JNew.setActionCommand("New");
            File.add(JNew);
            
        JOpen = new JMenuItem("Open");
            JOpen.addActionListener(this);
            JOpen.setActionCommand("Open");   
            File.add(JOpen);
            
        JSave = new JMenuItem("Save");
            JSave.addActionListener(this);
            JSave.setActionCommand("Save");
            File.add(JSave);
            
        JSaveAs = new JMenuItem("Save As");
            JSaveAs.addActionListener(this);
            JSaveAs.setActionCommand("Save As");
            File.add(JSaveAs);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
            switch(cmd){
                case "New": func.newNote();
                    break;
                case "Open": func.openNote();
                    break;
                case "Save": func.saveNote();
                    break;
                case "Save As": func.saveAsNote();
                    break;
            }
    }
    
   public static void main(String[] args) {
        new Notes();
    }      
    
}
