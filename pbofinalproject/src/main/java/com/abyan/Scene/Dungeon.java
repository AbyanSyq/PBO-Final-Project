package com.abyan.Scene;
import java.util.Random;

import com.abyan.Manager.GameManager;
import com.abyan.Object.Monster;

public class Dungeon {
    public Monster[] monsters = new Monster[3];


    public void walking(){
        if (GameManager.randomNum(0,10) == 0) {
            battle();
        }
    }
    public void battle(){
        //Menampilkan musuh
        //Menampilkan tombol enter atau kabur
        //masuk battle arne 
    }
    public void enterBattle(Monster monster){
        
    }
}
