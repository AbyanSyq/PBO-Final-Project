package com.abyan.Frame;

import com.abyan.Manager.Player;
import com.abyan.Object.Monster;
import com.abyan.Scene.*;
//import com.abyan.Scene.FightingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DungeonGame extends JFrame {
    public Dungeon dungeon;

    private JPanel gamePanel;
    private JLabel[][] gridLabels;
    private HashMap<Point, Monster> enemyMonster;
    private HashMap<Point, JLabel> treasureLabels;
    private ArrayList<Point> monsterLocations;
    private ArrayList<Point> treasureLocations;
    private Point playerPosition;
    private ImageIcon playerIcon;
    private ImageIcon treasureIcon;

    public DungeonGame(Dungeon dungeon) {
        this.dungeon = dungeon;
        playerIcon = new ImageIcon(dungeon.monsters.get(0).getImagePath());
        treasureIcon = loadTreasureIcon();
        initComponent();
        initGame();
    }

    private ImageIcon loadTreasureIcon() {
        ImageIcon treasure = new ImageIcon("MonsterImage/MonsterApi.png");
        Image treasureImage = treasure.getImage();
        treasureImage = treasureImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(treasureImage);
    }

    private void initComponent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem homebaseMenuItem = new JMenuItem("Return to Homebase");

        homebaseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeBaseFrame();
                dispose();
            }
        });

        menu.add(homebaseMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        gamePanel = new JPanel(new GridLayout(10, 10));
        gridLabels = new JLabel[10][10];
        enemyMonster = new HashMap<>();
        treasureLabels = new HashMap<>();
        monsterLocations = new ArrayList<>();
        treasureLocations = new ArrayList<>();

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
            while (monsterLocations.contains(location) || treasureLocations.contains(location)) {
                x = random.nextInt(10);
                y = random.nextInt(10);
                location = new Point(x, y);
            }
            enemyMonster.put(location, dungeon.enemyMonsters.get(i));
            monsterLocations.add(location);
            gridLabels[y][x].setIcon(new ImageIcon(dungeon.enemyMonsters.get(i).getImagePath()));
        }

        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Point location = new Point(x, y);
            while (monsterLocations.contains(location) || treasureLocations.contains(location)) {
                x = random.nextInt(10);
                y = random.nextInt(10);
                location = new Point(x, y);
            }
            JLabel label = new JLabel(treasureIcon);
            treasureLabels.put(location, label);
            treasureLocations.add(location);
            gridLabels[y][x].setIcon(treasureIcon);
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
                if (playerPosition.y > 0)
                    newPlayerPosition.y--;
                break;
            case KeyEvent.VK_DOWN:
                if (playerPosition.y < 9)
                    newPlayerPosition.y++;
                break;
            case KeyEvent.VK_LEFT:
                if (playerPosition.x > 0)
                    newPlayerPosition.x--;
                break;
            case KeyEvent.VK_RIGHT:
                if (playerPosition.x < 9)
                    newPlayerPosition.x++;
                break;
        }

        if (!newPlayerPosition.equals(playerPosition)) {
            movePlayer(newPlayerPosition);
        }
    }

    private void movePlayer(Point newPlayerPosition) {
        if (monsterLocations.contains(newPlayerPosition)) {
            Monster monster = enemyMonster.get(newPlayerPosition);
            if (monster != null) {
                startBattle(monster);
            } else {
                System.err.println("Error: Enemy monster is null.");
            }
        } else if (treasureLocations.contains(newPlayerPosition)) {
            collectTreasure(newPlayerPosition);
        } else {
            gridLabels[playerPosition.y][playerPosition.x].setIcon(null);
            playerPosition = newPlayerPosition;
            gridLabels[playerPosition.y][playerPosition.x].setIcon(playerIcon);
        }
    }

    private void startBattle(Monster monster) {
        JFrame frame = new JFrame("Select Monsters for Battle");
        frame.setLayout(new BorderLayout());

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(dungeon.monsters.size(), 1));

        JCheckBox[] checkBoxes = new JCheckBox[dungeon.monsters.size()];
        for (int i = 0; i < dungeon.monsters.size(); i++) {
            checkBoxes[i] = new JCheckBox((i + 1) + ", " + dungeon.monsters.get(i).getName() + "(" + dungeon.monsters.get(i).getElement() + ")");
            checkBoxPanel.add(checkBoxes[i]);
        }

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        frame.add(checkBoxPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        Monster[] selectedMonsters = new Monster[1];

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedCount = 0;

                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        selectedCount++;
                        int i = checkBox.getText().charAt(0) - '0';
                        selectedMonsters[0] = dungeon.monsters.get(i - 1);
                    }
                }

                if (selectedCount > 1) {
                    JOptionPane.showMessageDialog(frame, "You can only select 1 monster.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                } else if (selectedCount <= 0) {
                    JOptionPane.showMessageDialog(frame, "You have to select 1 monster.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (selectedMonsters[0] != null && monster != null) {
                        new BattleArena(selectedMonsters[0],monster);
                        System.out.println(selectedMonsters[0].getName() + " VS " + monster.getLevel());
                        frame.setVisible(false);
                        dispose();
                    } else {
                        System.err.println("Error: Selected player monster or enemy monster is null.");
                    }
                }
            }
        });

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

    private void collectTreasure(Point treasurePosition) {
        treasureLocations.remove(treasurePosition);
        gridLabels[treasurePosition.y][treasurePosition.x].setIcon(null);
        Player.ep += 100;
        JOptionPane.showMessageDialog(this, "You found a treasure! EP increased by 100.");
        gridLabels[playerPosition.y][playerPosition.x].setIcon(null);
        playerPosition = treasurePosition;
        gridLabels[playerPosition.y][playerPosition.x].setIcon(playerIcon);
    }

    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon(Player.monsters);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DungeonGame(dungeon).setVisible(true);
            }
        });
    }
}
