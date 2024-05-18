package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;
import com.abyan.Scene.*;
import java.util.*;

public class Monster {
    private String name;
    private double hp;
    private double maksHp;
    private int level;
    protected Element element;
    private int exp;
    private double baseDamage;
    
    private boolean canEvolve;

    public Monster(String name, int level, int exp) {
        this.name = name;
        this.hp = LVManager.getMaksHpByLv(level);
        setByLV(level);
        this.level = level;
        // this.element = element;
        this.exp = exp;
    }

    private void setByLV(int level) {
        this.setMaksHp(LVManager.getMaksHpByLv(level));
        this.setBaseDamage(LVManager.getBaseDamageByLv(level));
        this.level = level;
    }

    // ==============================================================================================================================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getHp() {//aodujfnaposdunfpoausdnfpdoun
        return (int)hp;
    }

    public void setHp(double hp) {//
        this.hp = hp;
        if (this.hp > maksHp) {
            this.hp = maksHp;
        }
    }

    public double getMaksHp() {
        return this.maksHp;
    }

    public void setMaksHp(double maksHp) {
        this.maksHp = maksHp;
    }
    // ==============================================================================================================================

    public int upLevel() {
        if (this.exp >= LVManager.expToLvUp) {
            this.exp -= LVManager.expToLvUp;
        }else{
            System.out.println("exp not enough");
            return 0;
        }
        this.level++;
        setByLV(level);
        canEvolve = true;
        return this.level;
    }

    public void heal(double hp) {
        setHp(this.getHp() + hp);
    }

    public double basicAttack() {
        return baseDamage;
    }

    public double specialAttack() {
        return baseDamage * 2;
    }

    public Element getElement() {
        return this.element;
    }
    // ==============================================================================================================================
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", maksHp=" + maksHp +
                ", level=" + level +
                ", element=" + element.toString().toLowerCase() +
                ", exp=" + exp +
                ", baseDamage=" + baseDamage +
                ", canEvolve=" + canEvolve +
                '}';
    }
}





