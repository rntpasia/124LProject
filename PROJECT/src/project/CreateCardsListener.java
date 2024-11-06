package com.mycompany.flashcards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Chimmy
 */
public class CreateCardsListener implements ActionListener {
    private FlashcardBuilder builder;

    public CreateCardsListener(FlashcardBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String questionText = builder.question.getText().trim();
        String answerText = builder.answer.getText().trim();

        //para lang ma check kung may values yung dalawang fields
        if (!questionText.isEmpty() && !answerText.isEmpty()) {
            //Add current flashcard to the list
            FlashCard card = new FlashCard(questionText, answerText);
            builder.cardList.add(card);
            JOptionPane.showMessageDialog(builder.frame, "Flashcard added!");

            //Clear fields for the next card
            builder.question.setText("");
            builder.answer.setText("");
        } else {
            JOptionPane.showMessageDialog(builder.frame, "Please enter both question and answer.");
        }
    }
}