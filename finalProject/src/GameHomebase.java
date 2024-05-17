import java.util.*;

abstract class HomeBase {
    public abstract void storeMonster(Monster monster);
    public abstract void levelUpMonster(Monster monster);
    public abstract void evolveMonster(Monster monster,Element element);
    public abstract void enterDungeon(Monster monster);
}

public class GameHomebase extends HomeBase {
    HashMap<String,Monster> monster = new HashMap();
    
    public Monster pickMonster(String name) {

        // Implement pick monster logic
        return null;
    }
    public void storeAllMonster() {
        for (String key : GameManager.dataMonster.keySet()) {
            String[] monsterData = GameManager.dataMonster.get(key);
            int level = Integer.parseInt(monsterData[0]);
            int exp = Integer.parseInt(monsterData[1]);
            Element element = Element.getElementByValue(Integer.parseInt(monsterData[2]));

            Monster mons = null;
            if (element == Element.API) {
                mons = new Api(key, level, exp, element);
            }else if (element == Element.TANAH){
                
            }else if (element == Element.ANGIN) {
                
            }else if (element == Element.AIR) {
                
            }else if (element == Element.ES) {
                
            }
            monster.put(key, mons);
        }
    }
    public void storeMonster(Monster monster){
        this.monster.put(monster.getName(),monster);
    }

    public void levelUpMonster(Monster monster) {
        monster.upLevel();
        // Memilih monster mana yang ingin di lv up
    }

    public void evolveMonster(Monster monster,Element element) {
        monster.setElement(element);
        // Memilih Monster yang dimaksud 
    }

    public void enterDungeon(Monster monster) {
        // Memasuki dungeon dengan sudah memilih monster
    }

}   