package com.abyan.Scene;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import javax.swing.SwingUtilities;

import com.abyan.Frame.DungeonGame;
import com.abyan.Manager.Element;
import com.abyan.Manager.GameManager;
import com.abyan.Object.Monster;

public class Dungeon {
    public ArrayList<Monster> monsters;
    public ArrayList<Monster> enemyMonsters = new ArrayList();
    public DungeonGame dungeonFrame;



    public Dungeon(ArrayList<Monster> monsters){
        this.monsters = monsters;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DungeonGame(Dungeon.this).setVisible(true);
            }
        });
        for (int index = 0; index < 5; index++) {
            enemyMonsters.add(GameManager.monsterGen("", Element.getElementByValue(index),0, 80.0, 15.0, 20.0,0));
            enemyMonsters.get(index).setByLV(getPlayerMonsterLV());
            enemyMonsters.get(index).heal(999999999);
            enemyMonsters.get(index).setMp(999999999);
        }
    }
    public int getPlayerMonsterLV(){
        int highgestLV =0;
        for (Monster monster : monsters) {
            if (monster.getLevel() > highgestLV) {
                highgestLV = monster.getLevel();
            }
        }
        System.out.println("higest enemy lv" + highgestLV);
        return highgestLV;
    }

    public void battle(Monster monster){
        //Menampilkan musuh
        //Menampilkan tombol enter atau kabur
        //masuk battle arne 
    }
}