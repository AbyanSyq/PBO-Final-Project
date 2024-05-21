package com.abyan.Manager;

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
    public static String fileItem = null;
    public static String fileAkun = "Data_akun/dataAkun.csv";
    public static HashMap<String, String[]> dataAkun = new HashMap<>();
    private static String loggedInUser = null;

    //private static final String DATA_PATH = "D:\\S2\\PBO\\PBO-Final-Project\\Data\\";

    public static String login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (dataAkun.containsKey(username) && dataAkun.get(username)[0].equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return username;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    public static void logout() {
        if (loggedInUser != null) {
            System.out.println("Logged out successfully.");
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public static String signUp(Scanner scanner) {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        if (dataAkun.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return null;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter profile photo path: ");
        String profilePhotoPath = scanner.nextLine();

        dataAkun.put(username, new String[] {password, profilePhotoPath});
        System.out.println("Sign up successful. You can now log in with your new account.");
        return username;
    }
    public static void saveAkun(HashMap<String, String[]> userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAkun))) {
            writer.write("username,password,photoProfilePath,exp\n"); // Menulis header
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
                if (parts.length == 4) {
                    String username = parts[0];
                    String[] values = new String[]{parts[1], parts[2], parts[3]};
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
            writer.write("name,element,level,maksHp,baseDamage,maksMp\n");
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
                String name = attributes[0];
                Element element = Element.getElementByValue(Integer.parseInt(attributes[1]));
                int level = Integer.parseInt(attributes[2]);
                double maksHp = Double.parseDouble(attributes[3]);
                double baseDamage = Double.parseDouble(attributes[4]);
                double maksMp = Double.parseDouble(attributes[5]);
                monsters.add(monsterGen(name,element, level, maksHp, baseDamage, maksMp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return monsters;
    }

    public static void saveData(){
        saveAkun(dataAkun);
        saveMonsters(Player.monsters);
        //saveItem(Player.items);
    }
    public static void loadData() {
        if ((checkFileExists(fileAkun))) { 
            dataAkun = loadAkun();
        }
        if ((checkFileExists(fileMonster))) {
            Player.monsters = loadMonsters();
        }
    }

    public static void setFile(String name) {
        fileMonster = "Data_Monster/" + name + "Monster.csv";
        fileItem = "Data_Monster/" +name + "Item.csv";
    }

    public static boolean checkFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static Monster monsterGen(String name, Element element, int level, double maksHp, double baseDamage,double maksMp) {
        Monster newMonster = null;
        switch (element.getValue()) {
            case 0:
                newMonster = new Api(name, level, maksHp, baseDamage, maksMp);
                break;
            case 1:
                newMonster = new Tanah(name, level, maksHp, baseDamage, maksMp);
                break;
            case 2:
                newMonster = new Angin(name, level, maksHp, baseDamage, maksMp);
                break;
            case 3:
                newMonster = new Air(name, level, maksHp, baseDamage, maksMp);
                break;
            case 4:
                newMonster = new Es(name, level, maksHp, baseDamage, maksMp);
                break;
            default:
                break;
        }
        return newMonster;
    }

    public static Monster randomMonsterGen(String nama, Element element) {
        return monsterGen(nama,element,1, randomNum(90, 110), randomNum(15, 25), randomNum(80, 100));
    }

    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        setFile("abyan");
        loadData();
        signUp(scan);
        login(scan);

        Monster monster1 = monsterGen("Dragon", Element.AIR, 1, 150.5, 35.0, 50.0);
        Monster monster2 = monsterGen("Goblin", Element.API, 1, 80.0, 15.0, 20.0);
        Player.monsters.add(monster1);
        Player.monsters.add(monster2);

        // Player.monsters.add(monsterGen("Dragon", Element.AIR, 1, 150.5, 35.0, 50.0));
        // Player.monsters.add(monsterGen("Goblin", Element.API, 1, 80.0, 15.0, 20.0));

        saveData();

        //Player.monsters = loadMonsters();
        for (Monster n : Player.monsters) {
            System.out.println(n.toString());
        }
    }
}
