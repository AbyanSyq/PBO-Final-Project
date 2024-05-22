package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class Air extends Monster {
    public Air(String name, int level,double maksHp, double baseDamage,double maksMp, double ep) {
        super(name, level, maksHp, baseDamage, maksMp,ep);
        this.element = Element.AIR;
        super.imagePath = "MonsterImage/MonsterAir.png";

        super.defaultmaksHp = 100;
        super.defaultbaseDamage = 20;
        super.defaultmaksMp = 40;
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
