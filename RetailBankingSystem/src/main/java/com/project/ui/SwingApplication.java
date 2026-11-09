package com.project.ui;

import javax.swing.*;

/**
 * Main application launcher for Swing UI
 */
public class SwingApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginSelectionFrame frame = new LoginSelectionFrame();
            frame.setVisible(true);
        });
    }
}

