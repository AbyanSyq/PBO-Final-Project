package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class Air extends Monster {
    public Air(String name, int level, int exp) {
        super(name, level, exp);
        this.element = Element.AIR;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        if (Math.abs(this.element.getValue() - element.getValue()) == 1) {
            this.element = element;
            return;
        } else {
            System.out.println("Invalid evolution. Element change must be adjacent.");
            return;
        }
    }

    public double elementAttack() {
        // if else buat cek
        return super.getBaseDamage() * 1.5;
    }
}
