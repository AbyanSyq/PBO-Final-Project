import java.util.*;

class Monster {
    private String name;
    private double hp;
    private double maksHp;
    private int level;
    // private Element element;
    private int exp;
    private double baseDamage;

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
        // if else naik level
        setByLV(this.level + 1);
        ;
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

    public double getHp() {
        return hp;
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
        this.level++;
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

    public double useItem(Item item) {
        // super.setHp();
        // super.getHp() += item.getEffect();
        return item.getEffect();
    }

    public Element getElement() {
        return null;
    }

    public void setElement(Element element) {
    }
}

class Api extends Monster {
    private Element element;

    Api(String name, int level, int exp, Element element) {
        super(name, level, exp);
        this.element = element;
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

class Tanah extends Monster {
    private Element element;

    Tanah(String name, int level, int exp, Element element) {
        super(name, level, exp);
        this.element = element;
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

class Angin extends Monster {
    private Element element;

    Angin(String name, int level, int exp, Element element) {
        super(name, level, exp);
        this.element = element;
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

class Air extends Monster {
    private Element element;

    Air(String name, int level, int exp, Element element) {
        super(name, level, exp);
        this.element = element;
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

class Es extends Monster {
    private Element element;

    Es(String name, int level, int exp, Element element) {
        super(name, level, exp);
        this.element = element;
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