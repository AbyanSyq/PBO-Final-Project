package com.abyan.Object;
import com.abyan.Manager.*;

public class Air extends Monster {
    public Air(String name, int level,double maksHp, double baseDamage,double maksMp, double ep) {
        super(name, level, maksHp, baseDamage, maksMp,ep);
        this.element = Element.AIR;
        super.imagePath = "MonsterImage/MonsterAir.png";

        super.defaultmaksHp = 100;
        super.defaultbaseDamage = 20;
        super.defaultmaksMp = 40;
    }
    @Override
    public Element getElement() {
        return element;
    }
    @Override
    public double elementAttack(Monster monster) {
        if (mp <= 10) {
            return 0;
        }
        mp -=10;
        if (monster.getElement().getValue() == 0) {
            monster.takeDamage(super.getBaseDamage()*2);
            return super.getBaseDamage()*2;
        }
        monster.takeDamage(super.getBaseDamage()/2);


        return super.getBaseDamage() / 2;
    }
}
