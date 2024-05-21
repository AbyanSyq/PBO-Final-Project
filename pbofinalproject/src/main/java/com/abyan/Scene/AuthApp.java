package com.abyan.Scene;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class AuthApp {

    public static void main(String[] args) {
        login();
        GameManager.saveData();
    }
    public static void login() {
        GameManager.setFile("abyan");
        GameManager.loadData();

        JFrame frame = new JFrame("Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton signInButton = new JButton("sign in");
        JButton signUpButton = new JButton("sign up");

        mainPanel.add(signInButton, gbc);
        gbc.gridy++;
        mainPanel.add(signUpButton, gbc);

        frame.add(mainPanel);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignInPanel(frame);
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignUpPanel(frame);
            }
        });

        frame.setVisible(true);
       
    }

    private static void showSignInPanel(JFrame frame) {
        JPanel signInPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton signInButton = new JButton("sign in");

        signInPanel.add(new JLabel("masukan username"), gbc);
        gbc.gridy++;
        signInPanel.add(usernameField, gbc);
        gbc.gridy++;
        signInPanel.add(new JLabel("Masukan password"), gbc);
        gbc.gridy++;
        signInPanel.add(passwordField, gbc);
        gbc.gridy++;
        signInPanel.add(signInButton, gbc);

        frame.getContentPane().removeAll();
        frame.add(signInPanel);
        frame.revalidate();
        frame.repaint();

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.login(usernameField.getText(),passwordField.getText());
            }
        });
    }

    private static void showSignUpPanel(JFrame frame) {
        JPanel signUpPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton signUpButton = new JButton("sign up");

        signUpPanel.add(new JLabel("buat username"), gbc);
        gbc.gridy++;
        signUpPanel.add(usernameField, gbc);
        gbc.gridy++;
        signUpPanel.add(new JLabel("buat password"), gbc);
        gbc.gridy++;
        signUpPanel.add(passwordField, gbc);
        gbc.gridy++;
        signUpPanel.add(signUpButton, gbc);

        frame.getContentPane().removeAll();
        frame.add(signUpPanel);
        frame.revalidate();
        frame.repaint();
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.signUp(usernameField.getText(),passwordField.getText());
                GameManager.saveData();
            }
        });
    }
}
