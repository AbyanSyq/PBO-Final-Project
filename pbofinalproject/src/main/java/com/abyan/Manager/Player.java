package com.abyan.Manager;

import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player {
    public static String name;
    public static String password;
    public static String profilPath = "Data_Akun/defaultProfile.jpg";
    public static int ep;
    public static int healPotion;
    public static int damagePotion; 

    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Monster> monsters = new ArrayList<>();

    public static String[] data(){
        String[] s = {password,profilPath,Integer.toString(ep),Integer.toString(healPotion),Integer.toString(damagePotion)};
        return s;
    }
    

    public static String playerInfo(){
        return "name                                                =" + name + '\n' +
                "Ep                                                 =" + ep +'\n' +
                "Heal Potion                                        =" + ep +'\n' +
                "Damage Potion(increse your monster damage)         =" + ep +'\n'
                ;
    }
}
