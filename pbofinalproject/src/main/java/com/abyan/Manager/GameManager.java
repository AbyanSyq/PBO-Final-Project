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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameManager {
    public static String fileMonster = null;
    public static String fileItem = null;
    public static HashMap<String, String[]> dataAkun = new HashMap<>();
    private static String loggedInUser = null;

    private static final String DATA_PATH = "D:\\S2\\PBO\\PBO-Final-Project\\Data\\";

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

    public static void saveDataMonster(String fileName, ArrayList<Monster> monsterList) {
        saveObjectData(DATA_PATH + fileName, monsterList);
    }

    public static void saveDataItem(String fileName, ArrayList<Item> itemList) {
        saveObjectData(DATA_PATH + fileName, itemList);
    }

    private static <T> void saveObjectData(String fileName, ArrayList<T> dataList) {
        File file = new File(fileName);
        // Ensure the directory exists
        file.getParentFile().mkdirs();
        // If the file does not exist, create a new file
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(dataList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> ArrayList<T> loadObjectData(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            // Return an empty ArrayList if the file does not exist
            return new ArrayList<>();
        }
        System.out.println("exist");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void loadAllData() {
        Player.monsters = loadObjectData(DATA_PATH + fileMonster);
        Player.items = loadObjectData(DATA_PATH + fileItem);
    }

    public static void setFile(String name) {
        fileMonster = name + "Monster.dat";
        fileItem = name + "Item.dat";
    }
}
