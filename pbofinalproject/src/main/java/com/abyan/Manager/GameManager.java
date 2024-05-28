package com.abyan.Manager;


import com.abyan.Frame.LoginFrame;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.net.ssl.SNIHostName;

public class GameManager {
    public static String fileMonster = null;
    public static String fileAkun = "Data_akun/dataAkun.csv";
    public static HashMap<String, String[]> dataAkun = new HashMap<>();

    public static String login(String username, String password) {

        if (dataAkun.containsKey(username) && dataAkun.get(username)[0].equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            setFile(username);
            loadData();
            saveData();
            loadPlayerData(username);
            return username;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
    public static String signUp(String username, String password) {
        System.out.print("Enter new username: ");

        if (dataAkun.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return null;
        }
        String profilePhotoPath = "Data_akun/defaultProfile.jpg";

        dataAkun.put(username, new String[] {password, profilePhotoPath,"0","0","0"});
        System.out.println("Sign up successful. You can now log in with your new account.");
        Monster newMonster = new Api(username,1, 80.0, 15.0, 20.0,0);
        Player.monsters.add(newMonster);
        setFile(username);
        saveAkun(dataAkun);
        loadData();
        saveData();
        loadPlayerData(username);
        return username;
    }
    public static void saveAkun(HashMap<String, String[]> userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAkun))) {
            writer.write("username,password,photoProfilePath,exp,heal Potion,damage potion\n"); // Menulis header
            for (String username : userData.keySet()) {
                String[] values = userData.get(username);
                String line = username + "," + String.join(",", values);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Data berhasil disimpan.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static HashMap<String, String[]> loadAkun() {
        HashMap<String, String[]> userData = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileAkun))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String username = parts[0];
                    String[] values = new String[]{parts[1], parts[2], parts[3], parts[4], parts[5]};
                    userData.put(username, values);
                }
            }
            System.out.println("Data berhasil dimuat.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData;
    }
    public static void saveMonsters(ArrayList<Monster> monsters) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileMonster))) {
            writer.write("name,element,level,maksHp,baseDamage,maksMp,ep\n");
            for (Monster monster : monsters) {
                writer.write(monster.saveData());
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Monster> loadMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileMonster))) {
            String line;
            reader.readLine(); // skip header line
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 7) {
                    String name = attributes[0];
                    Element element = Element.getElementByValue(Integer.parseInt(attributes[1]));
                    int level = Integer.parseInt(attributes[2]);
                    double maksHp = Double.parseDouble(attributes[3]);
                    double baseDamage = Double.parseDouble(attributes[4]);
                    double maksMp = Double.parseDouble(attributes[5]);
                    double ep = Double.parseDouble(attributes[6]);
                    monsters.add(monsterGen(name,element, level, maksHp, baseDamage, maksMp,ep));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return monsters;
    }
    public static void loadPlayerData(String username){
        String[] playerData = dataAkun.get(username);

        Player.name = username;
        Player.password = playerData[0];
        Player.profilPath = playerData[1];
        Player.ep = Integer.parseInt(playerData[2]);
        Player.healPotion = Integer.parseInt(playerData[3]);
        Player.damagePotion = Integer.parseInt(playerData[4]);
    }

    public static void saveData(){
        dataAkun.put(Player.name, Player.data());
        saveAkun(dataAkun);
        saveMonsters(Player.monsters);
        //saveItem(Player.items);
    }
    public static void loadData() {
        if ((checkFileExists(fileAkun))) { 
            dataAkun = loadAkun();
        }
        if (fileMonster !=null) {
            if ((checkFileExists(fileMonster))) {
            Player.monsters = loadMonsters();
            }
        }
    }

    public static void setFile(String name) {
        fileMonster = "Data_Monster/" + name + "Monster.csv";
    }

    public static boolean checkFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static Monster monsterGen(String name, Element element, int level, double maksHp, double baseDamage,double maksMp,double ep) {
        Monster newMonster = null;
        switch (element.getValue()) {
            case 0:
                newMonster = new Api(name, level, maksHp, baseDamage, maksMp,ep);
                break;
            case 1:
                newMonster = new Tanah(name, level, maksHp, baseDamage, maksMp,ep);
                break;
            case 2:
                newMonster = new Angin(name, level, maksHp, baseDamage, maksMp,ep);
                break;
            case 3:
                newMonster = new Air(name, level, maksHp, baseDamage, maksMp,ep);
                break;
            case 4:
                newMonster = new Es(name, level, maksHp, baseDamage, maksMp,ep);
                break;
            default:
                break;
        }
        return newMonster;
    }


    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        //saveData();
        setFile("abyan");
        loadData();
        System.out.println(dataAkun.size());
        //signUp(scan);
        login("abyan","123");

        // Monster monster1 = monsterGen("Dragon", Element.AIR, 1, 150.5, 35.0, 50.0,0);
        // Monster monster2 = monsterGen("yoga", Element.API, 1, 80.0, 15.0, 20.0,0);
        // Player.monsters.add(monster1);
        // Player.monsters.add(monster2);
 
        // Player.monsters.add(monsterGen("Dragon", Element.AIR, 1, 150.5, 35.0, 50.0));
        // Player.monsters.add(monsterGen("Goblin", Element.API, 1, 80.0, 15.0, 20.0));

        saveData();

        //Player.monsters = loadMonsters();
        for (Monster n : Player.monsters) {
            System.out.println(n.toString());
        }
    }
}
