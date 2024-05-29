package com.abyan;

import javax.swing.SwingUtilities;
import com.abyan.Frame.LoginFrame;
import com.abyan.Manager.*;
import com.abyan.Object.*;

public class Main {
    public static void main(String[] args) {
        GameManager.loadData();
        
        Player.items.add(new healPotion());
        Player.items.add(new increaseDamagePotion());
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            } 
        });
    }  
}