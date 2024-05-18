package com.abyan.Manager;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameManager {
    public static String fileMonster = null;
    public static HashMap<String, String[]> dataMonster = new HashMap<>();
    public static HashMap<String, String[]> dataAkun = new HashMap<>();
    private static String loggedInUser = null;

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

        dataAkun.put(username, new String[]{password, profilePhotoPath});
        System.out.println("Sign up successful. You can now log in with your new account.");
        return username;
    }


    public static void saveData(String fileName,ArrayList<Monster> monsterList){
        for (Monster monster : monsterList) {
            String key = monster.getName();
            String[] value = new String[]{
                Integer.toString(monster.getElement().getValue()),
                Integer.toString(monster.getLevel()),
                Integer.toString(monster.getExp())
            };
            dataMonster.put(key, value);
        }
        saveData(fileName, dataMonster);
    }

    public static void saveData(String fileName, HashMap<String, String[]> data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String key : data.keySet()) {
                String[] value = data.get(key);
                String line = key + "," + String.join(",", value);
                writer.write(line);
                writer.newLine();
            }
            writer.flush(); 
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String[]> loadData(String fileName) {
        HashMap<String, String[]> data = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String key = parts[0];
                String[] value = new String[parts.length - 1];
                System.arraycopy(parts, 1, value, 0, parts.length - 1);
                data.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void setFile(String name){
        fileMonster = name+"Monster.csv";
    }
}
