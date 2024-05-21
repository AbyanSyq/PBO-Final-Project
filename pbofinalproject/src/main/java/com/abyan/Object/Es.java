package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class Es extends Monster {
    public Es(String name, int level,double maksHp, double baseDamage,double maksMp) {
        super(name, level, maksHp, baseDamage, maksMp);
        this.element = Element.ES;
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

