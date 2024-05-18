package com.abyan;
import java.util.Scanner;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;
import com.abyan.Manager.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Player.name = scan.nextLine();
        GameManager.setFile(Player.name);
        GameManager.dataMonster = GameManager.loadData(GameManager.fileMonster);

        GameHomebase gameHomebase = new GameHomebase();
        gameHomebase.storeAllMonster();
        if (gameHomebase.monster != null) {
            for (Monster mons : gameHomebase.monster.values()) {
                System.out.println("Monster Name: " + mons.getName());
                System.out.println("Element: " + mons.getElement());
                System.out.println("Level: " + mons.getLevel());
                System.out.println("Exp: " + mons.getExp());
                System.out.println("Base damage: " + mons.getBaseDamage());
                System.out.println("hp: " + mons.getHp());
                System.out.println("makshp: " + mons.getMaksHp());
                System.out.println();
            }
        }
    }
}
