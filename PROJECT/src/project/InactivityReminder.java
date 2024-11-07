/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author allyson
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class InactivityReminder {
    private static final int INACTIVITY_LIMIT_MS = 300000; // 5 minutes
    private Timer inactivityTimer;
    private JFrame frame;

    // Constructor that accepts the main frame and JTextArea for tracking
    public InactivityReminder(JFrame frame, JTextArea textArea) {
        this.frame = frame;
        setupInactivityTimer();
        setupActivityListener(textArea);
    }

    // Sets up the timer that will trigger the alert after inactivity
    private void setupInactivityTimer() {
        inactivityTimer = new Timer();
        resetInactivityTimer();
    }

    // Resets the inactivity timer on activity
    private void resetInactivityTimer() {
        inactivityTimer.cancel();
        inactivityTimer = new Timer();
        inactivityTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                showReminderAlert();
            }
        }, INACTIVITY_LIMIT_MS);
    }

    // Displays the inactivity reminder alert
    private void showReminderAlert() {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame, "Reminder: Stay focused on your study session!"));
    }

    // Adds a listener to reset timer on any keystroke
    private void setupActivityListener(JTextArea textArea) {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                resetInactivityTimer();
            }
        });
    }
}

