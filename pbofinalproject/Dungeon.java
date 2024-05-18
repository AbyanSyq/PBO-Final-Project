package pbofinalproject;
//test
import java.util.*;

public class Dungeon {
    private Player player;
    private Monster[] monsters;
    private boolean exitDungeon;

    // Constructor
    public Dungeon(Player player, Monster[] monsters) {
        this.player = player;
        this.monsters = monsters;
        this.exitDungeon = false;
    }

    // Method untuk mengatur exit dungeon
    public void setExitDungeon(boolean exitDungeon) {
        this.exitDungeon = exitDungeon;
    }

    public boolean isExitDungeon() {
        return exitDungeon;
    }

    // Method untuk player berjalan di dungeon
    public void walking() {
        System.out.println(player.getName() + " is walking in the dungeon.");
    }

    // Method untuk memilih monster untuk pertempuran
    public Monster pickMonster() {
        int index = (int) (Math.random() * monsters.length);
        return monsters[index];
    }

    // Method untuk pertempuran antara player dan monster
    public void battle(Monster enemyMonster) {
        System.out.println("A wild " + enemyMonster.getName() + " appears!");

        while (player.getHealth() > 0 && enemyMonster.getHealth() > 0) {
            // Player's turn
            playerAttack(enemyMonster);
            if (enemyMonster.getHealth() <= 0) break;

            // Monster's turn
            enemyMonster.attack(player);
        }

        if (player.getHealth() > 0) {
            System.out.println(player.getName() + " has defeated " + enemyMonster.getName() + "!");
        } else {
            System.out.println(player.getName() + " has been defeated by " + enemyMonster.getName() + "...");
        }
    }

    // Method untuk aksi serangan player
    private void playerAttack(Monster enemyMonster) {
        // Contoh sederhana interaksi pemain (bisa diperluas dengan input pemain)
        System.out.println("Choose an action: 1. Basic Attack 2. Special Attack 3. Element Attack 4. Use Item 5. Surrender");
        int choice = 1; // Di sini harus ada mekanisme input pemain

        switch (choice) {
            case 1:
                basicAttack(enemyMonster);
                break;
            case 2:
                specialAttack(enemyMonster);
                break;
            case 3:
                elementAttack(enemyMonster);
                break;
            case 4:
                useItem();
                break;
            case 5:
                surrender();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    // Method untuk serangan dasar
    private void basicAttack(Monster enemyMonster) {
        System.out.println(player.getName() + " uses Basic Attack on " + enemyMonster.getName());
        int damage = 10 + player.getLevel(); // damage dipengaruhi level player
        enemyMonster.setHealth(enemyMonster.getHealth() - damage);
    }

    // Method untuk serangan spesial
    private void specialAttack(Monster enemyMonster) {
        System.out.println(player.getName() + " uses Special Attack on " + enemyMonster.getName());
        Random rand = new Random();
        if (rand.nextInt(100) < 90) { // 90% chance to hit
            int damage = 20 + player.getLevel(); // damage dipengaruhi level player
            enemyMonster.setHealth(enemyMonster.getHealth() - damage);
            player.setHealth(player.getHealth() - 5); // mengorbankan HP sendiri
        } else {
            System.out.println("Special Attack missed!");
        }
    }

    // Method untuk serangan elemen
    private void elementAttack(Monster enemyMonster) {
        System.out.println(player.getName() + " uses Element Attack on " + enemyMonster.getName());
        int damage = 15 + player.getLevel(); // damage dipengaruhi level player
        enemyMonster.setHealth(enemyMonster.getHealth() - damage);
    }

    // Method untuk menggunakan item
    private void useItem() {
        System.out.println(player.getName() + " uses an item.");
        player.setHealth(player.getHealth() + 20); // Contoh efek item
    }

    // Method untuk menyerah
    private void surrender() {
        System.out.println(player.getName() + " surrenders.");
        player.setHealth(0);
    }

    // Getter dan Setter untuk player
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // Getter dan Setter untuk monsters
    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }

    public static void main(String[] args) {
        // Contoh penggunaan class Dungeon
        Player player = new Player("Hero", 100, 5); // player dengan level
        Monster[] monsters = {
            new Monster("Goblin", 30, 2),
            new Monster("Troll", 50, 4),
            new Monster("Dragon", 200, 10)
        };

        Dungeon dungeon = new Dungeon(player, monsters);
        dungeon.walking();

        Monster enemy = dungeon.pickMonster();
        dungeon.battle(enemy);

        dungeon.setExitDungeon(true);
        if (dungeon.isExitDungeon()) {
            System.out.println(player.getName() + " has exited the dungeon.");
        }
    }
}

class Player {
    private String name;
    private int health;
    private int level;

    public Player(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void attack(Monster monster) {
        // Logic untuk menyerang monster
        System.out.println(name + " attacks " + monster.getName());
        monster.setHealth(monster.getHealth() - (10 + level));
    }
}

class Monster {
    private String name;
    private int health;
    private int level;

    public Monster(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void attack(Player player) {
        // Logic untuk menyerang player
        System.out.println(name + " attacks " + player.getName());
        player.setHealth(player.getHealth() - (10 + level));
    }

    // Implementasi aksi monster bisa ditambah sesuai kebutuhan
}
