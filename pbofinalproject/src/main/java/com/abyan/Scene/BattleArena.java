package com.abyan.Scene;

import javax.swing.JOptionPane;

import com.abyan.Frame.BattleArenaFrame;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class BattleArena {
    public BattleArenaFrame battleArenaFrame;
    public Monster playerMonster;
    public Monster enemyMonster;

    public BattleArena(Monster playerMonster, Monster enemyMonster) {
        this.playerMonster = playerMonster;
        this.enemyMonster = enemyMonster;
        this.battleArenaFrame = new BattleArenaFrame(this);
    }

    public void enemyTurn() {
        int action = GameManager.randomNum(0, 2); // Randomly select an action: 0 = basic, 1 = special, 2 = element
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
    }

    public void playerTurn(int skill) {
        switch (skill) {
            case 0:
                surrender(GameManager.randomNum(0,5));
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
        enemyTurn();
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
        playerMonster.useItem(item);
    }

    public void surrender(int i){
        if (i == 3) {
            battleArenaFrame.surrender();
        }
    }
}
