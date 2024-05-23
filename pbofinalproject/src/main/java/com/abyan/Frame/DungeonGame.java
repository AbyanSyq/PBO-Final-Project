/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.abyan.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class DungeonGame extends JFrame {
    private JPanel gamePanel;
    private JLabel[][] gridLabels;
    private HashMap<Point, JLabel> monsterLabels;
    private ArrayList<Point> monsterLocations;
    private Point playerPosition;
    private ImageIcon playerIcon;
    private List<ImageIcon> monsterIcons;

    public DungeonGame() {
        playerIcon = new ImageIcon("MonsterImage/MonsterTanah.png");
        monsterIcons = loadMonsterIcons();
        initComponent();
        initGame();
    }

    private List<ImageIcon> loadMonsterIcons() {
        List<ImageIcon> icons = new ArrayList<>();
        // Load different monster images
        
        icons.add(new ImageIcon("MonsterImage/MonsterAngin.png"));
        // Add more images as needed
        return icons;
    }

    private void initComponent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        gamePanel = new JPanel(new GridLayout(10, 10));
        gridLabels = new JLabel[10][10];
        monsterLabels = new HashMap<>();
        monsterLocations = new ArrayList<>();

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                gridLabels[y][x] = label;
                gamePanel.add(label);
            }
        }

        add(gamePanel);
    }

    private void initGame() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Point location = new Point(x, y);
            while (monsterLocations.contains(location)) {
                x = random.nextInt(10);
                y = random.nextInt(10);
                location = new Point(x, y);
            }
            // Randomly select a monster icon
            ImageIcon monsterIcon = monsterIcons.get(random.nextInt(monsterIcons.size()));
            JLabel label = new JLabel(monsterIcon);
            monsterLabels.put(location, label);
            monsterLocations.add(location);
            gridLabels[y][x].setIcon(monsterIcon);
        }

        playerPosition = new Point(0, 0);
        gridLabels[playerPosition.y][playerPosition.x].setIcon(playerIcon);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        setFocusable(true);
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Point newPlayerPosition = new Point(playerPosition);

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (playerPosition.y > 0) newPlayerPosition.y--;
                break;
            case KeyEvent.VK_DOWN:
                if (playerPosition.y < 9) newPlayerPosition.y++;
                break;
            case KeyEvent.VK_LEFT:
                if (playerPosition.x > 0) newPlayerPosition.x--;
                break;
            case KeyEvent.VK_RIGHT:
                if (playerPosition.x < 9) newPlayerPosition.x++;
                break;
        }

        if (!newPlayerPosition.equals(playerPosition)) {
            movePlayer(newPlayerPosition);
        }
    }

    private void movePlayer(Point newPlayerPosition) {
        if (monsterLocations.contains(newPlayerPosition)) {
            startBattle(monsterLabels.get(newPlayerPosition));
        } else {
            gridLabels[playerPosition.y][playerPosition.x].setIcon(null);
            playerPosition = newPlayerPosition;
            gridLabels[playerPosition.y][playerPosition.x].setIcon(playerIcon);
        }
    }

    private void startBattle(JLabel monsterLabel) {
        //pick Monster
        // For simplicity, we'll just remove the monster in this example
        monsterLocations.remove(playerPosition);
        gridLabels[playerPosition.y][playerPosition.x].setIcon(null);
        playerPosition = playerPosition;
        gridLabels[playerPosition.y][playerPosition.x].setIcon(playerIcon);

        JOptionPane.showMessageDialog(this, "You encountered a monster! The battle is over.");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DungeonGame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
