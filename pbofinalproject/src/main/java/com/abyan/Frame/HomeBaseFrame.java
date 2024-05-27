package com.abyan.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import com.abyan.Manager.Element;
import com.abyan.Manager.GameManager;
import com.abyan.Manager.Player;
import com.abyan.Object.*;
import com.abyan.Scene.Dungeon;
import com.abyan.Scene.GameHomebase;



public class HomeBaseFrame extends JFrame {
    private int currentMonster = 0;
    private JTextArea textArea1;
    private JLabel pokeName;
    private JLabel pokeLevel;
    private JLabel pokeElement;
    private JLabel pokeEvolve;
    private JProgressBar healthBar;
    private JProgressBar damageBar;
    private JProgressBar mpBar;
    private JProgressBar expBar;
    private JLabel label1;
    private HomeBaseFrame thisClass;

    public HomeBaseFrame() {
        thisClass = this;
        //super("");
        initComponent();
    }

    private void initComponent() {
        Font font = new Font("Roboto", Font.PLAIN, 25);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Menggunakan null layout

        // Membuat label
        // Mengubah label1 menjadi label kustom dengan gambar
        ImageIcon imageIcon1 = new ImageIcon(Player.monsters.get(currentMonster).getImagePath());
        label1 = new JLabel(imageIcon1);
        label1.setBounds(50, 80, 50, 20);
        add(label1);

        // Mengubah label2 menjadi label kustom dengan gambar
        ImageIcon imageIcon2 = new ImageIcon(Player.profilPath);
        JLabel label2 = new JLabel(imageIcon2);
        label2.setBounds(50, 20, 100, 100);
        add(label2);

        // Membuat textarea dalam scrollpane untuk pesan
        textArea1 = new JTextArea(Player.playerInfo());
        textArea1.setEditable(false);
        textArea1.setFont(font);
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        add(scrollPane1);

        // Info monster
        JLabel pokeNameLabel = new JLabel("Name");
        pokeNameLabel.setFont(font);
        add(pokeNameLabel);

        pokeName = new JLabel();
        pokeName.setFont(font);
        add(pokeName);

        JLabel pokeLevelLabel = new JLabel("Level");
        pokeLevelLabel.setFont(font);
        add(pokeLevelLabel);

        pokeLevel = new JLabel();
        pokeLevel.setFont(font);
        add(pokeLevel);

        JLabel pokeElementLabel = new JLabel("Element");
        pokeElementLabel.setFont(font);
        add(pokeElementLabel);

        pokeElement = new JLabel();
        pokeElement.setFont(font);
        add(pokeElement);

        JLabel pokeEvolveLabel = new JLabel("Evolve");
        pokeEvolveLabel.setFont(font);
        add(pokeEvolveLabel);

        pokeEvolve = new JLabel();
        pokeEvolve.setFont(font);
        add(pokeEvolve);

        // Membuat bar untuk kesehatan, damage, magic point, dan exp
        healthBar = new JProgressBar(0, 2000);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.green);
        JLabel healthInfo = new JLabel("Health");
        add(healthInfo);
        add(healthBar);

        damageBar = new JProgressBar(0, 500);
        damageBar.setStringPainted(true);
        damageBar.setForeground(Color.red);
        JLabel damageInfo = new JLabel("Damage");
        add(damageInfo);
        add(damageBar);

        mpBar = new JProgressBar(0, 1000);
        mpBar.setStringPainted(true);
        mpBar.setForeground(Color.blue);
        JLabel mpInfo = new JLabel("Magic Point");
        add(mpInfo);
        add(mpBar);

        expBar = new JProgressBar(0, 100);
        expBar.setStringPainted(true);
        expBar.setForeground(Color.orange);
        JLabel expInfo = new JLabel("EXP");
        add(expInfo);
        add(expBar);

        // Membuat tombol
        JButton button1 = new JButton("Prev");
        JButton button2 = new JButton("Next");
        JButton button3 = new JButton("Level Up");
        JButton button4 = new JButton("Enter Dungeon");
        JButton button5 = new JButton("Revive all mosnter");
        JButton button6 = new JButton("Evolve");
        JButton button7 = new JButton("jButton7");
        JButton button8 = new JButton("Save");

        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(button7);
        add(button8);

        // Menambahkan ActionListener ke setiap tombol
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCurrentMonster(-1);
                updateMonsterInfo();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCurrentMonster(1);
                updateMonsterInfo();
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        levelUpComponent();
                    }
                });
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        selectMonsterComponent();
                    }
                });
            }
        });

        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Monster monster : Player.monsters) {
                    monster.heal(999999999);
                    monster.setMp(999999999);
                }
            }
        });

        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                evolveCompoment();
            }
        });

        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InventorySystem inventorySystem = new InventorySystem(thisClass);
                inventorySystem.setVisible(true);
                updateMonsterInfo();
            }
        });

        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameManager.saveData();
                JOptionPane.showMessageDialog(HomeBaseFrame.this, "Your progress saved");
            }
        });

        // Menambahkan teks secara otomatis saat frame ditampilkan
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                textArea1.setText(Player.playerInfo());
                updateMonsterInfo();
            }
        });

        // Mengatur ulang posisi dan ukuran komponen saat ukuran jendela berubah
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();

                // Mengatur posisi dan ukuran label
                label1.setBounds(300, 190, 500, 460);
                label2.setBounds(50, 20, 150, 150);

                // Mengatur posisi dan ukuran scrollpane
                scrollPane1.setBounds(210, 20, width - 220, 150);

                pokeNameLabel.setBounds(900, 200, 200, 30);
                pokeName.setBounds(1100, 200, 200, 30);

                pokeLevelLabel.setBounds(900, 240, 200, 30);
                pokeLevel.setBounds(1100, 240, 200, 30);

                pokeElementLabel.setBounds(900, 280, 200, 30);
                pokeElement.setBounds(1100, 280, 200, 30);

                pokeEvolveLabel.setBounds(900, 320, 200, 30);
                pokeEvolve.setBounds(1100, 320, 200, 30);

                // Mengatur posisi dan ukuran progress bar
                healthInfo.setBounds(900, 360, 200, 30);
                healthBar.setBounds(1100, 360, 400, 30);

                damageInfo.setBounds(900, 400, 200, 30);
                damageBar.setBounds(1100, 400, 400, 30);

                mpInfo.setBounds(900, 440, 200, 30);
                mpBar.setBounds(1100, 440, 400, 30);

                expInfo.setBounds(900, 480, 200, 30);
                expBar.setBounds(1100, 480, 400, 30);

                // Mengatur posisi dan ukuran tombol
                button4.setBounds(50, 300, 150, 50);
                button7.setBounds(50, 370, 150, 50);
                button5.setBounds(50, 440, 150, 50);
                button1.setBounds(300, 660, 245, 50);
                button2.setBounds(555, 660, 245, 50);
                button3.setBounds(810, 660, 245, 50);
                button6.setBounds(1065, 660, 245, 50);
                button8.setBounds(50, 510, 150, 50);
            }
        });

        // Menampilkan frame
        setSize(1500, 900);
        setVisible(true);
    }

    private void levelUpComponent() {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        JLabel epLabel = new JLabel("Masukan EP:");
        JTextField epField = new JTextField(10);
        JLabel infoEpLabel = new JLabel("");
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Masukan EP
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(epLabel, constraints);

        constraints.gridx = 1;
        panel.add(epField, constraints);

        // Total EP anda
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2; // Mengatur gridwidth menjadi 2 untuk menggabungkan dua kolom
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(infoEpLabel, constraints);
        constraints.gridwidth = 1; // Mengatur gridwidth menjadi 2 untuk menggabungkan dua kolom

        // Buttons
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(cancelButton, constraints);

        constraints.gridx = 1;
        panel.add(okButton, constraints);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ep = Integer.parseInt(epField.getText());/////exception
                    int playerEp = GameHomebase.levelUpMonster(currentMonster,ep);
                    if (playerEp >= 0) {
                        epLabel.setText("Level Up succes");
                        updateMonsterInfo();
                        frame.setVisible(false);
                    }else{
                        infoEpLabel.setText("Your Ep is not enough");
                    }
                    // GameManager.saveData();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        frame.add(panel);
        frame.setTitle("Level Up Frame");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void evolveCompoment() {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        JLabel elementLabel = new JLabel("Pilih Element:");
        JLabel infoEvolveLabel = new JLabel("");
        JRadioButton apiButton = new JRadioButton("Api");
        JRadioButton tanahButton = new JRadioButton("Tanah");
        JRadioButton anginButton = new JRadioButton("Angin");
        JRadioButton airButton = new JRadioButton("Air");
        JRadioButton esButton = new JRadioButton("Es");
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");

        // Button group for radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(apiButton);
        group.add(tanahButton);
        group.add(anginButton);
        group.add(airButton);
        group.add(esButton);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Pilih Element Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(elementLabel, constraints);

        // Radio Buttons
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridy = 1;
        panel.add(apiButton, constraints);

        constraints.gridy = 2;
        panel.add(tanahButton, constraints);

        constraints.gridy = 3;
        panel.add(anginButton, constraints);

        constraints.gridy = 4;
        panel.add(airButton, constraints);

        constraints.gridy = 5;
        panel.add(esButton, constraints);

        // Total EP anda
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(infoEvolveLabel, constraints);
        constraints.gridwidth = 1;

        // Buttons
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(cancelButton, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(okButton, constraints);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Element selectedElement = null;
                if (apiButton.isSelected()) {
                    selectedElement = Element.API;
                } else if (tanahButton.isSelected()) {
                    selectedElement = Element.TANAH;
                } else if (anginButton.isSelected()) {
                    selectedElement = Element.ANGIN;
                } else if (airButton.isSelected()) {
                    selectedElement = Element.AIR;
                } else if (esButton.isSelected()) {
                    selectedElement = Element.ES;
                }

                if (selectedElement != null) {
                    if (GameHomebase.evolveMonster(currentMonster, selectedElement).equalsIgnoreCase("success")) {
                        updateMonsterInfo();
                        frame.setVisible(false);
                    }
                    infoEvolveLabel.setText(GameHomebase.evolveMonster(currentMonster, selectedElement));
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an element.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        frame.add(panel);
        frame.setTitle("Select Element");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void selectMonsterComponent() {
        JFrame frame = new JFrame("Select Monsters for Dungeon");
        frame.setLayout(new BorderLayout());

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(Player.monsters.size(), 1));

        // Membuat checkbox untuk setiap monster
        JCheckBox[] checkBoxes = new JCheckBox[Player.monsters.size()];
        for (int i = 0; i < Player.monsters.size(); i++) {
            checkBoxes[i] = new JCheckBox((i + 1)+ ", "+Player.monsters.get(i).getName() + "(" + Player.monsters.get(i).getElement() + ")");
            checkBoxPanel.add(checkBoxes[i]);
        }

        // Membuat panel untuk tombol
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Menambahkan panel checkbox dan panel tombol ke frame
        frame.add(checkBoxPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        ArrayList<Monster> selectedMonsters = new ArrayList<>();

        // Menambahkan action listener untuk tombol OK
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedCount = 0;

                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        selectedCount++;
                        int i = checkBox.getText().charAt(0) - '0';
                        selectedMonsters.add(Player.monsters.get(i-1));
                    }
                }

                if (selectedCount > 3 ) {
                    JOptionPane.showMessageDialog(frame, "You can only select up to 3 monsters.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                    selectedMonsters.removeAll(selectedMonsters);
                } else if(selectedCount <= 0){
                    JOptionPane.showMessageDialog(frame, "You have to select atleast 1 monster", "Selection Error", JOptionPane.ERROR_MESSAGE);
                    selectedMonsters.removeAll(selectedMonsters);
                }else {
                    // Proses monster yang dipilih
                    new Dungeon(selectedMonsters);
                    System.out.println("Selected Monsters: " + selectedMonsters);
                    frame.setVisible(false);
                    dispose();
                }
            }
        });

        // Menambahkan action listener untuk tombol Cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setCurrentMonster(int i) {
        int size = Player.monsters.size();

        if (size == 0) {
            return;
        }

        currentMonster = (currentMonster + i) % size;

        if (currentMonster < 0) {
            currentMonster += size;
        }
    }

    public void updateMonsterInfo() {
        Monster currentMonsterObj = Player.monsters.get(currentMonster);

        textArea1.setText(Player.playerInfo());

        pokeName.setText(": " + currentMonsterObj.getName());
        pokeLevel.setText(": " + currentMonsterObj.getLevel());
        pokeElement.setText(": " + currentMonsterObj.getElement());
        pokeEvolve.setText(": " + currentMonsterObj.getCanEvolve());

        healthBar.setValue((int) currentMonsterObj.getMaksHp());
        damageBar.setValue((int) currentMonsterObj.getBaseDamage());
        mpBar.setValue((int) currentMonsterObj.getMaksMp());
        expBar.setValue((int) currentMonsterObj.getEp());

        // Mengganti gambar pada label1
        ImageIcon imageIcon1 = new ImageIcon(currentMonsterObj.getImagePath());
        label1.setIcon(imageIcon1);
    }

    public static void main(String[] args) {
        GameManager.setFile("abyan");
        GameManager.loadData();
        System.out.println(Player.monsters.get(0).toString());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomeBaseFrame();
            }
        });
    }
}
