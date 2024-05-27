package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;
import com.abyan.Scene.*;
import java.util.*;

public class Monster  implements Pertarungan{
    private String name;
    protected Element element;
    private int level;
    private double hp;
    protected double mp;
    protected double ep;
    private boolean canEvolve;

    protected double maksHp;
    protected double baseDamage;
    protected double maksMp;

    protected String imagePath;

    protected double defaultmaksHp;
    protected double defaultbaseDamage;
    protected double defaultmaksMp;

    
    public String getImagePath() {
        return imagePath;
    }

    public Monster(String name, int level,double maksHp, double baseDamage,double maksMp,double ep) {
        this.name = name;
        this.level = level;
        this.hp = maksHp;
        this.mp = maksMp;
        this.maksHp = maksHp;
        this.maksMp = maksMp;
        this.baseDamage = baseDamage;
        this.ep = ep;
    }
    
    public void setByLV(int n) {//increase base artibut if level up
        this.maksHp = maksHp + (defaultmaksHp * n);
        this.baseDamage = baseDamage + (defaultbaseDamage/2 * n);
        this.maksMp = maksMp +  (defaultmaksMp/2 * n);
        this.level += n;
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
    public boolean getCanEvolve(){
        return canEvolve;
    }
    public double getMaksMp() {
        return maksMp;
    }

    public void setMaksMp(double maksMp) {
        this.maksMp = maksMp;
    }
    public double getEp() {
        return ep;
    }

    public void setEp(double ep) {
        this.ep = ep;
    }
    // ==============================================================================================================================
    
    public void takeDamage(double damage){
        setHp(this.hp - damage);
    }
    public int upLevel(int ep) {
        int n = 0;//menghitung jumlah kenaikan level
        this.ep += ep;
        while (this.ep >= 100) {
            this.ep -= 100;
            n++;
            canEvolve = true;
        }
        setByLV(n);
        return n;
    }
    
    public void heal(double hp) {
        setHp(this.hp + hp);
    }

    public double basicAttack(Monster monster) {
        monster.takeDamage(baseDamage);;
        return baseDamage;
    }

    public double specialAttack(Monster monster) {
        monster.takeDamage(baseDamage * 1.5);
        takeDamage(baseDamage * 0.5);
        return baseDamage * 2;
    }
    public double elementAttack(Monster monster){
        return 0;
    }
    public double useItem(Item item){
        heal(item.healAmount);
        this.baseDamage = baseDamage + item.damageAmount;

        return item.healAmount + item.damageAmount;
    }

    public Element getElement() {
        return this.element;
    }
    // ==============================================================================================================================
    public String toString() {
        return 
                "name       ='" + name + '\n' +
                //"hp         =" + hp +'\n' +
                //"maksHp     =" + maksHp +'\n' +
                "level      =" + level +'\n' +
                "element    =" + element.toString().toLowerCase() +'\n' +
                //"baseDamage =" + baseDamage +'\n' +
                "canEvolve  =" + canEvolve +'\n' +
                '}';
    }
    public String saveData(){
        return name+","+element.getValue()+","+level+","+maksHp  +","+baseDamage +","+maksMp+","+ep;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getMp() {
        return mp;
    }

    public double setMp(double mp) {
        this.mp = this.mp + mp;
        if (this.mp > this.maksMp) {
            this.mp = this.maksMp;
        }
        return this.mp;
    }

    public void setCanEvolve(boolean canEvolve) {
        this.canEvolve = canEvolve;
    }
    public void surrender(){

    }
}





