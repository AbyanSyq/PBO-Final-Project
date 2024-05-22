/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.abyan.Frame;

/**
 *
 * @author Acer
 */
import javax.swing.*;

import com.abyan.Manager.GameManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        initComponent();
    }

    private void initComponent() {
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel info = new JLabel();
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");
        JButton newAccountButton = new JButton("Create new Account");

        // loginButton.setPreferredSize(new Dimension(150, 50)); // Set your desired
        // size
        // newAccountButton.setPreferredSize(new Dimension(150, 50));

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        // constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(loginButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        // constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(newAccountButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2; // Mengatur gridwidth menjadi 2 untuk menggabungkan dua kolom
        constraints.anchor = GridBagConstraints.CENTER; // Mengatur label berada di tengah
        panel.add(info, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (GameManager.login(username, password) != null) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new homeBasePanel();
                        }
                    });
                    setVisible(false);
                } else {
                    info.setText("Invalid username or password.");
                }
            }
        });

        newAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (GameManager.signUp(username, password) != null) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new homeBasePanel();
                        }
                    });
                    setVisible(false);
                } else {
                    info.setText("Username already exists. Please choose a different username.");
                }
            }
        });

        add(panel);
        setTitle("Login Frame");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}