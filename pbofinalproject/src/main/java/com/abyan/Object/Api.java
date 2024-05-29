package com.abyan.Object;
import com.abyan.Manager.*;

public class Api extends Monster {
    public Api(String name, int level,double maksHp, double baseDamage,double maksMp,double ep) {
        super(name, level, maksHp, baseDamage, maksMp,ep);
        this.element = Element.API;
        super.imagePath = "MonsterImage/MonsterApi.png";

        super.defaultmaksHp = 80;
        super.defaultbaseDamage = 30;
        super.defaultmaksMp = 45;
    }

    public Element getElement() {
        return element;
    }

    public double elementAttack(Monster monster) {
        if (mp <= 10) {
            return 0;
        }
        mp -=10;
        if (monster.getElement().getValue() == 4) {
            monster.takeDamage(super.getBaseDamage()*2);
            return super.getBaseDamage()*2;
        }
        monster.takeDamage(super.getBaseDamage()/2);
        return super.getBaseDamage() / 2;
    }
}
