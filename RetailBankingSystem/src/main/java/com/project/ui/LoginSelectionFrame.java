package com.project.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a login selection screen for users to choose between
 * Employee Login and Customer Login
 */
public class LoginSelectionFrame extends JFrame {

    public LoginSelectionFrame() {
        setTitle("Retail Banking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Title
        JLabel titleLabel = new JLabel("Retail Banking System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Please select your login type");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(subtitleLabel, gbc);

        // Employee Login Button
        JButton employeeLoginBtn = new JButton("Employee Login");
        employeeLoginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        employeeLoginBtn.setPreferredSize(new Dimension(200, 50));
        employeeLoginBtn.setBackground(new Color(0, 102, 204));
        employeeLoginBtn.setForeground(Color.WHITE);
        employeeLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        employeeLoginBtn.addActionListener(e -> openEmployeeLogin());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainPanel.add(employeeLoginBtn, gbc);

        // Customer Login Button
        JButton customerLoginBtn = new JButton("Customer Login");
        customerLoginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        customerLoginBtn.setPreferredSize(new Dimension(200, 50));
        customerLoginBtn.setBackground(new Color(0, 153, 76));
        customerLoginBtn.setForeground(Color.WHITE);
        customerLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        customerLoginBtn.addActionListener(e -> openCustomerLogin());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainPanel.add(customerLoginBtn, gbc);

        // Exit Button
        JButton exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        exitBtn.setPreferredSize(new Dimension(100, 40));
        exitBtn.setBackground(new Color(204, 0, 0));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitBtn.addActionListener(e -> System.exit(0));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(exitBtn, gbc);

        add(mainPanel);
    }

    private void openEmployeeLogin() {
        EmployeeLoginFrame employeeFrame = new EmployeeLoginFrame();
        employeeFrame.setVisible(true);
        this.dispose();
    }

    private void openCustomerLogin() {
        CustomerLoginFrame customerFrame = new CustomerLoginFrame();
        customerFrame.setVisible(true);
        this.dispose();
    }
}

