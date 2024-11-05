package com.mycompany.flashcards;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Chimmy
 */

public class FlashcardPlayer {
    private JFrame frame;
    private JTextArea displayArea;
    private ArrayList<FlashCard> cardList;
    private int currentIndex;

    //Constructor to build the interface
    public FlashcardPlayer() {
        frame = new JFrame("Flash Card Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel to hold everything together
        JPanel mainPanel = new JPanel();
        cardList = new ArrayList<>();

        //Font
        Font greatFont = new Font("Georgia", Font.BOLD, 23);

        //Display TextArea
        displayArea = new JTextArea(10, 30);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setFont(greatFont);
        displayArea.setEditable(false); // Make it read-only

        // Buttons
        JButton openButton = new JButton("Open Flashcards");
        JButton revealButton = new JButton("Show Answer");
        JButton nextButton = new JButton("Next Question");

        //Displaying them all together
        mainPanel.add(new JLabel("Flashcard Viewer"));
        mainPanel.add(displayArea);
        mainPanel.add(openButton);
        mainPanel.add(revealButton);
        mainPanel.add(nextButton);

        //Adding action listeners
        openButton.addActionListener(new OpenCardListener(this));
        revealButton.addActionListener(e -> showAnswer());
        nextButton.addActionListener(e -> showNextCard());

        //Adding to the frame
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(800, 450);
        frame.setVisible(true);
    }

    //Method to load cards from a file
    public void loadCardsFromFile(File file) {
        cardList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length == 2) {
                    FlashCard card = new FlashCard(parts[0], parts[1]);
                    cardList.add(card);
                }
            }
            currentIndex = 0; // Reset current index
            displayCard(currentIndex); // Display the first card
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading cards from file.");
        }
    }

    // Method to display the current card
    public void displayCard(int index) {
        if (index < cardList.size()) {
            displayArea.setText(cardList.get(index).getQuestion()); // Show question
        } else {
            JOptionPane.showMessageDialog(frame, "No more cards available.");
        }
    }

    //show answer
    private void showAnswer() {
        if (currentIndex < cardList.size()) {
            displayArea.setText(cardList.get(currentIndex).getAnswer()); // Show answer
        } else {
            JOptionPane.showMessageDialog(frame, "No card to reveal.");
        }
    }

    //para makapag next ng card
    private void showNextCard() {
        currentIndex++;
        if (currentIndex < cardList.size()) {
            displayCard(currentIndex); //pakita yung next question
        } else {
            JOptionPane.showMessageDialog(frame, "No more cards available.");
            currentIndex--;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlashcardPlayer::new);
    }

    //Inner class for OpenCardListener
    private static class OpenCardListener implements ActionListener {
        private FlashcardPlayer player; // Reference to FlashcardPlayer

        //Constructor para tumanggap ng reference to FlashcardPlayer
        public OpenCardListener(FlashcardPlayer player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                player.loadCardsFromFile(selectedFile); // Load selected file
            }
        }
    }
}
