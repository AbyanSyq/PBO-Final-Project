// package com.abyan.Frame;

// /**
//  *
//  * @author Acer
//  */
// import javax.swing.*;
// import com.abyan.Manager.GameManager;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.File;

// public class Auth extends JFrame {

//     private JTextField usernameField;
//     private JPasswordField passwordField;
//     private JLabel photoLabel;

//     public Auth() {
//         initComponent();
//     }

//     private void initComponent() {
//         JPanel panel = new JPanel();
//         JLabel usernameLabel = new JLabel("Username:");
//         JLabel passwordLabel = new JLabel("Password:");
//         usernameField = new JTextField(10);
//         passwordField = new JPasswordField(10);
//         JButton loginButton = new JButton("Login");
//         JButton newAccountButton = new JButton("Create new Account");
//         JButton uploadButton = new JButton("Upload Photo");

//         // Set preferred size for buttons
//         loginButton.setPreferredSize(new Dimension(150, 50));
//         newAccountButton.setPreferredSize(new Dimension(150, 50));
//         uploadButton.setPreferredSize(new Dimension(150, 50));

//         panel.setLayout(new GridBagLayout());
//         GridBagConstraints constraints = new GridBagConstraints();

//         constraints.gridx = 0;
//         constraints.gridy = 0;
//         constraints.insets = new Insets(5, 5, 5, 5);
//         panel.add(usernameLabel, constraints);

//         constraints.gridx = 1;
//         panel.add(usernameField, constraints);

//         constraints.gridx = 0;
//         constraints.gridy = 1;
//         panel.add(passwordLabel, constraints);

//         constraints.gridx = 1;
//         panel.add(passwordField, constraints);

//         constraints.gridx = 1;
//         constraints.gridy = 2;
//         constraints.anchor = GridBagConstraints.LINE_END;
//         panel.add(loginButton, constraints);

//         constraints.gridx = 0;
//         constraints.gridy = 2;
//         constraints.anchor = GridBagConstraints.LINE_END;
//         panel.add(newAccountButton, constraints);

//         constraints.gridx = 1;
//         constraints.gridy = 3;
//         constraints.anchor = GridBagConstraints.LINE_END;
//         panel.add(uploadButton, constraints);

//         loginButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String username = usernameField.getText();
//                 String password = new String(passwordField.getPassword());
//                 GameManager.login(username, password);
//                 JOptionPane.showMessageDialog(null, "Login button pressed!\nUsername: " + username + "\nPassword: " + password);
//             }
//         });

//         newAccountButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String username = usernameField.getText();
//                 String password = new String(passwordField.getPassword());
//                 GameManager.signUp(username, password);
//                 JOptionPane.showMessageDialog(null, "Sign up button pressed!\nUsername: " + username + "\nPassword: " + password);
//             }
//         });

//         uploadButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 JFileChooser fileChooser = new JFileChooser();
//                 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                 int returnValue = fileChooser.showOpenDialog(null);
//                 if (returnValue == JFileChooser.APPROVE_OPTION) {
//                     File selectedFile = fileChooser.getSelectedFile();
//                     JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath());
//                 }
//             }
//         });

//         add(panel);
//         setTitle("Login Frame");
//         //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(800, 600); // Mengatur ukuran frame
//         setLocationRelativeTo(null); // Menempatkan frame di tengah layar
//         setVisible(true);
//     }

//     public static void main(String[] args) {
//         // Jalankan frame pada event dispatch thread
//         SwingUtilities.invokeLater(new Runnable() {
//             @Override
//             public void run() {
//                 new LoginFrame();
//             }
//         });
//     }
// }
