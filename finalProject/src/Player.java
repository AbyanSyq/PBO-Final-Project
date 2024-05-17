import java.util.Scanner;

public class Player {
    public static String name;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
        GameManager.setFile(name);
        GameManager.dataMonster = GameManager.loadData(GameManager.fileMonster);

        GameHomebase gameHomebase = new GameHomebase();
        gameHomebase.storeAllMonster();
        for (Monster mons : gameHomebase.monster.values()) {
            System.out.println("Monster Name: " + mons.getName());
            System.out.println("Element: " + mons.getElement());
            System.out.println("Level: " + mons.getLevel());
            System.out.println("Exp: " + mons.getExp());
            System.out.println("Base damage: " + mons.getBaseDamage());
            System.out.println("hp: " + mons.getHp());
            System.out.println("makshp: " + mons.getMaksHp());
            System.out.println();
        }

    }
}
