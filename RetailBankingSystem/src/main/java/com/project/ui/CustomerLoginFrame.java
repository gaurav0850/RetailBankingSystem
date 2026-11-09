package com.project.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Customer Login Frame - Swing UI version of customerLogin.html
 */
public class CustomerLoginFrame extends JFrame {
    private JTextField customerIdField;
    private JPasswordField passwordField;
    private JLabel resultLabel;
    private JButton loginButton;
    private JButton togglePasswordButton;
    private JLabel employeeLoginLink;
    private boolean passwordVisible = false;

    public CustomerLoginFrame() {
        setTitle("Retail Banking System - Customer Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setLayout(new BorderLayout());

        // Create form panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Customer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        // Customer ID Label
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(customerIdLabel, gbc);

        // Customer ID Field with Icon
        JPanel customerIdPanel = new JPanel(new BorderLayout(5, 0));
        customerIdPanel.setBackground(Color.WHITE);
        JLabel customerIdIcon = new JLabel("👤");
        customerIdIcon.setFont(new Font("Arial", Font.PLAIN, 14));
        customerIdField = new JTextField();
        customerIdField.setFont(new Font("Arial", Font.PLAIN, 12));
        customerIdField.setPreferredSize(new Dimension(200, 30));
        customerIdPanel.add(customerIdIcon, BorderLayout.WEST);
        customerIdPanel.add(customerIdField, BorderLayout.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(customerIdPanel, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        // Password Field with Icon and Toggle
        JPanel passwordFieldPanel = new JPanel(new BorderLayout(5, 0));
        passwordFieldPanel.setBackground(Color.WHITE);
        JLabel passwordIcon = new JLabel("🔒");
        passwordIcon.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setPreferredSize(new Dimension(150, 30));
        togglePasswordButton = new JButton("👁️");
        togglePasswordButton.setFont(new Font("Arial", Font.PLAIN, 12));
        togglePasswordButton.setBorderPainted(false);
        togglePasswordButton.setContentAreaFilled(false);
        togglePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        togglePasswordButton.addActionListener(e -> togglePasswordVisibility());

        passwordFieldPanel.add(passwordIcon, BorderLayout.WEST);
        passwordFieldPanel.add(passwordField, BorderLayout.CENTER);
        passwordFieldPanel.add(togglePasswordButton, BorderLayout.EAST);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(passwordFieldPanel, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> performLogin());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);

        // Result Label
        resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        resultLabel.setForeground(Color.RED);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(resultLabel, gbc);

        // Employee Login Link
        employeeLoginLink = new JLabel("Employee Login");
        employeeLoginLink.setFont(new Font("Arial", Font.PLAIN, 12));
        employeeLoginLink.setForeground(new Color(0, 102, 204));
        employeeLoginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        employeeLoginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEmployeeLogin();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                employeeLoginLink.setText("<html><u>Employee Login</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                employeeLoginLink.setText("Employee Login");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(employeeLoginLink, gbc);

        // Add form panel to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            passwordField.setEchoChar((char) 0);
            togglePasswordButton.setText("🙈");
        } else {
            passwordField.setEchoChar('•');
            togglePasswordButton.setText("👁️");
        }
    }

    private void performLogin() {
        String customerId = customerIdField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validate input
        if (customerId.isEmpty() || password.isEmpty()) {
            resultLabel.setText("Please fill in all fields");
            resultLabel.setForeground(Color.RED);
            return;
        }

        // Validate customer ID is numeric
        try {
            Integer.parseInt(customerId);
        } catch (NumberFormatException e) {
            resultLabel.setText("Customer ID must be numeric");
            resultLabel.setForeground(Color.RED);
            return;
        }

        // Disable button while processing
        loginButton.setEnabled(false);
        resultLabel.setText("Logging in...");
        resultLabel.setForeground(new Color(0, 102, 204));

        // Perform login in background thread
        new Thread(() -> {
            try {
                boolean success = authenticateCustomer(customerId, password);
                if (success) {
                    SwingUtilities.invokeLater(() -> {
                        resultLabel.setText("");
                        JOptionPane.showMessageDialog(CustomerLoginFrame.this,
                                "Login successful!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        // Navigate to customer home (in a real app, open new frame)
                        openCustomerHome();
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        resultLabel.setText("Invalid customer ID or password");
                        resultLabel.setForeground(Color.RED);
                        loginButton.setEnabled(true);
                    });
                }
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    resultLabel.setText("Error: " + e.getMessage());
                    resultLabel.setForeground(Color.RED);
                    loginButton.setEnabled(true);
                });
            }
        }).start();
    }

    private boolean authenticateCustomer(String customerId, String password) throws Exception {
        // Make HTTP POST request to backend
        String backendUrl = "http://localhost:8080/customerHome";
        URL url = new URL(backendUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        // Prepare request body
        String params = "customerId=" + URLEncoder.encode(customerId, StandardCharsets.UTF_8) +
                "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = params.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_TEMP;
    }

    private void openCustomerHome() {
        // In a real application, you would open the customer home frame
        JFrame customerFrame = new JFrame("Customer Home");
        customerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        customerFrame.setSize(800, 600);
        customerFrame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Welcome to Customer Home");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        customerFrame.add(label);
        customerFrame.setVisible(true);
        this.dispose();
    }

    private void openEmployeeLogin() {
        JOptionPane.showMessageDialog(this,
                "Opening Employee Login",
                "Navigation",
                JOptionPane.INFORMATION_MESSAGE);
        // In a real application, you would open the employee login frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerLoginFrame frame = new CustomerLoginFrame();
            frame.setVisible(true);
        });
    }
}

