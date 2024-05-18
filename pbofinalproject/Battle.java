package pbofinalproject;
//test
import java.util.Scanner;
import java.util.*;
class Monster {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private String element;

    public Monster(String name, int hp, int attack, int defense, String element) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.element = element;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getElement() {
        return element;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setElement(String element) {
        this.element = element;
    }
}

class Item {
    private String name;
    private String effect;

    public Item(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public void use(Monster monster) {
        // Implement effect logic, e.g., healing
        System.out.println(monster.getName() + " uses " + name + " and " + effect);
    }
}

class Battle {
    private Monster playerMonster;
    private Monster enemyMonster;

    public Battle(Monster playerMonster, Monster enemyMonster) {
        this.playerMonster = playerMonster;
        this.enemyMonster = enemyMonster;
    }

    public void basicAttack(Monster attacker, Monster defender) {
        int damage = Math.max(0, attacker.getAttack() - defender.getDefense());
        defender.takeDamage(damage);
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + damage + " damage!");
    }

    public void elementAttack(Monster attacker, Monster defender, double elementMultiplier) {
        int damage = Math.max(0, (int) (attacker.getAttack() * elementMultiplier) - defender.getDefense());
        defender.takeDamage(damage);
        System.out.println(attacker.getName() + " uses elemental attack on " + defender.getName() + " for " + damage + " damage!");
    }

    public void specialAttack(Monster attacker, Monster defender, int specialDamage) {
        defender.takeDamage(specialDamage);
        System.out.println(attacker.getName() + " uses special attack on " + defender.getName() + " for " + specialDamage + " damage!");
    }

    public void surrender(Monster player) {
        System.out.println(player.getName() + " surrenders!");
        // Implement logic for surrendering, like ending the game or giving the victory to the enemy
    }

    public void useItem(Monster player, Item item) {
        item.use(player);
        System.out.println(player.getName() + " uses " + item.getName() + "!");
    }

    // Setter methods
    public void setPlayerMonster(Monster playerMonster) {
        this.playerMonster = playerMonster;
    }

    public void setEnemyMonster(Monster enemyMonster) {
        this.enemyMonster = enemyMonster;
    }

    // Getter methods
    public Monster getPlayerMonster() {
        return playerMonster;
    }

    public Monster getEnemyMonster() {
        return enemyMonster;
    }

    public void battle() {
        Scanner scanner = new Scanner(System.in);
        while (playerMonster.isAlive() && enemyMonster.isAlive()) {
            System.out.println("Choose an action:");
            System.out.println("1. Basic Attack");
            System.out.println("2. Elemental Attack");
            System.out.println("3. Special Attack");
            System.out.println("4. Use Item");
            System.out.println("5. Surrender");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    basicAttack(playerMonster, enemyMonster);
                    break;
                case 2:
                    System.out.println("Enter elemental multiplier:");
                    double multiplier = scanner.nextDouble();
                    elementAttack(playerMonster, enemyMonster, multiplier);
                    break;
                case 3:
                    System.out.println("Enter special attack damage:");
                    int specialDamage = scanner.nextInt();
                    specialAttack(playerMonster, enemyMonster, specialDamage);
                    break;
                case 4:
                    // Assume we have some items to use
                    Item potion = new Item("Potion", "heals 20 HP");
                    useItem(playerMonster, potion);
                    break;
                case 5:
                    surrender(playerMonster);
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

            // Enemy's turn (for simplicity, enemy always does basic attack)
            if (enemyMonster.isAlive()) {
                basicAttack(enemyMonster, playerMonster);
            }

            // Display health status
            System.out.println(playerMonster.getName() + " HP: " + playerMonster.getHp());
            System.out.println(enemyMonster.getName() + " HP: " + enemyMonster.getHp());
        }

        if (playerMonster.isAlive()) {
            System.out.println(playerMonster.getName() + " wins!");
        } else {
            System.out.println(enemyMonster.getName() + " wins!");
        }
    }

    public static void main(String[] args) {
        Monster playerMonster = new Monster("PlayerMon", 100, 20, 10, "Fire");
        Monster enemyMonster = new Monster("EnemyMon", 80, 15, 5, "Water");
        Battle battle = new Battle(playerMonster, enemyMonster);

        battle.battle();
    }
}
