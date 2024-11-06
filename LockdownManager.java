/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ira
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockdownManager {
    private final Notes notes;            // Reference to the Notes instance
    private Timer lockdownTimer;           // Timer for the lockdown
    private JLabel countdownLabel;         // Label to show the elapsed time
    private boolean isLockdownActive = false;

    public LockdownManager(Notes notes) {
        this.notes = notes;
        setupLockdownPanel();
    }

    // Sets up the lockdown panel in the Notes UI
    private void setupLockdownPanel() {
        JButton lockdownButton = new JButton("Activate 30-Minute Lockdown");
        lockdownButton.addActionListener(e -> startLockdown());

        countdownLabel = new JLabel("Elapsed time: 00:00");
        notes.timerPanel.add(lockdownButton);
        notes.timerPanel.add(countdownLabel);  // Adds the countdown label to Notes' timer panel
    }

    // Starts the lockdown process and timer
    private void startLockdown() {
        if (isLockdownActive) {
            JOptionPane.showMessageDialog(notes.window, "Lockdown is already active!");
            return;
        }

        // Activate lockdown
        isLockdownActive = true;
        notes.window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JOptionPane.showMessageDialog(notes.window, "Lockdown starts. You cannot exit until 30 minutes have passed.");

        // Initialize the timer that counts up
        lockdownTimer = new Timer(1000, new ActionListener() {
            int elapsedSeconds = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedSeconds++;
                int minutes = elapsedSeconds / 60;
                int seconds = elapsedSeconds % 60;
                countdownLabel.setText(String.format("Elapsed time: %02d:%02d", minutes, seconds));

                // Check if 30 minutes have passed
                if (elapsedSeconds >= 5 && isLockdownActive) { // 1800 seconds = 30 minutes
                    // End the lockdown
                    isLockdownActive = false;
                    notes.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JOptionPane.showMessageDialog(notes.window, "Lockdown period is over. You have done it! :D ");
                }
            }
        });
        lockdownTimer.start();
    }
}
