
package com.abyan.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightingGame {
    private JFrame frame;
    private Monster playerMonster;
    private Monster enemyMonster;
    private JButton normalAttackButton;
    private JButton specialAttackButton;
    private JButton usePotionButton;
    private JButton surrenderButton;
    private JProgressBar playerHPBar;
    private JProgressBar playerMPBar;
    private JProgressBar enemyHPBar;
    private JProgressBar enemyMPBar;
    private JLabel playerImageLabel;
    private JLabel enemyImageLabel;

    public FightingGame() {
        playerMonster = new Monster("Player", 100, 50);
        enemyMonster = new Monster("Enemy", 100, 50);

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
        playerStatsPanel.add(createHPBar(playerMonster, "Player"));
        playerStatsPanel.add(createMPBar(playerMonster, "Player"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        frame.add(playerStatsPanel, gbc);

        // Enemy Stats Panel
        JPanel enemyStatsPanel = new JPanel(new GridLayout(2, 1));
        enemyStatsPanel.add(createHPBar(enemyMonster, "Enemy"));
        enemyStatsPanel.add(createMPBar(enemyMonster, "Enemy"));
        gbc.gridx = 2;
        gbc.gridy = 0;
        frame.add(enemyStatsPanel, gbc);

        // Player Image
        playerImageLabel = createImageLabel(playerMonster.getName());
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
        enemyImageLabel = createImageLabel(enemyMonster.getName());
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        frame.add(enemyImageLabel, gbc);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        normalAttackButton = new JButton("Normal Attack");
        normalAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // playerMonster.attack
                // playerMonster.attack(enemyMonster);
                updateLabels();
            }
        });
        buttonsPanel.add(normalAttackButton);

        specialAttackButton = new JButton("Special Attack");
        specialAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMonster.specialAttack(enemyMonster);
                updateLabels();
            }
        });
        buttonsPanel.add(specialAttackButton);

        usePotionButton = new JButton("Use Potion");
        usePotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMonster.usePotion();
                updateLabels();
            }
        });
        buttonsPanel.add(usePotionButton);

        surrenderButton = new JButton("Surrender");
        surrenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        bar.setMaximum(monster.getMaxHP());
        bar.setValue(monster.getHP());
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
        bar.setMaximum(monster.getMaxMP());
        bar.setValue(monster.getMP());
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

    private JLabel createImageLabel(String name) {
        ImageIcon icon = new ImageIcon(name + ".png");
        Image image = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(400, 400));
        return label;
    }

    private void updateLabels() {
        playerHPBar.setValue(playerMonster.getHP());
        playerMPBar.setValue(playerMonster.getMP());
        enemyHPBar.setValue(enemyMonster.getHP());
        enemyMPBar.setValue(enemyMonster.getMP());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FightingGame();
            }
        });
    }
}

class Monster {
    private String name;
    private int hp;
    private int mp;
    private int maxHP;
    private int maxMP;

    public Monster(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.maxHP = hp;
        this.maxMP = mp;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMP() {
        return mp;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public void attack(Monster target) {
        target.hp -= 10;
        if (target.hp < 0) target.hp = 0;
    }

    public void specialAttack(Monster target) {
        target.hp -= 20;
        mp -= 10;
        if (target.hp < 0) target.hp = 0;
        if (mp < 0) mp = 0;
    }

    public void usePotion() {
        hp += 10;
        mp += 5;
        if (hp > maxHP) hp = maxHP;
        if (mp > maxMP) mp = maxMP;
    }
}
