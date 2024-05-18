package com.abyan.Scene;

import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

import java.util.*;

abstract class HomeBase {
    public abstract void storeMonster(Monster monster);

    public abstract void levelUpMonster(Monster monster);

    public abstract Monster evolveMonster(Monster monster, Element element);

    public abstract void enterDungeon(Monster monster0,Monster monster1,Monster monster2);
}

public class GameHomebase extends HomeBase {
    public static Monster pickMonster(String name) {
        return null;
    }

    public void storeMonster(Monster monster) {
        Player.monsters.add(monster);
    }

    public void levelUpMonster(Monster monster) {
        monster.upLevel();
    }

    public Monster evolveMonster(Monster monster, Element element) {
        if (Math.abs(monster.getElement().getValue() - element.getValue()) == 1) {
            return evolving(monster, element);
        } else {
            System.out.println("Invalid evolution. Element change must be adjacent.");
            return monster;
        }
    }

    public Monster evolving(Monster monster, Element element) {
        switch (element.getValue()) {
            case 0:
                return new Api(monster.getName(), monster.getLevel(), monster.getExp());
            case 1:
                return new Tanah(monster.getName(), monster.getLevel(), monster.getExp());
            case 2:
                return new Angin(monster.getName(), monster.getLevel(), monster.getExp());
            case 3:
                return new Air(monster.getName(), monster.getLevel(), monster.getExp());
            case 4:
                return new Es(monster.getName(), monster.getLevel(), monster.getExp());
            default:
                System.out.println("Evolve didn't work");
                return monster;
        }
    }

    public void enterDungeon(Monster monster0,Monster monster1,Monster monster2) {

        // Memasuki dungeon dengan sudah memilih monster
    }

}