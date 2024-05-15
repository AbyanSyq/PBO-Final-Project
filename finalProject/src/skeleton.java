import java.util.ArrayList;
import java.util.List;

enum Element {
    Api, Tanah, Angin, Air, Es
}

interface Combat {
    double basicAttack();
    double specialAttack();
    double elementAttack();
    double useItem(Item item);
    void surrender();
}

class Item {
    private String name;
    private int effect;

    public Item(String name, int effect) {
        this.name = name;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }
}

class Monster implements Combat {
    private String name;
    private int level;
    private String element;
    private int exp;
    private double hp;
    private double baseDamage;

    public Monster(String name, int level, String element, int exp, double hp, double baseDamage) {
        this.name = name;
        this.level = level;
        this.element = element;
        this.exp = exp;
        this.hp = hp;
        this.baseDamage = baseDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int upLevel() {
        this.level++;
        return this.level;
    }

    public void heal() {
        this.hp = 100;
    }

    public double basicAttack() {
        return baseDamage;
    }

    public double specialAttack() {
        return baseDamage * 2;
    }

    public double elementAttack() {
        return baseDamage * 1.5;
    }

    public double useItem(Item item) {
        this.hp += item.getEffect();
        return item.getEffect();
    }

    public void surrender() {
        System.out.println(name + " has surrendered!");
    }
}

class Player {
    private String name;
    private List<Item> items = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void deleteMonster(Monster monster) {
        // Implement delete monster logic
    }

    public void addMonster(Monster monster) {
        // Implement add monster logic
    }
}

abstract class Homebase {
    public abstract void storeMonster(Monster monster);
    public abstract void levelMonster(Monster monster);
    public abstract void levelUpMonster(Monster monster);
    public abstract void evolveMonster(Monster monster);
    public abstract void enterDungeon(Monster monster);
}

class GameHomebase extends Homebase {
    private List<Monster> petMonsters = new ArrayList<>();

    public GameHomebase(List<Monster> petMonsters) {
        this.petMonsters = petMonsters;
    }

    public void storeMonster(Monster monster) {
        // Implement store monster logic
    }

    public void levelMonster(Monster monster) {
        // Implement level monster logic
    }

    public void levelUpMonster(Monster monster) {
        // Implement level up monster logic
    }

    public void evolveMonster(Monster monster) {
        // Implement evolve monster logic
    }

    public void enterDungeon(Monster monster) {
        // Implement enter dungeon logic
    }

    public Monster pickMonster() {
        // Implement pick monster logic
        return null;
    }
}

class Dungeon {
    private List<Monster> playerMonsters = new ArrayList<>();
    private List<Monster> enemyMonsters = new ArrayList<>();

    public Dungeon(List<Monster> playerMonsters, List<Monster> enemyMonsters) {
        this.playerMonsters = playerMonsters;
        this.enemyMonsters = enemyMonsters;
    }

    public List<Monster> getPlayerMonsters() {
        return playerMonsters;
    }

    public void setPlayerMonsters(List<Monster> playerMonsters) {
        this.playerMonsters = playerMonsters;
    }

    public List<Monster> getEnemyMonsters() {
        return enemyMonsters;
    }

    public void setEnemyMonsters(List<Monster> enemyMonsters) {
        this.enemyMonsters = enemyMonsters;
    }

    public void walking() {
        // Implement walking logic
    }

    public void exitDungeon() {
        // Implement exit dungeon logic
    }

    public Monster pickMonster() {
        // Implement pick monster logic
        return null;
    }

    public void generateMonster() {
        // Implement generate monster logic
    }

    public void battle() {
        // Implement battle logic
    }
}

class Battle {
    private Monster enemyMonster;
    private Monster playerMonster;

    public Battle(Monster enemyMonster, Monster playerMonster) {
        this.enemyMonster = enemyMonster;
        this.playerMonster = playerMonster;
    }

    public Monster getEnemyMonster() {
        return enemyMonster;
    }

    public void setEnemyMonster(Monster enemyMonster) {
        this.enemyMonster = enemyMonster;
    }

    public Monster getPlayerMonster() {
        return playerMonster;
    }

    public void setPlayerMonster(Monster playerMonster) {
        this.playerMonster = playerMonster;
    }

    public double basicAttack() {
        return playerMonster.basicAttack();
    }

    public double specialAttack() {
        return playerMonster.specialAttack();
    }

    public double elementAttack() {
        return playerMonster.elementAttack();
    }

    public double useItem(Item item) {
        return playerMonster.useItem(item);
    }

    public void surrender() {
        playerMonster.surrender();
    }
}

class GameManager {
    public void saveGame() {
        // Implement save game logic
    }

    public void loadGame() {
        // Implement load game logic
    }
}
