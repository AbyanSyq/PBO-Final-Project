package com.abyan.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.abyan.Frame.BattleArenaFrame;
import com.abyan.Manager.*;
import com.abyan.Object.*;

public class BattleArena {
    public Dungeon dungeon;
    public BattleArenaFrame battleArenaFrame;
    public Monster playerMonster;
    public Monster enemyMonster;

    public BattleArena(Monster playerMonster, Monster enemyMonster, Dungeon dungeon) {
        this.dungeon = dungeon;
        this.playerMonster = playerMonster;
        this.enemyMonster = enemyMonster;
        this.battleArenaFrame = new BattleArenaFrame(this);
    }

    public void enemyTurn() {
        int action = GameManager.randomNum(0, 2); 
        double oldHp = playerMonster.getHp();
        String actionName = "";

        switch (action) {
            case 0:
                enemyMonster.basicAttack(playerMonster);
                actionName = "Enemy used Basic Attack!";
                break;
            case 1:
                enemyMonster.specialAttack(playerMonster);
                actionName = "Enemy used Special Attack!";
                break;
            case 2:
                enemyMonster.elementAttack(playerMonster);
                actionName = "Enemy used Element Attack!";
                break;
        }
        double damage = oldHp - playerMonster.getHp();
        battleArenaFrame.showMassage(actionName);
        battleArenaFrame.showDamageDialog(playerMonster.getName(), damage);
        battleArenaFrame.updateLabels();
        checkPlayerHp();
    }

    public void playerTurn(int skill) {
        switch (skill) {
            case 0:
                try {
                    surrender(GameManager.randomNum(0, 5));
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 1:
                basicAttack(playerMonster, enemyMonster);
                break;
            case 2:
                specialAttack(playerMonster, enemyMonster);
                break;
            case 3:
                elementAttack(playerMonster, enemyMonster);
                break;
            default:
                break;
        }
        battleArenaFrame.updateLabels();
        checkEnemyHp();
    }

    public void basicAttack(Monster monster1, Monster monster2) {
        double oldHp = monster2.getHp();
        monster1.basicAttack(monster2);
        double damage = oldHp - monster2.getHp();
        battleArenaFrame.showDamageDialog("Enemy", damage);
    }

    public void specialAttack(Monster monster1, Monster monster2) {
        double oldHp = monster2.getHp();
        monster1.specialAttack(monster2);
        double damage = oldHp - monster2.getHp();
        battleArenaFrame.showDamageDialog("Enemy", damage);
    }

    public void elementAttack(Monster monster1, Monster monster2) {
        double oldHp = monster2.getHp();
        monster1.elementAttack(monster2);
        double damage = oldHp - monster2.getHp();
        battleArenaFrame.showDamageDialog("Enemy", damage);
    }

    public void useItem(Item item) {
        if (item.getName().equalsIgnoreCase("Heal Potion")) {
            Player.healPotion--;
        } else if (item.getName().equalsIgnoreCase("Damage Potion")) {
            Player.damagePotion--;
        }
        playerMonster.useItem(item);
        battleArenaFrame.updateLabels();
    }

    public void surrender(int i) throws GameException {
        System.out.println(i);
        if (i == 3) {
            battleArenaFrame.showMassage("Anda berhasil kabur");
            battleArenaFrame.exit();
        } else {
            battleArenaFrame.showMassage("anda tidak berhasil kabur");
            throw new GameException("Anda tidak berhasil kabur");
        }
    }

    public void checkPlayerHp() {
        if (playerMonster.getHp() <= 0) {
            battleArenaFrame.showMassage("DEFEAT");
            battleArenaFrame.exit();
            dungeon.monsters.remove(playerMonster);
            dungeon.monsters.add(playerMonster);
        }
    }

    public void checkEnemyHp() {
        if (enemyMonster.getHp() <= 0) {
            battleArenaFrame.showMassage("VICOTRY");
            battleArenaFrame.exit();
            rekrut();
            dungeon.monsters.remove(playerMonster);
            dungeon.monsters.add(playerMonster);
            return;
        }
        enemyTurn();
    }

    public void rekrut(String name) {
        enemyMonster.setName(name);
        Player.monsters.add(enemyMonster);
        battleArenaFrame.exit();
    }

    public void rekrut() {

        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        JLabel epLabel = new JLabel("Masukan nama Monster barumu:");
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
                rekrut(epField.getText());
                frame.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.disable();
            }
        });

        frame.add(panel);
        frame.setTitle("Level Up Frame");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
