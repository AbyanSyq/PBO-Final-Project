import org.junit.Test;

import com.abyan.Manager.Element;
import com.abyan.Manager.GameManager;
import com.abyan.Manager.Player;
import com.abyan.Object.*;
import com.abyan.Scene.GameHomebase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class GameManagerTest {
    @Test
    public void saveTest() {
        
    }
    public static void main(String[] args) {
        GameHomebase gameHomebase = new GameHomebase();
        Player player = new Player();
        //Player.monsters.add(new Monster("halo", 0, 0));

        GameManager.setFile("abyan");
        //GameManager.saveDataMonster(GameManager.fileMonster, Player.monsters);
        GameManager.loadData();
    }
}