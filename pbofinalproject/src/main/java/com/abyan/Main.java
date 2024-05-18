package com.abyan;
import java.util.Scanner;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;
import com.abyan.Manager.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Player.name = scan.nextLine();
        GameManager.setFile(Player.name);
        
    }
}
