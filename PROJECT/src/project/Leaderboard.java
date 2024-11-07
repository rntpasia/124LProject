package com.mycompany.flashcards;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard {
    private JFrame window;
    private JTextArea textArea;
    private ArrayList<LeaderboardEntry> entries;

    public Leaderboard() {
        entries = new ArrayList<>();
        createUI();
    }

    private void createUI() {
        window = new JFrame("Leaderboard");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(300, 400);

        textArea = new JTextArea();
        textArea.setEditable(false);
        window.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh Rankings");
        refreshButton.addActionListener(e -> displayRankings());
        window.add(refreshButton, BorderLayout.SOUTH);

        window.setVisible(true);
    }

    public void addEntry(String name, long time) {
        entries.add(new LeaderboardEntry(name, time));
        Collections.sort(entries, Comparator.comparingLong(LeaderboardEntry::getTime));
    }

    public void displayRankings() {
        textArea.setText("Rankings:\n");
        for (int i = 0; i < entries.size(); i++) {
            LeaderboardEntry entry = entries.get(i);
            textArea.append((i + 1) + ". " + entry.getName() + " - " + entry.getTime() / 1000.0 + " seconds\n");
        }
    }

    private static class LeaderboardEntry {
        private String name;
        private long time;

        public LeaderboardEntry(String name, long time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public long getTime() {
            return time;
        }
    }
}
