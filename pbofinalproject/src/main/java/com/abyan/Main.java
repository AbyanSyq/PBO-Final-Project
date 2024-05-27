package com.abyan;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import com.abyan.Frame.LoginFrame;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;
import com.abyan.Manager.*;

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
