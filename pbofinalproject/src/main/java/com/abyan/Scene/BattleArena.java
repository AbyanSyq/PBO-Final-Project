package com.abyan.Scene;

import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class BattleArena {
    private Monster playerMonster;
    private Monster enemyMonster;

    BattleArena(Monster playerMonster,Monster enemyMonster){
        this.playerMonster = playerMonster;
        this.enemyMonster = enemyMonster;
    }
    public void enemyTurn(){
        
    }
    public void playerTurn(){
        //Display tombol
    }
    public void basicAttack(){
        playerMonster.basicAttack(enemyMonster);
    }
    public void specialAttack(){
        playerMonster.specialAttack(enemyMonster);
    }
    public void elementAttack(){
        playerMonster.elementAttack(enemyMonster);
    }

    public void battle(){
        int turn = 0;
        while (playerMonster.getHp() > 0 && enemyMonster.getHp() > 0) {
            if (turn % 2 == 0) {
                playerTurn();
            }else{
                enemyTurn();
            }
        }
    }
}
