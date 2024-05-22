package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;


public class Tanah extends Monster {
    public Tanah(String name, int level,double maksHp, double baseDamage,double maksMp,double ep) {
        super(name, level, maksHp, baseDamage, maksMp,ep);
        this.element = Element.TANAH;
        super.imagePath = "MonsterImage/MonsterTanah.png";

        super.defaultmaksHp = 150;
        super.defaultbaseDamage = 10;
        super.defaultmaksMp = 60;
    }

    public Element getElement() {
        return element;
    }
    public double elementAttack(Monster monster) {
        if (!(super.element.getValue() + 1 == monster.getElement().getValue())) {
            return super.getBaseDamage()/2;
        } 
        return super.getBaseDamage() * 1.5;
    }
}
