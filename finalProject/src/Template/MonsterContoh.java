// package Template;
// import java.util.*;

// public class MonsterContoh {
//     private String name;
//     private String element;
//     private int level;
//     private int exp;

//     public MonsterContoh(String name, String element, int level, int exp) {
//         this.name = name;
//         this.element = element;
//         this.level = level;
//         this.exp = exp;
//     }

//     // Metode getter dan setter sesuai kebutuhan

//     public static void main(String[] args) {
//         // Misalkan Anda memiliki HashMap yang berisi data monster
//         HashMap<String, String[]> data = new HashMap<>();
//         data.put("Pikachu", new String[]{"Electric", "10", "100"});
//         data.put("Bulbasaur", new String[]{"Grass", "45", "80"});

//         GameManager.saveData("abyanMonster.csv", data);

//         // Membuat objek monster dari data HashMap
//         ArrayList<MonsterContoh> monster = new ArrayList();
//         for (String key : data.keySet()) {
//             String[] monsterData = data.get(key);
//             String element = monsterData[0];
//             int level = Integer.parseInt(monsterData[1]);
//             int exp = Integer.parseInt(monsterData[2]);

//             MonsterContoh mons = new MonsterContoh(key, element, level, exp);
//             monster.add(mons);
//         }

//         // Sekarang Anda memiliki HashMap monsters yang berisi objek Monster
//         // Anda dapat mengakses objek-objek Monster tersebut sesuai kebutuhan
//         for (MonsterContoh mons : monster) {
//             System.out.println("Monster Name: " + mons.getName());
//             System.out.println("Element: " + mons.getElement());
//             System.out.println("Level: " + mons.getLevel());
//             System.out.println("Exp: " + mons.getExp());
//             System.out.println();
//         }
//     }

//     // Metode getter dan setter sesuai kebutuhan
//     public String getName() {
//         return name;
//     }

//     public String getElement() {
//         return element;
//     }

//     public int getLevel() {
//         return level;
//     }

//     public int getExp() {
//         return exp;
//     }
// }
