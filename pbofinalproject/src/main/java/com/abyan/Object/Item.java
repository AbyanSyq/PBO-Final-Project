package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

abstract class ItemBluePrint {
    private String name;
    protected double healAmount;
    protected double damageAmount;
    protected double harga;
    public abstract String getName();
    public abstract void setName(String name);
}

public class Item extends ItemBluePrint {
    private String name;
    protected double healAmount;
    protected double damageAmount;
    protected double harga = 50;
    private String imagePath; // Path to the item's image

    public Item(String name, double healAmount, double damageAmount, String imagePath) {
        this.name = name;
        this.healAmount = healAmount;
        this.damageAmount = damageAmount;
        this.imagePath = imagePath;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {      
        this.name = name;
    }
    
    public double getHarga() {
        return this.harga;
    }
    
    public String getImagePath() {
        return this.imagePath;
    }
    
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", healAmount=" + healAmount +
                ", damageAmount=" + damageAmount +
                ", harga=" + harga +
                '}';
    }
}
