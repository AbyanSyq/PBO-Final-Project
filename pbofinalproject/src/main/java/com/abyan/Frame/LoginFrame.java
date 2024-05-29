package com.abyan.Frame;

import javax.swing.*;

import com.abyan.Manager.GameManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private BufferedImage backgroundImage;
    private BufferedImage loginButtonImage;
    private BufferedImage newAccountButtonImage;

    public LoginFrame() {
        loadImages();
        initComponent();
    }

    private void loadImages() {
        try {
            backgroundImage = ImageIO.read(new File("Asset/LoginBackground.png"));
            loginButtonImage = ImageIO.read(new File("Asset/login.png"));
            newAccountButtonImage = ImageIO.read(new File("Asset/signUp.png"));

            backgroundImage = resizeImage(backgroundImage, 1920, 1080); 
            loginButtonImage = resizeImage(loginButtonImage, 200, 80); 
            newAccountButtonImage = resizeImage(newAccountButtonImage, 200, 80);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    private void initComponent() {
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel info = new JLabel();
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("");
        JButton newAccountButton = new JButton("");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        loginButton.setFont(buttonFont);
        newAccountButton.setFont(buttonFont);

        if (loginButtonImage != null) {
            loginButton.setIcon(new ImageIcon(loginButtonImage));
            loginButton.setContentAreaFilled(false);
            loginButton.setBorderPainted(false);
        }
        if (newAccountButtonImage != null) {
            newAccountButton.setIcon(new ImageIcon(newAccountButtonImage));
            newAccountButton.setContentAreaFilled(false);
            newAccountButton.setBorderPainted(false);
        }

        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        usernameField.setFont(textFieldFont);
        passwordField.setFont(textFieldFont);

        usernameField.setForeground(Color.BLACK);
        passwordField.setForeground(Color.BLACK);

        usernameField.setBackground(Color.LIGHT_GRAY);
        passwordField.setBackground(Color.LIGHT_GRAY);

        Border border = new LineBorder(Color.DARK_GRAY, 1);
        usernameField.setBorder(border);
        passwordField.setBorder(border);

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
        panel.add(loginButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(newAccountButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
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
                            new HomeBaseFrame();
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
                            new HomeBaseFrame();
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
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    class BackgroundPanel extends JPanel {
        private BufferedImage image;

        public BackgroundPanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
