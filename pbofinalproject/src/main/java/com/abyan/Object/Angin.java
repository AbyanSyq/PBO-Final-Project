package com.abyan.Object;
import com.abyan.Manager.*;

public class Angin extends Monster { 
    public double mpUsage = mp; 

    public Angin(String name, int level,double maksHp, double baseDamage,double maksMp,double ep) {
        super(name, level, maksHp, baseDamage, maksMp, ep);
        this.element = Element.ANGIN;
        super.imagePath = "MonsterImage/MonsterAngin.png";

        super.defaultmaksHp = 90;
        super.defaultbaseDamage = 25;
        super.defaultmaksMp = 40;
    }

    public Element getElement() {
        return element;
    }

    public double elementAttack(Monster monster) {
        if (mp <= 10) {
            return 0;
        }
        mp -=10;
        if (monster.getElement().getValue() == 1) {
            monster.takeDamage(super.getBaseDamage()*2);
            return super.getBaseDamage()*2;
        }
        monster.takeDamage(super.getBaseDamage()/2);
        return super.getBaseDamage() / 2;
    }
}
