package com.mycompany.flashcards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Chimmy
 */
public class SaveCardsListener implements ActionListener {
    private FlashcardBuilder builder;

    public SaveCardsListener(FlashcardBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (builder.cardList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No flashcards to save. Create some first.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, true))) { // 'true' enables appending
                for (FlashCard card : builder.cardList) {
                    writer.println(card.toString()); // Write each card to file
                }
                JOptionPane.showMessageDialog(null, "All cards saved to " + fileToSave.getAbsolutePath() + "!");
                builder.cardList.clear(); // Clear the list after saving
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving cards!");
            }
        }
    }
}