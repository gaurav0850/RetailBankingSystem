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
 * Employee Login Frame - Swing UI version of login.html
 */
public class EmployeeLoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel resultLabel;
    private JButton loginButton;
    private JButton togglePasswordButton;
    private JLabel customerLoginLink;
    private boolean passwordVisible = false;

    public EmployeeLoginFrame() {
        setTitle("Retail Banking System - Employee Login");
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
        JLabel titleLabel = new JLabel("Employee");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(usernameLabel, gbc);

        // Username Field with Icon
        JPanel usernamePanel = new JPanel(new BorderLayout(5, 0));
        usernamePanel.setBackground(Color.WHITE);
        JLabel usernameIcon = new JLabel("👤");
        usernameIcon.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernamePanel.add(usernameIcon, BorderLayout.WEST);
        usernamePanel.add(usernameField, BorderLayout.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(usernamePanel, gbc);

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

        // Customer Login Link
        customerLoginLink = new JLabel("Customer Login");
        customerLoginLink.setFont(new Font("Arial", Font.PLAIN, 12));
        customerLoginLink.setForeground(new Color(0, 102, 204));
        customerLoginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        customerLoginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openCustomerLogin();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                customerLoginLink.setText("<html><u>Customer Login</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                customerLoginLink.setText("Customer Login");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(customerLoginLink, gbc);

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
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            resultLabel.setText("Please fill in all fields");
            resultLabel.setForeground(Color.RED);
            return;
        }

        // Validate username pattern
        if (!username.matches("[a-zA-Z][a-zA-Z0-9-_.]{1,20}")) {
            resultLabel.setText("Invalid username format");
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
                boolean success = authenticateEmployee(username, password);
                if (success) {
                    SwingUtilities.invokeLater(() -> {
                        resultLabel.setText("");
                        JOptionPane.showMessageDialog(EmployeeLoginFrame.this,
                                "Login successful!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        // Navigate to employee home (in a real app, open new frame)
                        openEmployeeHome();
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        resultLabel.setText("Invalid username or password");
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

    private boolean authenticateEmployee(String username, String password) throws Exception {
        // Make HTTP POST request to backend
        String backendUrl = "http://localhost:8080/employeeHome";
        URL url = new URL(backendUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        // Prepare request body
        String params = "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) +
                "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = params.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_TEMP;
    }

    private void openEmployeeHome() {
        // In a real application, you would open the employee home frame
        JFrame employeeFrame = new JFrame("Employee Home");
        employeeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        employeeFrame.setSize(800, 600);
        employeeFrame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Welcome to Employee Home");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        employeeFrame.add(label);
        employeeFrame.setVisible(true);
        this.dispose();
    }

    private void openCustomerLogin() {
        JOptionPane.showMessageDialog(this,
                "Opening Customer Login",
                "Navigation",
                JOptionPane.INFORMATION_MESSAGE);
        // In a real application, you would open the customer login frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeLoginFrame frame = new EmployeeLoginFrame();
            frame.setVisible(true);
        });
    }
}

