package com.abyan.Scene;

import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

import java.util.*;

// abstract class HomeBase {
//     public abstract void storeMonster(Monster monster);

//     public abstract void levelUpMonster(Monster monster);

//     public abstract Monster evolveMonster(Monster monster, Element element);

//     public abstract void enterDungeon(Monster monster0,Monster monster1,Monster monster2);
// }

public class GameHomebase  {
    public static Monster pickMonster(String name) {
        return null;
    }

    public static void storeMonster(Monster monster) {
        Player.monsters.add(monster);
    }
    public static void levelUpMonster(int n) {
        Player.monsters.get(n).upLevel();
    }
    public static void levelUpMonster(Monster monster) {
        monster.upLevel();
    }
    public static void evolveMonster(int n, Element element){
        Monster temp = Player.monsters.get(n);
        Player.monsters.set(n, evolveMonster(temp, element));
    }

    public static Monster evolveMonster(Monster monster, Element element) {
        if (!monster.getCanEvolve()) {
            System.out.println("anda hanya dapat evolusi 1 kali dalam 1 level");
            return monster;
        }
        if (Math.abs(monster.getElement().getValue() - element.getValue()) == 1) {
            return evolving(monster, element);
        } else {
            System.out.println("Invalid evolution. Element change must be adjacent.");
            return monster;
        }
    }

    public static Monster evolving(Monster monster, Element element) {
        switch (element.getValue()) {
            case 0:
                return new Api(monster.getName(), monster.getLevel(),monster.getMaksHp(),monster.getBaseDamage(),monster.getMaksMp(),monster.getEp());
            case 1:
                return new Tanah(monster.getName(), monster.getLevel(),monster.getMaksHp(),monster.getBaseDamage(),monster.getMaksMp(),monster.getEp());
            case 2:
                return new Angin(monster.getName(), monster.getLevel(),monster.getMaksHp(),monster.getBaseDamage(),monster.getMaksMp(),monster.getEp());
            case 3:
                return new Air(monster.getName(), monster.getLevel(),monster.getMaksHp(),monster.getBaseDamage(),monster.getMaksMp(),monster.getEp());
            case 4:
                return new Es(monster.getName(), monster.getLevel(),monster.getMaksHp(),monster.getBaseDamage(),monster.getMaksMp(),monster.getEp());
            default:
                System.out.println("Evolve didn't work");
                return monster;
        }
    }

    public static void enterDungeon(Monster monster0,Monster monster1,Monster monster2) {

        // Memasuki dungeon dengan sudah memilih monster
    }

}