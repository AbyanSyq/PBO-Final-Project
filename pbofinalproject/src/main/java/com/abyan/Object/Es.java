package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class Es extends Monster {
    public Es(String name, int level,double maksHp, double baseDamage,double maksMp,double ep) {
        super(name, level, maksHp, baseDamage, maksMp,ep);
        this.element = Element.ES;
        super.imagePath = "MonsterImage/MonsterEs.png";

        super.defaultmaksHp = 60;
        super.defaultbaseDamage = 40;
        super.defaultmaksMp = 50;
    }

    public Element getElement() {
        return element;
    }

    public double elementAttack(Monster monster) {
        if (mp <= 10) {
            return 0;
        }
        mp -=10;
        if (monster.getElement().getValue() == 2) {
            monster.takeDamage(super.getBaseDamage()*2);
            return super.getBaseDamage()*2;
        }
        monster.takeDamage(super.getBaseDamage()/2);
        return super.getBaseDamage() / 2;
    }
}

