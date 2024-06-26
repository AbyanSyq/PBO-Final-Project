package com.abyan.Scene;

import com.abyan.Manager.*;
import com.abyan.Object.*;

public class GameHomebase  {
    public GameHomebase(){
        
    }
    public static void storeMonster(Monster monster) {
        Player.monsters.add(monster);
    }
    public static int levelUpMonster(int n, int ep) {
        if (Player.ep >= ep) {
            Player.monsters.get(n).upLevel(ep);
            Player.ep -= ep;
            return Player.ep;
        }else{
            return Player.ep - ep;
        }
    }
    public static String evolveMonster(int n, Element element){
        Monster monster = Player.monsters.get(n);

        if (!monster.getCanEvolve()) {
            return "anda hanya dapat evolusi 1 kali dalam 1 level";
        }
        if (Math.abs(monster.getElement().getValue() - element.getValue()) == 1) {
            try {
                Player.monsters.set(n, evolving(monster, element));
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            return "Invalid evolution. Element change must be adjacent.";
        }
        return "success";
    }

    public static Monster evolving(Monster monster, Element element)  throws GameException{
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
                throw new GameException("Evolusi tidak berhasil");
        }
    }
}