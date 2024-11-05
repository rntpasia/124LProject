package com.mycompany.flashcards;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Chimmy
 */
public class FlashcardBuilder {

    //declaration
    JTextArea question;
    JTextArea answer;
    ArrayList<FlashCard> cardList;
    JFrame frame;
    int currentIndex = 0;
    
    
    //constructor to build the interface
    public FlashcardBuilder() {
        frame = new JFrame("Flash Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //intializing cardlist
        cardList = new ArrayList<>();
        
        //panel to hold everything together
        JPanel mainPanel = new JPanel();
        
        //Font
        Font greatFont = new Font("Georgia", Font.BOLD, 23);
        
                
        //question Area
        question = new JTextArea(6, 20);//rows,columns
        question.setLineWrap(true);//so that everything fits
        question.setWrapStyleWord(true);
        question.setFont(greatFont);
        
        //question part, question muna bago answer for the sake of organization and process
        JScrollPane aJScrollPane1 = new JScrollPane(question);
        aJScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aJScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        //answer area
        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(greatFont);

        
        
        //Jscroll, para vertical and scrolling
        JScrollPane aJScrollPane2 = new JScrollPane(answer);
        aJScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aJScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        //buttons
        JButton saveButton = new JButton("Save Card");
        JButton createButton = new JButton("Create New Card");
        
        //labellings
        JLabel aJLabel1 = new JLabel("Question");
        JLabel aJLabel2 = new JLabel("Answer");
        
    ///////////////////////////////////////////////////////////////////////////////////////////////
        //Displaying them all together
            //question section
            mainPanel.add(aJLabel1);
            mainPanel.add(aJScrollPane1);

            //answer section
            mainPanel.add(aJLabel2);
            mainPanel.add(aJScrollPane2);
            
            //next button
            mainPanel.add(saveButton);
            saveButton.addActionListener(new SaveCardsListener(this));//importing from the same package
            mainPanel.add(createButton);
            createButton.addActionListener(new CreateCardsListener(this));
            
            //Adding to the frame
            frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
            frame.setSize(600, 450);
            frame.setVisible(true);
        
    }//end of the constructor
    
    //Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new FlashcardBuilder();
            }
        });
    }
    
    public void createNewCard() {
        question.setText("");
        answer.setText("");
        JOptionPane.showMessageDialog(frame, "Ready to create a new card!");
    }
}
