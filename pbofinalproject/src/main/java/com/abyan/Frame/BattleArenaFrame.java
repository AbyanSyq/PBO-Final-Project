package com.abyan.Frame;

import com.abyan.Manager.Player;
import com.abyan.Object.*;
import com.abyan.Scene.BattleArena;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BattleArenaFrame {
    private JFrame frame;
    private BattleArena battleArena;
    private JButton normalAttackButton;
    private JButton specialAttackButton;
    private JButton elementAttackButton; // New button for Element Attack
    private JButton usePotionButton;
    private JButton surrenderButton;
    private JProgressBar playerHPBar;
    private JProgressBar playerMPBar;
    private JProgressBar enemyHPBar;
    private JProgressBar enemyMPBar;
    private JLabel playerImageLabel;
    private JLabel enemyImageLabel;
    private Random random;

    public BattleArenaFrame(BattleArena battleArena) {
        this.battleArena = battleArena;
        this.random = new Random();

        frame = new JFrame("Fighting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);
        frame.setLayout(new GridBagLayout());

        createComponents();

        frame.setVisible(true);
        updateLabels();
    }

    private void createComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Player Stats Panel
        JPanel playerStatsPanel = new JPanel(new GridLayout(2, 1));
        playerStatsPanel.add(createHPBar(battleArena.playerMonster, "Player"));
        playerStatsPanel.add(createMPBar(battleArena.playerMonster, "Player"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        frame.add(playerStatsPanel, gbc);

        // Enemy Stats Panel
        JPanel enemyStatsPanel = new JPanel(new GridLayout(2, 1));
        enemyStatsPanel.add(createHPBar(battleArena.enemyMonster, "Enemy"));
        enemyStatsPanel.add(createMPBar(battleArena.enemyMonster, "Enemy"));
        gbc.gridx = 2;
        gbc.gridy = 0;
        frame.add(enemyStatsPanel, gbc);

        // Player Image
        playerImageLabel = new JLabel(new ImageIcon(battleArena.playerMonster.getImagePath()));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        frame.add(playerImageLabel, gbc);

        // VS Label
        JLabel vsLabel = new JLabel("VS");
        vsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vsLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        frame.add(vsLabel, gbc);

        // Enemy Image
        enemyImageLabel = new JLabel(new ImageIcon(battleArena.enemyMonster.getImagePath()));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        frame.add(enemyImageLabel, gbc);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 5)); // Update grid layout to 1x5 for the new button
        normalAttackButton = new JButton("Normal Attack");
        normalAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleArena.playerTurn(1);
            }
        });
        buttonsPanel.add(normalAttackButton);

        specialAttackButton = new JButton("Special Attack");
        specialAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleArena.playerTurn(2);
            }
        });
        buttonsPanel.add(specialAttackButton);

        elementAttackButton = new JButton("Element Attack"); // New button for Element Attack
        elementAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleArena.playerTurn(3);
            }
        });
        buttonsPanel.add(elementAttackButton);

        usePotionButton = new JButton("Use Potion");
        usePotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPotionDialog();
            }
        });
        buttonsPanel.add(usePotionButton);

        surrenderButton = new JButton("Kabur");
        surrenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleArena.playerTurn(0);
                System.out.println("You surrendered!");
            }
        });
        buttonsPanel.add(surrenderButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        frame.add(buttonsPanel, gbc);
    }

    private JPanel createHPBar(Monster monster, String name) {
        JProgressBar bar = new JProgressBar();
        bar.setMinimum(0);
        bar.setMaximum((int) monster.getMaksHp());
        bar.setValue((int) monster.getHp());
        bar.setStringPainted(true);
        bar.setPreferredSize(new Dimension(300, 30));
        JLabel nameLabel = new JLabel(name + " HP:");
        nameLabel.setPreferredSize(new Dimension(100, 30));
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(nameLabel);
        panel.add(bar);

        if (name.equals("Player")) {
            playerHPBar = bar;
        } else {
            enemyHPBar = bar;
        }

        return panel;
    }

    private JPanel createMPBar(Monster monster, String name) {
        JProgressBar bar = new JProgressBar();
        bar.setMinimum(0);
        bar.setMaximum((int) monster.getMaksMp());
        bar.setValue((int) monster.getMp());
        bar.setStringPainted(true);
        bar.setPreferredSize(new Dimension(300, 30));
        JLabel nameLabel = new JLabel(name + " MP:");
        nameLabel.setPreferredSize(new Dimension(100, 30));
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(nameLabel);
        panel.add(bar);

        if (name.equals("Player")) {
            playerMPBar = bar;
        } else {
            enemyMPBar = bar;
        }

        return panel;
    }

    private void showPotionDialog() {
        JDialog dialog = new JDialog(frame, "Select a Potion", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        JPanel potionPanel = new JPanel();
        potionPanel.setLayout(new BoxLayout(potionPanel, BoxLayout.Y_AXIS));

        ButtonGroup group = new ButtonGroup();
        ArrayList<JRadioButton> radioButtons = new ArrayList<>();

        if (Player.healPotion > 0) {
            JRadioButton radioButton = new JRadioButton(Player.items.get(0).getName());
            radioButton.setActionCommand(Player.items.get(0).getName());
            group.add(radioButton);
            radioButtons.add(radioButton);
            potionPanel.add(radioButton);
        }
        if (Player.damagePotion > 0) {
            JRadioButton radioButton = new JRadioButton(Player.items.get(1).getName());
            radioButton.setActionCommand(Player.items.get(1).getName());
            group.add(radioButton);
            radioButtons.add(radioButton);
            potionPanel.add(radioButton);
        }

        JButton useButton = new JButton("Use");
        useButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPotionName = group.getSelection().getActionCommand();
                for (Item item : Player.items) {
                    if (item.getName().equals(selectedPotionName)) {
                        battleArena.useItem(item);
                        dialog.dispose();
                        updateLabels();
                        battleArena.enemyTurn();
                        break;
                    }
                }
            }
        });

        dialog.add(potionPanel, BorderLayout.CENTER);
        dialog.add(useButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public void showDamageDialog(String name, double damage) {
        String message;
        Color color;
        if (damage > 0) {
            message = name + " Hp : -" + damage;
            color = Color.RED;
        } else {
            message = name + " Hp : +" + (-damage);
            color = Color.GREEN;
        }
        JLabel label = new JLabel(message);
        label.setForeground(color);
        JOptionPane.showMessageDialog(frame, label);
    }

    public void showMassage(String massage) {
        JOptionPane.showMessageDialog(frame, massage);
    }

    public void updateLabels() {
        playerHPBar.setValue((int) battleArena.playerMonster.getHp());
        playerMPBar.setValue((int) battleArena.playerMonster.getMp());
        enemyHPBar.setValue((int) battleArena.enemyMonster.getHp());
        enemyMPBar.setValue((int) battleArena.enemyMonster.getMp());
    }

    public void exit() {
        frame.dispose();
        // System.exit(0);
    }
}
